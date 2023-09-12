package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.Parity.half_carry_table;
import static spec.assembler.Parity.parity;

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
        int work16 = a + b + c;
        int index = ((a & 0x88) >> 1) |
                    ((b & 0x88) >> 2) |
                    ((work16 & 0x88) >> 3);
        int res = work16 & 0xff;
        r.ts((res & 0x80) != 0);
        r.tz(res == 0);
        r.th(half_carry_table[index & 0x7]);
        r.tp(parity[res]);
        r.tc((work16 & 0x0100) != 0);
        return res;
    }
}