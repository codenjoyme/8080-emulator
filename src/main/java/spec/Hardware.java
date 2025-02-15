package spec;

import spec.image.PngScreenToText;
import spec.platforms.Lik;
import spec.sound.*;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static spec.Constants.*;
import static spec.KeyCode.END;
import static spec.KeyCode.ENTER;
import static spec.math.WordMath.hex16;

public class Hardware {

    private PngVideo png;
    private Memory memory;
    private Cpu cpu;
    private RomLoader roms;
    private HardwareData data;
    private IOPorts ports;
    private Video video;
    private Audio audio;
    private KeyLogger keyLogger;
    private Keyboard keyboard;
    private KeyRecord record;
    private FileRecorder fileRecorder;
    private GraphicControl graphtic;
    private Timings timings;
    private RomSwitcher romSwitcher;
    private PngScreenToText screenToText;

    private boolean lineOut;
    private boolean cpuEnabled;
    private boolean cpuSuspended;

    private final List<Runnable> cpuSuspendedListeners = new CopyOnWriteArrayList<>();

    public Hardware(int screenWidth, int screenHeight, Container parent, String base) {
        lineOut = true;
        timings = createTimings();
        graphtic = createGraphicControl(parent);
        memory = createMemory();
        fileRecorder = createFileRecorder();
        keyLogger = createKeyLogger();
        keyboard = createKeyboard();
        ports = createIoPorts();
        record = createKeyRecord();
        video = createVideo(screenWidth, screenHeight);
        screenToText = createScreenToText(base);
        audio = createAudio();
        data = createHardwareData();
        cpu = createCpu(CPU_TICKS_PER_INTERRUPT);
        png = createPngVideo();
        romSwitcher = createRomSwitcher();
        roms = createRomLoader();
    }

    // components

    private PngScreenToText createScreenToText(String base) {
        return new PngScreenToText(base);
    }

    protected RomSwitcher createRomSwitcher() {
        return new RomSwitcher(this);
    }

    protected Timings createTimings() {
        return new Timings(this);
    }

    protected GraphicControl createGraphicControl(Container parent) {
        return new GraphicControl(parent);
    }

    protected PngVideo createPngVideo() {
        return new PngVideo(video, memory);
    }

    protected Audio createAudio() {
        // TODO #39 закончить с аудио пока отключил для веб версии - там ошибка
        if (Files.isRunningFromJar()) {
            return new NoAudio();
        }
        // TODO #39 Добавить поддержку звука. Закончить попытки и сделать звук красивым.
        if (lineOut) {
            return audio = new ContinuousAudio();
        } else {
            return audio = new NewAudio();
        }
    }

    protected RomLoader createRomLoader() {
        return new RomLoader(memory, cpu, ports, keyboard, graphtic, timings, romSwitcher);
    }

    protected Video createVideo(int width, int height) {
        Video video = new Video(width, height);
        video.drawer(graphtic.getDrawer());

        return video;
    }

    protected Cpu createCpu(int ticksPerInterrupt) {
        return new Cpu(ticksPerInterrupt, data, this::cpuInterrupt, record::accept);
    }

    protected HardwareData createHardwareData() {
        return new HardwareData(memory, ports, video) {

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

    protected FileRecorder createFileRecorder() {
        return new FileRecorder();
    }

    protected KeyRecord createKeyRecord() {
        return new KeyRecord(fileRecorder, keyboard, this::stop, this::pause);
    }

    protected IOPorts createIoPorts() {
        return new IOPorts(memory, keyboard){

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
                    } else { // звучит вывод на динамик
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

    protected Keyboard createKeyboard() {
        return new Keyboard(keyLogger::process, new Layout());
    }

    protected Memory createMemory() {
        return new Memory(x10000);
    }

    // logic

    private boolean cpuInterrupt() {
        timings.updateState();

        while (cpuSuspended) {
            cpuSuspended();
            timings.sleep(100);
        }
        return cpuEnabled;
    }

    private void cpuSuspended() {
        cpuSuspendedListeners.forEach(Runnable::run);
        cpuSuspendedListeners.clear();
    }

    public void pause(Runnable listener) {
        cpuSuspendedListeners.add(listener);
        pause();
    }

    public void stop() {
        Logger.debug("Stop CPU");
        cpuEnabled = false;
    }

    public void pause() {
        Logger.debug("Pause CPU");
        cpuSuspended = true;
    }

    public boolean isPaused() {
        return cpuSuspended;
    }

    public void resume() {
        Logger.debug("Resume CPU");
        cpuSuspended = false;
    }

    // OUT команда процессора
    protected void out8(int port, int bite) {
        graphtic.printIO(port, bite);
    }

    // запись в порты КР580ВВ55
    protected void outPort8(int port, int bite) {
        graphtic.printIO(port, bite);
    }

    protected int in8(int port) {
        return 0xFF;
    }

    public void reset() {
        justReset();
        cpuEnabled = true;
        cpuSuspended = false;
    }

    private void justReset() {
        cpu.reset();
        ports.reset();
        keyboard.reset(true);
        keyLogger.reset();
    }

    public void start() {
        cpuEnabled = true;
        cpuSuspended = false;
        cpu.execute();
    }

    public void loadSnapshot(String base, String path) {
        pause(() -> forceLoadSnapshot(base, path));
    }

    public void forceLoadSnapshot(String base, String path) {
        roms.loadSnapshot(base, path);
        keyLogger.reset();
        video.redraw(SCREEN_MEMORY_START, memory);
        resume();
    }

    public void saveSnapshot(String path) {
        roms.saveSnapshot("", path);
    }

    public int loadRecord(String base, String path) {
        if (!record.ready()) return -1;

        Logger.debug("Loading record from %s", base + path);
        pause();
        int lastTick = record.load(base, path);
        reset();
        return lastTick;
    }

    public Range loadData(String base, String path, String platform) {
        pause();

        Logger.debug("Loading data from %s", base + path);
        Range range = roms.load(base, path);
        Logger.debug("Data loaded in range: %s", range);

        // TODO #37 А точно тут надо для всех типов файлов делать загрузку монитора и команд J/G?
        int delta = 25_000;
        if (platform.equals(Lik.NAME)) {
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
        return range;
    }

    public void pauseResume() {
        if (isPaused()) {
            resume();
        } else {
            pause();
        }
    }

    // components getters

    public PngVideo png() {
        return png;
    }

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

    public Keyboard keyboard() {
        return keyboard;
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

    public GraphicControl graphic() {
        return graphtic;
    }

    public Timings timings() {
        return timings;
    }

    public RomSwitcher romSwitcher() {
        return romSwitcher;
    }

    public String screenToText(String path) {
        return screenToText.parse(path);
    }
}