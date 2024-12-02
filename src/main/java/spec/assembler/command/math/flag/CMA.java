package spec.assembler.command.math.flag;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.math.WordMath.BITE;

// TODO test me
public class CMA extends Command {

    private static final List<Integer> CODES = from(
            0x2F);

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
        cma(r, r.rA);
    }

    private void cma(Registry r, Reg reg) {
        int ans = reg.get() ^ BITE;

        reg.set(ans);
    }
}