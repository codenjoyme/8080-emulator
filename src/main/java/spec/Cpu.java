package spec;

import spec.assembler.Assembler;
import spec.assembler.Command;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Cpu extends Registry {

    private Consumer<Cpu> onTick;
    private Supplier<Boolean> onInterrupt;
    private int interrupt;
    private int tick;
    private Assembler asm;
    private CpuDebug debug;
    private boolean enabled;

    public Cpu(double clockFrequencyInMHz, Data data, Supplier<Boolean> onInterrupt, Consumer<Cpu> onTick) {
        super(data);
        // Количество тактов на 1 прерывание, которое происходит 50 раз в секунду.
        // 1000000/50 раз в секунду
        interrupt = (int) ((clockFrequencyInMHz * 1e6) / 50);
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

    // TODO переименовать, это не tick а commandCount
    public int tick() {
        return tick;
    }

    public void execute() {
        // закладываем время до прерывания
        int ticks = 0;

        // цикл выборки/выполнения
        while (enabled) {
            if (onTick != null) {
                onTick.accept(this);
            }
            tick++;

            if (ticks >= interrupt) {
                while (ticks >= interrupt) {
                    ticks -= interrupt;
                }
                if (!onInterrupt.get()) {
                    break;
                }
            }

            on("PC");
            debug.log(PC());
            int bite = data.read8(rPC);
            Command command = asm.find(bite);
            if (command != null) {
                command.apply(bite, this);
                // каждая операция уменьшает число тактов на
                // прерывание на свою длительность в тактах
                ticks += command.ticks();
                continue;
            }

            if (bite == 0x76) { // TODO выделить эту команду
                int haltsToInterrupt = (-ticks - 1) / 4 + 1;
                ticks += haltsToInterrupt * 4;
                enabled = false;
                continue;
            }
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
}