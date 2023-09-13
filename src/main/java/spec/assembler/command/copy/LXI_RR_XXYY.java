package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

/**
 * LXI B,XXYY      [01 YY XX]
 *    B <- (XXYY)
 *
 * LXI C,XXYY      [11 YY XX]
 *    C <- (XXYY)
 *
 * LXI D,XXYY      [21 YY XX]
 *    D <- (XXYY)
 *
 * LXI SP,XXYY     [31 YY XX]
 *    SP <- (XXYY)
 */
public class LXI_RR_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0x01, 0x11, 0x21, 0x31);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public String operands() {
        return "RR,4";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks(int command) {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        int word = r.attr16();
        rRR(command, r).set(word);
    }
}