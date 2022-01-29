package spec.assembler.command.system;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class HLT extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x76);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks() {
        return 4; // TODO не верное значение
    }

    @Override
    public void apply(int command, Registry r) {
        // TODO надо как-то остановить процессор
    }
}