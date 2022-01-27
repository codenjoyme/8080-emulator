package spec.assembler.command.math.bits;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.Constants.x01;
import static spec.Constants.x80;

// TODO test me
public class RAR extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0x1F);

    @Override
    public List<Integer> codes() {
        return CODES;
    }
    
    @Override
    public String pattern() {
        return "RAR";
    }

    @Override
    public int ticks() {
        return 4;
    }

    @Override
    public void apply(int command, Registry r) {
        r.A(rar(r, r.A()));
    }

    private int rar(Registry r, int a) {
        int ans = a;
        boolean c = (ans & x01) != 0;

        if (r.tc()) {
            ans = (ans >> 1) | x80;
        } else {
            ans >>= 1;
        }

        r.th(false);
        r.tc(c);

        return ans;
    }
}