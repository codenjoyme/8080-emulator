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

    protected Accessor accessor;

    private int A = 0;
    private int HL = 0;
    private int BC = 0;
    private int DE = 0;

    private boolean ts = false;
    private boolean tz = false;
    private boolean th = false;
    private boolean tp = false;
    private boolean tc = false;

    private int SP = 0;
    private int PC = START_POINT;

    public Reg rBC = new Reg() {
        @Override
        public int get() {
            return BC();
        }
        @Override
        public void set(int word) {
            BC(word);
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
    };

    public Registry(Accessor accessor) {
        this.accessor = accessor;
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

    public Reg reg(int index) {
        switch (index) {
            case 0: return rBC;
            case 1: return rDE;
            case 2: return rHL;
            case 3: return rSP;
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
        return accessor.peekb(HL());
    }

    public void M(int bite) {
        accessor.pokeb(HL(), bite);
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
                "BC: 0x%04X\n" +
                        "DE: 0x%04X\n" +
                        "HL: 0x%04X\n" +
                        "AF: 0x%04X\n" +
                        "SP: 0x%04X\n" +
                        "PC: 0x%04X\n",
                BC(),
                DE(),
                HL(),
                AF(),
                SP(),
                PC());
    }

    public String toStringDetails() {
        return String.format(
                "BC:   0x%04X\n" +
                        "DE:   0x%04X\n" +
                        "HL:   0x%04X\n" +
                        "AF:   0x%04X\n" +
                        "SP:   0x%04X\n" +
                        "PC:   0x%04X\n" +
                        "B,C:  0x%02X 0x%02X\n" +
                        "D,E:  0x%02X 0x%02X\n" +
                        "H,L:  0x%02X 0x%02X\n" +
                        "M:    0x%02X\n" +
                        "A,F:  0x%02X 0x%02X\n" +
                        "        76543210   76543210\n" +
                        "SP:   0b%s 0b%s\n" +
                        "PC:   0b%s 0b%s\n" +
                        "        76543210\n" +
                        "B:    0b%s\n" +
                        "C:    0b%s\n" +
                        "D:    0b%s\n" +
                        "E:    0b%s\n" +
                        "H:    0b%s\n" +
                        "L:    0b%s\n" +
                        "M:    0b%s\n" +
                        "A:    0b%s\n" +
                        "        sz0h0p1c\n" +
                        "F:    0b%s\n" +
                        "ts:   %s\n" +
                        "tz:   %s\n" +
                        "th:   %s\n" +
                        "tp:   %s\n" +
                        "tc:   %s\n",
                BC(),
                DE(),
                HL(),
                AF(),
                SP(),
                PC(),
                B(), C(),
                D(), E(),
                H(), L(),
                M(),
                A(), F(),
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

    public Accessor accessor() {
        return accessor;
    }
}