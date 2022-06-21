package spec.assembler.command.procedure.call;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;
import java.util.function.Predicate;

import static spec.mods.Event.RUN_CALL;

public class CALL_XXYY extends Command {

    private static final List<Integer> CODES = from(
            0xCD);

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
        return 17;
    }

    @Override
    public void apply(int command, Registry r) {
        call_if(r, reg -> true);
    }

    public static void call_if(Registry r, Predicate<Registry> predicate) {
        int addr = r.attr16();
        if (predicate.test(r)) {
            call(r, addr);
        }
    }

    public static void call(Registry r, int addr) {
        r.data().push16(r.rSP, r.PC());
        r.PC(addr);
        r.on(RUN_CALL);
    }
}