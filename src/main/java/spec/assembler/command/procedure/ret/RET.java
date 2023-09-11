package spec.assembler.command.procedure.ret;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;
import java.util.function.Predicate;

import static spec.mods.Event.RUN_RET;

// TODO test me
public class RET extends Command {

    private static final List<Integer> CODES = from(
            0xC9);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        ret_if(r, reg -> true);
    }

    public static void ret_if(Registry r, Predicate<Registry> predicate) {
        if (predicate.test(r)) {
            ret(r);
        }
    }

    public static void ret(Registry r) {
        int addr = r.data().pop16(r.rSP);
        r.PC(addr);
        r.on(RUN_RET);
    }
}