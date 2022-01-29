package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class XTHL extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xE3);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks() {
        return 19;
    }

    @Override
    public void apply(int command, Registry r) {
        int hl = r.HL();
        int sp = r.SP();
        r.HL(r.data().read16(sp));
        r.data().write16(sp, hl);
    }
}