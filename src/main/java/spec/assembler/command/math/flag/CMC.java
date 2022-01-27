package spec.assembler.command.math.flag;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

// TODO test me
public class CMC extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x3F);

    @Override
    public List<Integer> codes() {
        return CODES;
    }
    
    @Override
    public String pattern() {
        return "CMC";
    }

    @Override
    public int ticks() {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        cmc(r);
    }

    private void cmc(Registry r) {
        r.tc(!r.tc());
    }
}