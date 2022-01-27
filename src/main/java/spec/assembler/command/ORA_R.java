package spec.assembler.command;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Registry.T7s;
import static spec.assembler.command.Parity.parity;

// TODO test me
public class ORA_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xB0, 0xB1, 0xB2, 0xB3, 0xB4, 0xB5, 0xB6, 0xB7);

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
        return "ORA (B|C|D|E|H|L|M|A)";
    }

    @Override
    public int ticks() {
        return 4; // TODO для M регистра 7 тиков
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        r.A(or8(r, r.A(), reg.get()));
    }

    public static int or8(Registry r, int a, int b) {
        int ans = a | b;

        r.ts((ans & T7s) != 0);
        r.th(false);
        r.tp(parity[ans]);
        r.tz(ans == 0);
        r.tc(false);

        return ans;
    }
}