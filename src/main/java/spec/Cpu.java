package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;
import spec.math.Bites;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static spec.math.WordMath.hex8;
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
        enable();
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

        enable();
    }

    public Assembler asm() {
        return asm;
    }

    public CpuDebug debug() {
        return debug;
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }

    public Bites commandBites() {
        int addr = PC();
        int commandBite = data.read8(addr);
        Command command = asm.find(commandBite);
        if (command.size() == 1) {
            return Bites.of(commandBite);
        }
        int lo = data.read8(addr + 1);
        if (command.size() == 2) {
            return Bites.of(commandBite, lo);
        }
        int hi = data.read8(addr + 2);
        return Bites.of(commandBite, lo, hi);
    }
}