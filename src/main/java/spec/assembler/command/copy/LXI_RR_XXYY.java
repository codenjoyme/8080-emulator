package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class LXI_RR_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x01, 0x11, 0x21, 0x31);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public String operands() {
        return "RR,4";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks() {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        int word = r.attr16();
        rRR(command, r).set(word);
    }
}