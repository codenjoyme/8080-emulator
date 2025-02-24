package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * SPHL        [F9]
 *    SP <- HL
 */
// FIXME test me
public class SPHL extends Command {

    private static final List<Integer> CODES = from(
            0xF9);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 6;
    }

    @Override
    public void apply(int command, Registry r) {
        r.SP(r.HL());
    }
}