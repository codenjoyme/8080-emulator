package spec.assembler.command.system;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

// FIXME test me
public class DI extends Command {

    private static final List<Integer> CODES = from(
            0xF3);

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
        // TODO #7 понять что делает команда
    }
}