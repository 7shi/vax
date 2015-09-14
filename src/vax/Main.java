package vax;

import java.nio.ByteBuffer;
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

    static String[] regs = {
        "r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7",
        "r8", "r9", "r10", "r11", "ap", "fp", "sp", "pc"
    };

    static String getArg() {
        int b = fetch(), b1 = b >> 4, b2 = b & 7;
        String r = regs[b2];
        switch (b1) {
            case 5:
                return r;
            case 6:
                return "(" + r + ")";
            case 0xa:
                return String.format("0x%x(%s)", fetch8(), r); // 符号?
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
