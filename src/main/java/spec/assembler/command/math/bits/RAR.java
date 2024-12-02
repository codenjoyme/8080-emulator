package spec.assembler.command.math.bits;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x01;
import static spec.math.WordMath.lo;

public class RAR extends Command {

    private static final List<Integer> CODES = from(
            0x1F);

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
        r.A(rar(r, r.A()));
    }

    private int rar(Registry r, int a) {
        int c = r.tci();
        r.tc((a & x01) != 0);
        int ans = (a >> 1) | (c << 7);
        return lo(ans);
    }
}