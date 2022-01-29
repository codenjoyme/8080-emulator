package spec;

import static spec.WordMath.*;

public class Memory {

    protected int[] mem;

    public Memory(int size) {
        mem = new int[size];
    }

    public int read8(int addr) {
        return mem[addr];
    }

    public void write8(int addr, int bite) {
        mem[addr] = bite;
    }

    public int[] all() {
        return mem;
    }

    public void write8arr(int addr, int... bites) {
        for (int bite : bites) {
            write8(addr, bite);
            addr = inc16(addr);
        }
    }

    public void write8str(int addr, String bites) {
        bites = bites.replace(" ", "");
        int[] array = new int[bites.length() / 2];
        for (int i = 0; i < array.length; i++) {
            String hex = bites.substring(i*2, (i + 1)*2);
            int bite = Integer.parseInt(hex, 16);
            array[i] = bite;
        }

        write8arr(addr, array);
    }

    public void write16(int addr, int word) {
        write8(addr, lo(word));
        addr = inc16(addr);
        write8(addr, hi(word));
    }

    public int[] read8arr(Range range) {
        int length = range.length();
        if (length < 0) {
            return new int[0];
        }
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = read8(range.offset(i));
        }
        return array;
    }

    public String read8srt(Range range) {
        int[] bites = read8arr(range);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bites.length; i++) {
            if (i != 0) {
                result.append(' ');
            }
            result.append(hex8(bites[i]));
        }
        return result.toString();
    }
}