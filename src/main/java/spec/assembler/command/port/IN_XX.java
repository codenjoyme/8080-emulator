package spec.assembler.command.port;

import spec.Registry;
import spec.assembler.Command;

import java.util.List;

// TODO test me
public class IN_XX extends Command {

    private static final List<Integer> CODES = from(
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
    public int ticks(int command) {
        return 11;
    }

    @Override
    public void apply(int command, Registry r) {
        int port = (r.A() << 8) | r.attr8();
        int bite = r.in8(port);
        r.A(bite);
    }
}