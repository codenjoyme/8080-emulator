package spec;

import java.awt.*;
import java.net.URL;

import static spec.Constants.x10000;

public class Hardware {

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
        keyLogger = createKeyLogger();
        ports = createIoPorts();
        record = createKeyRecord();
        data = createHardwareData();
        cpu = createCpu(1.6); // Specialist runs at 3.5Mhz;
        video = createVideo();
        roms = createRomLoader();
    }

    // components

    protected RomLoader createRomLoader() {
        return new RomLoader(memory, cpu);
    }

    protected Video createVideo() {
        return new Video(this::drawPixel);
    }

    protected Cpu createCpu(double clock) {
        return new Cpu(clock, data, this::cpuInterrupt, record::accept);
    }

    protected HardwareData createHardwareData() {
        return new HardwareData(this) {
            @Override
            public void out8(int port, int bite) {
                Hardware.this.out8(port, bite);
            }
        };
    }

    protected KeyRecord createKeyRecord() {
        return new KeyRecord(ports, this::stop);
    }

    protected IOPorts createIoPorts() {
        return new IOPorts(memory, new Layout(), keyLogger::process);
    }

    protected KeyLogger createKeyLogger() {
        return new KeyLogger(() -> cpu.tick());
    }

    protected Memory createMemory() {
        return new Memory(x10000);
    }

    // logic

    private boolean cpuInterrupt() {
        update();
        return cpuEnabled;
    }

    public void stop() {
        cpuEnabled = false;
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

    public void loadSnapshot(URL base, String snapshot) {
        roms.loadSnapshot(base, snapshot);
        ports.resetKeyboard();
    }

    // components getters

    public RomLoader roms() {
        return roms;
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