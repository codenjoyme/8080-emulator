package spec.assembler.command.math.bits;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x01;
import static spec.math.WordMath.lo;

public class RRC extends Command {

    private static final List<Integer> CODES = from(
            0x0F);

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
        r.A(rrc(r, r.A()));
    }

    private int rrc(Registry r, int a) {
        int c = a & x01;
        r.tc(c != 0);
        int ans = (a >> 1) | (c << 7);
        return lo(ans);
    }
}