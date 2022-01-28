package spec.assembler;

import spec.WordMath;
import spec.assembler.command.copy.*;
import spec.assembler.command.jump.*;
import spec.assembler.command.math.bits.RAL;
import spec.assembler.command.math.bits.RAR;
import spec.assembler.command.math.bits.RLC;
import spec.assembler.command.math.bits.RRC;
import spec.assembler.command.math.flag.*;
import spec.assembler.command.math.incdec.DCR_R;
import spec.assembler.command.math.incdec.DCX_RR;
import spec.assembler.command.math.incdec.INR_R;
import spec.assembler.command.math.incdec.INX_RR;
import spec.assembler.command.math.logic.*;
import spec.assembler.command.math.sum.*;
import spec.assembler.command.port.IN_XX;
import spec.assembler.command.port.OUT_XX;
import spec.assembler.command.procedure.call.*;
import spec.assembler.command.procedure.ret.*;
import spec.assembler.command.stack.POP_RS;
import spec.assembler.command.stack.PUSH_RS;
import spec.assembler.command.system.DI;
import spec.assembler.command.system.EI;
import spec.assembler.command.system.NONE;
import spec.assembler.command.system.NOP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Assembler {

    private static final Command[] COMMANDS; static {
        COMMANDS = new Command[0x100];

        add(new LDA_XXYY());
        add(new LDAX_RR());
        add(new LHLD_XXYY());
        add(new LXI_RR_XXYY());
        add(new MOV_R_R());
        add(new MVI_R_XX());
        add(new PCHL());
        add(new SHLD_XXYY());
        add(new SPHL());
        add(new STA_XXYY());
        add(new STAX_RR());
        add(new XCHG());
        add(new XTHL());

        add(new JC_XXYY());
        add(new JM_XXYY());
        add(new JMP_XXYY());
        add(new JNC_XXYY());
        add(new JNZ_XXYY());
        add(new JP_XXYY());
        add(new JPE_XXYY());
        add(new JPO_XXYY());
        add(new JZ_XXYY());

        add(new RLC());
        add(new RRC());
        add(new RAL());
        add(new RAR());

        add(new CMA());
        add(new CMC());
        add(new CMP_R());
        add(new CPI_XX());
        add(new DAA());
        add(new STC());

        add(new DCR_R());
        add(new DCX_RR());
        add(new INR_R());
        add(new INX_RR());

        add(new ANA_R());
        add(new ANI_XX());
        add(new ORA_R());
        add(new ORI_XX());
        add(new XRA_R());
        add(new XRI_XX());

        add(new ACI_XX());
        add(new ADC_R());
        add(new ADD_R());
        add(new ADI_XX());
        add(new DAD_RR());
        add(new SBB_R());
        add(new SBI_XX());
        add(new SUB_R());
        add(new SUI_XX());

        add(new OUT_XX());
        add(new IN_XX());

        add(new CALL_XXYY());
        add(new CC_XXYY());
        add(new CM_XXYY());
        add(new CNC_XXYY());
        add(new CNZ_XXYY());
        add(new CP_XXYY());
        add(new CPE_XXYY());
        add(new CPO_XXYY());
        add(new CZ_XXYY());
        add(new RST_N());

        add(new RC());
        add(new RET());
        add(new RM());
        add(new RNC());
        add(new RNZ());
        add(new RP());
        add(new RPE());
        add(new RPO());
        add(new RZ());

        add(new POP_RS());
        add(new PUSH_RS());

        add(new DI());
        add(new EI());
        // add(new HLT()); // TODO добавить команду
        add(new NONE());
        add(new NOP());
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

    public String dizAssembly(String programBites) {
        List<String> result = new LinkedList<>();
        programBites = programBites.replace("\n", " ");
        for (List<Integer> commandBites : split(programBites)) {
            for (Command command : COMMANDS) {
                if (command == null) continue;

                if (command.itsMe(commandBites)) {
                    result.add(command.print(commandBites));
                    break;
                }
            }
        }
        return String.join("\n", result) + "\n";
    }

    public String assembly(String program) {
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

    public List<List<Integer>> split(String input) {
        List<Integer> bites = Arrays.stream(input.split(" "))
                .map(bite -> Integer.parseInt(bite, 16))
                .collect(toList());

        List<List<Integer>> result = new LinkedList<>();
        while (!bites.isEmpty()) {
            for (Command command : COMMANDS) {
                if (command == null) continue;

                List<Integer> commandBits = command.take(bites);
                if (commandBits.isEmpty()) {
                    continue;
                }

                result.add(commandBits);
                break;
            }
        }
        return result;
    }

    public static String asString(List<List<Integer>> bites) {
        List<String> result = new LinkedList<>();

        for (List<Integer> command : bites) {
            result.add(command.stream()
                    .map(WordMath::hex8)
                    .collect(joining(" ")));
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