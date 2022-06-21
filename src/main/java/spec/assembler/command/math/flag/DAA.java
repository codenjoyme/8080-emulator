package spec.assembler.command.math.flag;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.*;
import static spec.assembler.Parity.parity;
import static spec.assembler.command.math.sum.ADD_R.add8;

// TODO test me
public class DAA extends Command {

    private static final List<Integer> CODES = from(
            0x27);

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
        daa(r, r.rA);
    }

    private void daa(Registry r, Reg reg) {
        int ans = reg.get();
        int incr = 0;
        boolean carry = r.tc();

        if (r.th() || (ans & x0F) > x09) {
            incr = x06;
        }
        if (carry || (ans >> 4) > x09 || ((ans >> 4) >= x09 && ((ans & x0F) > x09))) {
            incr |= x60;
            carry = true;
        }
        ans = add8(r, reg.get(), incr);
        reg.set(ans);

        r.tc(carry);
        r.tp(parity[ans]);
    }
}