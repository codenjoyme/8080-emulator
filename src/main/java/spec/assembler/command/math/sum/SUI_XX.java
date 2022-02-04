package spec.assembler.command.math.sum;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.math.sum.SUB_R.sub8;

// TODO test me
public class SUI_XX extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xD6);

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
        r.A(sub8(r, r.A(), bite));
    }
}