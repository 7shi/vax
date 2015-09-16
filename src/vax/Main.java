// This file is licensed under the CC0.
package vax;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Paths;

class Memory {

    protected final String path;
    protected final byte[] text;
    protected final ByteBuffer buf;
    public int pc;

    public Memory(String path) throws IOException {
        this.path = path;
        text = java.nio.file.Files.readAllBytes(Paths.get(path));
        buf = ByteBuffer.wrap(text).order(ByteOrder.LITTLE_ENDIAN);
    }

    public int fetch() {
        return Byte.toUnsignedInt(text[pc++]);
    }

    public int fetch(int n) {
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

    public int fetchSigned(VAXType t) {
        int ret = 0;
        switch (t) {
            case BYTE:
            case RELB:
                ret = text[pc++];
                break;
            case WORD:
            case RELW:
                ret = buf.getShort(pc);
                pc += 2;
                break;
            case LONG:
                ret = buf.getInt(pc);
                pc += 4;
                break;
        }
        return ret;
    }

    public String fetchHex(VAXType t) {
        int len = VAX.getLength(t);
        int[] bs = new int[len];
        for (int i = 0; i < len; ++i) {
            bs[i] = fetch();
        }
        StringBuilder sb = new StringBuilder("0x");
        for (int i = len - 1; i >= 0; --i) {
            sb.append(String.format("%02x", bs[i]));
        }
        sb.append(VAX.getValueSuffix(t));
        return sb.toString();
    }

    public void output(PrintStream out, int pc, int len, String asm) {
        String fmt = text.length < 0x1000 ? "%4x:\t" : "%8x:\t";
        for (int i = 0;; ++i) {
            if (i == len) {
                if (i <= 4) {
                    for (; i < 4; ++i) {
                        out.print("   ");
                    }
                    out.printf("\t%s", asm);
                }
                out.println();
                break;
            } else if ((i & 3) == 0) {
                if (i == 4) {
                    out.printf("\t%s", asm);
                }
                if (i > 0) {
                    out.println();
                }
                out.printf(fmt, pc + i);
            }
            out.printf("%02x ", text[pc + i]);
        }
    }
}

enum VAXType {

    BYTE, WORD, LONG, QWORD, OWORD,
    FFLOAT, DFLOAT, GFLOAT, HFLOAT, RELB, RELW
}

class VAX {

    private static final int[] typeLen = {1, 2, 4, 8, 16, 4, 8, 8, 16};
    private static final String[] typeSfx = {
        "", "", "", "", "",
        " [f-float]", " [d-float]", " [g-float]", " [h-float]"
    };
    private static final VAXType[] opType = {
        VAXType.FFLOAT, VAXType.DFLOAT,
        VAXType.BYTE, VAXType.WORD, VAXType.LONG
    };
    private static final String sfx = "bwlqofdgh12";

    public static int getLength(VAXType t) {
        return typeLen[t.ordinal()];
    }

    public static String getValueSuffix(VAXType t) {
        return typeSfx[t.ordinal()];
    }

    public static VAXType fromOp(int op) {
        return opType[(op - 0x40) >> 5];
    }

    public static char getSuffix(VAXType t) {
        return sfx.charAt(t.ordinal());
    }

    public static VAXType fromSuffix(char ch) {
        return VAXType.values()[sfx.indexOf(ch)];
    }
}

class VAXOp {

    private final String mne;
    private final VAXType[] types;

    public VAXOp(String mne) {
        this.mne = mne;
        types = new VAXType[0];
    }

    public VAXOp(String mne, VAXType type, int count) {
        this.mne = mne;
        types = new VAXType[count];
        for (int i = 0; i < count; ++i) {
            types[i] = type;
        }
    }

    public VAXOp(String mne, VAXType type, int count, VAXType rel) {
        this(mne, type, count + 1);
        types[count] = rel;
    }

    public String read(Disasm dis) {
        StringBuilder sb = new StringBuilder(mne);
        for (int i = 0; i < types.length; ++i) {
            sb.append(i == 0 ? " " : ",");
            VAXType t = types[i];
            switch (t) {
                case RELB:
                case RELW: {
                    int r = dis.fetchSigned(t);
                    sb.append(String.format("0x%x", dis.pc + r));
                    break;
                }
                default:
                    sb.append(dis.getOpr(t));
                    break;
            }
        }
        return sb.toString();
    }
}

class VAXOps {

    private static VAXOp op(String mne) {
        return new VAXOp(mne);
    }

    private static VAXOp b(String mne, int c) {
        return new VAXOp(mne, VAXType.BYTE, c);
    }

    private static VAXOp bw(String mne, int c) {
        return new VAXOp(mne, VAXType.BYTE, c, VAXType.RELW);
    }

    private static VAXOp w(String mne, int c) {
        return new VAXOp(mne, VAXType.WORD, c);
    }

    private static VAXOp ww(String mne, int c) {
        return new VAXOp(mne, VAXType.WORD, c, VAXType.RELW);
    }

    private static VAXOp l(String mne, int c) {
        return new VAXOp(mne, VAXType.LONG, c);
    }

    private static VAXOp lb(String mne, int c) {
        return new VAXOp(mne, VAXType.LONG, c, VAXType.RELB);
    }

    private static VAXOp lw(String mne, int c) {
        return new VAXOp(mne, VAXType.LONG, c, VAXType.RELW);
    }

    private static VAXOp q(String mne, int c) {
        return new VAXOp(mne, VAXType.QWORD, c);
    }

    private static VAXOp o(String mne, int c) {
        return new VAXOp(mne, VAXType.OWORD, c);
    }

    private static VAXOp f(String mne, int c) {
        return new VAXOp(mne, VAXType.FFLOAT, c);
    }

    private static VAXOp fw(String mne, int c) {
        return new VAXOp(mne, VAXType.FFLOAT, c, VAXType.RELW);
    }

    private static VAXOp d(String mne, int c) {
        return new VAXOp(mne, VAXType.DFLOAT, c);
    }

    private static VAXOp dw(String mne, int c) {
        return new VAXOp(mne, VAXType.DFLOAT, c, VAXType.RELW);
    }

    private static VAXOp g(String mne, int c) {
        return new VAXOp(mne, VAXType.GFLOAT, c);
    }

    private static VAXOp gw(String mne, int c) {
        return new VAXOp(mne, VAXType.GFLOAT, c, VAXType.RELW);
    }

    private static VAXOp h(String mne, int c) {
        return new VAXOp(mne, VAXType.HFLOAT, c);
    }

    private static VAXOp hw(String mne, int c) {
        return new VAXOp(mne, VAXType.HFLOAT, c, VAXType.RELW);
    }

    private static VAXOp brb(String mne) {
        return new VAXOp(mne, VAXType.RELB, 1);
    }

    private static VAXOp brw(String mne) {
        return new VAXOp(mne, VAXType.RELW, 1);
    }

    private static VAXOp cvt(String t) {
        VAXType t1 = VAX.fromSuffix(t.charAt(t.length() - 2));
        VAXType t2 = VAX.fromSuffix(t.charAt(t.length() - 1));
        return new VAXOp("cvt" + t, t1, 1, t2);
    }

    private static VAXOp word(int w) {
        return new VAXOp(String.format(".word 0x%x", w));
    }

    private static final VAXOp[][] ops = {
        new VAXOp[]{
            /* 00 */op("halt"), op("nop"), op("rei"), op("bpt"), op("ret"), op("rsb"), op("ldpctx"), op("svpctx"),
            /* 08 */ w("cvtps", 4), w("cvtsp", 4), l("index", 6), b("crc", 4), b("prober", 3), b("probew", 3), b("insque", 2), b("remque", 2),
            /* 10 */ brb("bsbb"), brb("brb"), brb("bneq"), brb("beql"), brb("bgtr"), brb("bleq"), b("jsb", 1), b("jmp", 1),
            /* 18 */ brb("bgeq"), brb("blss"), brb("bgtru"), brb("blequ"), brb("bvc"), brb("bvs"), brb("bcc"), brb("blssu"),
            /* 20 */ w("addp4", 4), w("addp6", 6), w("subp4", 4), w("subp6", 6), w("cvtpt", 5), w("mulp", 6), w("cvttp", 5), w("divp", 6),
            /* 28 */ w("movc3", 3), w("cmpc3", 3), w("scanc", 4), w("spanc", 4), w("movc5", 5), w("cmpc5", 5), w("movtc", 6), w("movtuc", 6),
            /* 30 */ brw("bsbw"), brw("brw"), cvt("wl"), cvt("wb"), w("movp", 3), w("cmpp3", 3), w("cvtpl", 3), w("cmpp4", 4),
            /* 38 */ w("editpc", 4), w("matchc", 4), b("locc", 3), b("skpc", 3), w("movzwl", 2), ww("acbw", 3), w("movaw", 2), w("pushaw", 1),
            /* 40 */ f("addf2", 2), f("addf3", 3), f("subf2", 2), f("subf3", 3), f("mulf2", 2), f("mulf3", 3), f("divf2", 2), f("divf3", 3),
            /* 48 */ cvt("fb"), cvt("fw"), cvt("fl"), cvt("rfl"), cvt("bf"), cvt("wf"), cvt("lf"), fw("acbf", 3),
            /* 50 */ f("movf", 2), f("cmpf", 2), f("mnegf", 2), f("tstf", 1), f("emodf", 5), f("polyf", 3), cvt("fd"), null,
            /* 58 */ w("adawi", 2), null, null, null, b("insqhi", 2), b("insqti", 2), q("remqhi", 2), q("remqti", 2),
            /* 60 */ d("addd2", 2), d("addd3", 3), d("subd2", 2), d("subd3", 3), d("muld2", 2), d("muld3", 3), d("divd2", 2), d("divd3", 3),
            /* 68 */ cvt("db"), cvt("dw"), cvt("dl"), cvt("rdl"), cvt("bd"), cvt("wd"), cvt("ld"), dw("acbd", 3),
            /* 70 */ d("movd", 2), d("cmpd", 2), d("mnegd", 2), d("tstd", 1), d("emodd", 5), d("polyd", 3), cvt("df"), null,
            /* 78 */ b("ashl", 3), b("ashq", 3), l("emul", 4), l("ediv", 4), d("clrd", 1), q("movq", 2), q("movaq", 2), q("pushaq", 1),
            /* 80 */ b("addb2", 2), b("addb3", 3), b("subb2", 2), b("subb3", 3), b("mulb2", 2), b("mulb3", 3), b("divb2", 2), b("divb3", 3),
            /* 88 */ b("bisb2", 2), b("bisb3", 3), b("bicb2", 2), b("bicb3", 3), b("xorb2", 2), b("xorb3", 3), b("mnegb", 2), b("caseb", 3),
            /* 90 */ b("movb", 2), b("cmpb", 2), b("mcomb", 2), b("bitb", 2), b("clrb", 1), b("tstb", 1), b("incb", 1), b("decb", 1),
            /* 98 */ cvt("bl"), cvt("bw"), b("movzbl", 2), b("movzbw", 2), b("rotl", 3), bw("acbb", 3), b("movab", 2), b("pushab", 1),
            /* a0 */ w("addw2", 2), w("addw3", 3), w("subw2", 2), w("subw3", 3), w("mulw2", 2), w("mulw3", 3), w("divw2", 2), w("divw3", 3),
            /* a8 */ w("bisw2", 2), w("bisw3", 3), w("bicw2", 2), w("bicw3", 3), w("xorw2", 2), w("xorw3", 3), w("mnegw", 2), w("casew", 3),
            /* b0 */ w("movw", 2), w("cmpw", 2), w("mcomw", 2), w("bitw", 2), w("clrw", 1), w("tstw", 1), w("incw", 1), w("decw", 1),
            /* b8 */ w("bispsw", 1), w("bicpsw", 1), w("popr", 1), w("pushr", 1), w("chmk", 1), w("chme", 1), w("chms", 1), w("chmu", 1),
            /* c0 */ l("addl2", 2), l("addl3", 3), l("subl2", 2), l("subl3", 3), l("mull2", 2), l("mull3", 3), l("divl2", 2), l("divl3", 3),
            /* c8 */ l("bisl2", 2), l("bisl3", 3), l("bicl2", 2), l("bicl3", 3), l("xorl2", 2), l("xorl3", 3), l("mnegl", 2), l("casel", 3),
            /* d0 */ l("movl", 2), l("cmpl", 2), l("mcoml", 2), l("bitl", 2), f("clrf", 1), l("tstl", 1), l("incl", 1), l("decl", 1),
            /* d8 */ l("adwc", 2), l("sbwc", 2), l("mtpr", 2), l("mfpr", 2), l("movpsl", 1), l("pushl", 1), l("moval", 2), l("pushal", 1),
            /* e0 */ lb("bbs", 2), lb("bbc", 2), lb("bbss", 2), lb("bbcs", 2), lb("bbsc", 2), lb("bbcc", 2), lb("bbssi", 2), lb("bbcci", 2),
            /* e8 */ lb("blbs", 1), lb("blbc", 1), l("ffs", 4), l("ffc", 4), l("cmpv", 4), l("cmpzv", 4), l("extv", 4), l("extzv", 4),
            /* f0 */ l("insv", 4), lw("acbl", 3), lb("aoblss", 2), lb("aobleq", 2), lb("sobgeq", 1), lb("sobgtr", 1), cvt("lb"), cvt("lw"),
            /* f8 */ b("ashp", 6), l("cvtlp", 3), b("callg", 2), l("calls", 2), op("xfc"), null, null, null
        }, new VAXOp[0x100], new VAXOp[0x100], new VAXOp[0x100]
    };

    static {
        System.arraycopy(new VAXOp[]{
            /* fd40 */g("addg2", 2), g("addg3", 3), g("subg2", 2), g("subg3", 3), g("mulg2", 2), g("mulg3", 3), g("divg2", 2), g("divg3", 3),
            /* fd48 */ cvt("gb"), cvt("gw"), cvt("gl"), cvt("rgl"), cvt("bg"), cvt("wg"), cvt("lg"), gw("acbg", 3),
            /* fd50 */ g("movg", 2), g("cmpg", 2), g("mnegg", 2), g("tstg", 1), g("emodg", 5), g("polyg", 3), cvt("gh"), null,
            /* fd58 */ null, null, null, null, null, null, null, null,
            /* fd60 */ h("addh2", 2), h("addh3", 3), h("subh2", 2), h("subh3", 3), h("mulh2", 2), h("mulh3", 3), h("divh2", 2), h("divh3", 3),
            /* fd68 */ cvt("hb"), cvt("hw"), cvt("hl"), cvt("rhl"), cvt("bh"), cvt("wh"), cvt("lh"), hw("acbh", 3),
            /* fd70 */ h("movh", 2), h("cmph", 2), h("mnegh", 2), h("tsth", 1), h("emodh", 5), h("polyh", 3), cvt("hg"), null,
            /* fd78 */ null, null, null, null, h("clrh", 1), o("movo", 2), h("movah", 2), h("pushah", 1)
        }, 0, ops[1], 0x40, 0x40);

        System.arraycopy(new VAXOp[]{/* fd32 */cvt("dh"), cvt("gf")}, 0, ops[1], 0x32, 2);
        System.arraycopy(new VAXOp[]{/* fd98 */cvt("fh"), cvt("fg")}, 0, ops[1], 0x98, 2);
        System.arraycopy(new VAXOp[]{/* fdf6 */cvt("hf"), cvt("hd")}, 0, ops[1], 0xf6, 2);
        System.arraycopy(new VAXOp[]{/* fffd */l("bugl", 1), w("bugw", 1)}, 0, ops[3], 0xfd, 2);

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 0x100; ++j) {
                if (ops[i][j] == null) {
                    ops[i][j] = word((0xfc + i) << 8 | j);
                }
            }
        }
    }

    private static final VAXOp[] words = new VAXOp[0x10000];

    public static VAXOp fetch(Disasm dis) {
        int b = dis.fetch();
        if (b >= 0xfd) {
            return ops[b - 0xfc][dis.fetch()];
        }
        VAXOp op = ops[0][b];
        if (op != null) {
            return op;
        }
        int bb = b << 8 | dis.fetch();
        if (words[bb] == null) {
            words[bb] = word(bb);
        }
        return words[bb];
    }
}

class Disasm extends Memory {

    private static final String[] REGS = {
        "r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7",
        "r8", "r9", "r10", "r11", "ap", "fp", "sp", "pc"
    };
    private static final VAXType[] atype = {
        VAXType.BYTE, VAXType.WORD, VAXType.LONG
    };

    public Disasm(String path) throws IOException {
        super(path);
    }

    String getOpr(VAXType t) {
        int b = fetch(), b1 = b >> 4, b2 = b & 15;
        String r = REGS[b2];
        switch (b1) {
            case 0:
            case 1:
            case 2:
            case 3:
                return String.format("$0x%x%s", b, VAX.getValueSuffix(t));
            case 4:
                return getOpr(t) + "[" + r + "]";
            case 5:
                return r;
            case 6:
                return "(" + r + ")";
            case 7:
                return "-(" + r + ")";
            case 8:
                if (b2 == 15) {
                    return "$" + fetchHex(t);
                } else {
                    return "(" + r + ")+";
                }
            case 9:
                if (b2 == 15) {
                    return "*" + fetchHex(VAXType.LONG);
                } else {
                    return "@(" + r + ")+"; // @?
                }
            case 0xa:
            case 0xb:
            case 0xc:
            case 0xd:
            case 0xe:
            case 0xf: {
                String prefix = (b1 & 1) == 1 ? "*" : "";
                int disp = fetchSigned(atype[(b1 - 0xa) >> 1]);
                if (b2 == 15) {
                    return String.format("%s0x%x", prefix, pc + disp);
                } else {
                    return String.format("%s0x%x(%s)", prefix, disp, r); // 符号?
                }
            }
            default:
                return "???";
        }
    }

    String disasm1() {
        return VAXOps.fetch(this).read(this);
    }

    void disasm(PrintStream out) {
        out.println(path);
        while (pc < text.length) {
            int oldpc = pc;
            String asm = disasm1();
            output(out, oldpc, pc - oldpc, asm);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                //new Disasm("samples/unix.text").disasm(System.out);
                new Disasm("samples/echo.text").disasm(System.out);
            } else {
                for (int i = 0; i < args.length; ++i) {
                    if (i > 0) {
                        System.out.println();
                    }
                    new Disasm(args[i]).disasm(System.out);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
