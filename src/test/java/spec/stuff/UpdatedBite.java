package spec.stuff;

import static spec.math.WordMath.hex16;
import static spec.math.WordMath.hex8;

public class UpdatedBite {

    private final int addr;
    private final int prev;
    private final int next;

    public UpdatedBite(int addr, int prev, int next) {
        this.addr = addr;
        this.prev = prev;
        this.next = next;
    }

    public int addr() {
        return addr;
    }

    public int prev() {
        return prev;
    }

    public int next() {
        return next;
    }

    @Override
    public String toString() {
        return String.format("%s: %s -> %s",
                hex16(addr),
                hex8(prev),
                hex8(next));
    }
}
