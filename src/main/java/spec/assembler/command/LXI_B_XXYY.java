package spec.assembler.command;

import spec.assembler.Command;

import java.util.regex.Matcher;

public class LXI_B_XXYY extends Command {

    @Override
    public int[] code(Matcher matcher) {
        String value = matcher.group(1);
        int[] result = new int[1 + value.length() / 2];
        result[0] = 0x01;
        for (int i = 0; i < result.length - 1; i++) {
            result[i + 1] = Integer.parseInt(value.substring(i * 2, (i + 1) * 2), 16);
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
