package spec;

import java.awt.*;

import static spec.Constants.*;

public abstract class Hard {

    private static final double CLOCK = 1.6; // Specialist runs at 3.5Mhz;

    private Memory memory;
    private Cpu cpu;
    protected RomLoader roms;
    protected IOPorts ports;
    protected Video video;

    public Hard() {
        memory = new Memory(x10000);
        video = new Video(Hard.this::drawPixel);
        cpu = new Cpu(CLOCK, new Data() {
            @Override
            public boolean interrupt() {
                return Hard.this.interrupt();
            }

            @Override
            public void out8(int port, int bite) {
                Hard.this.outb(port, bite);
            }

            @Override
            public int read8(int addr) {
                return Hard.this.read8(addr);
            }

            @Override
            public void write8(int addr, int bite) {
                Hard.this.write8(addr, bite);
            }
        });
        ports = new IOPorts(memory);
        roms = new RomLoader(memory, cpu);
    }

    protected abstract void outb(int port, int bite);

    protected abstract boolean interrupt();

    protected abstract void drawPixel(Point point, Color color);

    public void reset() {
        cpu.reset();
        ports.reset();
    }

    public void start() {
        cpu.execute();
    }

    private int read8(int addr) {
        if (PORTS.includes(addr)) {
            return ports.inPort(addr);
        }
        return memory.read8(addr);
    }

    private void write8(int addr, int bite) {
        if (ROM.includes(addr)) {
            // в ПЗУ не пишем
            return;
        }

        if (SCREEN.includes(addr)) {
            if (memory.read8(addr) != bite) {
                // было изменение ячейки видеопамяти
                video.plot(addr, bite);
            }
        }

        if (PORTS.includes(addr)) {
            ports.outPort(addr, bite);
        }

        memory.write8(addr, bite);
    }

}