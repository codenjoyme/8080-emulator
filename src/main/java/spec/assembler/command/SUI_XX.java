package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.SUB_R.sub8;

// TODO test me
public class SUI_XX extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xD6);

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
        return "SUI (..)";
    }

    @Override
    public int ticks() {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int bite = r.data().read8(r.rPC);
        r.A(sub8(r, r.A(), bite));
    }
}