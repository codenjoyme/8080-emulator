package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Constants.*;
import static spec.Registry.T4h;
import static spec.Registry.T7s;
import static spec.WordMath.lo;

// TODO test me
public class ADD_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BCDEHLMA;
    }
    
    @Override
    public String pattern() {
        return "ADD (B|C|D|E|H|L|M|A)";
    }

    @Override
    public int ticks() {
        return 4; // TODO для M регистра 7 тиков
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        r.A(add8(r, r.A(), reg.get()));
    }

    public static int add8(Registry r, int a, int b) {
        int wans = a + b;
        int ans = lo(wans);

        r.ts((ans & T7s) != 0);
        r.tz(ans == 0);
        r.tc((wans & x100) != 0);
        r.tp(((a ^ ~b) & (a ^ ans) & x80) != 0);
        // TODO #01 вероятно тут надо пофиксить
        //  r.tp(parity[ans]);
        r.th((((a & x0F) + (b & x0F)) & T4h) != 0);

        return ans;
    }
}