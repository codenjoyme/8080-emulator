package spec.assembler.command.math.logic;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.math.logic.ANA_R.and8;

// FIXME test me
public class ANI_XX extends Command {

    private static final List<Integer> CODES = from(
            0xE6);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public int ticks(int command) {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int bite = r.attr8();
        r.A(and8(r, r.A(), bite));
    }
}