package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.image.PngScreenToText;
import spec.platforms.Lik;
import spec.platforms.Platform;
import spec.sound.*;
import spec.state.JsonState;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static spec.Application.toRelative;
import static spec.Constants.*;
import static spec.KeyCode.END;
import static spec.KeyCode.ENTER;
import static spec.RomLoader.TYPE_SNP;
import static spec.math.WordMath.hex16;

public class Hardware implements JsonState {

    private String base;

    private PngVideo png;
    private Memory memory;
    private Cpu cpu;
    private RomLoader roms;
    private HardwareData data;
    private IOPorts ports;
    private Video video;
    private AudioDriver audio;
    private KeyLogger keyLogger;
    private Keyboard keyboard;
    private KeyRecord record;
    private FileRecorder fileRecorder;
    private GraphicControl graphic;
    private Timings timings;
    private RomSwitcher romSwitcher;
    private PngScreenToText screenToText;

    private boolean cpuEnabled;
    private boolean cpuSuspended;
    private boolean hasFocus;

    private String lastRom;
    private String lastRecord;
    private String lastSnapshot;

    private final List<Runnable> cpuSuspendedListeners = new CopyOnWriteArrayList<>();

    public Hardware(int screenWidth, int screenHeight, Container parent, String base) {
        this.base = base;
        timings = createTimings();
        graphic = createGraphicControl(parent);
        memory = createMemory();
        fileRecorder = createFileRecorder();
        keyLogger = createKeyLogger();
        keyboard = createKeyboard();
        ports = createIoPorts();
        record = createKeyRecord();
        video = createVideo(screenWidth, screenHeight);
        screenToText = createScreenToText(base);
        audio = createAudioDriver();
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

    public AudioDriver createAudioDriver() {
        return new AudioDriver();
    }

    protected RomLoader createRomLoader() {
        return new RomLoader(this);
    }

    protected Video createVideo(int width, int height) {
        Video video = new Video(width, height);
        video.drawer(graphic.getDrawer());

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
                Hardware.this.audio.write(bite);
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
            timings.sleep(100_000);
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
        graphic.printIO(port, bite);
    }

    // запись в порты КР580ВВ55
    protected void outPort8(int port, int bite) {
        graphic.printIO(port, bite);
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
        lastSnapshot = path;
        roms.loadSnapshot(base, path);
        keyLogger.reset();
        video.redraw(SCREEN_MEMORY_START, memory);
        resume();
        refreshBorder();
    }

    public void saveSnapshot() {
        String file = Files.newSnapshot(base);
        lastSnapshot = toRelative(base, new File(file));
        roms.saveSnapshot("", file);
    }

    public int loadRecord(String base, String path) {
        Logger.debug("Loading record from %s", base + path);
        lastRecord = path;

        pause();
        int lastTick = record.load(base, path);
        reset();

        return lastTick;
    }

    public Range loadData(String base, String path, String platform, String command) {
        pause();

        Logger.debug("Loading data from %s", base + path);
        Range range = roms.load(base, path);
        Logger.debug("Data loaded in range: %s", range);

        // TODO #37 А точно тут надо для всех типов файлов делать загрузку монитора и команд J/G?
        int delta = 25_000;
        if (platform.equals(Lik.NAME)) {
            command = command == null ? "J" + hex16(range.begin()) : command;
            Logger.debug("Command to run: %s", command);

            record.reset().after(delta)
                    .press(END).after(delta)
                    .press(ENTER).after(delta)
                    .enter(command).press(ENTER).after(delta)
                    .reset();
        } else {
            command = command == null ? "G" + hex16(range.begin()) : command;
            Logger.debug("Command to run: %s", command);

            record.reset().after(2 * delta)
                    .enter(command).press(ENTER).after(delta)
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

    public void refreshBorder() {
        if (hasFocus) {
            if (audio.audioMode()) {
                graphic.printIO(BORDER_PORT, 0xC0);
            } else {
                graphic.printIO(BORDER_PORT, 0x30);
            }
        } else {
            graphic.printIO(BORDER_PORT, 0x50);
        }
    }

    public void gotFocus() {
        hasFocus = true;
        refreshBorder();
    }

    public void lostFocus() {
        hasFocus = false;
        refreshBorder();
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

    public AudioDriver audio() {
        return audio;
    }

    public GraphicControl graphic() {
        return graphic;
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

    private List<JsonState> states() {
        return Arrays.asList(
                cpu,
                timings,
                ports,
                romSwitcher,
                keyboard,
                graphic,
                audio,
                memory);
    }

    private static String getName(JsonState state) {
        Class<?> clazz = state.getClass();
        while (!Arrays.asList(clazz.getInterfaces()).contains(JsonState.class)) {
            clazz = clazz.getSuperclass();
        }

        return clazz.getSimpleName();
    }

    @Override
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("cpuEnabled", cpuEnabled ? 1 : 0);
        result.addProperty("cpuSuspended", cpuSuspended ? 1 : 0);
        result.addProperty("hasFocus", hasFocus ? 1 : 0);

        result.addProperty("lastRom", lastRom);
        result.addProperty("lastRecord", lastRecord);
        result.addProperty("lastSnapshot", lastSnapshot);

        for (JsonState state : states()) {
            result.add(getName(state), state.toJson());
        }

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        cpuEnabled = json.get("cpuEnabled").getAsInt() == 1;
        cpuSuspended = json.get("cpuSuspended").getAsInt() == 1;
        hasFocus = json.get("hasFocus").getAsInt() == 1;

        lastRom = nullable(json.get("lastRom"));
        lastRecord = nullable(json.get("lastRecord"));
        lastSnapshot = nullable(json.get("lastSnapshot"));

        for (JsonState state : states()) {
            state.fromJson(json.get(getName(state)));
        }
    }

    private String nullable(JsonElement element) {
        return element == null ? null : element.getAsString();
    }

    public void startRecord() {
        String path = Files.newRecord(base);
        lastRecord = toRelative(base, new File(path));
        fileRecorder.with(path);
        fileRecorder.startWriting();
    }

    public void loadRom(String base, String rom, String command) {
        lastRom = rom;
        romSwitcher.load(base, rom, command);
    }

    public void load(String platform, String rom, String command) {
        Logger.debug("Load platform2: '%s', file: '%s'", platform, rom);

        // ничего нет, грузим по умолчанию
        if (platform == null && rom == null) {
            romSwitcher.loadRoms(base);
            return;
        }
        // грузим снепшот и только его
        if (rom != null && rom.endsWith("." + TYPE_SNP)) {
            // TODO #41 этот костыыль надо при загрузке снепшота из командной строки по полному
            //      пути там под windows не работает добавление base
            String realBase = rom.contains(":") ? "" : base;
            forceLoadSnapshot(realBase, rom);
            return;
        }
        // грузим платформу если указана
        if (platform != null) {
            romSwitcher.selectRom(base, platform);
        }
        // грузим файл если указан
        if (rom != null) {
            // TODO #41 этот костыыль надо при загрузке снепшота из командной строки по полному
            //      пути там под windows не работает добавление base
            String realBase = rom.contains(":") ? "" : base;
            loadRom(realBase, rom, command);
        }
    }

    private String checkNull(String base, String file) {
        return file != null ? base + file : null;
    }

    public String lastRecord() {
        return checkNull(base, lastRecord);
    }

    public String lastRom() {
        return checkNull(base, lastRom);
    }

    public String lastSnapshot() {
        return checkNull(base, lastSnapshot);
    }

    public Platform platform() {
        return romSwitcher.current();
    }
}