package spec.assembler.command.stack;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class PUSH_RS extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xC5, 0xD5, 0xE5, 0xF5);

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
        return "PUSH (B|D|H|PSW)";
    }

    @Override
    public int ticks() {
        return 11;
    }

    @Override
    public void apply(int command, Registry r) {
        int word = rRS(command, r).get();
        r.data().push16(r.rSP, word);
    }
}