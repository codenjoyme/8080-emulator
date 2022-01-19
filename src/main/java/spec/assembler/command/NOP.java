package spec.assembler.command;

import spec.assembler.Command;

public class NOP implements Command {

    @Override
    public int[] code(int[] data) {
        return new int[]{ 0x00 };
    }

    @Override
    public String name() {
        return "NOP";
    }
}
