package spec.assembler.command.math.flag;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.math.flag.CMP_R.cmp8;

// TODO test me
public class CPI_XX extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xFE);

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
        cmp8(r, r.A(), bite);
    }
}