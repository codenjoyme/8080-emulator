package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;

import java.util.List;

import static spec.Constants.CPU_TRACE;
import static spec.WordMath.hex;
import static spec.WordMath.hex16;

public class CpuDebug {

    private Data data;
    private Assembler asm;

    public CpuDebug(Assembler asm, Data data) {
        this.data = data;
        this.asm = asm;
    }

    public void log(int addr) {
        if (!CPU_TRACE) return;

        List<Integer> bites = data.read3x8(addr);
        Command command = asm.find(bites.get(0));
        bites = bites.subList(0, command.size());
        Logger.debug("%s  %s  %s",
                hex16(addr),
                pad(hex(bites), 9),
                asm.dizAssembly(bites)
        );
    }

    private String pad(String string, int length) {
        while (string.length() < length) {
            string += " ";
        }
        return string;
    }
}
