package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;
import spec.mods.CallDeep;

import java.util.LinkedList;
import java.util.List;

import static spec.WordMath.*;

public class CpuDebug {

    private Data data;
    private Assembler asm;
    private Registry registry;
    private List<String> lines;
    private boolean enabled;
    private boolean console;
    private int maxDeepCall;
    private Range range;

    public CpuDebug(Assembler asm, Data data, Registry registry) {
        this.data = data;
        this.asm = asm;
        this.registry = registry;
        lines = new LinkedList<>();
        disable();
        console(true);
        showCallBellow(Integer.MAX_VALUE);
    }

    public void console(boolean print) {
        this.console = print;
    }

    public void log(int addr) {
        if (!enabled) return;
        if (!range.includes(addr)) return;

        int callDeep = registry.mod(CallDeep.class).callDeep();
        if (callDeep >= maxDeepCall) return;

        List<Integer> bites = data.read3x8(addr);
        Command command = asm.find(bites.get(0));
        bites = bites.subList(0, command.size());
        String out = String.format("%s%s%s%s%s",
                padRight(hex16(addr), 6, ' '),
                padRight(hex(bites), 10, ' '),
                padRight(asm.dizAssembly(bites), 12, ' '),
                padRight("(" + callDeep + ")", 5, ' '),
                padRight(registry.toString().replace("\n", "  ").replace(": ", ":"), 55, ' '));
        lines.add(out);
        if (console) {
            Logger.debug(out);
        }
    }

    public List<String> lines() {
        return lines;
    }

    public void enable() {
        enable(new Range(0x0000, 0xFFFF));
    }

    public void enable(Range range) {
        this.range = range;
        enabled = true;
        registry.modAdd(new CallDeep());
    }

    public void disable() {
        enabled = false;
        registry.modRemove(CallDeep.class);
    }

    public void showCallBellow(int deepCall) {
        this.maxDeepCall = deepCall;
    }
}