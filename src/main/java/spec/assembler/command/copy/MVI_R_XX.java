package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 *               B  C  D  E  H  L  M  A
 * MVI R,XX    [06,0E,16,1E,26,2E,36,3E][XX]
 *    R <- XX
 */
public class MVI_R_XX extends Command {

    private static final List<Integer> CODES = from(
            0x06, 0x0E, 0x16, 0x1E, 0x26, 0x2E, 0x36, 0x3E);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BCDEHLMA;
    }

    @Override
    public String operands() {
        return "R,2";
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public int ticks(int command) {
        return rindex(command) == iM ? 10 : 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int bite = r.attr8();
        r.reg8(rindex(command)).set(bite);
    }
}