package spec.assembler.command.procedure.call;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.procedure.call.CALL_XXYY.call_if;

// TODO test me
public class CNZ_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0xC4);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks(int command) {
        return 17; // TODO если условие не прошло то 10
    }

    @Override
    public void apply(int command, Registry r) {
        call_if(r, Registry::tnz);
    }
}