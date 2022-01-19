package spec.assembler;

import spec.assembler.command.LXI_B_XXYY;
import spec.assembler.command.NOP;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spec.Memory.hex;

public class Assembler {

    private List<Command> commands = Arrays.asList(
            new NOP(),
            new LXI_B_XXYY()
    );

    public String parse(String program) {
        String[] commands = program.split("\n");
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (String command : commands) {
            int[] bites = parseCommand(command);
            for (int i = 0; i < bites.length; i++) {
                if (first) {
                    first = false;
                } else {
                    result.append(' ');
                }
                result.append(hex(bites[i]));
            }
        }
        return result.toString();
    }

    private int[] parseCommand(String inputCommand) {
        for (Command command : commands) {
            Pattern pattern = Pattern.compile(command.pattern());
            Matcher matcher = pattern.matcher(inputCommand);
            if (!matcher.matches()) {
                continue;
            }
            switch (matcher.groupCount()) {
                case 0: {
                    return command.code(null);
                }
                case 1: {
                    String group = matcher.group(1);
                    int[] bits = new int[group.length() / 2];
                    for (int i = 0; i < bits.length; i++) {
                        bits[i] = Integer.parseInt(group.substring(i * 2, (i + 1) * 2), 16);
                    }
                    return command.code(bits);
                }
            }
        }
        return new int[0];
    }
}