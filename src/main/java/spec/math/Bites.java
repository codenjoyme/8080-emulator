package spec.math;

import spec.Range;

import java.util.Arrays;
import java.util.Iterator;

import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class Bites implements Iterable<Integer> {

    private int[] data;

    public Bites(int length) {
        data = new int[length];
    }

    public Bites(int[] data) {
        this.data = data;
    }

    public Bites() {
        this(0);
    }

    public static Bites of(int... data) {
        return new Bites(data);
    }

    public Bites array(Range range) {
        int length = range.length();
        int begin = range.begin();

        Bites bytes = new Bites(length);
        for (int i = 0; i < length; i++) {
            bytes.set(i, get(begin + i));
        }
        return bytes;
    }

    public Bites array() {
        int length = size();
        int begin = 0;

        return array(new Range(begin, -length));
    }

    // TODO remove code duplicate
    public byte[] byteArray(Range range) {
        int length = range.length();
        int begin = range.begin();

        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) data[begin + i];
        }
        return bytes;
    }

    public byte[] byteArray() {
        int length = size();
        int begin = 0;

        return byteArray(new Range(begin, -length));
    }

    public int size() {
        return data.length;
    }

    public void set(int index, int bite) {
        data[index] = bite;
    }

    public void set(Range range, Bites bites) {
        int length = range.length();
        int begin = range.begin();

        for (int i = 0; i < length; i++) {
            set(begin + i, bites.get(i));
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
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

    @Override
    public String toString() {
        return toString(-1, -1);
    }

    public String toString(int start, int end) {
        if (start == -1) {
            start = 0;
        }
        if (end == -1 || end > data.length) {
            end = data.length;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("      ");
        for (int i = 0; i < 16; i++) {
            sb.append(hex8(i))
                    .append(" ");
        }
        sb.append("\n");

        for (int line = start; line < end; line += 16) {
            sb.append(hex16(line))
                    .append(": ");
            for (int i = line; i < line + 16 && i < end; i++) {
                sb.append(hex8(get(i)))
                        .append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
