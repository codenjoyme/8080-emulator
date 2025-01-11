package spec.assembler.command.math.logic;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.math.WordMath.lo;
import static spec.assembler.command.math.logic.ANA_R.and_flags;

// FIXME test me
public class XRA_R extends Command {

    private static final List<Integer> CODES = from(
            0xA8, 0xA9, 0xAA, 0xAB, 0xAC, 0xAD, 0xAE, 0xAF);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BCDEHLMA;
    }

    @Override
    public int ticks(int command) {
        return rindex(command) == iM ? 7 : 4;
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        r.A(xor8(r, r.A(), reg.get()));
    }

    public static int xor8(Registry r, int a, int b) {
        int ans = lo(a ^ b);
        and_flags(r, ans, false, a, b);
        return ans;
    }
}