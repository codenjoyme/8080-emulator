package spec.assembler.command.math.incdec;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

public class INX_RR extends Command {

    private static final List<Integer> CODES = from(
            0x03, 0x13, 0x23, 0x33);

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
        rRR(command, r).inc16();
    }
}