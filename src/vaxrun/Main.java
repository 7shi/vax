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

enum VAXOp {

    HALT(0x00, ""), NOP(0x01, ""), REI(0x02, ""), BPT(0x03, ""),
    RET(0x04, ""), RSB(0x05, ""), LDPCTX(0x06, ""), SVPCTX(0x07, ""),
    CVTPS(0x08, "wbwb"), CVTSP(0x09, "wbwb"), INDEX(0x0a, "llllll"), CRC(0x0b, "blwb"),
    PROBER(0x0c, "bwb"), PROBEW(0x0d, "bwb"), INSQUE(0x0e, "bb"), REMQUE(0x0f, "bl"),
    BSBB(0x10, "1"), BRB(0x11, "1"), BNEQ(0x12, "1"), BEQL(0x13, "1"),
    BGTR(0x14, "1"), BLEQ(0x15, "1"), JSB(0x16, "b"), JMP(0x17, "b"),
    BGEQ(0x18, "1"), BLSS(0x19, "1"), BGTRU(0x1a, "1"), BLEQU(0x1b, "1"),
    BVC(0x1c, "1"), BVS(0x1d, "1"), BCC(0x1e, "1"), BLSSU(0x1f, "1"),
    ADDP4(0x20, "wbwb"), ADDP6(0x21, "wbwbwb"), SUBP4(0x22, "wbwb"), SUBP6(0x23, "wbwbwb"),
    CVTPT(0x24, "wbbwb"), MULP(0x25, "wbwbwb"), CVTTP(0x26, "wbbwb"), DIVP(0x27, "wbwbwb"),
    MOVC3(0x28, "wbb"), CMPC3(0x29, "wbb"), SCANC(0x2a, "wbbb"), SPANC(0x2b, "wbbb"),
    MOVC5(0x2c, "wbbwb"), CMPC5(0x2d, "wbbwb"), MOVTC(0x2e, "wbbbwb"), MOVTUC(0x2f, "wbbbwb"),
    BSBW(0x30, "2"), BRW(0x31, "2"), CVTWL(0x32, "wl"), CVTWB(0x33, "wb"),
    MOVP(0x34, "wbb"), CMPP3(0x35, "wbb"), CVTPL(0x36, "wbl"), CMPP4(0x37, "wbwb"),
    EDITPC(0x38, "wbbb"), MATCHC(0x39, "wbwb"), LOCC(0x3a, "bwb"), SKPC(0x3b, "bwb"),
    MOVZWL(0x3c, "wl"), ACBW(0x3d, "www2"), MOVAW(0x3e, "wl"), PUSHAW(0x3f, "w"),
    ADDF2(0x40, "ff"), ADDF3(0x41, "fff"), SUBF2(0x42, "ff"), SUBF3(0x43, "fff"),
    MULF2(0x44, "ff"), MULF3(0x45, "fff"), DIVF2(0x46, "ff"), DIVF3(0x47, "fff"),
    CVTFB(0x48, "fb"), CVTFW(0x49, "fw"), CVTFL(0x4a, "fl"), CVTRFL(0x4b, "fl"),
    CVTBF(0x4c, "bf"), CVTWF(0x4d, "wf"), CVTLF(0x4e, "lf"), ACBF(0x4f, "fff2"),
    MOVF(0x50, "ff"), CMPF(0x51, "ff"), MNEGF(0x52, "ff"), TSTF(0x53, "f"),
    EMODF(0x54, "fbflf"), POLYF(0x55, "fwb"), CVTFD(0x56, "fd"),
    ADAWI(0x58, "ww"),
    INSQHI(0x5c, "bq"), INSQTI(0x5d, "bq"), REMQHI(0x5e, "ql"), REMQTI(0x5f, "ql"),
    ADDD2(0x60, "dd"), ADDD3(0x61, "ddd"), SUBD2(0x62, "dd"), SUBD3(0x63, "ddd"),
    MULD2(0x64, "dd"), MULD3(0x65, "ddd"), DIVD2(0x66, "dd"), DIVD3(0x67, "ddd"),
    CVTDB(0x68, "db"), CVTDW(0x69, "dw"), CVTDL(0x6a, "dl"), CVTRDL(0x6b, "dl"),
    CVTBD(0x6c, "bd"), CVTWD(0x6d, "wd"), CVTLD(0x6e, "ld"), ACBD(0x6f, "ddd2"),
    MOVD(0x70, "dd"), CMPD(0x71, "dd"), MNEGD(0x72, "dd"), TSTD(0x73, "d"),
    EMODD(0x74, "dbdld"), POLYD(0x75, "dwb"), CVTDF(0x76, "df"),
    ASHL(0x78, "bll"), ASHQ(0x79, "bqq"), EMUL(0x7a, "lllq"), EDIV(0x7b, "lqll"),
    CLRD(0x7c, "d"), MOVQ(0x7d, "qq"), MOVAQ(0x7e, "ql"), PUSHAQ(0x7f, "q"),
    ADDB2(0x80, "bb"), ADDB3(0x81, "bbb"), SUBB2(0x82, "bb"), SUBB3(0x83, "bbb"),
    MULB2(0x84, "bb"), MULB3(0x85, "bbb"), DIVB2(0x86, "bb"), DIVB3(0x87, "bbb"),
    BISB2(0x88, "bb"), BISB3(0x89, "bbb"), BICB2(0x8a, "bb"), BICB3(0x8b, "bbb"),
    XORB2(0x8c, "bb"), XORB3(0x8d, "bbb"), MNEGB(0x8e, "bb"), CASEB(0x8f, "bbb"),
    MOVB(0x90, "bb"), CMPB(0x91, "bb"), MCOMB(0x92, "bb"), BITB(0x93, "bb"),
    CLRB(0x94, "b"), TSTB(0x95, "b"), INCB(0x96, "b"), DECB(0x97, "b"),
    CVTBL(0x98, "bl"), CVTBW(0x99, "bw"), MOVZBL(0x9a, "bl"), MOVZBW(0x9b, "bw"),
    ROTL(0x9c, "bll"), ACBB(0x9d, "bbb2"), MOVAB(0x9e, "bl"), PUSHAB(0x9f, "b"),
    ADDW2(0xa0, "ww"), ADDW3(0xa1, "www"), SUBW2(0xa2, "ww"), SUBW3(0xa3, "www"),
    MULW2(0xa4, "ww"), MULW3(0xa5, "www"), DIVW2(0xa6, "ww"), DIVW3(0xa7, "www"),
    BISW2(0xa8, "ww"), BISW3(0xa9, "www"), BICW2(0xaa, "ww"), BICW3(0xab, "www"),
    XORW2(0xac, "ww"), XORW3(0xad, "www"), MNEGW(0xae, "ww"), CASEW(0xaf, "www"),
    MOVW(0xb0, "ww"), CMPW(0xb1, "ww"), MCOMW(0xb2, "ww"), BITW(0xb3, "ww"),
    CLRW(0xb4, "w"), TSTW(0xb5, "w"), INCW(0xb6, "w"), DECW(0xb7, "w"),
    BISPSW(0xb8, "w"), BICPSW(0xb9, "w"), POPR(0xba, "w"), PUSHR(0xbb, "w"),
    CHMK(0xbc, "w"), CHME(0xbd, "w"), CHMS(0xbe, "w"), CHMU(0xbf, "w"),
    ADDL2(0xc0, "ll"), ADDL3(0xc1, "lll"), SUBL2(0xc2, "ll"), SUBL3(0xc3, "lll"),
    MULL2(0xc4, "ll"), MULL3(0xc5, "lll"), DIVL2(0xc6, "ll"), DIVL3(0xc7, "lll"),
    BISL2(0xc8, "ll"), BISL3(0xc9, "lll"), BICL2(0xca, "ll"), BICL3(0xcb, "lll"),
    XORL2(0xcc, "ll"), XORL3(0xcd, "lll"), MNEGL(0xce, "ll"), CASEL(0xcf, "lll"),
    MOVL(0xd0, "ll"), CMPL(0xd1, "ll"), MCOML(0xd2, "ll"), BITL(0xd3, "ll"),
    CLRF(0xd4, "f"), TSTL(0xd5, "l"), INCL(0xd6, "l"), DECL(0xd7, "l"),
    ADWC(0xd8, "ll"), SBWC(0xd9, "ll"), MTPR(0xda, "ll"), MFPR(0xdb, "ll"),
    MOVPSL(0xdc, "l"), PUSHL(0xdd, "l"), MOVAL(0xde, "ll"), PUSHAL(0xdf, "l"),
    BBS(0xe0, "lb1"), BBC(0xe1, "lb1"), BBSS(0xe2, "lb1"), BBCS(0xe3, "lb1"),
    BBSC(0xe4, "lb1"), BBCC(0xe5, "lb1"), BBSSI(0xe6, "lb1"), BBCCI(0xe7, "lb1"),
    BLBS(0xe8, "l1"), BLBC(0xe9, "l1"), FFS(0xea, "lbbl"), FFC(0xeb, "lbbl"),
    CMPV(0xec, "lbbl"), CMPZV(0xed, "lbbl"), EXTV(0xee, "lbbl"), EXTZV(0xef, "lbbl"),
    INSV(0xf0, "llbb"), ACBL(0xf1, "lll2"), AOBLSS(0xf2, "ll1"), AOBLEQ(0xf3, "ll1"),
    SOBGEQ(0xf4, "l1"), SOBGTR(0xf5, "l1"), CVTLB(0xf6, "lb"), CVTLW(0xf7, "lw"),
    ASHP(0xf8, "bwbbwb"), CVTLP(0xf9, "lwb"), CALLG(0xfa, "bb"), CALLS(0xfb, "lb"),
    XFC(0xfc, ""),
    CVTDH(0xfd32, "dh"), CVTGF(0xfd33, "gh"),
    ADDG2(0xfd40, "gg"), ADDG3(0xfd41, "ggg"), SUBG2(0xfd42, "gg"), SUBG3(0xfd43, "ggg"),
    MULG2(0xfd44, "gg"), MULG3(0xfd45, "ggg"), DIVG2(0xfd46, "gg"), DIVG3(0xfd47, "ggg"),
    CVTGB(0xfd48, "gb"), CVTGW(0xfd49, "gw"), CVTGL(0xfd4a, "gl"), CVTRGL(0xfd4b, "gl"),
    CVTBG(0xfd4c, "bg"), CVTWG(0xfd4d, "wg"), CVTLG(0xfd4e, "lg"), ACBG(0xfd4f, "ggg2"),
    MOVG(0xfd50, "gg"), CMPG(0xfd51, "gg"), MNEGG(0xfd52, "gg"), TSTG(0xfd53, "g"),
    EMODG(0xfd54, "gwglg"), POLYG(0xfd55, "gwb"), CVTGH(0xfd56, "gh"),
    ADDH2(0xfd60, "hh"), ADDH3(0xfd61, "hhh"), SUBH2(0xfd62, "hh"), SUBH3(0xfd63, "hhh"),
    MULH2(0xfd64, "hh"), MULH3(0xfd65, "hhh"), DIVH2(0xfd66, "hh"), DIVH3(0xfd67, "hhh"),
    CVTHB(0xfd68, "hb"), CVTHW(0xfd69, "hw"), CVTHL(0xfd6a, "hl"), CVTRHL(0xfd6b, "hl"),
    CVTBH(0xfd6c, "bh"), CVTWH(0xfd6d, "wh"), CVTLH(0xfd6e, "lh"), ACBH(0xfd6f, "hhh2"),
    MOVH(0xfd70, "hh"), CMPH(0xfd71, "hh"), MNEGH(0xfd72, "hh"), TSTH(0xfd73, "h"),
    EMODH(0xfd74, "hwhlh"), POLYH(0xfd75, "hwb"), CVTHG(0xfd76, "hg"),
    CLRH(0xfd7c, "h"), MOVO(0xfd7d, "oo"), MOVAH(0xfd7e, "hl"), PUSHAH(0xfd7f, "h"),
    CVTFH(0xfd98, "fh"), CVTFG(0xfd99, "fg"),
    CVTHF(0xfdf6, "hf"), CVTHD(0xfdf7, "hd"),
    BUGL(0xfffd, "l"), BUGW(0xfffe, "w");

    public static final VAXOp[] table = new VAXOp[0x10000];
    public final int op;
    public final String mne;
    public final char[] oprs;

    static {
        for (VAXOp op : VAXOp.values()) {
            table[op.op] = op;
        }
    }

    private VAXOp(int op, String oprs) {
        this.op = op;
        this.mne = toString().toLowerCase();
        this.oprs = oprs.toCharArray();
    }
}

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

    public int reg(int n, int offset) {
        return n == 15 ? r[n] + offset : r[n];
    }

    public int getOperand(int size, boolean pre) throws Exception {
        int pc = r[PC];
        int b = Byte.toUnsignedInt(mem[pc++]);
        int t = b >> 4, n = b & 15, len = 0, ret = 0;
        switch (t) {
            case 0:
            case 1:
            case 2:
            case 3:
                len = 1;
                ret = b;
                break;
            case 5: // r
                len = 1;
                ret = reg(n, len);
                break;
            case 6: // (r)
                len = 1;
                ret = getSigned(reg(n, len), size);
                break;
            case 8:
                if (n == 15) {
                    len = 1 + size;
                    ret = getSigned(pc, size);
                }
                break;
            case 0xa: // b(r)
                len = 2;
                ret = getSigned(reg(n, len) + mem[pc], size);
                break;
            case 0xe: // l(r)
                len = 5;
                ret = getSigned(reg(n, len) + buf.getInt(pc), size);
                break;
        }
        if (len == 0) {
            throw error("%08x: unknown operand %02x", r[PC], b);
        }
        if (!pre) {
            r[PC] += len;
        }
        return ret;
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
        int opcode, s;
        for (;;) {
            switch (opcode = fetch()) {
                case 0x82: // subb2
                    s = getOperand(1, false);
                    setOperand(1, getOperand(1, true) - s);
                    break;
                case 0x90: // movb
                    setOperand(1, getOperand(1, false));
                    break;
                case 0xbc: // chmk
                    chmk();
                    break;
                case 0xb0: // movw
                    setOperand(2, getOperand(2, false));
                    break;
                case 0xc2: // subl2
                    s = getOperand(4, false);
                    setOperand(4, getOperand(4, true) - s);
                    break;
                case 0xd0: // movl
                    setOperand(4, getOperand(4, false));
                    break;
                default: {
                    VAXOp op = VAXOp.table[opcode];
                    String mne = op == null ? "???" : op.mne;
                    throw error("%08x: unknown opcode %02x %s", r[PC] - 1, opcode, mne);
                }

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
