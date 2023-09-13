package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * LDAX B         [0A]
 *    A <- (BC)
 * LDAX D         [1A]
 *    A <- (DE)
 */
public class LDAX_RD extends Command {

    private static final List<Integer> CODES = from(
            0x0A, 0x1A);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BD;
    }

    @Override
    public int ticks(int command) {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = rRR(command, r).get();
        int bite = r.read8(addr);
        r.A(bite);
    }
}