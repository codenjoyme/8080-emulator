package spec.math;

import java.util.Arrays;
import java.util.Iterator;

public class Bites implements Iterable<Integer> {

    private int[] data;

    public Bites(int length) {
        data = new int[length];
    }

    public Bites(int... value) {
        this.data = value;
    }

    public Bites() {
        this(0);
    }

    public int[] array() {
        return data;
    }

    public int size() {
        return data.length;
    }

    public void set(int index, int bite) {
        data[index] = bite;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < data.length;
            }

            @Override
            public Integer next() {
                return data[index++];
            }
        };
    }

    public int get(int index) {
        return data[index];
    }

    public Bites subset(int size) {
        return new Bites(Arrays.copyOf(data, size));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bites integers = (Bites) o;
        return Arrays.equals(data, integers.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
