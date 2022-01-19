package spec.assembler;

import spec.assembler.command.LXI_R_XXYY;
import spec.assembler.command.NOP;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Assembler {

    private List<Command> commands = Arrays.asList(
            new NOP(),
            new LXI_R_XXYY()
    );

    public String parse(String program) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (String command : program.split("\n")) {
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

    private String parseCommand(String input) {
        for (Command command : commands) {
            Optional<String> result = command.parse(input);
            if (result.isPresent()) {
                return result.get();
            }
        }
        throw new UnsupportedOperationException("Unsupported command: " + input);
    }
}