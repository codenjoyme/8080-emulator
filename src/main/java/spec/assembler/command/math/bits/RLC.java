package spec.assembler.command.math.bits;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x80;
import static spec.WordMath.lo;

public class RLC extends Command {

    private static final List<Integer> CODES = from(
            0x07);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        r.A(rlc(r, r.A()));
    }

    private int rlc(Registry r, int a) {
        r.tc((a & x80) != 0);
        int ans = (a << 1) | r.tci();
        return lo(ans);
    }
}