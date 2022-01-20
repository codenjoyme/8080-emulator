package spec;

import static spec.WordMath.merge;

public interface Reg {

    int get();

    void set(int word);

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
        };
    }
}