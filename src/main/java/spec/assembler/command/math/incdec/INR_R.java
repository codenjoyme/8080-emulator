package spec.assembler.command.math.incdec;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Constants.x0F;
import static spec.Constants.x7F;
import static spec.Registry.T4h;
import static spec.Registry.T7s;
import static spec.WordMath.lo;

// TODO test me
public class INR_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x04, 0x0C, 0x14, 0x1C, 0x24, 0x2C, 0x34, 0x3C);

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
        return "INR (B|C|D|E|H|L|M|A)";
    }

    @Override
    public int ticks() {
        return 4; // TODO для M регистра 11 тиков
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        int op = reg.get();
        int bite = inc8(r, op);
        reg.set(bite);
    }

    private int inc8(Registry r, int ans) {
        boolean p = (ans == x7F);
        boolean h = (((ans & x0F) + 1) & T4h) != 0;
        ans = lo(ans + 1);

        r.ts((ans & T7s) != 0);
        r.tz(ans == 0);
        r.tp(p);
        r.th(h);

        return ans;
    }
}