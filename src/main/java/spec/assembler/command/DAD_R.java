package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static spec.Constants.x10000;
import static spec.WordMath.word;

public class DAD_R extends Command {

    private static final List<Integer> CODES = Arrays.asList(0x09, 0x19, 0x29, 0x39);

    @Override
    public List<Integer> code(String... params) {
        return new LinkedList<Integer>(){{
            add(codes().get(registers().indexOf(params[0])));
        }};
    }

    @Override
    public List<String> registers() {
        return Arrays.asList("B", "D", "H", "SP");
    }

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "DAD (B|D|H|SP)";
    }

    @Override
    public int ticks() {
        return 11;
    }

    @Override
    public void apply(int command, Registry r) {
        int op2 = r.reg(rindex(command)).get();
        int value = calc(r, r.HL(), op2);
        r.HL(value);
    }

    private int calc(Registry r, int a, int b) {
        int lans = a + b;
        int ans = word(lans);

        r.tc((lans & x10000) != 0);

        return ans;
    }
}