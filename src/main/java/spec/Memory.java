package spec;

import static spec.CPU.inc16;

public class Memory {

    protected int[] mem;

    public Memory(int size) {
        mem = new int[size];
    }

    public int get(int addr) {
        return mem[addr];
    }

    public void set(int addr, int bite) {
        mem[addr] = bite;
    }

    public int[] all() {
        return mem;
    }

    public void write(int addr, int... bites) {
        for (int bite : bites) {
            set(addr, bite);
            addr = inc16(addr);
        }
    }

    public void write(int addr, String bites) {
        bites = bites.replace(" ", "");
        int[] array = new int[bites.length() / 2];
        for (int i = 0; i < array.length; i++) {
            String hex = bites.substring(i*2, (i + 1)*2);
            int bite = Integer.parseInt(hex, 16);
            array[i] = bite;
        }

        write(addr, array);
    }

    public int[] get(Range range) {
        int length = range.size();
        if (length < 0) {
            return new int[0];
        }
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = get(range.offset(i));
        }
        return array;
    }

    public String asString(Range range) {
        int[] bites = get(range);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bites.length; i++) {
            if (i != 0) {
                result.append(' ');
            }
            result.append(String.format("%02x", bites[i]));
        }
        return result.toString();
    }
}
