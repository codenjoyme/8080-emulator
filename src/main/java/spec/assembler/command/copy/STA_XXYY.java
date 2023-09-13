package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * STA XXYY      [32 YY XX]
 *    (XXYY) <- A
 */
public class STA_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0x32);

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
        r.write8(addr, r.A());
    }
}