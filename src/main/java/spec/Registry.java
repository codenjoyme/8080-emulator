package spec;

import static spec.Constants.*;
import static spec.WordMath.*;

public class Registry {

    public static final int T0c = x01; // Разряд Tc = 1, если был перенос или заем
    public static final int T11 = x02; // Всегда 1
    public static final int T2p = x04; // Разряд Tp = 1, если число единиц в результате четно
    public static final int T30 = x08; // Всегда 0
    public static final int T4h = x10; // Разряд Th = 1, если был перенос из старшей тетрады в младшую
    public static final int T50 = x20; // Всегда 0
    public static final int T6z = x40; // Разряд Tz = 1, если результат = 0
    public static final int T7s = x80; // Разряд Ts = 1, если результат отрицательный (первый бит результата = 1)

    protected Data data;

    private int A;
    private int HL;
    private int BC;
    private int DE;

    private boolean ts;
    private boolean tz;
    private boolean th;
    private boolean tp;
    private boolean tc;

    private int SP;
    private int PC;

    public Reg rAF = new Reg() {

        @Override
        public int get() {
            return AF();
        }

        @Override
        public void set(int word) {
            AF(word);
        }

        @Override
        public String toString() {
            return "AF=" + hex16(get());
        }
    };

    public Reg rBC = new Reg() {

        @Override
        public int get() {
            return BC();
        }

        @Override
        public void set(int word) {
            BC(word);
        }

        @Override
        public String toString() {
            return "BC=" + hex16(get());
        }
    };

    public Reg rDE = new Reg() {

        @Override
        public int get() {
            return DE();
        }

        @Override
        public void set(int word) {
            DE(word);
        }

        @Override
        public String toString() {
            return "DE=" + hex16(get());
        }
    };

    public Reg rHL = new Reg() {

        @Override
        public int get() {
            return HL();
        }

        @Override
        public void set(int word) {
            HL(word);
        }

        @Override
        public String toString() {
            return "HL=" + hex16(get());
        }
    };

    public Reg rPC = new Reg() {

        @Override
        public int get() {
            return PC();
        }

        @Override
        public void set(int word) {
            PC(word);
        }

        @Override
        public String toString() {
            return "PC=" + hex16(get());
        }
    };

    public Reg rSP = new Reg() {

        @Override
        public int get() {
            return SP();
        }

        @Override
        public void set(int word) {
            SP(word);
        }

        @Override
        public String toString() {
            return "SP=" + hex16(get());
        }
    };

    public Reg rM = new Reg() {

        @Override
        public int get() {
            int addr = HL();
            return data.read8(addr);
        }

        @Override
        public void set(int bite) {
            int addr = HL();
            data.write8(addr, bite);
        }

        @Override
        public String toString() {
            return String.format("M[%s]=%s",
                    hex16(Registry.this.HL()),
                    hex8(get()));
        }
    };

    public Reg rA = new Reg() {

        @Override
        public int get() {
            return A();
        }

        @Override
        public void set(int bite) {
            A(bite);
        }

        @Override
        public String toString() {
            return "A=" + hex8(get());
        }
    };

    public Registry(Data data) {
        this.data = data;
        reset();
    }

    public void reset() {
        PC(START_POINT);
        SP(0);
        A(0);
        F(0);
        BC(0);
        DE(0);
        HL(0);
    }

    public static boolean _SP = true;
    public static boolean _PSW = !_SP;

    public Reg reg16(int index, boolean spOrPsw) {
        switch (index) {
            case 0: return rBC;
            case 1: return rDE;
            case 2: return rHL;
            case 3: return (spOrPsw == _SP) ? rSP : rAF;
        }
        throw new UnsupportedOperationException("Unexpected registry index: " + index);
    }

    public Reg reg8(int index) {
        switch (index) {
            case 0: return rBC.hi();
            case 1: return rBC.lo();
            case 2: return rDE.hi();
            case 3: return rDE.lo();
            case 4: return rHL.hi();
            case 5: return rHL.lo();
            case 6: return rM;
            case 7: return rA;
        }
        throw new UnsupportedOperationException("Unexpected registry index: " + index);
    }

    public int AF() {
        return merge(A(), F());
    }

    public void AF(int word) {
        A(word >> 8);
        F(lo(word));
    }

    public int BC() {
        return BC;
    }

    public void BC(int word) {
        BC = word;
    }

    public int DE() {
        return DE;
    }

    public void DE(int word) {
        DE = word;
    }

    public int HL() {
        return HL;
    }

    public void HL(int word) {
        HL = word;
    }

    public int SP() {
        return SP;
    }

    public void SP(int word) {
        SP = word;
    }

    public int PC() {
        return PC;
    }

    public void PC(int word) {
        PC = word;
    }

    public int F() {
        return (ts ? T7s : 0) |
                (tz ? T6z : 0) |
                (false ? T50 : 0) |
                (th ? T4h : 0) |
                (false ? T30 : 0) |
                (tp ? T2p : 0) |
                (true ? T11 : 0) |
                (tc ? T0c : 0);
    }

    public void F(int bite) {
        ts = (bite & T7s) != 0;
        tz = (bite & T6z) != 0;
        th = (bite & T4h) != 0;
        tp = (bite & T2p) != 0;
        tc = (bite & T0c) != 0;
    }

    public int A() {
        return A;
    }

    public void A(int bite) {
        A = bite;
    }

    public int B() {
        return hi(BC);
    }

    public void B(int bite) {
        BC(merge(bite, C()));
    }

    public int M() {
        return data.read8(HL());
    }

    public void M(int bite) {
        data.write8(HL(), bite);
    }

    public int C() {
        return lo(BC());
    }

    public void C(int bite) {
        BC(merge(B(), bite));
    }

    public int D() {
        return hi(DE());
    }

    public void D(int bite) {
        DE(merge(bite, E()));
    }

    public int E() {
        return lo(DE());
    }

    public void E(int bite) {
        DE(merge(D(), bite));
    }

    public int H() {
        return hi(HL());
    }

    public void H(int bite) {
        HL(merge(bite, L()));
    }

    public int L() {
        return lo(HL());
    }

    public void L(int bite) {
        HL(merge(H(), bite));
    }

    // его часто суммируют, а потому тут этот метод
    public int tci() {
        return tc() ? 1 : 0;
    }

    public boolean tc() {
        return tc;
    }

    public void tc(boolean value) {
        tc = value;
    }

    public boolean ts() {
        return ts;
    }

    public void ts(boolean value) {
        ts = value;
    }

    public boolean tz() {
        return tz;
    }

    public void tz(boolean value) {
        tz = value;
    }

    public boolean th() {
        return th;
    }

    public void th(boolean value) {
        th = value;
    }

    public boolean tp() {
        return tp;
    }

    public void tp(boolean value) {
        tp = value;
    }

    public String toString() {
        return String.format(
                "BC: %s\n" +
                "DE: %s\n" +
                "HL: %s\n" +
                "AF: %s\n" +
                "SP: %s\n" +
                "PC: %s\n",
                hex16(BC()),
                hex16(DE()),
                hex16(HL()),
                hex16(AF()),
                hex16(SP()),
                hex16(PC()));
    }

    public String toStringDetails() {
        return String.format(
                "BC:  %s\n" +
                "DE:  %s\n" +
                "HL:  %s\n" +
                "AF:  %s\n" +
                "SP:  %s\n" +
                "PC:  %s\n" +
                "B,C: %s %s\n" +
                "D,E: %s %s\n" +
                "H,L: %s %s\n" +
                "M:   %s\n" +
                "A,F: %s %s\n" +
                "     76543210 76543210\n" +
                "SP:  %s %s\n" +
                "PC:  %s %s\n" +
                "     76543210\n" +
                "B:   %s\n" +
                "C:   %s\n" +
                "D:   %s\n" +
                "E:   %s\n" +
                "H:   %s\n" +
                "L:   %s\n" +
                "M:   %s\n" +
                "A:   %s\n" +
                "     sz0h0p1c\n" +
                "F:   %s\n" +
                "ts:  %s\n" +
                "tz:  %s\n" +
                "th:  %s\n" +
                "tp:  %s\n" +
                "tc:  %s\n",
                hex16(BC()),
                hex16(DE()),
                hex16(HL()),
                hex16(AF()),
                hex16(SP()),
                hex16(PC()),
                hex8(B()), hex8(C()),
                hex8(D()), hex8(E()),
                hex8(H()), hex8(L()),
                hex8(M()),
                hex8(A()), hex8(F()),
                bits(hi(SP())), bits(lo(SP())),
                bits(hi(PC())), bits(lo(PC())),
                bits(B()),
                bits(C()),
                bits(D()),
                bits(E()),
                bits(H()),
                bits(L()),
                bits(M()),
                bits(A()),
                bits(F()),
                ts(),
                tz(),
                th(),
                tp(),
                tc());
    }

    public Data data() {
        return data;
    }
}