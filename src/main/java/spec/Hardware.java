package spec;

import spec.platforms.Lik;
import spec.sound.Audio;
import spec.sound.NewAudio;
import spec.sound.OldAudio;

import java.io.File;
import java.net.URL;

import static spec.Constants.*;
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
    private Audio audio;
    private KeyLogger keyLogger;
    private KeyRecord record;
    private FileRecorder fileRecorder;
    private Video.Drawer drawer;

    private boolean lineOut;
    private boolean cpuEnabled;
    private boolean cpuSuspended;

    public Hardware(int screenWidth, int screenHeight, Video.Drawer drawer) {
        lineOut = true;
        this.drawer = drawer;
        memory = createMemory();
        fileRecorder = createFileRecorder(logFile());
        keyLogger = createKeyLogger();
        ports = createIoPorts();
        record = createKeyRecord();
        video = createVideo(screenWidth, screenHeight);
        audio = createAudio();
        data = createHardwareData();
        cpu = createCpu(CPU_TICKS_PER_INTERRUPT);
        roms = createRomLoader();
    }

    private Audio createAudio() {
        if (lineOut) {
            return audio = new OldAudio();
        } else {
            return audio = new NewAudio();
        }
    }

    // components
    protected RomLoader createRomLoader() {
        return new RomLoader(memory, cpu);
    }

    protected Video createVideo(int width, int height) {
        Video video = new Video(width, height);
        video.drawer(drawer);
        return video;
    }

    protected Cpu createCpu(int ticksPerInterrupt) {
        return new Cpu(ticksPerInterrupt, data, this::cpuInterrupt, record::accept);
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
        return new IOPorts(memory, new Layout(), keyLogger::process){

            @Override
            protected void A(int bite) {
                super.A(bite);
                Hardware.this.outPort8(IOPorts.PortA, bite);
            }

            @Override
            protected void B(int bite) {
                super.B(bite);
                Hardware.this.outPort8(IOPorts.PortB, bite);
            }

            @Override
            protected void C(int bite) {
                super.C(bite);
                Hardware.this.outPort8(IOPorts.PortC, bite);
            }

            @Override
            protected void R(int bite) {
                super.R(bite);
                if (bite == 0x82 || bite == 0x91 ||  // непонятное
                    bite == 0x0E || bite == 0x0F ||  // запись на магнитофон
                    bite == 0x0A || bite == 0x0B)    // вывод звука на динамик
                {
                    if (lineOut) { // звучит запись на магнитофон
                        if (bite == 0x0E) {
                            audio.write(0x00);
                        } else if (bite == 0x0F) {
                            audio.write(0xFF);
                        }
                    } else { // звучит вівод на динамик
                        if (bite == 0x0A) {
                            audio.write(0x00);
                        } else if (bite == 0x0B) {
                            audio.write(0xFF);
                        }
                    }
                    // System.out.println(WordMath.hex8(bite));
                }
                Hardware.this.outPort8(IOPorts.RgRGB, bite);
            }
        };
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

    public boolean isPaused() {
        return cpuSuspended;
    }

    public void resume() {
        cpuSuspended = false;
    }

    // OUT команда процессора
    protected void out8(int port, int bite) {
        // please override if needed
    }

    // запись в порты КР580ВВ55
    protected void outPort8(int port, int bite) {
        // please override if needed
    }

    protected int in8(int port) {
        // please override if needed
        return 0xFF;
    }

    protected void update() {
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

    public void loadData(String path, boolean platform) {
        pause();

        Range range = roms.load(path);

        int delta = 25_000;
        if (platform == Lik.PLATFORM) {
            record.reset().after(delta)
                    .press(END).after(delta)
                    .press(ENTER).after(delta)
                    .enter("J" + hex16(range.begin())).press(ENTER).after(delta)
                    .reset();
        } else {
            record.reset().after(2 * delta)
                    .enter("G" + hex16(range.begin())).press(ENTER).after(delta)
                    .reset();
        }

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

    public KeyLogger keyLogger() {
        return keyLogger;
    }

    public Audio audio() {
        return audio;
    }
}