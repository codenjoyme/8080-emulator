package spec.assembler.command.math.flag;

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
public class CMP_R extends Command {

    private static final List<Integer> CODES = from(
            0xB8, 0xB9, 0xBA, 0xBB, 0xBC, 0xBD, 0xBE, 0xBF);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BCDEHLMA;
    }

    @Override
    public int ticks() {
        return 4; // TODO для M регистра 7 тиков
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        cmp8(r, r.A(), reg.get());
    }

    public static void cmp8(Registry r, int a, int b) {
        int wans = a - b;
        int ans = lo(wans);

        r.ts((ans & T7s) != 0);
        r.tz(ans == 0);
        r.tc((wans & x100) != 0);
        r.th((((a & x0F) - (b & x0F)) & T4h) != 0);
        r.tp(parity[ans]);
    }
}