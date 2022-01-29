package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class LHLD_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x2A);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "LHLD (....)";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks() {
        return 16;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = r.data().read16(r.rPC);
        int word = r.data().read16(addr);
        r.HL(word);
    }
}