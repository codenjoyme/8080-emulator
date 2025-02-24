package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * XCHG          [EB]
 *    HL <-> DE
 */
// FIXME test me
public class XCHG extends Command {

    private static final List<Integer> CODES = from(
            0xEB);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        int hl = r.HL();
        r.HL(r.DE());
        r.DE(hl);
    }
}