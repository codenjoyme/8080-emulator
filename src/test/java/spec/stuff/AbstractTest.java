package spec.stuff;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import spec.*;
import spec.assembler.Assembler;
import spec.assembler.DizAssembler;
import spec.basic.BasicCompiler;
import spec.image.PngScreenToText;
import spec.math.Bites;
import spec.mods.WhereIsData;
import spec.platforms.Lik;
import spec.platforms.Platform;
import spec.platforms.PlatformFactory;
import spec.platforms.Specialist;
import spec.sound.AudioDriver;
import spec.sound.NoAudio;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.mockito.Mockito.mock;
import static spec.Constants.*;
import static spec.assembler.Assembler.asString;
import static spec.math.WordMath.hex8;
import static spec.stuff.SmartAssert.assertEquals;
import static spec.stuff.TrackUpdatedMemory.TRACK_ALL_CHANGES;
import static spec.stuff.TrackUpdatedMemory.TRACK_ONLY_UPDATED_VALUES;

public abstract class AbstractTest {

    public static final int K10 = 10_000;
    public static final int M1 = 1_000_000;

    public static int START = 0x0000;

    public static final int TICKS = 10_000_000;

    public static final String TEST_RESOURCES = "src/test/resources/";
    public static final String TARGET_RESOURCES = "target/";
    public static final String MAIN_RESOURCES = "src/main/resources/";
    public static final String CPU_TESTS_RESOURCES = "test/";

    protected boolean memoryInit;

    protected Hardware hard;
    protected TrackUpdatedMemory memory;
    protected Cpu cpu;
    protected Assembler asm;
    protected KeyRecord record;
    protected RomLoader roms;
    protected FileRecorder fileRecorder;
    protected CpuDebug debug;
    protected KeyLogger keyLogger;
    protected Keyboard keyboard;
    protected IOPorts ports;
    protected GraphicControl graphic;
    protected Timings timings;
    protected RomSwitcher romSwitcher;
    protected PngVideo video;
    protected DizAssembler dizAssembler;

    @Rule
    public TestName test = new TestName();
    protected FileAssert fileAssert;
    protected String base;
    protected String targetBase;
    protected PngScreenToText scanner;
    protected String subFolder;

    @Before
    public void setup() {
        SmartAssert.setup();
    }

    @Before
    public void before() {
        Logger.DEBUG = false;

        base = base();
        targetBase = targetBase();

        scanner = new PngScreenToText(base);

        fileAssert = new FileAssert(testBase());
        fileAssert.removeTestsData();

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, null, base) {

            @Override
            protected Memory createMemory() {
                return memory = new TrackUpdatedMemory(x10000, TRACK_ALL_CHANGES);
            }

            @Override
            protected Cpu createCpu(int ticksPerInterrupt) {
                // interrupt будет проходить каждый тик
                return cpu = super.createCpu(1);
            }

            @Override
            protected GraphicControl createGraphicControl(Container parent) {
                return AbstractTest.this.createGraphicControl(parent);
            }

            @Override
            protected Timings createTimings() {
                return AbstractTest.this.createTimings(this);
            }

            @Override
            protected void out8(int port, int bite) {
                // делаем все по быстрому
            }

            @Override
            protected void outPort8(int port, int bite) {
                // делаем все по быстрому
            }

            @Override
            public AudioDriver createAudioDriver() {
                return AbstractTest.this.createAudioDriver();
            }
        };

        keyboard = hard.keyboard();

        fileRecorder = hard.fileRecorder();
        fileRecorder.stopWriting();

        keyLogger = hard.keyLogger();
        keyLogger.console(false);

        roms = hard.roms();

        record = hard.record();
        record.screenshot(this::assertScreen);

        asm = hard.cpu().asm();
        ports = hard.ports();
        graphic = hard.graphic();
        timings = hard.timings();
        memory.resetChanges();
        romSwitcher = hard.romSwitcher();

        video = new PngVideo(hard.video(), hard.memory());

        dizAssembler = new DizAssembler(cpu.data());

        debug = cpu.debug();
        debug.disable();
        debug.console(false);

        memoryInit = false;

        cpu.PC(START);

        // reset();
    }

    public void reset() {
        record.reset();
        hard.reset();
    }

    public Platform lik() {
        return PlatformFactory.platform(Lik.NAME);
    }

    public Platform specialist() {
        return PlatformFactory.platform(Specialist.NAME);
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    public String base()  {
        return MAIN_RESOURCES;
    }

    public String targetBase()  {
        return TARGET_RESOURCES;
    }

    public String testBase() {
        return TEST_RESOURCES + "/" + getTestResultFolder() + ((subFolder != null) ? "/" + subFolder : "");
    }

    protected String getTestResultFolder() {
        return this.getClass().getSimpleName() + "/" +
                test.getMethodName().replaceAll("_", "/");
    }

    protected String getTestInfo() {
        return this.getClass().getSimpleName() + "." + test.getMethodName() + "()";
    }

    protected GraphicControl createGraphicControl(Container parent) {
        return mock(GraphicControl.class);
    }

    protected AudioDriver createAudioDriver() {
        return new AudioDriver() {
            @Override
            public void createAudio(boolean mode, boolean skip) {
                // делаем все по быстрому
                audio = new NoAudio();
            }
        };
    }

    protected Timings createTimings(Hardware hard) {
        return new Timings(hard) {
            @Override
            public void updateState() {
                // делаем все по быстрому
                hard.keyboard().tick();
            }

            @Override
            protected void sleep() {
                // делаем все по быстрому
            }

            @Override
            public void sleep(long millis) {
                // делаем все по быстрому
            }

            @Override
            protected void profiling() {
                // делаем все по быстрому
            }
        };
    }

    public void givenPr(String program) {
        String bites = asString(asm.split(asm.assembly(program)));
        givenMm(bites);
        assertEquals(program, asm.dizAssembly(bites));
    }

    /**
     * Либо метод запишет данные в память без валидации (если еще никто не делал этого),
     * либо будет валидировать уже измененную ранее область памяти.
     */
    public void givenMm(String bites) {
        if (!memoryInit) {
            memoryInit = true;
            memory.write8str(START, bites.replace("\n", " "));
        }
        String split = asString(asm.split(memory.changedBites()));
        if (!split.endsWith("\n")) {
            split += "\n";
        }
        if (!bites.endsWith("\n")) {
            bites += "\n";
        }
        assertEquals(bites, split);
        int ticks = split.split("\n").length;
        record.after(ticks).stopCpu();
    }

    public void asrtCpu(String expected) {
        assertEquals(expected, cpu.toStringDetails());
    }

    public void cpuShort(String expected) {
        assertEquals(expected, cpu.toString());
    }

    public void asrtMem(int addr, String expected) {
        assertEquals(expected, hex8(cpu.data().read8(addr)));
    }

    public void asrtMem(int begin, int endOrLength, String expected) {
        int end = (begin < endOrLength)
                ? endOrLength
                : begin + endOrLength - 1;
        assertEquals(expected, memory.read8str(new Range(begin, end)));
    }

    public void assertTrace(String expected) {
        assertEquals(expected, trace());
    }

    public String trace() {
        return String.join("\n", debug.lines());
    }

    public void start() {
        memory.resetChanges();
        hard.start();
    }

    public void asrtMem(String expected) {
        assertEquals(expected, memory.details());
        memory.resetChanges();
    }

    public void assertScreen() {
        assertScreen("end");
    }

    public void assertScreen(String name) {
        fileAssert.check("Screenshots", name + ".png",
                file -> {
                    video.drawToFile(SCREEN.begin(), file.getAbsolutePath());
                    return null;
                });
    }

    public String assertCpu(String name) {
        return fileAssert.check("Cpu state", name + ".log",
                file -> fileAssert.write(file, cpu.toStringDetails()));
    }

    public void assertCpu() {
        assertCpu("cpu");
    }

    public String assertCpuAt(WhereIsData data) {
        return fileAssert.check("Cpu was at info", "cpuAt.log",
                file -> fileAssert.write(file, data.toString()));
    }

    public String assertDizAssembly(WhereIsData data, String binPath, String asmPath) {
        return fileAssert.check("DizAssembled program", asmPath,
                file -> fileAssert.write(file,
                        getHeader(binPath) + dizAssembler.program(data.range(), data.info())));
    }

    private String getHeader(String binPath) {
        return ";*********************************************************************************************************\n" +
                "; \n" +
                "; Autogenerated by DizAssembler:\n" +
                ";   https://github.com/codenjoyme/8080-emulator/blob/master/src/main/java/spec/assembler/DizAssembler.java\n" +
                "; From: " + binPath + "\n" +
                "; In test: " + getTestInfo() + "\n" +
                "; \n" +
                ";*********************************************************************************************************\n" +
                "\n";
    }

    public String assertTrace() {
        return fileAssert.check("Cpu trace", "trace.log",
                file -> fileAssert.write(file, trace()));
    }

    public void assertPngMemory(Range range, String image) {
        PngVideo.drawToFile(range, SCREEN_WIDTH, memory,
                testBase() + "/" + image);
    }

    public String pngPath(String name) {
        List<String> found = pngsPath(name);
        if (found.isEmpty()) {
            throw new RuntimeException("File not found: " + name);
        }
        return found.get(0);
    }

    public LinkedList<String> pngsPath(String name) {
        return Arrays.stream(Objects.requireNonNull(fileAssert.testDir().listFiles()))
                .filter(it -> it.getName().contains(name))
                .map(File::getAbsolutePath)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public String assertFromPng(String sourceFile) {
        String destFile = new File(sourceFile).getName().replace(".png", ".txt");
        return fileAssert.check("Screen text", destFile,
                file -> fileAssert.write(file, scanner.parse(sourceFile)));
    }

    public Bites assertAssembly(String sourceCode, String recompiledFile, String detailsFile) {
        svofski.Assembler assembler = new svofski.Assembler();
        Map<String, Object> map = assembler.process(sourceCode);
        Bites result = (Bites) map.get("bin");
        String listing = (String) map.get("listing");
        byte[] bytes = result.byteArray();

        if (recompiledFile != null) {
            fileAssert.write(new File(recompiledFile), bytes);
        }

        if (detailsFile != null) {
            detailsFile = fixPath(detailsFile);
            fileAssert.write(new File(detailsFile), listing);
        }
        return result;
    }

    private static String fixPath(String detailsFile) {
        return detailsFile.startsWith("src") ? detailsFile : MAIN_RESOURCES + detailsFile;
    }

    public Memory assertMemory(Range range, String romFileName, String diffFileName) {
        TrackUpdatedMemory source = new TrackUpdatedMemory(0x10000, TRACK_ONLY_UPDATED_VALUES);
        try {
            roms.loadROM(TEST_RESOURCES, getTestResultFolder() + "/" + romFileName, source.all(), 0x0000);
            source.resetChanges();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            int bite = memory.read8(addr);
            source.write8(addr, bite);
        }
        fileAssert.check("Diff", diffFileName,
                file -> fileAssert.write(file, source.detailsTable()));
        return source;
    }

    /**
     * Проверка состояни памяти в определенном диапазоне.
     * @param range Диапазон памяти.
     * @param name В окрежнии теста с этим именем будет создан файл с содержимым памяти.
     *             В последствии с ним будет происходить сравнение.
     * @return Строка с результатом проверки.
     */
    public String assertMemory(Range range, String name) {
        return fileAssert.check("Memory in range: " + range, name,
                file -> fileAssert.write(file, memory.all().toString(range, true, true)));
    }

    /**
     * Парсит программу на `BASIC ЛИК V2` в машинных кодах в текст
     * @param range диапазон памяти, где находится программа
     * @param name имя файла, куда запишется результат
     * @return тест программы на бейсике
     */
    public String assertBasicProgram(Range range, String name) {
        BasicCompiler basic = new BasicCompiler();
        List<String> lines = basic.getSource(memory.all(), range);

        String path = fixPath(name);
        return fileAssert.check("Memory in range: " + range, path,
                file -> fileAssert.write(file, StringUtils.join(lines, "\n")));
    }

    public void assertRecord(String path, Runnable... configure) {
        fileRecorder.startWriting();
        int lastTick = hard.loadRecord(TEST_RESOURCES, "inputs/" + path);
        record.after(lastTick).stopCpu();
        Arrays.asList(configure).forEach(Runnable::run);
        cpu.PC(0xC000);
        hard.start();
    }

    public static List<Object[]> findAllFiles(String base, String type) {
        Path start = Paths.get(base);
        try (Stream<Path> stream = java.nio.file.Files.walk(start)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(type))
                    .map(file -> new Object[]{ file.toString().replace("\\", "/").substring(base.length()) })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public List<Pair<Platform, String>> getAllFiles(String dir, String type) {
        String platformName = dir.contains(Lik.NAME) ? Lik.NAME : Specialist.NAME;
        return findAllFiles(dir, type).stream()
                .map(it -> Pair.of(
                        PlatformFactory.platform(platformName),
                        new File(it[0].toString()).getParentFile().getName()))
                .collect(toList());
    }

    public List<Pair<Platform, String>> getAllFiles(String type) {
        return PlatformFactory.all().stream()
                .flatMap(name -> getAllFiles(MAIN_RESOURCES + name + "/apps", type).stream())
                .collect(toList());
    }

    public static void assertMemoryChanges(String message, String expected, Range range, Bites original, Bites recompiled) {
        TrackUpdatedMemory tracker = new TrackUpdatedMemory(x10000, true);

        tracker.all().set(range.begin(), original);

        tracker.resetChanges();

        tracker.all().set(range.begin(), recompiled);

        assertEquals("Memory size after recompilation for: " + message,
                original.size(), recompiled.size());

        assertEquals("File are not equal for: " + message,
                expected, tracker.detailsTable());
    }
}