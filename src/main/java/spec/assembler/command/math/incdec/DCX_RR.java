package spec.assembler.command.math.incdec;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

// FIXME test me
public class DCX_RR extends Command {

    private static final List<Integer> CODES = from(
            0x0B, 0x1B, 0x2B, 0x3B);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public int ticks(int command) {
        return 6;
    }

    @Override
    public void apply(int command, Registry r) {
        rRR(command, r).dec16();
    }
}