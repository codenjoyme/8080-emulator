package spec;

import static spec.WordMath.*;

public interface Data {

    boolean interrupt();

    void out8(int port, int bite);

    int read8(int addr);

    void write8(int addr, int bite);

    default void write16(int addr, int word) {
        write8(addr, lo(word));
        addr = inc16(addr);
        write8(addr, hi(word));
    }

    default int read16(int addr) {
        int lo = read8(addr);
        addr = inc16(addr);
        int hi = read8(addr) << 8;
        return hi | lo;
    }

    default int read16(Reg r) {
        int lo = read8(r.get());
        r.set(inc16(r.get()));
        int hi = read8(r.get()) << 8;
        r.set(inc16(r.get()));
        return hi | lo;
    }
}