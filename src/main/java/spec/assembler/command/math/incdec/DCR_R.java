package spec.assembler.command.math.incdec;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x0F;
import static spec.Registry.T7s;
import static spec.WordMath.lo;
import static spec.assembler.Parity.parity;

// TODO test me
public class DCR_R extends Command {

    private static final List<Integer> CODES = from(
            0x05, 0x0D, 0x15, 0x1D, 0x25, 0x2D, 0x35, 0x3D);

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
        return rindex(command) == iM ? 11 : 4;
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        int op = reg.get();
        int bite = dec8(r, op);
        reg.set(bite);
    }

    private int dec8(Registry r, int ans) {
        ans = lo(ans - 1);

        r.ts((ans & T7s) != 0);
        r.tz(ans == 0);
        r.tp(parity[ans]);
        r.th((ans & x0F) != x0F);

        return ans;
    }
}