package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;

import java.util.LinkedList;
import java.util.List;

import static spec.Constants.CPU_TRACE;
import static spec.WordMath.hex;
import static spec.WordMath.hex16;

public class CpuDebug {

    private Data data;
    private Assembler asm;
    private List<String> lines;

    public CpuDebug(Assembler asm, Data data) {
        this.data = data;
        this.asm = asm;
        lines = new LinkedList<>();
    }

    public void log(int addr) {
        if (!CPU_TRACE) return;

        List<Integer> bites = data.read3x8(addr);
        Command command = asm.find(bites.get(0));
        bites = bites.subList(0, command.size());
        String out = String.format("%s  %s  %s",
                hex16(addr),
                pad(hex(bites), 9),
                asm.dizAssembly(bites));
        lines.add(out);
        Logger.debug(out);
    }

    private String pad(String string, int length) {
        while (string.length() < length) {
            string += " ";
        }
        return string;
    }

    public List<String> lines() {
        return lines;
    }
}