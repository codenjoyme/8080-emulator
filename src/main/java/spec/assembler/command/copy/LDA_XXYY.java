package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class LDA_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x3A);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "LDA (....)";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks() {
        return 13;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = r.data().read16(r.rPC);
        int bite = r.data().read8(addr);
        r.A(bite);
    }
}