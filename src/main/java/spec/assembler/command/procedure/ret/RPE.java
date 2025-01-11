package spec.assembler.command.procedure.ret;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.procedure.ret.RET.ret_if;

// FIXME test me
public class RPE extends Command {

    private static final List<Integer> CODES = from(
            0xE8);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 15; // TODO #5 если условие не прошло то 11
    }

    @Override
    public void apply(int command, Registry r) {
        ret_if(r, Registry::tp);
    }
}