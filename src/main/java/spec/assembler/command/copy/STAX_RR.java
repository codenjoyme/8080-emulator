package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class STAX_RR extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x02, 0x12);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BD;
    }

    @Override
    public String pattern() {
        return "STAX (B|D)";
    }

    @Override
    public int ticks() {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = rRR(command, r).get();
        r.data().write8(addr, r.A());
    }
}