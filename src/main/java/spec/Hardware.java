package spec;

import java.awt.*;
import java.net.URL;

import static spec.Constants.*;

public class Hardware {

    public static double CLOCK = 1.6; // Specialist runs at 3.5Mhz;

    private Memory memory;
    private Cpu cpu;
    protected RomLoader roms;
    protected IOPorts ports;
    protected Video video;
    private KeyLogger keyLogger;
    private KeyRecord record;

    private boolean cpuEnabled;

    public Hardware() {
        memory = createMemory();

        keyLogger = new KeyLogger(() -> cpu.tick());

        ports = new IOPorts(memory, new Layout(), keyLogger::process);

        record = new KeyRecord(ports, this::stop);

        cpu = new Cpu(CLOCK, new Data() {

            @Override
            public boolean interrupt() {
                Hardware.this.update();
                return cpuEnabled;
            }

            @Override
            public void out8(int port, int bite) {
                Hardware.this.out8(port, bite);
            }

            @Override
            public int read8(int addr) {
                return Hardware.this.read8(addr);
            }

            @Override
            public void write8(int addr, int bite) {
                Hardware.this.write8(addr, bite);
            }

        }, record::accept);

        video = new Video(Hardware.this::drawPixel);

        roms = new RomLoader(memory, cpu);
    }

    public void stop() {
        cpuEnabled = false;
    }

    protected Memory createMemory() {
        return new Memory(x10000);
    }

    protected void out8(int port, int bite) {
        // please override if needed
    }

    protected void update() {
        // please override if needed
    }

    protected void drawPixel(Point point, Color color) {
        // please override if needed
    }

    public void reset() {
        cpuEnabled = true;
        cpu.reset();
        ports.reset();
        keyLogger.reset();
    }

    public void start() {
        cpuEnabled = true;
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

    public Cpu cpu() {
        return cpu;
    }

    public Memory memory() {
        return memory;
    }
}
