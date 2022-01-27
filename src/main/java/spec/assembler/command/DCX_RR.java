package spec.assembler.command;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Registry._SP;
import static spec.WordMath.dec16;

// TODO test me
public class DCX_RR extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x0B, 0x1B, 0x2B, 0x3B);

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
        return "DCX (B|D|H|SP)";
    }

    @Override
    public int ticks() {
        return 6;
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg16(rindex(command), _SP);
        int op = reg.get();
        int word = dec16(op);
        reg.set(word);
    }
}