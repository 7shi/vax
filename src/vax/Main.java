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
                String prefix = (b1 & 1) == 1 ? "@" : "";
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
                String prefix = (b1 & 1) == 1 ? "*" : "";
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

    static String op(int count, int n, String mne, boolean hasIsfx, String sfx) {
        StringBuilder sb = new StringBuilder();
        sb.append(mne);
        if (hasIsfx) {
            sb.append(isfx[n]);
        }
        sb.append(sfx);
        sb.append(" ");
        for (int i = 0; i < count; ++i) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(getOpr(n));
        }
        return sb.toString();
    }

    static String disasm1() {
        int b = fetch();
        switch (b) {
            case 0x01:
                return "nop";
            case 0x04:
                return "ret";
            case 0x05:
                return "rsb";
            case 0x80:
            case 0xa0:
            case 0xc0:
                return op(2, (b - 0x80) >> 5, "add", true, "2");
            case 0x81:
            case 0xa1:
            case 0xc1:
                return op(3, (b - 0x80) >> 5, "add", true, "3");
            case 0x82:
            case 0xa2:
            case 0xc2:
                return op(2, (b - 0x80) >> 5, "sub", true, "2");
            case 0x83:
            case 0xa3:
            case 0xc3:
                return op(3, (b - 0x80) >> 5, "sub", true, "3");
            case 0x90:
            case 0xb0:
            case 0xd0:
                return op(2, (b - 0x90) >> 5, "mov", true, "");
            case 0xfb:
                return op(2, 2, "call", false, "s");
            case 0xdd:
                return op(1, 2, "push", true, "");
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
                String asm = disasm1();
                if (asm.isEmpty()) {
                    continue;
                }
                for (int i = 0;; ++i) {
                    if (pc2 + i == pc) {
                        if (i <= 4) {
                            for (; i < 4; ++i) {
                                System.out.print("   ");
                            }
                            System.out.printf("\t%s", asm);
                        }
                        System.out.println();
                        break;
                    } else if ((i & 3) == 0) {
                        if (i == 4) {
                            System.out.printf("\t%s", asm);
                        }
                        if (i > 0) {
                            System.out.println();
                        }
                        System.out.printf("%8x:\t", pc2 + i);
                    }
                    System.out.printf("%02x ", text[pc2 + i]);
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
