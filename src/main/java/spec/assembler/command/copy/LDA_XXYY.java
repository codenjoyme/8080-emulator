package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * LDA XXYY        [3A]
 *    A = (XXYY)
 */
public class LDA_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0x3A);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks(int command) {
        return 13;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = r.attr16();
        int bite = r.read8(addr);
        r.A(bite);
    }
}