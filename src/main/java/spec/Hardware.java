package spec;

import java.awt.*;
import java.io.File;
import java.net.URL;

import static spec.Constants.RECORD_LOG_FILE;
import static spec.Constants.x10000;
import static spec.KeyCode.END;
import static spec.KeyCode.ENTER;
import static spec.WordMath.hex16;

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
        keyLogger = createKeyLogger();
        ports = createIoPorts();
        record = createKeyRecord();
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
            public int in8(int port) {
                return Hardware.this.in8(port);
            }

            @Override
            public void out8(int port, int bite) {
                Hardware.this.out8(port, bite);
            }
        };
    }

    protected FileRecorder createFileRecorder(File logFile) {
        return new FileRecorder(logFile);
    }

    protected KeyRecord createKeyRecord() {
        return new KeyRecord(fileRecorder, ports, this::stop);
    }

    protected IOPorts createIoPorts() {
        return new IOPorts(memory, new Layout(), keyLogger::process);
    }

    protected KeyLogger createKeyLogger() {
        return new KeyLogger(fileRecorder, () -> cpu.tick());
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

    public void resume() {
        cpuSuspended = false;
    }

    protected void out8(int port, int bite) {
        // please override if needed
    }

    protected int in8(int port) {
        // please override if needed
        return 0xFF;
    }

    protected void update() {
        // please override if needed
    }

    protected void drawPixel(Point point, Color color) {
        // please override if needed
    }

    protected File logFile() {
        return new File(RECORD_LOG_FILE);
    }

    public void reset() {
        justReset();
        cpuEnabled = true;
        cpuSuspended = false;
    }

    private void justReset() {
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

    public int loadRecord(String path) {
        if (!record.ready()) return -1;

        pause();
        int lastTick = record.load(path);
        reset();
        return lastTick;
    }

    public void loadData(String path) {
        pause();
        int offset = roms.load(path);
        int delta = 25_000;
        record.reset().after(delta)
                .press(END).after(delta)
                .press(ENTER).after(delta)
                // TODO а что если specialis а не лик?
                .enter("J" + hex16(offset)).press(ENTER).after(delta);
        reset();
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

    public FileRecorder fileRecorder() {
        return fileRecorder;
    }
}