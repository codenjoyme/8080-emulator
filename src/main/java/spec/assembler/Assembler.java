package spec.assembler;

import spec.assembler.command.LXI_B_XXYY;
import spec.assembler.command.NOP;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
            String bites = parseCommand(command);
            if (first) {
                first = false;
            } else {
                result.append(' ');
            }
            result.append(bites);
        }
        return result.toString();
    }

    private String parseCommand(String inputCommand) {
        for (Command command : commands) {
            Optional<String> result = command.parse(inputCommand);
            if (result.isPresent()) {
                return result.get();
            }
        }
        throw new UnsupportedOperationException("Unknown command: " + inputCommand);
    }
}