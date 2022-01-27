package spec.assembler;

import spec.WordMath;
import spec.assembler.command.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Assembler {

    private static final Command[] COMMANDS; static {
        COMMANDS = new Command[0x100];
        add(new NONE());
        add(new NOP());
        add(new LXI_RR_XXYY());
        add(new DAD_RR());
        add(new STAX_RR());
        add(new LDAX_RR());
        add(new SHLD_XXYY());
        add(new LHLD_XXYY());
        add(new STA_XXYY());
        add(new LDA_XXYY());
        add(new INX_RR());
        add(new DCX_RR());
        add(new INR_R());
        add(new DCR_R());
        add(new MVI_R_XX());
        add(new MOV_R_R());
        add(new ADD_R());
        add(new ADI_XX());
        add(new ADC_R());
        add(new ACI_XX());
        add(new RLC());
        add(new RRC());
        add(new RAL());
        add(new RAR());
        add(new DAA());
        add(new SUB_R());
        add(new SUI_XX());
        add(new SBB_R());
        add(new SBI_XX());
        add(new CMA());
        add(new STC());
        add(new CMC());
        add(new ANA_R());
        add(new ANI_XX());
        add(new XRA_R());
        add(new XRI_XX());
        add(new ORA_R());
        add(new ORI_XX());
        add(new CMP_R());
        add(new CPI_XX());
        add(new POP_RS());
    }

    private static void add(Command command) {
        for (int code : command.codes()) {
            if (COMMANDS[code] != null) {
                throw new IllegalArgumentException(String.format(
                        "There is already such a command '%s' with code %s " +
                        "instead of new '%s'",
                        COMMANDS[code].pattern(),
                        code,
                        command.pattern()));
            }
            COMMANDS[code] = command;
        }
    }

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
        for (Command command : COMMANDS) {
            if (command == null) continue;

            Optional<String> result = command.parse(input);
            if (result.isPresent()) {
                return result.get();
            }
        }
        throw new UnsupportedOperationException("Unsupported command: " + input);
    }

    public String split(String input) {
        List<Integer> bites = Arrays.stream(input.split(" "))
                .map(bite -> Integer.parseInt(bite, 16))
                .collect(toList());

        List<String> result = new LinkedList<>();
        while (!bites.isEmpty()) {
            for (Command command : COMMANDS) {
                if (command == null) continue;

                List<Integer> commandBits = command.take(bites);
                if (commandBits.isEmpty()) {
                    continue;
                }

                result.add(commandBits.stream()
                        .map(WordMath::hex8)
                        .collect(joining(" ")));
                break;
            }
        }
        return String.join("\n", result);
    }

    public Command find(int bite) {
        Command command = COMMANDS[bite];
        return command == null || command.disabled()
                ? null
                : command;
    }
}