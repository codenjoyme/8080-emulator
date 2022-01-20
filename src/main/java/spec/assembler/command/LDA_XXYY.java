package spec.assembler.command;

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
        int addr = r.data().peekwi(r.rPC);
        int bite = r.data().peekb(addr);
        r.A(bite);
    }
}