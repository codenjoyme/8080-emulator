package spec;

import java.awt.*;
import java.io.File;
import java.net.URL;

import static spec.Constants.*;

public class Hardware {

    private Memory memory;
    private Cpu cpu;
    private RomLoader roms;
    private HardwareData data;
    private IOPorts ports;
    private Video video;
    private KeyLogger keyLogger;
    private KeyRecord record;
    private FileRecorder fileRecorder;

    private boolean cpuEnabled;
    private boolean cpuSuspended;

    public Hardware(int screenWidth, int screenHeight) {
        memory = createMemory();
        fileRecorder = createFileRecorder(logFile());
        keyLogger = createKeyLogger(recordPrecision());
        ports = createIoPorts();
        record = createKeyRecord(recordPrecision());
        data = createHardwareData();
        cpu = createCpu(1.6); // runs at 3.5Mhz;
        video = createVideo(screenWidth, screenHeight);
        roms = createRomLoader();
    }

    // components
    protected RomLoader createRomLoader() {
        return new RomLoader(memory, cpu);
    }

    protected Video createVideo(int width, int height) {
        Video video = new Video(width, height);
        video.drawer(this::drawPixel);
        return video;
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

    protected FileRecorder createFileRecorder(File logFile) {
        return new FileRecorder(logFile);
    }

    protected KeyRecord createKeyRecord(int precision) {
        return new KeyRecord(fileRecorder, precision, ports, this::stop);
    }

    protected IOPorts createIoPorts() {
        return new IOPorts(memory, new Layout(), keyLogger::process);
    }

    protected KeyLogger createKeyLogger(int precision) {
        return new KeyLogger(fileRecorder, precision, () -> cpu.tick());
    }

    protected Memory createMemory() {
        return new Memory(x10000);
    }

    // logic

    private boolean cpuInterrupt() {
        update();
        while (cpuSuspended) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        return cpuEnabled;
    }

    public void stop() {
        cpuEnabled = false;
    }

    public void pause() {
        cpuSuspended = true;
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

    protected int recordPrecision() {
        // запись происходит с точностью до 1 тика cpu
        return RECORD_PRECISION;
    }

    protected File logFile() {
        return new File(RECORD_LOG_FILE);
    }

    public void reset() {
        cpuEnabled = true;
        cpuSuspended = false;
        cpu.reset();
        ports.reset();
        keyLogger.reset();
    }

    public void start() {
        cpuEnabled = true;
        cpuSuspended = false;
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