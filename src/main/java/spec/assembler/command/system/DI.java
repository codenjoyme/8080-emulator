package spec.assembler.command.system;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class DI extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xF3);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "DI";
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