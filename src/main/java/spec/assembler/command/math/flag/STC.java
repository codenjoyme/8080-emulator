package spec.assembler.command.math.flag;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

// TODO test me
public class STC extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x37);

    @Override
    public List<Integer> codes() {
        return CODES;
    }
    
    @Override
    public String pattern() {
        return "STC";
    }

    @Override
    public int ticks() {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        stc(r);
    }

    private void stc(Registry r) {
        r.th(false);
        r.tc(true);
    }
}