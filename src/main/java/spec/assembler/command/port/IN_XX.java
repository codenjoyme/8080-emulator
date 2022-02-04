package spec.assembler.command.port;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class IN_XX extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xDB);

    @Override
    public List<Integer> codes() {
        return CODES;
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
        int port = (r.A() << 8) | r.attr8();
        int bite = r.in8(port);
        r.A(bite);
    }
}