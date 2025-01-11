package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.math.sum.ADC_R.add_flag;

// FIXME test me
public class SBB_R extends Command {

    private static final List<Integer> CODES = from(
            0x98, 0x99, 0x9A, 0x9B, 0x9C, 0x9D, 0x9E, 0x9F);

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
        r.A(sub8(r, r.A(), reg.get(), r.tci()));
    }

    public static int sub8(Registry r, int a, int b, int c) {
        int ans16 = a - b - c;
        return add_flag(r, a, b, ans16, false);
    }
}