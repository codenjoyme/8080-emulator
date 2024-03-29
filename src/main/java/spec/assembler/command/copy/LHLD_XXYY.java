package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * LHLD XXYY        [2A YY XX]
 *    H = (XXYY+1)
 *    L = (XXYY)
 */
public class LHLD_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0x2A);

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
        return 16;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = r.attr16();
        int word = r.read16(addr);
        r.HL(word);
    }
}