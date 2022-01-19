package spec.assembler.command;

import spec.assembler.Command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import static spec.WordMath.hex;
import static spec.WordMath.reverse;

public class LXI_R_XXYY extends Command {

    @Override
    public List<Integer> code(Matcher matcher) {
        return new LinkedList<Integer>(){{
            add(codes().get(registers().indexOf(matcher.group(1))));
            addAll(reverse(hex(matcher.group(2))));
        }};
    }

    @Override
    public List<Integer> codes() {
        return Arrays.asList(0x01, 0x11, 0x21, 0x31);
    }

    private List<String> registers() {
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
}