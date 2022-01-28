package spec.assembler.command.procedure.call;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CALL_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xCD);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "CALL (....)";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks() {
        return 17;
    }

    @Override
    public void apply(int command, Registry r) {
        call_if(r, reg -> true);
    }

    public static void call_if(Registry r, Predicate<Registry> predicate) {
        int addr = r.data().read16(r.rPC);
        if (predicate.test(r)) {
            call(r, addr);
        }
    }

    public static void call(Registry r, int addr) {
        r.data().push16(r.rSP, r.PC());
        r.PC(addr);
        r.on("call");
    }
}