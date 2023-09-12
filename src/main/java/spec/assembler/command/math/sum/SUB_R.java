package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.math.sum.SBB_R.sub8;

// TODO test me
public class SUB_R extends Command {

    private static final List<Integer> CODES = from(
            0x90, 0x91, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97);

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
        r.A(sub8(r, r.A(), reg.get(), 0));
    }
}