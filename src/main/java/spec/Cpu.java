package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static spec.WordMath.hex8;
import static spec.mods.Event.CHANGE_PC;

public class Cpu extends Registry {

    private Consumer<Cpu> onTick;
    private Supplier<Boolean> onInterrupt;
    private int interrupt; // количество тактов на 1 прерывание
    private int tick;      // количество операций, которые сделал процессор
    private int tact;
    private Assembler asm;
    private CpuDebug debug;
    private boolean enabled;

    public Cpu(int ticksPerInterrupt, Data data, Supplier<Boolean> onInterrupt, Consumer<Cpu> onTick) {
        super(data);
        interrupt = ticksPerInterrupt;
        this.onInterrupt = onInterrupt;
        asm = new Assembler();
        this.onTick = onTick;
        debug = new CpuDebug(asm, data, this);
    }

    @Override
    public void reset() {
        super.reset();
        enabled();
        tick = 0;
    }

    /**
     * @return Количество операций, которые сделал cpu.
     */
    public int tick() {
        return tick;
    }

    public int tact() {
        return tact;
    }

    public void tact(int value) {
        tact = value;
    }

    public void execute() {
        tact = 0; // счетчик тактов команд (из него будут извлекаться this.interrupt)

        while (enabled) {
            if (onTick != null) {
                onTick.accept(this);
            }
            tick++;

            if (tact >= interrupt) {
                while (tact >= interrupt) {
                    tact -= interrupt;
                }
                if (!onInterrupt.get()) {
                    break;
                }
            }

            debug.log();
            int bite = data.read8(PC());
            Command command = asm.find(bite);
            on(CHANGE_PC, command, this);
            if (command == null) {
                throw new IllegalArgumentException("Unknown command: " + hex8(bite));
            }
            rPC.inc16();
            command.apply(bite, this);
            // каждая операция уменьшает число тактов на
            // прерывание на свою длительность в тактах
            tact += command.ticks(bite);
        }

        enabled();
    }

    public Assembler asm() {
        return asm;
    }

    public CpuDebug debug() {
        return debug;
    }

    public void disabled() {
        enabled = false;
    }

    public void enabled() {
        enabled = true;
    }

    // TODO дублирование с CpuDebug.log(int callDeep)
    public String command() {
        int addr = PC();
        List<Integer> bites = data.read3x8(addr);
        Command command = asm.find(bites.get(0));
        bites = bites.subList(0, command.size());
        return asm.dizAssembly(bites);
    }
}