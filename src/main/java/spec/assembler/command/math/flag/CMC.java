package spec.assembler.command.math.flag;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

// FIXME test me
public class CMC extends Command {

    private static final List<Integer> CODES = from(
            0x3F);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        cmc(r);
    }

    private void cmc(Registry r) {
        r.tc(r.tnc());
    }
}