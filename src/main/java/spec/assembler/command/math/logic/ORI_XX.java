package spec.assembler.command.math.logic;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.math.logic.ORA_R.or8;

// TODO test me
public class ORI_XX extends Command {

    private static final List<Integer> CODES = from(
            0xF6);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public int ticks() {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int bite = r.attr8();
        r.A(or8(r, r.A(), bite));
    }
}