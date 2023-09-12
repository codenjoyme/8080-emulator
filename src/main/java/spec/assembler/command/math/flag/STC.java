package spec.assembler.command.math.flag;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

// TODO test me
public class STC extends Command {

    private static final List<Integer> CODES = from(
            0x37);

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
        stc(r);
    }

    private void stc(Registry r) {
        r.tc(true);
    }
}