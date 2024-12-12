package spec;

import spec.math.Bites;

import static spec.math.Bites.of;
import static spec.math.WordMath.*;

public class Memory implements StateProvider {

    protected Bites mem;

    public Memory(int size) {
        mem = new Bites(size);
    }

    protected Memory() {
        // do nothing
    }

    public int read8(int addr) {
        return all().get(addr);
    }

    public void write8(int addr, int bite) {
        all().set(addr, bite);
    }

    public Bites all() {
        return mem;
    }

    public void write8arr(int addr, Bites bites) {
        mem.set(addr, bites);
    }

    public void write8str(int addr, String bites) {
        write8arr(addr, of(bites));
    }

    public void write16(int addr, int word) {
        write8(addr, lo(word));
        addr = inc16(addr);
        write8(addr, hi(word));
    }

    public Bites read8arr(Range range) {
        return mem.array(range);
    }

    public String read8str(Range range) {
        return read8arr(range).toString(false, false);
    }

    @Override
    public int stateSize() {
        return mem.size();
    }

    @Override
    public void state(Bites bites) {
        validateState("memory", bites);

        mem.set(bites);
    }

    @Override
    public Bites state() {
        return mem.array();
    }
}