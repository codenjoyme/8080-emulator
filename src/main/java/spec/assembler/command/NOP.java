package spec.assembler.command;

import spec.assembler.Command;

import java.util.regex.Matcher;

public class NOP extends Command {

    @Override
    public int[] code(Matcher matcher) {
        return new int[]{ 0x00 };
    }

    @Override
    public String pattern() {
        return "NOP";
    }
}