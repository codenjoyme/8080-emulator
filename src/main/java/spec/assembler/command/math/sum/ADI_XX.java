package spec.assembler.command.math.sum;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.math.sum.ADD_R.add8;

// TODO test me
public class ADI_XX extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xC6);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BCDEHLMA;
    }
    
    @Override
    public String pattern() {
        return "ADI (..)";
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
        int bite = r.data().read8(r.rPC);
        r.A(add8(r, r.A(), bite));
    }
}