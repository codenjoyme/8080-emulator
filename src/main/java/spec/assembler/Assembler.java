package spec.assembler;

import spec.assembler.command.NOP;

import java.util.Arrays;
import java.util.List;

import static spec.Memory.hex;

public class Assembler {

    private List<Command> commands = Arrays.asList(new NOP());

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
            if (command.name().equals(inputCommand)) {
                return command.code(new int[0]);
            }
        }
        return new int[0];
    }
}