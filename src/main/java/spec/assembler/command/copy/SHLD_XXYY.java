package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * SHLD XXYY        [22 YY XX]
 *   (XXYY) = L
 *   (XXYY+1) = H
 */
public class SHLD_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0x22);

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
        r.write16(addr, r.HL());
    }
}