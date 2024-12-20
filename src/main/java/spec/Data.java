package spec;

import spec.math.Bites;

import static spec.math.WordMath.*;

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

    default int pop16(Reg reg) {
        return read16(reg);
    }

    default int read16(int addr) {
        int lo = read8(addr);
        addr = inc16(addr);
        int hi = read8(addr) << 8;
        return hi | lo;
    }

    default int read8(Reg reg) {
        int addr = reg.get();
        int bite = read8(addr);
        reg.set(inc16(addr));
        return bite;
    }

    default int read16(Reg reg) {
        int lo = read8(reg);
        int hi = read8(reg) << 8;
        return hi | lo;
    }

    default Bites read3x8(int addr) {
        Bites result = new Bites(3);
        result.set(0, read8(addr));

        addr = inc16(addr);
        result.set(1, read8(addr));

        addr = inc16(addr);
        result.set(2, read8(addr));
        return result;
    }
}