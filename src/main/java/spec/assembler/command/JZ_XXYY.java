package spec.assembler.command;

import spec.Registry;
import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

import static spec.assembler.command.JMP_XXYY.jmp_if;

public class JZ_XXYY extends Command {

    private static final List<Integer> CODES = Arrays.asList(
            0xCA);

    @Override
    public List<Integer> codes() {
        return CODES;
    }

    @Override
    public String pattern() {
        return "JZ (....)";
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
        jmp_if(r, reg -> reg.tz());
    }
}