package spec.assembler.command.system;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

public class EI extends Command {

    private static final List<Integer> CODES = from(
            0xFB);

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
        // TODO понять что делает команда
    }
}