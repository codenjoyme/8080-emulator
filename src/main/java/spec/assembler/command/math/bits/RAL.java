package spec.assembler.command.math.bits;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x01;
import static spec.Constants.x80;
import static spec.WordMath.lo;

// TODO test me
public class RAL extends Command {

    private static final List<Integer> CODES = from(
            0x17);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks() {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        r.A(ral(r, r.A()));
    }

    private int ral(Registry r, int a) {
        int ans = a;
        boolean c = (ans & x80) != 0;

        if (r.tc()) {
            ans = (ans << 1) | x01;
        } else {
            ans <<= 1;
        }

        ans = lo(ans);

        r.th(false);
        r.tc(c);

        return ans;
    }
}