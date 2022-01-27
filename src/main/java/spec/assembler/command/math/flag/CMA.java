package spec.assembler.command.math.flag;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.WordMath.BITE;

// TODO test me
public class CMA extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x2F);

    @Override
    public List<Integer> codes() {
        return CODES;
    }
    
    @Override
    public String pattern() {
        return "CMA";
    }

    @Override
    public int ticks() {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        cma(r, r.rA);
    }

    private void cma(Registry r, Reg reg) {
        int ans = reg.get() ^ BITE;

        r.th(true);

        reg.set(ans);
    }
}