package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.*;
import static spec.assembler.Parity.*;

// FIXME test me
public class ADC_R extends Command {

    private static final List<Integer> CODES = from(
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
    public int ticks(int command) {
        return rindex(command) == iM ? 7 : 4;
    }

    @Override
    public void apply(int command, Registry r) {
        Reg reg = r.reg8(rindex(command));
        r.A(add8(r, r.A(), reg.get(), r.tci()));
    }

    public static int add8(Registry r, int a, int b, int c) {
        int res16 = a + b + c;
        return add_flag(r, a, b, res16, true);
    }

    public static int add_flag(Registry r, int a, int b, int ans16, boolean add) {
        int index = ((a & x88) >> 1) |
                    ((b & x88) >> 2) |
                    ((ans16 & x88) >> 3);
        int ans = ans16 & xFF;
        r.ts((ans & x80) != 0);
        r.tz(ans == 0);
        if (add) {
            r.th(half_carry_table[index & x07]);
        } else {
            r.th(!sub_half_carry_table[index & x07]);
        }
        r.tp(parity[ans]);
        r.tc((ans16 & x100) != 0);
        return ans;
    }
}