package spec.assembler.command.port;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class OUT_XX extends Command  {

    private static final List<Integer> CODES = Arrays.asList(
            0xD3);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "OUT (..)";
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public int ticks() {
        return 11;
    }

    @Override
    public void apply(int command, Registry r) {
        int port = r.data().read8(r.rPC);
        r.data().out8(port, r.A());
    }
}