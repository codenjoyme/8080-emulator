package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.math.sum.ADC_R.add8;

// TODO test me
public class ADD_R extends Command {

    private static final List<Integer> CODES = from(
            0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87);

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
        r.A(add8(r, r.A(), reg.get(), 0));
    }
}