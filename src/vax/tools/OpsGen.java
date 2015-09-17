// This file is licensed under the CC0.
package vax.tools;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpsGen {

    public static void main(String[] args) throws Exception {
        String[][] ops = new String[4][];
        for (int i = 0; i < 4; ++i) {
            ops[i] = new String[0x100];
        }
        try (BufferedReader br = new BufferedReader(
                new FileReader("research/allopr-t.txt"))) {
            System.out.print("enum VAXOp {");
            int prev = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] f = line.split("\t");
                String f0 = f[0].replaceAll("-", "");
                int op = Integer.parseInt(f0, 16);
                if ((op & 3) == 0 || op >> 4 != prev >> 4) {
                    System.out.println();
                    System.out.print("   ");
                }
                prev = op;
                String t = f.length > 2 ? f[2] : "";
                System.out.printf(" %s(0x%s, \"%s\"),", f[1].toUpperCase(), f0, t);
            }
            System.out.println();
            System.out.println("}");
        }
    }
}
