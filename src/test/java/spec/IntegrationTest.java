package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.mods.DebugWhen;
import spec.mods.StopWhen;
import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.io.File;
import java.net.URL;

import static spec.Constants.START_POINT;
import static spec.FileAssert.write;
import static spec.KeyCode.*;

public class IntegrationTest extends AbstractTest {

    private static final int K10 = 10_000;
    private static final int TICKS = 10_000_000;

    private static final String TEST_RESOURCES = "src/test/resources/";
    private static final String APP_RESOURCES = "src/main/resources/";

    @Rule
    public TestName test = new TestName();

    private URL base;
    private FileAssert fileAssert;
    private PngVideo video;

    @Before
    @Override
    public void before() throws Exception {
        super.before();

        video = new PngVideo(hard.video(), hard.memory());
        base = new File(APP_RESOURCES).toURI().toURL();
        record.screenShoot(this::assertScreen);
        fileAssert = new FileAssert(TEST_RESOURCES + test.getMethodName());
        fileAssert.removeTestsData();
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

            // этот хак позволяет запускать игру со следующим уровенем
            cpu.PC(0x4567);
            start();
        }
    }

    @Test
    public void testLik_klad_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when
        assertRecord("klad.rec");
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

    private void assertRecord(String path) {
        fileRecorder.startWriting();
        int lastTick = hard.loadRecord(TEST_RESOURCES + "recordings/" + path);
        record.after(lastTick).stopCpu();
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
        hard.loadData(APP_RESOURCES + "test/hello_world.rks", Lik.PLATFORM);
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
        hard.loadData(APP_RESOURCES + "test/test.rks", Lik.PLATFORM);
        // выводим trace только в этом диапазоне
        debug.enable(new Range(0x0000, 0x0100));
        // не показываем в trace все что относится к выводу на экран
        debug.showCallBellow(3);
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(0x0057));
        // если хочется подебажить внутри
        cpu.modAdd(new DebugWhen(0x0383, () ->
                assertCpu("cpu_at_0x0383")));

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
        hard.loadData(APP_RESOURCES + "test/8080pre.rks", Lik.PLATFORM);
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
}