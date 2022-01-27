package spec.assembler.command;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Registry.T7s;
import static spec.assembler.Parity.parity;

// TODO test me
public class ANA_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(
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
    public String pattern() {
        return "ANA (B|C|D|E|H|L|M|A)";
    }

    @Override
    public int ticks() {
        return 4; // TODO для M регистра 7 тиков
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        r.A(and8(r, r.A(), reg.get()));
    }

    public static int and8(Registry r, int a, int b) {
        int ans = a & b;

        r.ts((ans & T7s) != 0);
        r.th(true);
        r.tp(parity[ans]);
        r.tz(ans == 0);
        r.tc(false);

        return ans;
    }
}