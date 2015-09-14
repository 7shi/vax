package vax;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static byte[] text;
    static int pc;

    static int fetch() {
        return Byte.toUnsignedInt(text[pc++]);
    }

    static int fetch8() {
        return text[pc++];
    }

    static int fetch16() {
        int ret = ByteBuffer.wrap(text, pc, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        pc += 2;
        return ret;
    }

    static int fetch32() {
        int ret = ByteBuffer.wrap(text, pc, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        pc += 4;
        return ret;
    }

    static String[] regs = {
        "r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7",
        "r8", "r9", "r10", "r11", "ap", "fp", "sp", "pc"
    };

    static String getArg() {
        int b = fetch(), b1 = b >> 4, b2 = b & 15;
        String r = regs[b2];
        switch (b1) {
            case 0:
            case 1:
            case 2:
            case 3:
                return String.format("$0x%x", b);
            case 4:
                return getArg() + "[" + r + "]";
            case 5:
                return r;
            case 6:
                return "(" + r + ")";
            case 7:
                return "-(" + r + ")"; // pc?
            case 8:
                return "(" + r + ")+"; // pc?
            case 9:
                return "*(" + r + ")+"; // pc?
            case 0xa:
                return String.format("0x%x(%s)", fetch8(), r); // 符号?
            case 0xb:
                return String.format("*0x%x(%s)", fetch8(), r); // 符号?
            case 0xc:
                return String.format("0x%x(%s)", fetch16(), r); // 符号?
            case 0xd:
                return String.format("*0x%x(%s)", fetch16(), r); // 符号?
            case 0xe:
                return String.format("0x%x(%s)", fetch32(), r); // 符号?
            case 0xf:
                return String.format("*0x%x(%s)", fetch32(), r); // 符号?
            default:
                return "???";
        }
    }

    static String disasm1() {
        switch (fetch()) {
            case 0xd0:
                return "movl " + getArg() + ", " + getArg();
            default:
                return "";
        }
    }

    static void disasm(String path) {
        try {
            text = java.nio.file.Files.readAllBytes(Paths.get(path));
            while (pc < text.length) {
                int pc2 = pc;
                String mne = disasm1();
                if (!mne.isEmpty()) {
                    System.out.printf("%08x:", pc2);
                    for (; pc2 < pc; ++pc2) {
                        System.out.printf(" %02x", text[pc2]);
                    }
                    System.out.println("  " + mne);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }

    public static void main(String[] args) {
        disasm("samples/unix.text");
    }
}
