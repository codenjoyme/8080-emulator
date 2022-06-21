package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x0F;
import static spec.Constants.x100;
import static spec.Registry.T4h;
import static spec.Registry.T7s;
import static spec.WordMath.lo;
import static spec.assembler.Parity.parity;

// TODO test me
public class ADD_R extends Command {

    private static final List<Integer> CODES = from(
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
    public int ticks(int command) {
        return rindex(command) == iM ? 7 : 4;
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
        r.tp(parity[ans]);
        r.th((((a & x0F) + (b & x0F)) & T4h) != 0);

        return ans;
    }
}