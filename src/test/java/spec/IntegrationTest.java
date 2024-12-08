package spec;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.assembler.DizAssembler;
import spec.math.Bites;
import spec.mods.StopWhen;
import spec.mods.WhenPC;
import spec.mods.WhereIsData;
import spec.platforms.Lik;
import spec.platforms.Specialist;
import spec.stuff.AbstractTest;
import spec.stuff.FileAssert;
import spec.stuff.TrackUpdatedMemory;
import svofski.Assembler;
import svofski.TapeFormat;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;
import static spec.Constants.*;
import static spec.KeyCode.*;
import static spec.stuff.FileAssert.write;
import static spec.stuff.TrackUpdatedMemory.TRACK_ONLY_UPDATED_VALUES;
import static svofski.AssemblerTest.findAllFiles;

public class IntegrationTest extends AbstractTest {

    private static final int K10 = 10_000;
    private static final int TICKS = 10_000_000;

    public static final String TEST_RESOURCES = "src/test/resources/";
    public static final String APP_RESOURCES = "src/main/resources/";
    public static final String TARGET_RESOURCES = "target/";
    private static final String CPU_TESTS_RESOURCES = "test/";

    @Rule
    public TestName test = new TestName();

    private URL base;
    private FileAssert fileAssert;
    private PngVideo video;
    private DizAssembler dizAssembler;

    @Before
    @Override
    public void before() {
        super.before();

        video = new PngVideo(hard.video(), hard.memory());
        base = getBase();
        record.screenShoot(this::assertScreen);
        
        fileAssert = new FileAssert(
                TEST_RESOURCES + getTestResultFolder());
        fileAssert.removeTestsData();
        
        dizAssembler = new DizAssembler(cpu.data());
        reset();
        
        record.after(TICKS).stopCpu();
    }

    private String getTestResultFolder() {
        return "IntegrationTest/" +
                test.getMethodName().replaceAll("_", "/");
    }

    public static URL getBase()  {
        try {
            return new File(APP_RESOURCES).toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getTargetBase()  {
        try {
            return new File(TARGET_RESOURCES).toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    @Override
    public void after() throws Exception {
        assertScreen();
        super.after();
    }

    private void reset() {
        record.reset();
        hard.reset();
    }

    private void assertScreen() {
        assertScreen("end");
    }

    private void assertScreen(String name) {
        fileAssert.check("Screenshots", name + ".png",
                file -> {
                    video.drawToFile(SCREEN.begin(), file);
                    return null;
                });
    }

    private String assertCpu(String name) {
        return fileAssert.check("Cpu state", name + ".log",
                file -> write(file, cpu.toStringDetails()));
    }

    private void assertCpu() {
        assertCpu("cpu");
    }

    private String assertCpuAt(WhereIsData data) {
        return fileAssert.check("Cpu was at info", "cpuAt.log",
                file -> write(file, data.toString()));
    }

    private String assertDizAssembly(WhereIsData data, String name) {
        return fileAssert.check("DizAssembled program", name,
                file -> write(file, dizAssembler.program(data.range(), data.info())));
    }

    private String assertTrace() {
        return fileAssert.check("Cpu trace", "trace.log",
                file -> write(file, trace()));
    }

    @Test
    public void testLik_generateWave() {
        String dir = APP_RESOURCES + "lik/apps";
        List<String> names = findAllFiles(dir, ".rks").stream()
                .map(it -> new File(it[0].toString()).getParentFile().getName())
                .collect(toList());

        names.forEach(this::testLik_generateWave);
    }

    private void testLik_generateWave(String name) {
        // given
        Range range = Lik.loadGame(base, roms, name);

        // when
        byte[] mem = memory.all().byteArray(range);
        Bites wave = new TapeFormat("specialist-rks", false)
                .format(mem, 0, name + ".rks").makewav();

        // then
        String fileName = APP_RESOURCES + "lik/apps/" + name + "/" + name + ".wav";
        fileAssert.check(fileName, fileName,
                file -> write(file, wave.byteArray()));
    }

    @Test
    public void testLik_smoke() {
        // given
        Lik.loadRom(base, roms);

        // when
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", it -> it.press(ENTER).after(5 * K10))
                .shoot("assembler", it -> it.enter("AC000").press(ENTER).after(20 * K10))
                .shoot("exit", it -> it.down(ENTER).after(5 * K10).press(END).after(5 * K10).up(ENTER).after(15 * K10))
                .shoot("memory", it -> it.enter("D9000").press(ENTER).after(30 * K10))
                .shoot("exit", it -> it.press(ESC).after(5 * K10))
                .shoot("basic", it -> it.enter("B").press(ENTER).after(20 * K10))
                .shoot("line1", it -> it.enter("10 CLS2").press(ENTER).after(10 * K10))
                .shoot("line2", it -> it.enter("20 CLS1").press(ENTER).after(10 * K10))
                .shoot("line2", it -> it.enter("30 GOTO10").press(ENTER).after(10 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(10 * K10))
                .shoot("run", it -> it.enter("RUN").press(ENTER).after(1 * K10))
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_scenario() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when
        record.after(12 * K10).down(0x23)
                .after(5 * K10).up(0x23).shoot("")
                .after(10 * K10).down(0x0A)
                .after(5 * K10).up(0x0A).shoot("")
                .after(34 * K10).down(0x4A)
                .after(4 * K10).up(0x4A).shoot("")
                .after(4 * K10).down(0x0A)
                .after(10 * K10).up(0x0A).shoot("")
                .after(400 * K10).down(0x27)
                .after(59 * K10).up(0x27).shoot("")
                .after(1 * K10).down(0x26)
                .after(24 * K10).up(0x26).shoot("")
                .after(127 * K10).down(0x27)
                .after(53 * K10).up(0x27).shoot("")
                .after(1 * K10).down(0x26)
                .after(28 * K10).up(0x26).shoot("")
                .stopCpu();

        cpu.PC(START_POINT);
        start();
    }

    @Test
    public void testLik_game_klad_levels() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when then
        record.shoot("logo", it -> it.after(200 * K10))
                .shoot("logo", it -> it.after(170 * K10))
                .shoot("speed", it -> it.after(50 * K10))
                .stopCpu();

        cpu.PC(0x0000);
        start();

        int LEVELS = 33;
        for (int level = 1; level < LEVELS; level++) {
            // when then
            reset();

            // скорость выбранная в первый раз поменяла тайминги
            int speed = (level == 1) ? 60 * K10 : 60 * K10 + 13 * K10;
            record.shoot(null,
                            it -> it.after(50 * K10))
                    .shoot(null,
                            it -> it.down(RIGHT).after(speed).up(RIGHT).after(1 * K10))
                    .shoot("level[" + level + "]",
                            it -> it.down(UP).after(30 * K10).up(UP).after(100 * K10))
                    .stopCpu();

            // этот хак позволяет запускать игру со следующим уровнем
            cpu.PC(0x4567);
            start();
        }
    }

    @Test
    public void testLik_game_klad_recording() throws IOException {
        // about 36 sec

        // given
//        Logger.DEBUG = true;
//        WhereIsData.PRINT_RW = true;

        Lik.loadRom(base, roms);
        Range range = Lik.loadGame(base, roms, "klad");
        WhereIsData data = new WhereIsData(range);
        cpu.modAdd(data);

        // when then
        assertRecord("klad.rec",
                () -> record.at(100).shoot("screeen1"),
                () -> record.at(200).shoot("screeen2"),
                () -> record.at(300).shoot("screeen3"),
                () -> record.at(400).shoot("screeen4"),
                () -> record.at(500).shoot("screeen5"),
                () -> record.at(600).shoot("screeen6"),
                () -> record.at(700).shoot("screeen7"),
                () -> record.at(800).shoot("screeen8"),
                () -> record.at(900).shoot("screeen9"),
                () -> record.at(1000).shoot("screeen10"),
                () -> record.at(1100).shoot("screeen11"),
                () -> record.at(1200).shoot("screeen12"),
                () -> record.at(1300).shoot("screeen13"),
                () -> record.at(1400).shoot("screeen14"),
                () -> record.at(1500).shoot("screeen15"),
                () -> record.at(1600).shoot("screeen16"),
                () -> record.at(1700).shoot("screeen17"),
                () -> record.at(1800).shoot("screeen18"),
                () -> record.at(1900).shoot("screeen19"),
                () -> record.at(2000).shoot("screeen20"),
                () -> record.at(2100).shoot("screeen21"),
                () -> record.at(2200).shoot("screeen22"),
                () -> record.at(2300).shoot("screeen23"),
                () -> record.at(2310).shoot("screeen24"),
                () -> record.at(2311).shoot("screeen25"),
                () -> record.at(2312).shoot("screeen26"),
                () -> record.at(2313).shoot("screeen27"),
                () -> record.at(2314).shoot("screeen28"),
                () -> record.at(2315).shoot("screeen29"),
                () -> record.at(2316).shoot("screeen30"),
                () -> record.at(2317).shoot("screeen31"), // TODO тут почему-то в режиме тиста gameover а в реальной игре нет
                () -> record.at(2318).shoot("screeen32"),
                () -> record.at(2319).shoot("screeen33")
        );
        assertCpuAt(data);

        // check that all program was the same after running
        // when then
        String sourceCode = assertDizAssembly(data, "launchedProgram.asm");
        assertAssembly(sourceCode, "recompiled.mem");
        assertPngMemory(range, "recompiled.png");

        // when then
        Lik.loadGame(base, roms, "klad");
        assertDizAssembly(data, "newProgram.asm");
        assertPngMemory(range, "original.png");

        // when then
        assertMemory(range, "recompiled.mem", "recompiled.log");

    }

    private void assertPngMemory(Range range, String image) {
        PngVideo.drawToFile(range, SCREEN_WIDTH, memory, new File(TEST_RESOURCES + getTestResultFolder() + "/" + image));
    }

    private void assertAssembly(String sourceCode, String recompiledFile) throws IOException {
        Assembler assembler = new Assembler();
        Bites result = assembler.compile(sourceCode);
        byte[] bytes = result.byteArray();
        FileUtils.writeByteArrayToFile(new File(TEST_RESOURCES + getTestResultFolder() + "/" + recompiledFile), bytes);
    }

    private Memory assertMemory(Range range, String romFileName, String diffFileName) {
        TrackUpdatedMemory source = new TrackUpdatedMemory(0x10000, TRACK_ONLY_UPDATED_VALUES);
        try {
            URL base = new File(TEST_RESOURCES).toURI().toURL();
            roms.loadROM(base, getTestResultFolder() + "/" + romFileName, source.all(), 0x0000);
            source.resetChanges();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            int bite = memory.read8(addr);
            source.write8(addr, bite);
        }
        fileAssert.check("Diff", diffFileName,
                file -> write(file, source.detailsTable()));
        return source;
    }

    @Test
    public void testLik_game_reversi_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi");

        // when
        assertRecord("reversi.rec");
    }

    @Test
    public void testLik_game_reversi2_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi2");

        // when
        assertRecord("reversi2.rec");
    }

    private void assertRecord(String path, Runnable... configure) {
        fileRecorder.startWriting();
        int lastTick = hard.loadRecord(TEST_RESOURCES + "inputs/" + path);
        record.after(lastTick).stopCpu();
        Arrays.asList(configure).forEach(Runnable::run);
        cpu.PC(0xC000);
        hard.start();
    }

    @Test
    public void testLik_keyboard() {
        // given
        Lik.loadRom(base, roms);

        // when
        assertRecord("keyboard.rec");
    }

    @Test
    public void testSpecialist_monitor() {
        // given
        Specialist.loadRom(base, roms);

        // when
        cpu.PC(START_POINT);
        start();

        // then
        assertCpu();
    }

    @Test
    public void testSpecialist_game_blobcop() {
        // given
        Specialist.loadRom(base, roms);
        Specialist.loadGame(base, roms, "blobcop");

        // when
        cpu.PC(0x0000);
        start();

        // then
        assertCpu();
    }

    @Test
    public void testSpecialist_game_babnik() {
        // given
        Specialist.loadRom(base, roms);
        Specialist.loadGame(base, roms, "babnik");

        // when
        cpu.PC(0x0000);
        start();

        // then
        assertCpu();
    }

    @Test
    public void testLik_helloWorld() {
        // given
        Lik.loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "hello-world/hello-world.mem", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // последняя команда перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));
        // cpu.modAdd(new StopWhen(0x0022));
        // если хочется подебажить внутри
        // cpu.modAdd(new WhenPC(range, cpu -> {
        //     String log = cpu.debug().log(0);
        //     System.out.println(log);
        // }));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_microcosm() {
        // given
        Lik.loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "test/test.mem", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_exerciserPreliminary() {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The preliminary test
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080PRE.MAC

        // given
        Lik.loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080pre/8080pre.mem", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_zexlax8080exerciser() {
        // zexlax.z80 - Z80 instruction set exerciser
        // Copyright (C) 1994  Frank D. Cringle
        // Modified to exercise an 8080 by Ian Bartholomew, February 2009

        // given
        memory.doNotTrackChanges();
        Lik.loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080ex1/8080ex1.mem", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        // debug.enable(range);
        // debug.console(true);
        // Logger.DEBUG = true;
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(4);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));
        // если хочется подебажить внутри
        AtomicInteger counter = new AtomicInteger();
        cpu.modAdd(new WhenPC(range, cpu -> {
            // JMP на начало нового теста
            if (cpu.rPC.get() == 0x002A) {
                String name = "test-" + counter.incrementAndGet();
                assertScreen(name);
                System.out.println("Test '" + name + "' done");
            }
        }));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_apofig8080exerciser() {
        // given
        Lik.loadRom(base, roms);
        Range range = hard.loadData(base, CPU_TESTS_RESOURCES + "8080apofig/8080apofig.mem", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(range);
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(6);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(asm.parseCommand("JMP C800")));
        // если хочется подебажить внутри
        cpu.modAdd(new WhenPC(range, cpu -> {
            String log = cpu.debug().log(0);
            System.out.println(log);
        }));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }
}