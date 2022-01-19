package spec.assembler.command;

import spec.assembler.Command;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import static spec.WordMath.hex;

public class LXI_B_XXYY extends Command {

    @Override
    public List<Integer> code(Matcher matcher) {
        return new LinkedList<Integer>(){{
            add(0x01);
            addAll(hex(matcher.group(1)));
        }};
    }

    @Override
    public String pattern() {
        return "LXI B,(....)";
    }

    @Override
    public int size() {
        return 3;
    }
}
