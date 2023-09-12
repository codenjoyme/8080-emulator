package spec.assembler.command.math.sum;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.Parity.parity;
import static spec.assembler.Parity.sub_half_carry_table;

// TODO test me
public class SBB_R extends Command {

    private static final List<Integer> CODES = from(
            0x98, 0x99, 0x9A, 0x9B, 0x9C, 0x9D, 0x9E, 0x9F);

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
        r.A(sub8(r, r.A(), reg.get(), r.tci()));
    }

    public static int sub8(Registry r, int a, int b, int c) {
        int work16 = a - b - c;
        int index = ((a & 0x88) >> 1) |
                    ((b & 0x88) >> 2) |
                    ((work16 & 0x88) >> 3);
        int res = work16 & 0xff;
        r.ts((res & 0x80) != 0);
        r.tz(res == 0);
        r.th(!sub_half_carry_table[index & 0x7]);
        r.tp(parity[res]);
        r.tc((work16 & 0x0100) != 0);
        return res;
    }
}