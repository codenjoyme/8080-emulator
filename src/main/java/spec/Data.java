package spec;

import static spec.CPU.inc16;
import static spec.WordMath.hi;
import static spec.WordMath.lo;

public interface Data {

    boolean interrupt();

    void outb(int port, int bite);

    int readb(int addr);

    void writeb(int addr, int bite);

    default void writew(int addr, int word) {
        writeb(addr, lo(word));
        addr = inc16(addr);
        writeb(addr, hi(word));
    }

    default int readw(int addr) {
        int lo = readb(addr);
        addr = inc16(addr);
        int hi = readb(addr) << 8;
        return hi | lo;
    }

    default int readw(Reg r) {
        int lo = readb(r.get());
        r.set(inc16(r.get()));
        int hi = readb(r.get()) << 8;
        r.set(inc16(r.get()));
        return hi | lo;
    }
}