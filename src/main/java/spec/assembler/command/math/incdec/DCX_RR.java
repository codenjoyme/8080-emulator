package spec.assembler.command.math.incdec;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.WordMath.dec16;

// TODO test me
public class DCX_RR extends Command {

    private static final List<Integer> CODES = from(
            0x0B, 0x1B, 0x2B, 0x3B);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public int ticks(int command) {
        return 6;
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = rRR(command, r);
        int op = reg.get();
        int word = dec16(op);
        reg.set(word);
    }
}