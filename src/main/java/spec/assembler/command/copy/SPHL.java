package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class SPHL extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xF9);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "SPHL";
    }

    @Override
    public int ticks() {
        return 6;
    }

    @Override
    public void apply(int command, Registry r) {
        r.SP(r.HL());
    }
}