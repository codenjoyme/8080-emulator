package spec.assembler.command.stack;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

public class POP_RS extends Command {

    private static final List<Integer> CODES = from(
            0xC1, 0xD1, 0xE1, 0xF1);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHPSW;
    }

    @Override
    public int ticks(int command) {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        int word = r.data().pop16(r.rSP);
        rRS(command, r).set(word);
    }
}