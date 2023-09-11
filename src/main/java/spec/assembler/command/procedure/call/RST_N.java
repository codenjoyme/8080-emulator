package spec.assembler.command.procedure.call;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.procedure.call.CALL_XXYY.call;

// TODO test me
public class RST_N extends Command {

    private static final List<Integer> CODES = from(
            0xC7, 0xCF, 0xD7, 0xDF, 0xE7, 0xEF, 0xF7, 0xFF);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7");
    }

    @Override
    public String pattern() {
        return "RST (.)";
    }

    @Override
    public int ticks(int command) {
        return 11;
    }

    @Override
    public void apply(int command, Registry r) {
        int addr = rindex(command) * 8;
        call(r, addr);
    }
}