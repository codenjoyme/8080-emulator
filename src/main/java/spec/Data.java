package spec;

import static spec.CPU.inc16;
import static spec.WordMath.hi;
import static spec.WordMath.lo;

public interface Data {

    boolean interrupt();

    void outb(int port, int bite);

    int peekb(int addr);

    void pokeb(int addr, int bite);

    default void pokew(int addr, int word) {
        pokeb(addr, lo(word));
        addr = inc16(addr);
        pokeb(addr, hi(word));
    }

    default int peekw(int addr) {
        int lo = peekb(addr);
        addr = inc16(addr);
        int hi = peekb(addr) << 8;
        return lo | hi;
    }

    default int peekwi(Reg r) {
        int result = peekb(r.get());
        r.set(inc16(r.get()));
        result |= peekb(r.get()) << 8;
        r.set(inc16(r.get()));
        return result;
    }
}