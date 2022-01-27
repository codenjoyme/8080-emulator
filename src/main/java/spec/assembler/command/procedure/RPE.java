package spec.assembler.command.procedure;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.procedure.RET.ret_if;

public class RPE extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xE8);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "RPE";
    }

    @Override
    public int ticks() {
        return 15; // TODO если условие не прошло то 11
    }

    @Override
    public void apply(int command, Registry r) {
        ret_if(r, reg -> reg.tp());
    }
}