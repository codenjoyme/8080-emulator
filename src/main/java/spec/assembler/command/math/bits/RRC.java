package spec.assembler.command.math.bits;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.x01;
import static spec.Constants.x80;

// TODO test me
public class RRC extends Command {

    private static final List<Integer> CODES = from(
            0x0F);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public int ticks(int command) {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        r.A(rrc(r, r.A()));
    }

    private int rrc(Registry r, int a) {
        int ans = a;
        boolean c = (ans & x01) != 0;

        if (c) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        r.th(false);
        r.tc(c);

        return ans;
    }
}