package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class PCHL extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xE9);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "PCHL";
    }

    @Override
    public int ticks() {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        r.PC(r.HL());
    }
}