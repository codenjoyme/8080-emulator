package spec;

import java.awt.*;
import java.net.URL;

import static spec.Constants.x10000;

public class Hardware {

    public static double CLOCK = 1.6; // Specialist runs at 3.5Mhz;

    private Memory memory;
    private Cpu cpu;
    protected RomLoader roms;
    private HardwareData data;
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

        data = new HardwareData(this) {
            @Override
            public void out8(int port, int bite) {
                Hardware.this.out8(port, bite);
            }
        };

        cpu = new Cpu(CLOCK, data, this::cpuInterrupt, record::accept);

        video = new Video(this::drawPixel);

        roms = new RomLoader(memory, cpu);
    }

    private boolean cpuInterrupt() {
        update();
        return cpuEnabled;
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

    public IOPorts ports() {
        return ports;
    }

    public Video video() {
        return video;
    }
}
