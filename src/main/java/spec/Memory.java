package spec;

import static spec.CPU.inc16;

public class Memory {

    public int[] mem;

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
            inc16(addr);
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
}
