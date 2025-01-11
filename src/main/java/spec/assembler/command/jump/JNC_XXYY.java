package spec.assembler.command.jump;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.command.jump.JMP_XXYY.jmp_if;

// FIXME test me
public class JNC_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0xD2);

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
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        jmp_if(r, Registry::tnc);
    }
}