package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class STAX_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x02, 0x12);

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
        int addr = r.regw(rindex(command)).get();
        r.data().writeb(addr, r.A());
    }
}