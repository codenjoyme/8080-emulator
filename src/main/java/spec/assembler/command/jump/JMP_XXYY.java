package spec.assembler.command.jump;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class JMP_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xC3);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "JMP (....)";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks() {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        jmp_if(r, reg -> true);
    }

    public static void jmp_if(Registry r, Predicate<Registry> predicate) {
        int addr = r.data().read16(r.rPC);
        if (predicate.test(r)) {
            r.PC(addr);
        }
    }
}