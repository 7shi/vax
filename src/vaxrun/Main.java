// This file is licensed under the CC0.
package vaxrun;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

enum VAXType {

    BYTE('b', 1, ""), WORD('w', 2, ""), LONG('l', 4, ""),
    QWORD('q', 8, ""), OWORD('o', 16, ""),
    FFLOAT('f', 4, " [f-float]"), DFLOAT('d', 8, " [d-float]"),
    GFLOAT('g', 8, " [g-float]"), HFLOAT('h', 16, " [h-float]"),
    RELB('1', 1, ""), RELW('2', 2, "");

    public static final VAXType[] table = new VAXType[128];
    public final char suffix;
    public final int size;
    public final String valueSuffix;

    static {
        for (VAXType t : VAXType.values()) {
            table[(int) t.suffix] = t;
        }
    }

    private VAXType(char suffix, int size, String valueSuffix) {
        this.suffix = suffix;
        this.size = size;
        this.valueSuffix = valueSuffix;
    }
}

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
    CLRQ(0x7c, "q"), MOVQ(0x7d, "qq"), MOVAQ(0x7e, "ql"), PUSHAQ(0x7f, "q"),
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
    CLRL(0xd4, "l"), TSTL(0xd5, "l"), INCL(0xd6, "l"), DECL(0xd7, "l"),
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

    public Symbol(int addr) {
        name = null;
        value = addr;
        type = other = desc = tchar = 0;
    }

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
        value = buf.getInt(p + 12) & 0x7fffffff;
        tchar = type < 10 ? "uUaAtTdDbB".charAt(type) : '?';
    }

    @Override
    public String toString() {
        if (isNull()) {
            return String.format("%08x", value);
        }
        return String.format("%08x %c %s", value, tchar, name);
    }

    public boolean isObject() {
        return name != null && name.endsWith(".o");
    }

    public boolean isNull() {
        return name == null;
    }
}

class VAXDisasm {

    private static final String[] regs = {
        "r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7",
        "r8", "r9", "r10", "r11", "ap", "fp", "sp", "pc"
    };

    private final ByteBuffer buf;
    private final AOut aout;
    private int pc, offset, casead, casec;
    private LinkedList<Symbol> addrs;

    public VAXDisasm(ByteBuffer buf, AOut aout) {
        this.buf = buf;
        this.aout = aout;
        if (aout.a_entry < 0) {
            offset = 0x80000000;
        }
    }

    public int fetch() {
        return Byte.toUnsignedInt(buf.get(pc++));
    }

    public int fetch(int size) {
        int oldpc = pc;
        pc += size;
        switch (size) {
            case 1:
                return buf.get(oldpc);
            case 2:
                return buf.getShort(oldpc);
            case 4:
                return buf.getInt(oldpc);
        }
        return 0;
    }

    public String fetchHex(int size, String suffix) {
        int[] bs = new int[size];
        for (int i = 0; i < size; ++i) {
            bs[i] = fetch();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; --i) {
            sb.append(String.format("%02x", bs[i]));
        }
        String x = sb.toString();
        int p = 0;
        while (p < x.length() - 1 && x.charAt(p) == '0') {
            ++p;
        }
        String xx = x.substring(p);
        String prefix = "0x";
        if (xx.length() == 1 && Character.isDigit(xx.charAt(0))) {
            prefix = "";
        }
        return prefix + x.substring(p) + suffix;
    }

    public void output(PrintStream out, int pc, int len, String asm) {
        for (int i = 0; i < len; ++i) {
            if ((i & 7) == 0) {
                if (i > 0 && i == 8) {
                    out.println("  " + asm);
                }
                out.printf("%08x:", offset + pc + i);
            }
            out.printf(" %02x", buf.get(pc + i));
        }
        if (len <= 8) {
            for (int i = len; i < 8; ++i) {
                out.print("   ");
            }
            out.print("  " + asm);
        }
        out.println();
    }

    public void addAddress(int ad) {
        if (addrs == null || ad <= pc) {
            return;
        }
        Symbol sym = new Symbol(ad);
        int i = 0;
        for (Symbol s : addrs) {
            if (s.value > ad) {
                addrs.add(i, sym);
                return;
            }
            ++i;
        }
        addrs.add(sym);
    }

    public String getOperand(VAXType t) {
        if (t == VAXType.RELB || t == VAXType.RELW) {
            int rel = fetch(t.size), ad = pc + rel;
            addAddress(ad);
            return String.format("0x%x", ad);
        }
        int b = fetch(), b1 = b >> 4, b2 = b & 15;
        String r = regs[b2];
        switch (b1) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "$" + hex(b) + t.valueSuffix;
            case 4:
                return getOperand(t) + "[" + r + "]";
            case 5:
                return r;
            case 6:
                return "(" + r + ")";
            case 7:
                return "-(" + r + ")";
            case 8:
                if (b2 == 15) {
                    return "$" + fetchHex(t.size, t.valueSuffix);
                } else {
                    return "(" + r + ")+";
                }
            case 9:
                if (b2 == 15) {
                    return "*" + address(fetch(4));
                } else {
                    return "*(" + r + ")+";
                }
            default: {
                String prefix = (b1 & 1) == 1 ? "*" : "";
                int disp = fetch(1 << ((b1 - 0xa) >> 1));
                if (b2 == 15) {
                    return prefix + address(pc + disp);
                } else {
                    return String.format("%s%s(%s)", prefix, hex(disp), r);
                }
            }
        }
    }

    public String disasm1(int addr) {
        pc = addr;
        int opc = fetch();
        VAXOp op = VAXOp.table[opc];
        if (op == null) {
            op = VAXOp.table[opc = opc << 8 | fetch()];
        }
        if (op == null) {
            return String.format(".word 0x%x", opc);
        }
        StringBuilder sb = new StringBuilder(op.mne);
        for (int i = 0; i < op.oprs.length; ++i) {
            sb.append(i == 0 ? " " : ",");
            String opr = getOperand(VAXType.table[op.oprs[i]]);
            switch (op) {
                case CASEB:
                case CASEW:
                case CASEL:
                    if (i == op.oprs.length - 1) {
                        casead = pc;
                        if (opr.startsWith("$0x")) {
                            casec = Integer.parseInt(opr.substring(3), 16) + 1;
                        } else if (opr.startsWith("$")) {
                            casec = Integer.parseInt(opr.substring(1)) + 1;
                        }
                    }
                    break;
            }
            sb.append(opr);
        }
        return sb.toString();
    }

    public void disasm(PrintStream out, int start, int end) {
        addrs = aout != null ? aout.getAddresses() : new LinkedList<>();
        casec = 0;
        pc = start;
        while (pc < end) {
            while (!addrs.isEmpty() && addrs.peek().value < pc) {
                addrs.remove();
            }
            boolean w = pc == aout.a_entry;
            while (!addrs.isEmpty() && addrs.peek().value == pc) {
                Symbol s = addrs.remove();
                if (s.isObject()) {
                    System.out.printf("[%s]", s.name);
                    System.out.println();
                } else if (!s.isNull()) {
                    System.out.printf("%s:", s.name);
                    System.out.println();
                    w = true;
                }
            }
            int oldpc = pc;
            String asm;
            if (casec > 0) {
                int ad = casead + fetch(2);
                addAddress(ad);
                asm = String.format(".word 0x%x-0x%x", ad, casead);
                --casec;
            } else {
                asm = w ? word() : disasm1(pc);
            }
            if (!addrs.isEmpty() && addrs.peek().value < pc) {
                Symbol s = addrs.peek();
                pc = oldpc;
                asm = bytes(s.value - pc);
            }
            output(out, oldpc, pc - oldpc, asm);
        }
    }

    public String bytes(int len) {
        ArrayList<String> bs = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            bs.add(String.format("0x%02x", fetch()));
        }
        return ".byte " + String.join(", ", bs);
    }

    public String word() {
        return ".word " + hex(Short.toUnsignedInt((short) fetch(2)));
    }

    public String word(int pc) {
        this.pc = pc;
        return word();
    }

    public String address(int ad) {
        addAddress(ad);
        String ret = String.format("0x%x", ad);
        if (aout != null && aout.symT.containsKey(ad)) {
            ret += "<" + aout.symT.get(ad) + ">";
        }
        return ret;
    }

    public static String hex(int v) {
        String sign = "";
        if (v < 0) {
            sign = "-";
            v = -v;
        }
        String x = v < 10 ? "" : "0x";
        return String.format("%s%s%x", sign, x, v);
    }
}

class AOut {

    public final ByteBuffer header;
    public final int a_magic, a_text, a_data, a_bss, a_syms, a_entry, a_trsize, a_drsize;
    public final byte[] text, data;
    public final Symbol[] syms;
    public final HashMap<Integer, String> symO = new HashMap<>();
    public final HashMap<Integer, String> symT = new HashMap<>();
    private final Symbol[] addrs;

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
                    fis.skip(a_trsize + a_drsize);
                    byte[] sym = new byte[a_syms];
                    fis.read(sym);
                    ByteBuffer sbuf = ByteBuffer.wrap(sym).order(ByteOrder.LITTLE_ENDIAN);
                    ArrayList<Symbol> list = new ArrayList<>();
                    ArrayList<Symbol> ads = new ArrayList<>();
                    for (int p = 0; p <= a_syms - 16; p += 16) {
                        Symbol s = new Symbol(sbuf, p);
                        list.add(s);
                        if (4 <= s.type && s.type <= 9) {
                            if (s.isObject()) {
                                symO.put(s.value, s.name);
                            } else {
                                symT.put(s.value, s.name);
                            }
                            ads.add(s);
                        }
                    }
                    syms = list.toArray(new Symbol[list.size()]);
                    ads.sort((a, b) -> {
                        int ret = a.value - b.value;
                        if (ret == 0) {
                            int ao = a.isObject() ? 0 : 1;
                            int bo = b.isObject() ? 0 : 1;
                            return ao - bo;
                        }
                        return ret;
                    });
                    addrs = ads.toArray(new Symbol[ads.size()]);
                } else {
                    syms = null;
                    addrs = new Symbol[0];
                }
                return;
            }
        }
        header = null;
        a_text = a_data = a_bss = a_syms = a_entry = a_trsize = a_drsize = 0;
        text = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path));
        data = null;
        syms = null;
        addrs = new Symbol[0];
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

    public LinkedList<Symbol> getAddresses() {
        return new LinkedList<>(Arrays.asList(addrs));
    }
}

class AddrSym {

    public final int addr;
    public final String sym;

    public AddrSym(int addr, String sym) {
        this.addr = addr;
        this.sym = sym;
    }
}

class VAX {

    public static final int AP = 12, FP = 13, SP = 14, PC = 15;
    private final byte[] mem = new byte[0x40000];
    private final int[] r = new int[16];
    private boolean n, z, v, c;
    private final ByteBuffer buf;
    private final AOut aout;
    private final VAXDisasm dis;
    private final Stack<AddrSym> callStack = new Stack<>();
    private int mode;

    public VAX(AOut aout, String[] args) {
        this.aout = aout;
        System.arraycopy(aout.text, 0, mem, 0, aout.a_text);
        int dstart = (aout.a_text + 0x1ff) & ~0x1ff;
        System.arraycopy(aout.data, 0, mem, dstart, aout.a_data);
        r[PC] = aout.a_entry;
        buf = ByteBuffer.wrap(mem).order(ByteOrder.LITTLE_ENDIAN);
        dis = new VAXDisasm(buf, aout);
        setArgs(args);
    }

    private void setArgs(String[] args) {
        int s = mem.length;
        byte[][] bargs = new byte[args.length][];
        for (int i = 0; i < args.length; ++i) {
            bargs[i] = args[i].getBytes(StandardCharsets.US_ASCII);
            s -= bargs[i].length + 1;
        }
        s &= ~3;
        buf.putLong(s - 8, 0);
        int argv = s - (args.length + 2) * 4;
        r[SP] = argv - 4;
        buf.putInt(r[SP], args.length); // argc
        for (byte[] barg : bargs) {
            buf.putInt(argv, s);
            argv += 4;
            System.arraycopy(barg, 0, mem, s, barg.length);
            s += barg.length + 1;
            mem[s - 1] = 0;
        }
    }

    public void setNZVC(boolean n, boolean z, boolean v, boolean c) {
        this.n = n;
        this.z = z;
        this.v = v;
        this.c = c;
    }

    public int fetch() {
        return Byte.toUnsignedInt(mem[r[PC]++]);
    }

    public int fetch(int size) throws Exception {
        int pc = r[PC];
        r[PC] += size;
        return get(pc, size);
    }

    public int get(int addr, int size) throws Exception {
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

    public int set(int addr, int size, int value) throws Exception {
        switch (size) {
            case 1:
                return mem[addr] = (byte) value;
            case 2:
                buf.putShort(addr, (short) value);
                return (short) value;
            case 4:
                buf.putInt(addr, value);
                return value;
        }
        throw new Exception("invalid size " + size);
    }

    public String getString(int addr, int length) {
        byte[] bytes = Arrays.copyOfRange(mem, addr, addr + length);
        return new String(bytes, StandardCharsets.US_ASCII);
    }

    public Exception error(String format, Object... args) {
        return new Exception(String.format(format, args));
    }

    public int reg(int n, int offset) {
        return n == 15 ? r[n] + offset : r[n];
    }

    public int peekOperand(int size) throws Exception {
        int b = Byte.toUnsignedInt(mem[r[PC]]);
        switch (b >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
                return b;
            case 5: // r
                return reg(b & 15, 1);
        }
        return get(peekAddress(size, 0), size);
    }

    public int getOperand(int size) throws Exception {
        int b = Byte.toUnsignedInt(mem[r[PC]]);
        switch (b >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
                ++r[PC];
                return b;
            case 5: // r
                ++r[PC];
                return r[b & 15];
        }
        return get(getAddress(size), size);
    }

    public int setOperand(int size, int value) throws Exception {
        int b = Byte.toUnsignedInt(mem[r[PC]]);
        switch (b >> 4) {
            case 5: // r
            {
                ++r[PC];
                int rn = b & 15;
                switch (size) {
                    case 1:
                        return r[rn] = (byte) value;
                    case 2:
                        return r[rn] = (short) value;
                    case 4:
                        return r[rn] = value;
                    case 8:
                        r[rn + 1] = value < 0 ? -1 : 0;
                        return r[rn] = value;
                }
                break;
            }
        }
        return set(getAddress(size), size, value);
    }

    public int peekAddress(int size, int ofs) throws Exception {
        int pc = r[PC] + ofs;
        int b = Byte.toUnsignedInt(mem[pc++]);
        int rn = b & 15;
        switch (b >> 4) {
            case 4: // [r]
                return peekAddress(size, ofs + 1) + size * reg(rn, ofs + 1);
            case 6: // (r)
                return reg(rn, ofs + 1);
            case 7: // -(r)
                return reg(rn, ofs + 1) - size;
            case 8: // (r)+
                return reg(rn, ofs + 1);
            case 9: // *(r)+
                return get(reg(rn, ofs + 1), 4);
            case 0xa: // b(r)
                return reg(rn, ofs + 2) + mem[pc];
            case 0xb: // *b(r)
                return get(reg(rn, ofs + 2) + mem[pc], 4);
            case 0xc: // w(r)
                return reg(rn, ofs + 3) + buf.getShort(pc);
            case 0xd: // *w(r)
                return get(reg(rn, ofs + 3) + buf.getShort(pc), 4);
            case 0xe: // l(r)
                return reg(rn, ofs + 5) + buf.getInt(pc);
            case 0xf: // *l(r)
                return get(reg(rn, ofs + 5) + buf.getInt(pc), 4);
        }
        throw error("%08x: unknown operand %02x", r[PC] + ofs, b);
    }

    public int getAddress(int size) throws Exception {
        int pc = r[PC];
        int b = fetch();
        int rn = b & 15, disp, ret;
        switch (b >> 4) {
            case 4: // [r]
                return getAddress(size) + size * r[rn];
            case 6: // (r)
                return r[rn];
            case 7: // -(r)
                return r[rn] -= size;
            case 8: // (r)+
                ret = r[rn];
                r[rn] += size;
                return ret;
            case 9: // *(r)+
                ret = get(r[rn], 4);
                r[rn] += 4;
                return ret;
            case 0xa: // b(r)
                disp = fetch(1);
                return r[rn] + disp;
            case 0xb: // *b(r)
                disp = fetch(1);
                return get(r[rn] + disp, 4);
            case 0xc: // w(r)
                disp = fetch(2);
                return r[rn] + disp;
            case 0xd: // *w(r)
                disp = fetch(2);
                return get(r[rn] + disp, 4);
            case 0xe: // l(r)
                disp = fetch(4);
                return r[rn] + disp;
            case 0xf: // *l(r)
                disp = fetch(4);
                return get(r[rn] + disp, 4);
        }
        throw error("%08x: not addr %02x", pc, b);
    }

    public void debug() {
        System.err.printf("%08x %08x %08x %08x-%08x %08x %08x %08x-%08x %08x %08x %08x-%08x %08x %08x %c%c%c%c %08x %s",
                r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7],
                r[8], r[9], r[10], r[11], r[12], r[13], r[14],
                n ? 'N' : '-', z ? 'Z' : '-', v ? 'V' : '-', c ? 'C' : '-',
                r[15], dis.disasm1(r[PC]));
        System.err.println();
    }

    public String getCallStack() {
        if (callStack.empty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < callStack.size(); ++i) {
            if (i > 0) {
                sb.append(" > ");
            }
            sb.append(callStack.elementAt(i).sym);
        }
        return sb.toString();
    }

    public void pushCallStack(boolean args) {
        String sym = aout.symT.getOrDefault(r[PC], "???");
        callStack.push(new AddrSym(r[PC], sym));
        if (mode >= 2) {
            String s = getCallStack();
            if (args) {
                s += "(" + getArgs() + ")";
            }
            System.err.printf("%-139s %08x %s", s, r[PC], dis.word(r[PC]));
            System.err.println();
        }
        r[PC] += 2;
    }

    public String getArgs() {
        StringBuilder sb = new StringBuilder();
        int argc = buf.getInt(r[AP]);
        for (int i = 0; i < argc; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(String.format("%08x", buf.getInt(r[AP] + 4 + i * 4)));
        }
        return sb.toString();
    }

    public void push(int size, int value) throws Exception {
        set(r[SP] -= size, size, value);
    }

    public int pop(int size) throws Exception {
        int ret = get(r[SP], size);
        r[SP] += size;
        return ret;
    }

    public void disasm(PrintStream out) {
        dis.disasm(out, 0, aout.a_text);
    }

    public void run(int mode) throws Exception {
        this.mode = mode;
        if (mode >= 2) {
            System.err.print("   r0       r1       r2       r3   -");
            System.err.print("   r4       r5       r6       r7   -");
            System.err.print("   r8       r9       r10      r11  -");
            System.err.println(" r12(ap)  r13(fp)  r14(sp) flag  r15(pc) disasm");
        }
        pushCallStack(false);
        int pc = r[PC];
        try {
            for (;;) {
                pc = r[PC];
                step();
            }
        } catch (Exception e) {
            if (!callStack.empty()) {
                for (int i = 0; i < callStack.size(); ++i) {
                    if (i > 0) {
                        System.err.print(" > ");
                    }
                    AddrSym asym = callStack.elementAt(i);
                    System.err.printf("%08x(%s)", asym.addr, asym.sym);
                }
                System.err.println();
            }
            dis.disasm(System.err, pc, pc + 1);
            throw e;
        }
    }

    public void cvtlp(int src, int dstlen, int dstaddr) {
        int len = (dstlen >> 1) + 1;
        int d = dstaddr + len - 1;
        Arrays.fill(mem, dstaddr, d, (byte) 0);
        mem[d] = (byte) (src < 0 ? 13 : 12);
        int tmp = Math.abs(src);
        for (int i = 0; i < dstlen && tmp > 0; ++i) {
            byte b = (byte) (Integer.remainderUnsigned(tmp, 10));
            if ((i & 1) == 0) {
                mem[d] |= b << 4;
            } else {
                mem[--d] = b;
            }
            tmp = Integer.divideUnsigned(tmp, 10);
        }
        r[0] = r[1] = r[2] = 0;
        r[3] = d;
        setNZVC(src < 0, src == 0, tmp > 0, false);
        if (mode >= 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; ++i) {
                sb.append(String.format(" %02x", Byte.toUnsignedInt(mem[dstaddr + i])));
            }
            System.err.printf("[cvtlp %d:%08x]%s", src, dstaddr, sb.toString());
            System.err.println();
        }
    }

    private void editpcDebug(String cmd) {
        if (mode < 2) {
            return;
        }
        System.err.printf("[editpc] r0=%08x r1=%08x r2=%08x r4=%08x r5=%08x %c%c%c%c r3=%08x %s",
                r[0], r[1], r[2], r[4], r[5],
                n ? 'N' : '-', z ? 'Z' : '-', v ? 'V' : '-', c ? 'C' : '-',
                r[3], cmd);
        System.err.println();
    }

    private int editpcRead() throws Exception {
        if (r[0] == 0) {
            throw error("editpc: source is too short");
        }
        if (r[0] < 0) {
            r[0] += 0x10000;
            return 0;
        }
        if (((r[0]--) & 1) == 1) {
            return (mem[r[1]] >> 4) & 0xf;
        }
        return mem[r[1]++] & 0xf;
    }

    public void editpc(int srclen, int srcaddr, int pattern, int dstaddr) throws Exception {
        int s = mem[srcaddr + (srclen >> 1)] & 0xf;
        setNZVC(s == 0xb || s == 0xd, true, false, false);
        r[0] = srclen;
        r[1] = srcaddr;
        r[2] = (n ? '-' : ' ') << 8 | ' ';
        r[3] = pattern;
        r[4] = 0;
        r[5] = dstaddr;
        OUTER:
        for (;; r[3]++) {
            int b = Byte.toUnsignedInt(mem[r[3]]);
            switch (b) {
                case 0:
                    editpcDebug("eo$end");
                    break OUTER;
                case 1:
                    editpcDebug("eo$end_float");
                    if (!c) {
                        c = true;
                        r[4] = r[1];
                        mem[r[5]++] = (byte) ((r[2] >> 8) & 0xff);
                    }
                    continue;
            }
            int rep = b & 15;
            switch (b >> 4) {
                case 0x9:
                    editpcDebug("eo$move " + rep);
                    for (int i = 0; i < rep; ++i) {
                        int oldr1 = r[1], num = editpcRead();
                        if (num != 0 && !c) {
                            z = false;
                            c = true;
                            r[4] = oldr1;
                        }
                        mem[r[5]++] = (byte) (c ? '0' + num : r[2] & 0xff);
                    }
                    break;
                case 0xa:
                    editpcDebug("eo$float " + rep);
                    for (int i = 0; i < rep; ++i) {
                        int oldr1 = r[1], num = editpcRead();
                        if (num != 0 && !c) {
                            z = false;
                            c = true;
                            r[4] = oldr1;
                            mem[r[5]++] = (byte) ((r[2] >> 8) & 0xff);
                        }
                        mem[r[5]++] = (byte) (c ? '0' + num : r[2] & 0xff);
                    }
                    break;
                default:
                    throw error("editpc: unknown operator %02x", b);
            }
        }
        if (z) {
            n = false;
        }
        v = r[0] != 0;
        r[0] = srclen;
        r[1] = r[4];
        r[2] = 0;
        r[4] = 0;
        if (mode >= 2) {
            String dststr = getString(dstaddr, r[5] - dstaddr);
            System.err.printf("[editpc:%08x] \"%s\"", dstaddr, dststr);
            System.err.println();
        }
    }

    public void step() throws Exception {
        if (mode >= 2) {
            debug();
        }
        int op = fetch();
        int size = 1 << ((op & 0x7f) >> 5);
        int s1, s2, s3, d, tmp;
        switch (op) {
            case 0xbc: // chmk
                syscall();
                break;
            case 0xfb: // calls
                s1 = Byte.toUnsignedInt(mem[r[PC]++]);
                s2 = getAddress(4);
                d = get(s2, 2); // entry mask
                push(4, s1);
                tmp = r[SP];
                r[SP] &= ~3;
                if ((d & 0xfff) != 0) {
                    for (int i = 11, bit = 0x800; i >= 0; --i, bit >>= 1) {
                        if ((d & bit) != 0) {
                            push(4, r[i]);
                        }
                    }
                }
                push(4, r[PC]);
                push(4, r[FP]);
                push(4, r[AP]);
                push(4, ((tmp & 3) << 30)
                        | 0x2000
                        | ((d & 0xfff) << 16)
                        | (n ? 8 : 0)
                        | (z ? 4 : 0)
                        | (v ? 2 : 0)
                        | (c ? 1 : 0));
                push(4, 0); // handler
                r[AP] = tmp;
                r[FP] = r[SP];
                r[PC] = s2;
                n = z = v = c = false;
                pushCallStack(true);
                break;
            case 0x04: // ret
                r[SP] = r[FP] + 4;
                tmp = pop(4);
                n = (tmp & 8) != 0;
                z = (tmp & 4) != 0;
                v = (tmp & 2) != 0;
                c = (tmp & 1) != 0;
                r[AP] = pop(4);
                r[FP] = pop(4);
                r[PC] = pop(4);
                if ((tmp & 0xfff0000) != 0) {
                    for (int i = 0, bit = 0x10000; i <= 11; ++i, bit <<= 1) {
                        if ((tmp & bit) != 0) {
                            r[i] = pop(4);
                        }
                    }
                }
                r[SP] += (tmp >> 30) & 3;
                if ((tmp & 0x2000) != 0) { // calls
                    s1 = pop(4); // argc
                    r[SP] += s1 * 4;
                }
                callStack.pop();
                if (mode >= 2) {
                    System.err.println(getCallStack());
                }
                break;
            case 0x11: // brb
            case 0x31: // brw
                s1 = fetch(size);
                r[PC] += s1;
                break;
            case 0x17: // jmp
                r[PC] = getOperand(4);
                break;
            case 0x12: // bneq / bnequ
            case 0x13: // beql / beqlu
                s1 = fetch(1);
                if (z == ((op & 1) != 0)) {
                    r[PC] += s1;
                }
                break;
            case 0x14: // bgtr
            case 0x15: // bleq
                s1 = fetch(1);
                if ((n || z) == ((op & 1) != 0)) {
                    r[PC] += s1;
                }
                break;
            case 0x18: // bgeq
            case 0x19: // blss
                s1 = fetch(1);
                if (n == ((op & 1) != 0)) {
                    r[PC] += s1;
                }
                break;
            case 0x1a: // bgtru
            case 0x1b: // blequ
                s1 = fetch(1);
                if ((c || z) == ((op & 1) != 0)) {
                    r[PC] += s1;
                }
                break;
            case 0x1c: // bvc
            case 0x1d: // bvs
                s1 = fetch(1);
                if (v == ((op & 1) != 0)) {
                    r[PC] += s1;
                }
                break;
            case 0x1e: // bgequ / bcc
            case 0x1f: // blssu / bcs
                s1 = fetch(1);
                if (c == ((op & 1) != 0)) {
                    r[PC] += s1;
                }
                break;
            case 0xe0: // bbs
            case 0xe1: // bbc
                s1 = getOperand(4);
                s2 = getOperand(1);
                s3 = fetch(1);
                if (((s2 >> s1) & 1) != (op & 1)) {
                    r[PC] += s3;
                }
                break;
            case 0xe2: // bbss
            case 0xe3: // bbcs
            case 0xe4: // bbsc
            case 0xe5: // bbcc
                s1 = getOperand(4);
                s2 = peekOperand(1);
                if ((op & 4) == 0) {
                    setOperand(1, s2 | (1 << s1));
                } else {
                    setOperand(1, s2 & ~(1 << s1));
                }
                s3 = fetch(1);
                if (((s2 >> s1) & 1) != (op & 1)) {
                    r[PC] += s3;
                }
                break;
            case 0xe8: // blbs
            case 0xe9: // blbc
                s1 = getOperand(4);
                s2 = fetch(1);
                if ((s1 & 1) != (op & 1)) {
                    r[PC] += s2;
                }
                break;
            case 0xf4: // sobgeq
                s1 = peekOperand(4);
                d = setOperand(4, s1 - 1);
                setNZVC(d < 0, d == 0, s1 < 0 && d >= 0, c);
                s2 = fetch(1);
                if (d >= 0) {
                    r[PC] += s2;
                }
                break;
            case 0xf5: // sobgtr
                s1 = peekOperand(4);
                d = setOperand(4, s1 - 1);
                setNZVC(d < 0, d == 0, s1 < 0 && d >= 0, c);
                s2 = fetch(1);
                if (d > 0) {
                    r[PC] += s2;
                }
                break;
            case 0xf1: // acbl
                size = 4;
            case 0x9d: // acbb
            case 0x3d: // acbw
                s1 = getOperand(size);
                s2 = getOperand(size);
                s3 = peekOperand(size);
                d = setOperand(size, s3 + s2);
                setNZVC(d < 0, d == 0,
                        (s2 < 0) == (s3 < 0) && (s3 < 0) != (d < 0),
                        c);
                tmp = fetch(2);
                if ((s2 >= 0 && d < s1) || (s2 < 0 && d >= s1)) {
                    r[PC] += tmp;
                }
                break;
            case 0x8f: // caseb
            case 0xaf: // casew
            case 0xcf: // casel
                s1 = getOperand(size);
                s2 = getOperand(size);
                s3 = getOperand(size);
                tmp = s1 - s2;
                if (0 <= tmp && tmp <= s3) {
                    r[PC] += get(r[PC] + (tmp << 1), 2);
                } else {
                    r[PC] += (s3 + 1) << 1;
                }
                d = tmp - s3;
                setNZVC(d < 0, d == 0, false,
                        Integer.compareUnsigned(tmp, d) < 0);
                break;
            case 0x99: // cvtbw
                s1 = getOperand(1);
                d = setOperand(2, s1);
                setNZVC(d < 0, d == 0, s1 != d, false);
                break;
            case 0x98: // cvtbl
                s1 = getOperand(1);
                d = setOperand(4, s1);
                setNZVC(d < 0, d == 0, s1 != d, false);
                break;
            case 0x33: // cvtwb
                s1 = getOperand(2);
                d = setOperand(1, s1);
                setNZVC(d < 0, d == 0, s1 != d, false);
                break;
            case 0x32: // cvtwl
                s1 = getOperand(2);
                d = setOperand(4, s1);
                setNZVC(d < 0, d == 0, s1 != d, false);
                break;
            case 0xf6: // cvtlb
                s1 = getOperand(4);
                d = setOperand(1, s1);
                setNZVC(d < 0, d == 0, s1 != d, false);
                break;
            case 0xf7: // cvtlw
                s1 = getOperand(4);
                d = setOperand(2, s1);
                setNZVC(d < 0, d == 0, s1 != d, false);
                break;
            case 0xf9: // cvtlp
                s1 = getOperand(4);
                s2 = getOperand(2);
                s3 = getAddress(1);
                cvtlp(s1, s2, s3);
                break;
            case 0x38: // editpc
                s1 = getOperand(2);
                s2 = getAddress(1);
                s3 = getAddress(1);
                editpc(s1, s2, s3, getAddress(1));
                break;
            case 0x9a: // movzbl
                s1 = getOperand(1);
                setOperand(4, Byte.toUnsignedInt((byte) s1));
                setNZVC(false, s1 == 0, false, c);
                break;
            case 0x9b: // movzbw
                s1 = getOperand(1);
                setOperand(2, Byte.toUnsignedInt((byte) s1));
                setNZVC(false, s1 == 0, false, c);
                break;
            case 0x3c: // movzwl
                s1 = getOperand(2);
                setOperand(4, Short.toUnsignedInt((short) s1));
                setNZVC(false, s1 == 0, false, c);
                break;
            case 0xee: // extv
                s1 = getOperand(4);
                s2 = getOperand(1);
                s3 = getOperand(1);
                d = setOperand(4, s2 == 0 ? 0 : s3 << (32 - s1 - s2) >> (32 - s2));
                setNZVC(d < 0, d == 0, false, false);
                break;
            case 0xef: // extzv
                s1 = getOperand(4);
                s2 = getOperand(1);
                s3 = getOperand(1);
                d = setOperand(4, s2 == 0 ? 0 : (s3 >> s1) & ((1 << s2) - 1));
                setNZVC(d < 0, d == 0, false, false);
                break;
            case 0x80: // addb2
            case 0xa0: // addw2
            case 0xc0: // addl2
            case 0x81: // addb3
            case 0xa1: // addw3
            case 0xc1: // addl3
                s1 = getOperand(size);
                s2 = (op & 1) == 0 ? peekOperand(size) : getOperand(size);
                d = setOperand(size, s2 + s1);
                setNZVC(d < 0, d == 0,
                        (s1 < 0) == (s2 < 0) && (s2 < 0) != (d < 0),
                        Integer.compareUnsigned(s2, d) > 0);
                break;
            case 0x82: // subb2
            case 0xa2: // subw2
            case 0xc2: // subl2
            case 0x83: // subb3
            case 0xa3: // subw3
            case 0xc3: // subl3
                s1 = getOperand(size);
                s2 = (op & 1) == 0 ? peekOperand(size) : getOperand(size);
                d = setOperand(size, s2 - s1);
                setNZVC(d < 0, d == 0,
                        (s1 < 0) != (s2 < 0) && (s2 < 0) != (d < 0),
                        Integer.compareUnsigned(s2, d) < 0);
                break;
            case 0x90: // movb
            case 0xb0: // movw
            case 0xd0: // movl
                d = setOperand(size, getOperand(size));
                setNZVC(d < 0, d == 0, false, c);
                break;
            case 0x91: // cmpb
            case 0xb1: // cmpw
            case 0xd1: // cmpl
                s1 = getOperand(size);
                s2 = getOperand(size);
                d = s1 - s2;
                setNZVC(d < 0, d == 0,
                        (s1 < 0) != (s2 < 0) && (s1 < 0) != (d < 0),
                        Integer.compareUnsigned(s1, d) < 0);
                break;
            case 0x94: // clrb
            case 0xb4: // clrw
            case 0xd4: // clrl / clrf
            case 0x7c: // clrq / clrd
                setOperand(size, 0);
                setNZVC(false, true, false, c);
                break;
            case 0x95: // tstb
            case 0xb5: // tstw
            case 0xd5: // tstl
                s1 = getOperand(size);
                setNZVC(s1 < 0, s1 == 0, false, false);
                break;
            case 0x96: // incb
            case 0xb6: // incw
            case 0xd6: // incl
                s1 = peekOperand(size);
                d = setOperand(size, s1 + 1);
                setNZVC(d < 0, d == 0, s1 >= 0 && d < 0, d == 0);
                break;
            case 0x97: // decb
            case 0xb7: // decw
            case 0xd7: // decl
                s1 = peekOperand(size);
                d = setOperand(size, s1 - 1);
                setNZVC(d < 0, d == 0, s1 < 0 && d >= 0, s1 == 0);
                break;
            case 0x9e: // movab
            case 0x3e: // movaw
            case 0xde: // moval
                s1 = setOperand(4, getAddress(size));
                setNZVC(s1 < 0, s1 == 0, false, c);
                break;
            case 0x9f: // pushab
            case 0x3f: // pushaw
            case 0xdf: // pushal
                push(4, s1 = getAddress(size));
                setNZVC(s1 < 0, s1 == 0, false, c);
                break;
            case 0xdd: // pushl
                push(4, s1 = getOperand(4));
                setNZVC(s1 < 0, s1 == 0, false, c);
                break;
            case 0x88: // bisb2
            case 0xa8: // bisw2
            case 0xc8: // bisl2
            case 0x89: // bisb3
            case 0xa9: // bisw3
            case 0xc9: // bisl3
                s1 = getOperand(size);
                s2 = (op & 1) == 0 ? peekOperand(size) : getOperand(size);
                d = setOperand(size, s2 | s1);
                setNZVC(d < 0, d == 0, false, c);
                break;
            case 0x8a: // bicb2
            case 0xaa: // bicw2
            case 0xca: // bicl2
            case 0x8b: // bicb3
            case 0xab: // bicw3
            case 0xcb: // bicl3
                s1 = getOperand(size);
                s2 = (op & 1) == 0 ? peekOperand(size) : getOperand(size);
                d = setOperand(size, s2 & ~s1);
                setNZVC(d < 0, d == 0, false, c);
                break;
            case 0x93: // bitb
            case 0xb3: // bitw
            case 0xd3: // bitl
                s1 = getOperand(size);
                s2 = getOperand(size);
                tmp = s1 & s2;
                setNZVC(tmp < 0, tmp == 0, false, c);
                break;
            case 0x8e: // mnegb
            case 0xae: // mnegw
            case 0xce: // mnegl
                s1 = getOperand(size);
                d = setOperand(size, -s1);
                setNZVC(d < 0, d == 0, s1 == d, d != 0);
                break;
            case 0x3a: // locc
                s1 = getOperand(1);
                r[0] = getOperand(2);
                r[1] = getAddress(1);
                while (r[0] != 0 && mem[r[1]] != s1) {
                    --r[0];
                    ++r[1];
                }
                setNZVC(false, r[0] == 0, false, false);
                break;
            case 0x3b: // skpc
                s1 = getOperand(1);
                r[0] = getOperand(2);
                r[1] = getAddress(1);
                while (r[0] != 0 && mem[r[1]] == s1) {
                    --r[0];
                    ++r[1];
                }
                setNZVC(false, r[0] == 0, false, false);
                break;
            default:
                throw error("%08x: unknown opcode %02x", r[PC] - 1, op);
        }
    }

    public final static String[] syscalls = {
        "indir", "exit", "fork", "read", "write", "open", "close", "wait",
        "creat", "link", "unlink", "exec", "chdir", "time", "mknod", "chmod",
        "chown", "break", "stat", "seek", "getpid", "mount", "umount", "setuid",
        "getuid", "stime", "ptrace", "alarm", "fstat", "pause", "utime", "stty",
        "gtty", "access", "nice", "ftime", "sync", "kill", "switch", "setpgrp",
        "tell", "dup", "pipe", "times", "prof", "tiu", "setgid", "getgid",
        "sig", "(reserved)", "(reserved)", "sysacct", "sysphys", "syslock", "ioctl", "reboot",
        "mpxchan", "(reserved)", "(reserved)", "exece", "umask", "chroot"};

    public void syscall() throws Exception {
        int sc = fetch();
        if (mode >= 1) {
            System.err.println("[syscall] " + syscalls[sc] + "(" + getArgs() + ")");
        }
        switch (sc) {
            case 1: // exit
                System.exit(buf.getInt(r[AP] + 4));
                return;
            case 4: // write
                System.out.print(getString(buf.getInt(r[AP] + 8), buf.getInt(r[AP] + 12)));
                return;
            case 6: // close
                return;
            case 0x36: // ioctl
            {
                int fd = buf.getInt(r[AP] + 4);
                switch (buf.getInt(r[AP] + 8)) {
                    case 0x7408: // gtty
                        r[0] = 0 <= fd && fd <= 2 ? 0 : -1;
                        return;
                }
                break;
            }
        }
        throw error("%08x: unknown syscall %02x", r[PC] - 1, sc);
    }
}

public class Main {

    public static void main(String[] args) {
        boolean disasm = false;
        int mode = 0;
        String aout = null;
        String[] args2 = null;
        OUTER:
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            switch (arg) {
                case "-d":
                    disasm = true;
                    break;
                case "-s":
                    mode = 1;
                    break;
                case "-v":
                    mode = 2;
                    break;
                default:
                    aout = arg;
                    args2 = Arrays.copyOfRange(args, i, args.length);
                    break OUTER;
            }
        }
        if (aout == null) {
            System.err.println("usage: vaxrun [-d|-v] a.out [...]");
            System.err.println("    -d: disasm");
            System.err.println("    -v: verbose (debug mode)");
            System.err.println("    -s: syscall (debug mode)");
            System.exit(1);
        }
        try {
            VAX vax = new VAX(new AOut(aout), args2);
            if (disasm) {
                vax.disasm(System.out);
            } else {
                vax.run(mode);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
