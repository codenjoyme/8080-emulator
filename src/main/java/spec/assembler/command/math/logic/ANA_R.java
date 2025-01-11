package spec.assembler.command.math.logic;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x08;
import static spec.Registry.T7s;
import static spec.assembler.Parity.parity;

// FIXME test me
public class ANA_R extends Command {

    private static final List<Integer> CODES = from(
            0xA0, 0xA1, 0xA2, 0xA3, 0xA4, 0xA5, 0xA6, 0xA7);

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
        r.A(and8(r, r.A(), reg.get()));
    }

    public static int and8(Registry r, int a, int b) {
        int ans = a & b;
        and_flags(r, ans, true, a, b);
        return ans;
    }

    public static void and_flags(Registry r, int ans, boolean calc_h, int a, int b) {
        r.ts((ans & T7s) != 0);
        if (calc_h) {
            r.th(((a | b) & x08) != 0);
        } else {
            r.th(false);
        }
        r.tp(parity[ans]);
        r.tz(ans == 0);
        r.tc(false);
    }
}