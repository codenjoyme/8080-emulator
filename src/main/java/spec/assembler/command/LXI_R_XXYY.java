package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static spec.WordMath.hex;
import static spec.WordMath.reverse;

public class LXI_R_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x01, 0x11, 0x21, 0x31);

    @Override
    public List<Integer> code(String... params) {
        return new LinkedList<Integer>(){{
            add(codes().get(registers().indexOf(params[0])));
            addAll(reverse(hex(params[1])));
        }};
    }

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return Arrays.asList("B", "D", "H", "SP");
    }

    @Override
    public String pattern() {
        return "LXI (B|D|H|SP),(....)";
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public int ticks() {
        return 10;
    }

    @Override
    public void apply(int command, Registry r) {
        int bite = r.data().peekwi(r.rPC);
        r.reg(rindex(command)).set(bite);
    }
}