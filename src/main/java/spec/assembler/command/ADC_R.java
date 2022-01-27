package spec.assembler.command;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Constants.*;
import static spec.Registry.T4h;
import static spec.Registry.T7s;
import static spec.WordMath.lo;

// TODO test me
public class ADC_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(
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
    public String pattern() {
        return "ADC (B|C|D|E|H|L|M|A)";
    }

    @Override
    public int ticks() {
        return 4; // TODO для M регистра 7 тиков
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
        r.tp(((a ^ ~b) & (a ^ ans) & x80) != 0);
        r.th((((a & x0F) + (b & x0F) + c) & T4h) != 0);

        return ans;
    }
}