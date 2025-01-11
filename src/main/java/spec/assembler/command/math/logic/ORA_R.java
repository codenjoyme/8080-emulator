package spec.assembler.command.math.logic;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.math.logic.ANA_R.and_flags;

// FIXME test me
public class ORA_R extends Command {

    private static final List<Integer> CODES = from(
            0xB0, 0xB1, 0xB2, 0xB3, 0xB4, 0xB5, 0xB6, 0xB7);

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
        r.A(or8(r, r.A(), reg.get()));
    }

    public static int or8(Registry r, int a, int b) {
        int ans = a | b;
        and_flags(r, ans, false, a, b);
        return ans;
    }
}