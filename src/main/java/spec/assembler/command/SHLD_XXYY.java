package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class SHLD_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x22);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "SHLD (....)";
    }

    @Override
    public int ticks() {
        return 16;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = r.data().peekwi(r.rPC);
        r.data().pokew(addr, r.HL());
    }
}