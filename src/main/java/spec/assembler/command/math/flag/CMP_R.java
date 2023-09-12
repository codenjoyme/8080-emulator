package spec.assembler.command.math.flag;

import spec.Reg;
import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.assembler.Parity.parity;
import static spec.assembler.Parity.sub_half_carry_table;

// TODO test me
public class CMP_R extends Command {

    private static final List<Integer> CODES = from(
            0xB8, 0xB9, 0xBA, 0xBB, 0xBC, 0xBD, 0xBE, 0xBF);

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
        cmp8(r, r.A(), reg.get());
    }

    public static void cmp8(Registry r, int a, int b) {
        int work16 = a - b;
        int index = ((a & 0x88) >> 1) |
                    ((b & 0x88) >> 2) |
                    ((work16 & 0x88) >> 3);
        r.ts((work16 & 0x80) != 0);
        r.tz((work16 & 0xff) == 0);
        r.th(!sub_half_carry_table[index & 0x7]);
        r.tc((work16 & 0x0100) != 0);
        r.tp(parity[work16 & 0xff]);
    }
}