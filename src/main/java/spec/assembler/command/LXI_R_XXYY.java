package spec.assembler.command;

import spec.assembler.Command;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import static spec.WordMath.hex;
import static spec.WordMath.reverse;

public class LXI_R_XXYY extends Command {

    @Override
    public List<Integer> code(Matcher matcher) {
        return new LinkedList<Integer>(){{
            add(code(matcher.group(1)));
            addAll(reverse(hex(matcher.group(2))));
        }};
    }

    private Integer code(String reg) {
        switch (reg) {
            case "B": return 0x01;
            case "D": return 0x11;
            case "H": return 0x21;
            case "SP": return 0x31;
        }
        throw new UnsupportedOperationException("Unsupported registry: " + reg);
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