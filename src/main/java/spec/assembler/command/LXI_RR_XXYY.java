package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class LXI_RR_XXYY extends Command  {

    private static final List<Integer> CODES = Arrays.asList(0x01, 0x11, 0x21, 0x31);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public String pattern() {
        return "LXI (B|D|H|SP),(....)";
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
        int word = r.data().read16(r.rPC);
        rRR(command, r).set(word);
    }
}