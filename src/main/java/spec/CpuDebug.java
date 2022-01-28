package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;

import java.util.LinkedList;
import java.util.List;

import static spec.WordMath.hex;
import static spec.WordMath.hex16;

public class CpuDebug {

    private Data data;
    private Assembler asm;
    private Registry registry;
    private List<String> lines;
    private boolean enabled;

    public CpuDebug(Assembler asm, Data data, Registry registry) {
        this.data = data;
        this.asm = asm;
        this.registry = registry;
        lines = new LinkedList<>();
        disable();
    }

    public void log(int addr) {
        if (!enabled) return;

        List<Integer> bites = data.read3x8(addr);
        Command command = asm.find(bites.get(0));
        bites = bites.subList(0, command.size());
        String out = String.format("%s%s%s%s%s",
                pad(hex16(addr), 6),
                pad(hex(bites), 10),
                pad(asm.dizAssembly(bites), 12),
                pad("(" + registry.callDeep() + ")", 5),
                pad(registry.toString().replace("\n", "  ").replace(": ", ":"), 55));
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

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }
}