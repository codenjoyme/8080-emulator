package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class LDAX_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x0A, 0x1A);

    @Override
    public List<Integer> code(Matcher matcher) {
        return new LinkedList<Integer>(){{
            add(codes().get(registers().indexOf(matcher.group(1))));
        }};
    }

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public List<String> registers() {
        return Arrays.asList("B", "D");
    }

    @Override
    public String pattern() {
        return "LDAX (B|D)";
    }

    @Override
    public int ticks() {
        return 7;
    }

    @Override
    public void apply(int command, Registry r) {
        int op = r.reg(rindex(command)).get();
        r.A(r.data().peekb(op));
    }
}