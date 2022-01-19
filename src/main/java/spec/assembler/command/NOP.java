package spec.assembler.command;

import spec.assembler.Command;

import java.util.Arrays;
import java.util.List;

public class NOP extends Command {

    @Override
    public List<Integer> codes() {
        return Arrays.asList(0x00);
    }

    @Override
    public String pattern() {
        return "NOP";
    }
}