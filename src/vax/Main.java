package vax;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static byte[] text;
    static int pc;

    static int fetch() {
        return Byte.toUnsignedInt(text[pc++]);
    }

    public static void main(String[] args) {
        try {
            text = java.nio.file.Files.readAllBytes(Paths.get("samples/unix.text"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        while (pc < text.length) {
            int pc2 = pc;
            String mne = "???";
            switch (fetch()) {
                case 0xd0:
                    mne = "movl";
                    break;
                default:
                    continue;
            }
            System.out.printf("%08x:", pc2);
            for (; pc2 < pc; ++pc2) {
                System.out.printf(" %02x", text[pc2]);
            }
            System.out.println("  " + mne);
        }
    }
}
