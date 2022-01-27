package spec;

import static spec.WordMath.*;

public interface Data {

    int in8(int port);

    void out8(int port, int bite);

    int read8(int addr);

    void write8(int addr, int bite);

    default void write16(int addr, int word) {
        write8(addr, lo(word));
        addr = inc16(addr);
        write8(addr, hi(word));
    }

    default void push16(Reg reg, int word) {
        reg.set(word(reg.get() - 2));
        write16(reg.get(), word);
    }

    default int read16(int addr) {
        int lo = read8(addr);
        addr = inc16(addr);
        int hi = read8(addr) << 8;
        return hi | lo;
    }

    default int read8(Reg r) {
        int addr = r.get();
        int bite = read8(addr);
        r.set(inc16(addr));
        return bite;
    }

    default int read16(Reg r) {
        int lo = read8(r);
        int hi = read8(r) << 8;
        return hi | lo;
    }
}