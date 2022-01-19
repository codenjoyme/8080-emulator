package spec.assembler.command;

import spec.assembler.Command;

public class LXI_B_XXYY implements Command {

    @Override
    public int[] code(int[] data) {
        int[] result = new int[size()];
        result[0] = 0x01;
        for (int i = 0; i < data.length; i++) {
            result[i + 1] = data[i];
        }
        return result;
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
