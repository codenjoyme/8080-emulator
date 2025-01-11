package spec.assembler.command.procedure.call;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.procedure.call.CALL_XXYY.call_if;

// FIXME test me
public class CNC_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0xD4);

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
        return 17; // TODO #5 если условие не прошло то 10
    }

    @Override
    public void apply(int command, Registry r) {
        call_if(r, Registry::tnc);
    }
}