package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Registry._SP;

public class LDAX_RR extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x0A, 0x1A);

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
        return "LDAX (B|D)";
    }

    @Override
    public int ticks() {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = r.reg16(rindex(command), _SP).get();
        int bite = r.data().read8(addr);
        r.A(bite);
    }
}