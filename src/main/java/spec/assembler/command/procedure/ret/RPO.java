package spec.assembler.command.procedure.ret;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.procedure.ret.RET.ret_if;

public class RPO extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xE0);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "RPO";
    }

    @Override
    public int ticks() {
        return 15; // TODO если условие не прошло то 11
    }

    @Override
    public void apply(int command, Registry r) {
        ret_if(r, reg -> !reg.tp());
    }
}