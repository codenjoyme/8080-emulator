package spec.math;

import java.util.Arrays;
import java.util.Iterator;

import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class Bites implements Iterable<Integer> {

    private int[] data;

    public Bites(int length) {
        data = new int[length];
    }

    private Bites(int[] data) {
        this.data = data;
    }

    public Bites() {
        this(0);
    }

    public static Bites of(int... data) {
        return new Bites(data);
    }

    public int[] array() {
        return data;
    }

    public byte[] byteArray() {
        byte[] bytes = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            bytes[i] = (byte) data[i];
        }
        return bytes;
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

    @Override
    public String toString() {
        return detailsTable(-1, -1);
    }

    public String detailsTable(int start, int end) {
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
