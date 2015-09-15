// This file is licensed under the CC0.
package vax.tools;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpsGen {

    static String convert(String line, int p1, int b) {
        int p2 = line.indexOf(' ', p1);
        if (p2 < 0) {
            return String.format("op(\"%s\")", line.substring(p1));
        }
        String mne = "\"" + line.substring(p1, p2) + "\"";
        switch (line.substring(p2 + 1)) {
            case "$0x50":
                return String.format("b(%s, 1)", mne);
            case "$0x50,r1":
                return String.format("b(%s, 2)", mne);
            case "$0x50,r1,r2":
                return String.format("b(%s, 3)", mne);
            case "$0x50,r1,r2,0x545a":
                return String.format("bw(%s, 3)", mne);
            case "$0x50,r1,r2,r3":
                return String.format("b(%s, 4)", mne);
            case "$0x50,r1,r2,r3,r4,r5":
                return String.format("b(%s, 6)", mne);
            case "$0x5150":
                return String.format("w(%s, 1)", mne);
            case "$0x5150,r2":
                return String.format("w(%s, 2)", mne);
            case "$0x5150,r2,r3":
                return String.format("w(%s, 3)", mne);
            case "$0x5150,r2,r3,0x555c":
                return String.format("ww(%s, 3)", mne);
            case "$0x5150,r2,r3,r4":
                return String.format("w(%s, 4)", mne);
            case "$0x5150,r2,r3,r4,r5":
                return String.format("w(%s, 5)", mne);
            case "$0x5150,r2,r3,r4,r5,r6":
                return String.format("w(%s, 6)", mne);
            case "$0x53525150":
                return String.format("l(%s, 1)", mne);
            case "$0x53525150 [f-float]":
                return String.format("f(%s, 1)", mne);
            case "$0x53525150 [f-float],r4":
                return String.format("f(%s, 2)", mne);
            case "$0x53525150 [f-float],r4,r5":
                return String.format("f(%s, 3)", mne);
            case "$0x53525150 [f-float],r4,r5,0x5760":
                return String.format("fw(%s, 3)", mne);
            case "$0x53525150 [f-float],r4,r5,r6,r7":
                return String.format("f(%s, 5)", mne);
            case "$0x53525150,0x5b":
                return String.format("lb(%s, 1)", mne);
            case "$0x53525150,r4":
                return String.format("l(%s, 2)", mne);
            case "$0x53525150,r4,0x5d":
                return String.format("lb(%s, 2)", mne);
            case "$0x53525150,r4,r5":
                return String.format("l(%s, 3)", mne);
            case "$0x53525150,r4,r5,0x5760":
                return String.format("lw(%s, 3)", mne);
            case "$0x53525150,r4,r5,r6":
                return String.format("l(%s, 4)", mne);
            case "$0x53525150,r4,r5,r6,r7,r8":
                return String.format("l(%s, 6)", mne);
            case "$0x5756555453525150":
                return String.format("q(%s, 1)", mne);
            case "$0x5756555453525150 [d-float]":
                return String.format("d(%s, 1)", mne);
            case "$0x5756555453525150 [d-float],r8":
                return String.format("d(%s, 2)", mne);
            case "$0x5756555453525150 [d-float],r8,r9":
                return String.format("d(%s, 3)", mne);
            case "$0x5756555453525150 [d-float],r8,r9,0x5b68":
                return String.format("dw(%s, 3)", mne);
            case "$0x5756555453525150 [d-float],r8,r9,r10,r11":
                return String.format("d(%s, 5)", mne);
            case "$0x5756555453525150 [g-float]":
                return String.format("g(%s, 1)", mne);
            case "$0x5756555453525150 [g-float],r8":
                return String.format("g(%s, 2)", mne);
            case "$0x5756555453525150 [g-float],r8,r9":
                return String.format("g(%s, 3)", mne);
            case "$0x5756555453525150 [g-float],r8,r9,0x5b69":
                return String.format("gw(%s, 3)", mne);
            case "$0x5756555453525150 [g-float],r8,r9,r10,r11":
                return String.format("g(%s, 5)", mne);
            case "$0x5756555453525150,r8":
                return String.format("q(%s, 2)", mne);
            case "$0x5f5e5d5c5b5a59585756555453525150 [h-float]":
                return String.format("h(%s, 1)", mne);
            case "$0x5f5e5d5c5b5a59585756555453525150 [h-float],(r0)":
                return String.format("h(%s, 2)", mne);
            case "$0x5f5e5d5c5b5a59585756555453525150 [h-float],(r0),(r1)":
                return String.format("h(%s, 3)", mne);
            case "$0x5f5e5d5c5b5a59585756555453525150 [h-float],(r0),(r1),(r2),(r3)":
                return String.format("h(%s, 5)", mne);
            case "$0x5f5e5d5c5b5a59585756555453525150 [h-float],(r0),(r1),0x6379":
                return String.format("hw(%s, 3)", mne);
            case "$0x5f5e5d5c5b5a59585756555453525150,(r0)":
                return String.format("o(%s, 2)", mne);
            case "0x5092":
                return String.format("brw(%s)", mne);
            case "0xffffff91":
                return String.format("brb(%s)", mne);
            default:
                return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String[][] ops = new String[4][];
        for (int i = 0; i < 4; ++i) {
            ops[i] = new String[0x100];
        }
        FileReader fr = new FileReader("research/allop.d");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            int p1 = line.indexOf('\t') + 1;
            if (p1 > 0) {
                int b = Integer.parseInt(line.substring(0, 2), 16);
                int i = b < 0xfd ? 0 : b - 0xfc;
                int j = b < 0xfd ? b : Integer.parseInt(line.substring(3, 5), 16);
                ops[i][j] = convert(line, p1, b);
            }
        }
        br.close();
        fr.close();

        System.out.println("{");
        for (int i = 0; i < 4; ++i) {
            String h = i == 0 ? "" : String.format("%02x", 0xfc + i);
            System.out.println("    {");
            for (int j = 0; j < 0x100; j += 8) {
                System.out.printf("        /* %s%02x */", h, j);
                for (int k = 0; k < 8; ++k) {
                    String o = ops[i][j + k];
                    System.out.printf(" %s,", o != null ? o : "null");
                }
                System.out.println();
            }
            System.out.println("    },");
        }
        System.out.println("};");
    }
}
