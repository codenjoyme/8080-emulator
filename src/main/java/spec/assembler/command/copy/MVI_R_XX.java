package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class MVI_R_XX extends Command {

    private static final List<Integer> CODES = Arrays.asList(
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
    public int ticks() {
        return 7; // TODO для M регистра 10 тиков
    }

    @Override
    public void apply(int command, Registry r) {
        int bite = r.data().read8(r.rPC);
        r.reg8(rindex(command)).set(bite);
    }
}