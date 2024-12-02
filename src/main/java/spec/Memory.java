package spec;

import spec.math.Bites;

import static spec.math.WordMath.*;

public class Memory {

    protected Bites mem;

    public Memory(int size) {
        mem = new Bites(size);
    }

    public int read8(int addr) {
        return mem.get(addr);
    }

    public void write8(int addr, int bite) {
        mem.set(addr, bite);
    }

    public Bites all() {
        return mem;
    }

    public void write8arr(int addr, Bites bites) {
        for (int bite : bites) {
            write8(addr, bite);
            addr = inc16(addr);
        }
    }

    public void write8str(int addr, String bites) {
        write8arr(addr, toBites(bites));
    }

    public void write16(int addr, int word) {
        write8(addr, lo(word));
        addr = inc16(addr);
        write8(addr, hi(word));
    }

    public Bites read8arr(Range range) {
        int length = range.length();
        if (length < 0) {
            return new Bites();
        }
        Bites result = new Bites(length);
        for (int i = 0; i < length; i++) {
            result.set(i, read8(range.offset(i)));
        }
        return result;
    }

    public String read8srt(Range range) {
        Bites bites = read8arr(range);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bites.size(); i++) {
            if (i != 0) {
                result.append(' ');
            }
            result.append(hex8(bites.get(i)));
        }
        return result.toString();
    }
}