package spec.assembler;

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
import spec.assembler.command.system.*;
import spec.math.Bites;

import java.util.LinkedList;
import java.util.List;

import static spec.math.WordMath.hex;
import static spec.math.WordMath.toBites;

public class Assembler {

    private static final Command[] COMMANDS;

    static {
        COMMANDS = new Command[0x100];

        add(new LDA_XXYY());
        add(new LDAX_RD());
        add(new LHLD_XXYY());
        add(new LXI_RR_XXYY());
        add(new MOV_R_R());
        add(new MVI_R_XX());
        add(new PCHL());
        add(new SHLD_XXYY());
        add(new SPHL());
        add(new STA_XXYY());
        add(new STAX_RD());
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
        add(new HLT());
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
        StringBuilder result = new StringBuilder();
        programBites = programBites.replace("\n", " ");
        for (Bites bites : split(programBites)) {
            String command = dizAssembly(bites);
            if (command != null) {
                result.append(command).append('\n');
            }
        }
        return result.toString();
    }

    public String dizAssembly(Bites bites) {
        return dizAssembly(bites, false);
    }

    public String dizAssembly(Bites bites, boolean canonical) {
        return COMMANDS[bites.get(0)]
                .print(bites, canonical);
    }

    public String assembly(String program) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (String command : program.split("\n")) {
            if (first) {
                first = false;
            } else {
                result.append(' ');
            }

            String bites = hex(parseCommand(command));
            result.append(bites);
        }
        return result.toString();
    }

    public Bites parseCommand(String asmCommand) {
        String name = asmCommand.split(" ")[0];
        for (Command command : COMMANDS) {
            if (command == null) continue;
            if (!command.name().equals(name)) continue;

            Bites result = command.parse(asmCommand);
            if (result != null) {
                return result;
            }
        }
        throw new UnsupportedOperationException("Unsupported command: " + asmCommand);
    }

    public List<Bites> split(String bites) {
        Bites array = toBites(bites);
        int index = 0;

        List<Bites> result = new LinkedList<>();
        while (index < array.size()) {
            Command command = COMMANDS[array.get(index)];
            Bites bitesArr = command.take(array, index);
            result.add(bitesArr);
            index += bitesArr.size();
        }
        return result;
    }

    public static String asString(List<Bites> bites) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Bites command : bites) {
            if (first) {
                first = false;
            } else {
                result.append('\n');
            }
            hex(result, command);
        }
        return result.toString();
    }

    public Command find(int bite) {
        Command command = COMMANDS[bite];
        return command == null || command.disabled()
                ? null
                : command;
    }
}