package spec;

import java.awt.*;
import java.net.URL;

import static spec.Constants.*;

public abstract class Hardware {

    private static final double CLOCK = 1.6; // Specialist runs at 3.5Mhz;

    private Memory memory;
    private Cpu cpu;
    protected RomLoader roms;
    protected IOPorts ports;
    protected Video video;
    private KeyLogger keyLogger;
    private KeyRecord record;

    private boolean cpuEnabled;

    public Hardware() {
        memory = new Memory(x10000);

        keyLogger = new KeyLogger(() -> cpu.tick());

        ports = new IOPorts(memory, new Layout(), keyLogger::process);

        record = new KeyRecord(ports, null, () -> cpuEnabled = false);

        cpu = new Cpu(CLOCK, new Data() {
            @Override
            public boolean interrupt() {
                return Hardware.this.interrupt();
            }

            @Override
            public void out8(int port, int bite) {
                Hardware.this.outb(port, bite);
            }

            @Override
            public int read8(int addr) {
                return Hardware.this.read8(addr);
            }

            @Override
            public void write8(int addr, int bite) {
                Hardware.this.write8(addr, bite);
            }
        }, tick -> record.accept(tick));

        video = new Video(Hardware.this::drawPixel);

        roms = new RomLoader(memory, cpu);
    }

    protected abstract void outb(int port, int bite);

    protected boolean interrupt() {
        return cpuEnabled;
    }

    protected abstract void drawPixel(Point point, Color color);

    public void reset() {
        cpuEnabled = true;
        cpu.reset();
        ports.reset();
        keyLogger.reset();
    }

    public void start() {
        cpu.execute();
    }

    private int read8(int addr) {
        if (PORTS.includes(addr)) {
            return ports.read8(addr);
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
            ports.write8(addr, bite);
        }

        memory.write8(addr, bite);
    }

    public RomLoader roms() {
        return roms;
    }

    public void loadSnapshot(URL base, String snapshot) {
        roms.loadSnapshot(base, snapshot);
        ports.resetKeyboard();
    }

    public KeyRecord record() {
        return record;
    }
}
