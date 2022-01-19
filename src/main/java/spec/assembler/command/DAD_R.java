package spec.assembler.command;

import spec.assembler.Command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class DAD_R extends Command {

    @Override
    public List<Integer> code(Matcher matcher) {
        return new LinkedList<Integer>(){{
            add(codes().get(registers().indexOf(matcher.group(1))));
        }};
    }

    private List<String> registers() {
        return Arrays.asList("B", "D", "H", "SP");
    }

    @Override
    public List<Integer> codes() {
        return Arrays.asList(0x09, 0x19, 0x29, 0x39);
    }

    @Override
    public String pattern() {
        return "DAD (B|D|H|SP)";
    }

    @Override
    public int size() {
        return 1;
    }
}