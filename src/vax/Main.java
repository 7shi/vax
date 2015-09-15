// This file is licensed under the CC0.
package vax;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Paths;

class Memory {

    protected final byte[] text;
    protected final ByteBuffer buf;
    public int pc;

    public Memory(String path) throws IOException {
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
                ret = text[pc++];
                break;
            case WORD:
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
                out.printf("%8x:\t", pc + i);
            }
            out.printf("%02x ", text[pc + i]);
        }
    }
}

enum VAXType {

    NONE, BYTE, WORD, LONG, QWORD, OWORD,
    FFLOAT, DFLOAT, GFLOAT, HFLOAT
}

class VAX {

    private static final int[] typeLen = {0, 1, 2, 4, 8, 16, 4, 8, 8, 16};
    private static final String[] typeSfx = {
        "", "", "", "", "", "",
        " [f-float]", " [d-float]", " [g-float]", " [h-float]"
    };
    private static final VAXType[] opType = {
        VAXType.FFLOAT, VAXType.DFLOAT,
        VAXType.BYTE, VAXType.WORD, VAXType.LONG
    };

    public static int getLength(VAXType t) {
        return typeLen[t.ordinal()];
    }

    public static String getValueSuffix(VAXType t) {
        return typeSfx[t.ordinal()];
    }

    public static String getSuffix(VAXType t) {
        return t.toString().substring(0, 1).toLowerCase();
    }

    public static VAXType fromOp(int op) {
        return opType[(op - 0x40) >> 5];
    }
}

class VAXOp {

    private final String mne;
    private final VAXType type, rel;
    private final int count;

    public VAXOp(String mne) {
        this(mne, VAXType.NONE, 0);
    }

    public VAXOp(String mne, VAXType type, int count) {
        this(mne, type, count, VAXType.NONE);
    }

    public VAXOp(String mne, VAXType type, int count, VAXType rel) {
        this.mne = mne;
        this.type = type;
        this.count = count;
        this.rel = rel;
    }

    public String read(Disasm dis) {
        StringBuilder sb = new StringBuilder(mne);
        for (int i = 0; i < count; ++i) {
            sb.append(i == 0 ? " " : ",");
            sb.append(dis.getOpr(type));
        }
        if (rel != VAXType.NONE) {
            sb.append(count == 0 ? " " : ",");
            int r = dis.fetchSigned(rel);
            sb.append(String.format("0x%x", dis.pc + r));
        }
        return sb.toString();
    }
}

class VAXOps {

    private static final String[] BR = {
        "bneq", "beql", "bgtr", "bleq", "", "",
        "bgeq", "blss", "bgtru", "blequ", "bvc", "bvs", "bcc", "bcs"
    };
    private static final VAXOp[] noops = new VAXOp[0x10000];

    private static VAXOp noop(int b1, int b2) {
        int b = b1 << 8 | b2;
        if (noops[b] == null) {
            noops[b] = new VAXOp(String.format(".word 0x%x", b));
        }
        return noops[b];
    }

    private static VAXOp opi23(int b, String mne) {
        int c = (b & 1) + 2;
        VAXType t = VAX.fromOp(b);
        return new VAXOp(mne + VAX.getSuffix(t) + c, t, c);
    }

    public static VAXOp fetch(Disasm dis) {
        int b = dis.fetch();
        switch (b) {
            case 0x00:
                return new VAXOp("halt");
            case 0x01:
                return new VAXOp("nop");
            case 0x04:
                return new VAXOp("ret");
            case 0x05:
                return new VAXOp("rsb");
            case 0x80:
            case 0x81:
            case 0xa0:
            case 0xa1:
            case 0xc0:
            case 0xc1:
                return opi23(b, "add");
            case 0x82:
            case 0x83:
            case 0xa2:
            case 0xa3:
            case 0xc2:
            case 0xc3:
                return opi23(b, "sub");
            case 0x84:
            case 0x85:
            case 0xa4:
            case 0xa5:
            case 0xc4:
            case 0xc5:
                return opi23(b, "mul");
            case 0x86:
            case 0x87:
            case 0xa6:
            case 0xa7:
            case 0xc6:
            case 0xc7:
                return opi23(b, "div");
            case 0x88:
            case 0x89:
            case 0xa8:
            case 0xa9:
            case 0xc8:
            case 0xc9:
                return opi23(b, "bis");
            case 0x8a:
            case 0x8b:
            case 0xaa:
            case 0xab:
            case 0xca:
            case 0xcb:
                return opi23(b, "bic");
            case 0x8c:
            case 0x8d:
            case 0xac:
            case 0xad:
            case 0xcc:
            case 0xcd:
                return opi23(b, "xor");
            case 0x90:
            case 0xb0:
            case 0xd0: {
                VAXType t = VAX.fromOp(b);
                return new VAXOp("mov" + VAX.getSuffix(t), t, 2);
            }
            case 0x12:
            case 0x13:
            case 0x14:
            case 0x15:
            case 0x18:
            case 0x19:
            case 0x1a:
            case 0x1b:
            case 0x1c:
            case 0x1d:
            case 0x1e:
            case 0x1f: {
                return new VAXOp(BR[b - 0x12], VAXType.NONE, 0, VAXType.BYTE);
            }
            case 0x16:
                return new VAXOp("jsb", VAXType.WORD, 1);
            case 0x17:
                return new VAXOp("jmp", VAXType.WORD, 1);
            case 0xfb:
                return new VAXOp("calls", VAXType.WORD, 2);
            case 0x98:
                return new VAXOp("cvtbl", VAXType.BYTE, 2);
            case 0x99:
                return new VAXOp("cvtbw", VAXType.BYTE, 2);
            case 0xdd:
                return new VAXOp("pushl", VAXType.LONG, 1);
            case 0xdf:
                return new VAXOp("pushal", VAXType.LONG, 1);
            default:
                return noop(b, dis.fetch());
        }
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
                return String.format("$0x%x", b);
            case 4:
                return getOpr(VAXType.LONG) + "[" + r + "]";
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
                    return prefix + fetchHex(t);
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
                int disp = fetchSigned(atype[(b1 - 0xa) >> 1]);
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

    String disasm1() {
        return VAXOps.fetch(this).read(this);
    }

    void disasm(PrintStream out) {
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
            //new Disasm("samples/unix.text").disasm();
            new Disasm("samples/echo.text").disasm(System.out);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
