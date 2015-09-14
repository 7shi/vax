// This file is licensed under the CC0.
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

    static String[] dref = {"", "*"};

    static String getArgDisp(int b1, int b2, String r, int disp) {
        String prefix = dref[b1 & 1];
        if (b2 == 15) {
            return String.format("%s0x%x", prefix, pc + disp);
        } else if (disp == 0) {
            return String.format("%s(%s)", prefix, r);
        } else {
            return String.format("%s0x%x(%s)", prefix, disp, r); // 符号?
        }
    }

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
            case 9:
                if (b2 == 15) {
                    return dref[b1 & 1] + String.format("$0x%08x", fetch32());
                } else {
                    return dref[b1 & 1] + "(" + r + ")+";
                }
            case 0xa:
            case 0xb:
                return getArgDisp(b1, b2, r, fetch8());
            case 0xc:
            case 0xd:
                return getArgDisp(b1, b2, r, fetch16());
            case 0xe:
            case 0xf:
                return getArgDisp(b1, b2, r, fetch32());
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
