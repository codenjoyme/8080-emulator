package spec;

import spec.math.WordMath;

import static spec.math.WordMath.*;

public interface Reg {

    int get();

    void set(int word);

    default void inc16() {
        set(WordMath.inc16(get()));
    }

    default void dec16() {
        set(WordMath.dec16(get()));
    }

    default Reg hi() {
        return new Reg() {

            @Override
            public int get() {
                return WordMath.hi(Reg.this.get());
            }

            @Override
            public void set(int bite) {
                int lo = WordMath.lo(Reg.this.get());
                Reg.this.set(merge(bite, lo));
            }

            @Override
            public String toString() {
                return "hi(" + Reg.this + ")=" + hex8(get());
            }
        };
    }

    default Reg lo() {
        return new Reg() {

            @Override
            public int get() {
                return WordMath.lo(Reg.this.get());
            }

            @Override
            public void set(int bite) {
                int hi = WordMath.hi(Reg.this.get());
                Reg.this.set(merge(hi, bite));
            }

            @Override
            public String toString() {
                return "lo(" + Reg.this + ")=" + hex8(get());
            }
        };
    }
}