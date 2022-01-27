package spec.assembler.command;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Registry.T7s;
import static spec.WordMath.lo;
import static spec.assembler.command.Parity.parity;

// TODO test me
public class XRA_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xA8, 0xA9, 0xAA, 0xAB, 0xAC, 0xAD, 0xAE, 0xAF);

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
        return "XRA (B|C|D|E|H|L|M|A)";
    }

    @Override
    public int ticks() {
        return 4; // TODO для M регистра 7 тиков
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        r.A(xor8(r, r.A(), reg.get()));
    }

    public static int xor8(Registry r, int a, int b) {
        int ans = lo(a ^ b);

        r.ts((ans & T7s) != 0);
        r.th(false);
        r.tp(parity[ans]);
        r.tz(ans == 0);
        r.tc(false);

        return ans;
    }
}