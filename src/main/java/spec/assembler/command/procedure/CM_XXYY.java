package spec.assembler.command.procedure;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.procedure.CALL_XXYY.call_if;

public class CM_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xFC);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "CM (....)";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks() {
        return 17; // TODO если условие не прошло то 10
    }

    @Override
    public void apply(int command, Registry r) {
        call_if(r, reg -> reg.ts());
    }
}