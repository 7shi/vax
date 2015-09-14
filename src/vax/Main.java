package vax;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            byte[] text = java.nio.file.Files.readAllBytes(Paths.get("samples/unix.text"));
            for (int i = 0; i < text.length;) {
                int len = 1;
                String mne = "???";
                switch (Byte.toUnsignedInt(text[i])) {
                    case 0xd0:
                        mne = "movl";
                        break;
                    default:
                        ++i;
                        continue;
                }
                System.out.printf("%08x:", i);
                for (int j = 0; j < len; ++j) {
                    System.out.printf(" %02x", text[i + j]);
                }
                System.out.println("  " + mne);
                i += len;
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
