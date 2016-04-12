// This file is licensed under the CC0.
package vaxrun;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Symbol {

    public final String name;
    public final int type, other, desc, value;
    public final char tchar;

    public Symbol(ByteBuffer buf, int p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            char ch = (char) buf.get(p + i);
            if (ch == 0) {
                break;
            }
            sb.append(ch);
        }
        name = sb.toString();
        type = buf.get(p + 8);
        other = buf.get(p + 9);
        desc = buf.getShort(p + 10);
        value = buf.getInt(p + 12);
        tchar = type < 10 ? "uUaAtTdDbB".charAt(type) : '?';
    }

    @Override
    public String toString() {
        return String.format("%08x %c %s", value, tchar, name);
    }
}

class AOut {

    public final ByteBuffer header;
    public final int a_magic, a_text, a_data, a_bss, a_syms, a_entry, a_trsize, a_drsize;
    public final byte[] text, data;
    public final Symbol[] syms;
    public final HashMap<Integer, String> symO = new HashMap<>();
    public final HashMap<Integer, String> symT = new HashMap<>();

    public AOut(String path) throws IOException {
        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] h = new byte[0x20];
            fis.read(h);
            ByteBuffer hdr = ByteBuffer.wrap(h).order(ByteOrder.LITTLE_ENDIAN);
            a_magic = hdr.getInt();
            if (a_magic == 0x108) {
                header = hdr;
                a_text = hdr.getInt(4);
                a_data = hdr.getInt(8);
                a_bss = hdr.getInt(12);
                a_syms = hdr.getInt(16);
                a_entry = hdr.getInt(20);
                a_trsize = hdr.getInt(24);
                a_drsize = hdr.getInt(28);
                text = new byte[a_text];
                data = new byte[a_data];
                fis.read(text);
                fis.read(data);
                if (a_syms > 0) {
                    byte[] sym = new byte[a_syms];
                    fis.read(sym);
                    ByteBuffer sbuf = ByteBuffer.wrap(sym).order(ByteOrder.LITTLE_ENDIAN);
                    ArrayList<Symbol> list = new ArrayList<>();
                    for (int p = 0; p <= a_syms - 16; p += 16) {
                        Symbol s = new Symbol(sbuf, p);
                        list.add(s);
                        if (4 <= s.type && s.type <= 9) {
                            if (s.name.endsWith(".o")) {
                                symO.put(s.value, s.name);
                            } else {
                                symT.put(s.value, s.name);
                            }
                        }
                    }
                    syms = new Symbol[list.size()];
                    list.toArray(syms);
                } else {
                    syms = null;
                }
                return;
            }
        }
        header = null;
        a_text = a_data = a_bss = a_syms = a_entry = a_trsize = a_drsize = 0;
        text = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path));
        data = null;
        syms = null;
    }

    @Override
    public String toString() {
        if (a_magic != 0x108) {
            return String.format("unknown format: %08x", a_magic);
        }
        return String.format(
                "magic = %08x, text  = %08x, data   = %08x, bss    = %08x\n"
                + "syms  = %08x, entry = %08x, trsize = %08x, drsize = %08x",
                a_magic, a_text, a_data, a_bss,
                a_syms, a_entry, a_trsize, a_drsize);
    }
}

class VAX {

    public static final int AP = 12, FP = 13, SP = 14, PC = 15;
    private final byte[] mem = new byte[0x40000];
    private final int[] r = new int[16];
    private final ByteBuffer buf;

    public VAX(AOut aout) {
        System.arraycopy(aout.text, 0, mem, 0, aout.a_text);
        int dstart = (aout.a_text + 0x1ff) & ~0x1ff;
        System.arraycopy(aout.data, 0, mem, dstart, aout.a_data);
        r[PC] = aout.a_entry + 2;
        buf = ByteBuffer.wrap(mem).order(ByteOrder.LITTLE_ENDIAN);
    }

    public int fetch() {
        return Byte.toUnsignedInt(mem[r[PC]++]);
    }

    public int fetchSigned(int size) throws Exception {
        int pc = r[PC];
        r[PC] += size;
        return getSigned(pc, size);
    }

    public int getSigned(int addr, int size) throws Exception {
        switch (size) {
            case 1:
                return mem[addr];
            case 2:
                return buf.getShort(addr);
            case 4:
                return buf.getInt(addr);
        }
        throw new Exception("invalid size " + size);
    }

    public void setSigned(int addr, int size, int value) throws Exception {
        switch (size) {
            case 1:
                mem[addr] = (byte) value;
                return;
            case 2:
                buf.putShort(addr, (short) value);
                return;
            case 4:
                buf.putInt(addr, value);
                return;
        }
        throw new Exception("invalid size " + size);
    }

    public String getString(int addr, int length) throws UnsupportedEncodingException {
        return new String(Arrays.copyOfRange(mem, addr, addr + length), "ASCII");
    }

    public Exception error(String format, Object... args) {
        return new Exception(String.format(format, args));
    }

    public int getOperand(int size) throws Exception {
        int b = fetch();
        switch (b) {
            case 0x8f:
                return fetchSigned(size);
        }
        int t = b >> 4, n = b & 15;
        switch (t) {
            case 5: // r
                return r[n];
        }
        throw error("%08x: unknown operand %02x", r[PC] - 1, b);
    }

    public void setOperand(int size, int value) throws Exception {
        int b = fetch();
        int t = b >> 4, n = b & 15, disp;
        switch (t) {
            case 5: // r
                r[n] = value;
                return;
            case 6: // (r)
                setSigned(r[n], size, value);
                return;
            case 0xa: // b(r)
                disp = fetchSigned(1);
                setSigned(r[n] + disp, size, value);
                return;
            case 0xe: // l(r)
                disp = fetchSigned(4);
                setSigned(r[n] + disp, size, value);
                return;
        }
        throw error("%08x: unknown operand %02x", r[PC] - 1, b);
    }

    public void chmk() throws Exception {
        int syscall = fetch();
        switch (syscall) {
            case 1: // exit
                System.exit(buf.getInt(r[AP] + 4));
                return;
            case 4:  // write
                System.out.print(getString(buf.getInt(r[AP] + 8), buf.getInt(r[AP] + 12)));
                return;
        }
        throw error("%08x: unknown syscall %02x", r[PC] - 1, syscall);
    }

    public void run() throws Exception {
        int opcode;
        for (;;) {
            switch (opcode = fetch()) {
                case 0x90: // movb
                    setOperand(1, getOperand(1));
                    break;
                case 0xbc: // chmk
                    chmk();
                    break;
                case 0xb0: // movw
                    setOperand(2, getOperand(2));
                    break;
                case 0xd0: // movl
                    setOperand(4, getOperand(4));
                    break;
                default:
                    throw error("%08x: unknown opcode %02x", r[PC] - 1, opcode);

            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: vaxrun a.out");
            System.exit(1);
        }
        try {
            new VAX(new AOut(args[0])).run();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
