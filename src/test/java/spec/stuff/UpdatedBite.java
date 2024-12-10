package spec.stuff;

import spec.math.WordMath;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static spec.math.WordMath.hex16;

public class UpdatedBite {

    private int addr;
    private final List<Integer> values;

    public UpdatedBite(int addr, int value) {
        values = new ArrayList<>();
        values.add(value);
        this.addr = addr;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                hex16(addr),
                values.stream()
                        .map(WordMath::hex8)
                        .collect(joining(" -> ")));
    }

    public void add(int bite) {
        values.add(bite);
    }

    public boolean changed() {
        return values.size() > 1;
    }

    public int original() {
        return values.get(0);
    }

    public int last() {
        return values.get(values.size() - 1);
    }
}
