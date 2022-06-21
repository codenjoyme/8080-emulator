package spec.assembler.command.math.sum;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x10000;
import static spec.WordMath.word;

public class DAD_RR extends Command {

    private static final List<Integer> CODES = from(
            0x09, 0x19, 0x29, 0x39);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return BDHSP;
    }

    @Override
    public int ticks(int command) {
        return 11;
    }

    @Override
    public void apply(int command, Registry r) {
        int op2 = rRR(command, r).get();
        int res = sum16(r, r.HL(), op2);
        r.HL(res);
    }

    private int sum16(Registry r, int a, int b) {
        int lans = a + b;
        int ans = word(lans);

        r.tc((lans & x10000) != 0);

        return ans;
    }
}