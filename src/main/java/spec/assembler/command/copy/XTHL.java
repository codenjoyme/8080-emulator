package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * XTHL            [E3]
 *    H <-> (SP+1)
 *    L <-> (SP)
 */
// TODO test me
public class XTHL extends Command {

    private static final List<Integer> CODES = from(
            0xE3);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 19;
    }

    @Override
    public void apply(int command, Registry r) {
        int hl = r.HL();
        int sp = r.SP();
        r.HL(r.read16(sp));
        r.write16(sp, hl);
    }
}