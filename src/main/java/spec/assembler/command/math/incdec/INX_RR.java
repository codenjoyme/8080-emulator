package spec.assembler.command.math.incdec;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.WordMath.inc16;

public class INX_RR extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x03, 0x13, 0x23, 0x33);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public int ticks() {
        return 6;
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = rRR(command, r);
        int op = reg.get();
        int word = inc16(op);
        reg.set(word);
    }
}