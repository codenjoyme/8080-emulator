package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class POP_RS extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xC1, 0xD1, 0xE1, 0xF1);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public String pattern() {
        return "POP (B|D|H|PSW)";
    }

    @Override
    public int ticks() {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        int word = r.data().read16(r.rSP);
        rRS(command, r).set(word);
    }
}