package spec.assembler.command;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Constants.*;
import static spec.assembler.command.Parity.parity;
import static spec.assembler.command.SUB_R.sub8;

// TODO test me
public class DAA extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x27);

    @Override
    public List<Integer> codes() {
        return CODES;
    }
    
    @Override
    public String pattern() {
        return "DAA";
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
            incr |= x06;
        }
        if (carry || (ans > x9F) || ((ans > x8F) && ((ans & x0F) > x09))) {
            incr |= x60;
        }
        if (ans > x99) {
            carry = true;
        }
        ans = sub8(r, reg.get(), incr);
        reg.set(ans);

        r.tc(carry);
        r.tp(parity[ans]);
    }
}