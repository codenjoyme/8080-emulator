package spec.assembler.command.procedure.ret;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class RET extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xC9);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks() {
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
        int addr = r.data().read16(r.rSP);
        r.PC(addr);
        r.on("ret");
    }
}