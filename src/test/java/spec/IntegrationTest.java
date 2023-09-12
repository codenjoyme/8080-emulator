package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.assembler.DizAssembler;
import spec.mods.DebugWhen;
import spec.mods.StopWhen;
import spec.mods.WhereIsData;
import spec.platforms.Lik;
import spec.platforms.Specialist;
import spec.stuff.AbstractTest;
import spec.stuff.FileAssert;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

import static spec.Constants.START_POINT;
import static spec.KeyCode.*;
import static spec.WordMath.hex16;
import static spec.WordMath.hex8;
import static spec.stuff.FileAssert.write;
import static spec.stuff.SmartAssert.assertEquals;

public class IntegrationTest extends AbstractTest {

    private static final int K10 = 10_000;
    private static final int TICKS = 10_000_000;

    private static final String TEST_RESOURCES = "src/test/resources/";
    private static final String APP_RESOURCES = "src/main/resources/";
    private static final String CPU_TESTS_RESOURCES = APP_RESOURCES + "test/";

    @Rule
    public TestName test = new TestName();

    private URL base;
    private FileAssert fileAssert;
    private PngVideo video;
    private DizAssembler dizAssembler;

    @Before
    @Override
    public void before() throws Exception {
        super.before();

        video = new PngVideo(hard.video(), hard.memory());
        base = new File(APP_RESOURCES).toURI().toURL();
        record.screenShoot(this::assertScreen);
        fileAssert = new FileAssert(TEST_RESOURCES + test.getMethodName());
        fileAssert.removeTestsData();
        dizAssembler = new DizAssembler(cpu.data());
        reset();
        record.after(TICKS).stopCpu();
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
                file -> video.drawToFile(file));
    }

    private void assertCpu(String name) {
        fileAssert.check("Cpu state", name + ".log",
                file -> write(file, cpu.toStringDetails()));
    }

    private void assertCpu() {
        assertCpu("cpu");
    }

    private void assertCpuAt(WhereIsData data) {
        fileAssert.check("Cpu was at info", "cpuAt.log",
                file -> write(file, data.toString()));
    }

    private void assertDizAssembly(WhereIsData data, String name) {
        fileAssert.check("DizAssembled program", name + ".log",
                file -> write(file, dizAssembler.program(data.range(), data.info())));
    }

    private void assertTrace() {
        fileAssert.check("Cpu trace", "trace.log",
                file -> write(file, trace()));
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
    public void testLik_klad() {
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
    public void testLik_klad_recording() {
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
                () -> record.at(1379).shoot("screeen14")
        );
        assertCpuAt(data);
        assertDizAssembly(data, "launchedProgram");

        // when then
        Lik.loadGame(base, roms, "klad");
        assertDizAssembly(data, "newProgram");

        // when then
        assertMemory(range, "recompiled.mem");
    }

    private void assertMemory(Range range, String romFileName) {
        String diff = "";
        int[] source = new int[0x10000];
        try {
            URL base = new File(TEST_RESOURCES).toURI().toURL();
            roms.loadROM(base, test.getMethodName() + "/" + romFileName, source, 0x0000);
        } catch (Exception e) {
            // do nothing
        }
        for (int addr = range.begin(); addr <= range.end(); addr++) {
            int bite1 = memory.read8(addr);
            int bite2 = source[addr];
            if (bite1 != bite2) {
                diff += String.format("%s: %s != %s",
                        hex16(addr), hex8(bite1), hex8(bite2));
            }
        }
        assertEquals("", diff);
    }


    @Test
    public void testLik_reversi_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi");

        // when
        assertRecord("reversi.rec");
    }

    @Test
    public void testLik_reversi2_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi2");

        // when
        assertRecord("reversi2.rec");
    }

    private void assertRecord(String path, Runnable... configure) {
        fileRecorder.startWriting();
        int lastTick = hard.loadRecord(TEST_RESOURCES + "recordings/" + path);
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
    public void testSpecialist_blobcop() {
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
    public void testSpecialist_babnik() {
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
        hard.loadData(CPU_TESTS_RESOURCES + "hello-world/hello_world.rks", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(new Range(0x0000, 0x0100));
        // последняя команда перед выходом в монитор
        cpu.modAdd(new StopWhen(0x0022));

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
        hard.loadData(CPU_TESTS_RESOURCES + "test/test.rks", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(new Range(0x0000, 0x0600));
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(0x0057));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_exerciser_preliminary() {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The preliminary test
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080PRE.MAC

        // given
        Lik.loadRom(base, roms);
        hard.loadData(CPU_TESTS_RESOURCES + "8080pre/8080pre.rks", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(new Range(0x0000, 0x0400));
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(0x004D));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();
        assertTrace();
    }

    @Test
    public void testLik_diagnostic_zexlax_8080_exerciser() {
        // zexlax.z80 - Z80 instruction set exerciser
        // Copyright (C) 1994  Frank D. Cringle
        // Modified to exercise an 8080 by Ian Bartholomew, February 2009

        // given
        Lik.loadRom(base, roms);
        hard.loadData(CPU_TESTS_RESOURCES + "8080ex1/8080ex1.rks", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(new Range(0x0000, 0x0900));
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(4);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(0x0037));
        // если хочется подебажить внутри - это адрес точки сообщения об ошибке
         cpu.modAdd(new DebugWhen(0x0004, cpu -> {
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