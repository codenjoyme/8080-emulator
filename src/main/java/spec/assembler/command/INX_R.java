package spec.assembler.command;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.CPU.inc16;

public class INX_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x03, 0x13, 0x23, 0x33);

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
        return "INX (B|D|H|SP)";
    }

    @Override
    public int ticks() {
        return 6;
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg(rindex(command));
        int op = reg.get();
        int word = inc16(op);
        reg.set(word);
    }
}