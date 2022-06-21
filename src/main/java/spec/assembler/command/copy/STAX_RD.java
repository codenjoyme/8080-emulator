package spec.assembler.command.copy;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

public class STAX_RD extends Command {

    private static final List<Integer> CODES = from(
            0x02, 0x12);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BD;
    }

    @Override
    public int ticks(int command) {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = rRR(command, r).get();
        r.write8(addr, r.A());
    }
}