package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import static spec.WordMath.hex;
import static spec.WordMath.reverse;

public class SHLD_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x22);

    @Override
    public List<Integer> code(Matcher matcher) {
        return new LinkedList<Integer>(){{
            addAll(codes());
            addAll(reverse(hex(matcher.group(1))));
        }};
    }

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "SHLD (....)";
    }

    @Override
    public int ticks() {
        return 16;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public void apply(int command, Registry r) {
        r.accessor().pokew(r.accessor().peekwi(r.rPC), r.HL());
    }
}