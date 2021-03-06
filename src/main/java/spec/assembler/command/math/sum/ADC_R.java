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
public class ADC_R extends Command {

    private static final List<Integer> CODES = from(
            0x88, 0x89, 0x8A, 0x8B, 0x8C, 0x8D, 0x8E, 0x8F);

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
        r.A(adc8(r, r.A(), reg.get()));
    }

    public static int adc8(Registry r, int a, int b) {
        int c = r.tci();
        int wans = a + b + c;
        int ans = lo(wans);

        r.ts((ans & T7s) != 0);
        r.tz(ans == 0);
        r.tc((wans & x100) != 0);
        r.tp(parity[ans]);
        r.th((((a & x0F) + (b & x0F) + c) & T4h) != 0);

        return ans;
    }
}