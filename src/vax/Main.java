// This file is licensed under the CC0.
package vax;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static byte[] text;
    static ByteBuffer buf;
    static int pc;

    static int fetch() {
        return Byte.toUnsignedInt(text[pc++]);
    }

    static int fetch(int n) {
        int ret = 0;
        switch (n) {
            case 0:
                ret = Byte.toUnsignedInt(text[pc++]);
                break;
            case 1:
                ret = Short.toUnsignedInt(buf.getShort(pc));
                pc += 2;
                break;
            case 2:
                ret = buf.getInt(pc);
                pc += 4;
                break;
        }
        return ret;
    }

    static int fetchSigned(int n) {
        int ret = 0;
        switch (n) {
            case 0:
                ret = text[pc++];
                break;
            case 1:
                ret = buf.getShort(pc);
                pc += 2;
                break;
            case 2:
                ret = buf.getInt(pc);
                pc += 4;
                break;
        }
        return ret;
    }

    static String[] regs = {
        "r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7",
        "r8", "r9", "r10", "r11", "ap", "fp", "sp", "pc"
    };

    static String[] dref = {"", "*"};
    static String[] isfx = {"b", "w", "l"};

    static String getOpr(int n) {
        int b = fetch(), b1 = b >> 4, b2 = b & 15;
        String r = regs[b2];
        switch (b1) {
            case 0:
            case 1:
            case 2:
            case 3:
                return String.format("$0x%x", b);
            case 4:
                return getOpr(2) + "[" + r + "]";
            case 5:
                return r;
            case 6:
                return "(" + r + ")";
            case 7:
                return "-(" + r + ")";
            case 8:
            case 9: {
                String prefix = dref[b1 & 1];
                if (b2 == 15) {
                    String f = "$0x%0" + (1 << n) + "x";
                    return prefix + String.format(f, fetch(n));
                } else {
                    return prefix + "(" + r + ")+";
                }
            }
            case 0xa:
            case 0xb:
            case 0xc:
            case 0xd:
            case 0xe:
            case 0xf: {
                String prefix = dref[b1 & 1];
                int disp = fetchSigned((b1 - 0xa) >> 1);
                if (b2 == 15) {
                    return String.format("%s0x%x", prefix, pc + disp);
                } else if (disp == 0) {
                    return String.format("%s(%s)", prefix, r);
                } else {
                    return String.format("%s0x%x(%s)", prefix, disp, r); // 符号?
                }
            }
            default:
                return "???";
        }
    }

    static String op2(int n, String mne) {
        String opr1 = getOpr(n), opr2 = getOpr(n);
        return String.format("%s%s %s, %s", mne, isfx[n], opr1, opr2);
    }

    static String disasm1() {
        int b = fetch();
        switch (b) {
            case 0x90:
            case 0xb0:
            case 0xd0:
                return op2((b - 0x90) >> 5, "mov");
            default:
                return "";
        }
    }

    static void disasm(String path) {
        try {
            text = java.nio.file.Files.readAllBytes(Paths.get(path));
            buf = ByteBuffer.wrap(text).order(ByteOrder.LITTLE_ENDIAN);
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
        }
    }

    public static void main(String[] args) {
        disasm("samples/unix.text");
    }
}
