package spec.assembler.command.math.logic;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.math.logic.XRA_R.xor8;

// TODO test me
public class XRI_XX extends Command {

    private static final List<Integer> CODES = from(
            0xEE);

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
        r.A(xor8(r, r.A(), bite));
    }
}