package spec.assembler.command.math.logic;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.math.logic.ORA_R.or8;

// TODO test me
public class ORI_XX extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xF6);

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
        return "ORI (..)";
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
        r.A(or8(r, r.A(), bite));
    }
}