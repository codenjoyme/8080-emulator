package spec.assembler.command.system;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class NOP extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x00);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks() {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        // do nothing
    }
}