package spec;

import static spec.CPU.inc16;
import static spec.WordMath.hi;
import static spec.WordMath.lo;

public interface Accessor {

    boolean interrupt();

    void outb(int port, int bite);

    int peekb(int addr);

    void pokeb(int addr, int bite);

    default void pokew(int addr, int word) {
        pokeb(addr, lo(word));
        // увеличиваем адресс на 1 и если он превысил 0xFFFF, то делаем его равным 0x0000
        addr = inc16(addr);
        pokeb(addr, hi(word));
    }
}