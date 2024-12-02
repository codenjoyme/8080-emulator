package spec.assembler.command.math.bits;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x80;
import static spec.math.WordMath.lo;

public class RAL extends Command {

    private static final List<Integer> CODES = from(
            0x17);

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
        r.A(ral(r, r.A()));
    }

    private int ral(Registry r, int a) {
        int c = r.tci();
        r.tc((a & x80) != 0);
        int ans = (a << 1) | c;
        return lo(ans);
    }
}