package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.Parity.*;

// TODO test me
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
        int index = ((a & 0x88) >> 1) |
                    ((b & 0x88) >> 2) |
                    ((ans16 & 0x88) >> 3);
        int ans = ans16 & 0xFF;
        r.ts((ans & 0x80) != 0);
        r.tz(ans == 0);
        if (add) {
            r.th(half_carry_table[index & 0x07]);
        } else {
            r.th(!sub_half_carry_table[index & 0x07]);
        }
        r.tp(parity[ans]);
        r.tc((ans16 & 0x0100) != 0);
        return ans;
    }
}