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
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static spec.Constants.START_POINT;
import static spec.KeyCode.*;
import static spec.SmartAssert.fail;

public class IntegrationTest extends AbstractTest {

    private static final int K10 = 10_000;
    private static final int TICKS = 10_000_000;

    private static final String TEST_RESOURCES = "src/test/resources/";
    private static final String APP_RESOURCES = "src/main/resources/";

    @Rule
    public TestName test = new TestName();
    private URL base;

    private PngVideo video;
    private Map<String, String> hashes = new HashMap<>();

    @Before
    @Override
    public void before() throws Exception {
        super.before();

        video = new PngVideo(hard.video(), hard.memory());
        base = new File(APP_RESOURCES).toURI().toURL();
        record.screenShoot(this::assertScreen);
        removeTestsData();
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

    private void removeTestsData() {
        File dir = testDir();
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            String name = file.getName();
            // на всякий случай чтобы не удалить лишнего
            if (!name.endsWith(".png") && !name.equals("cpu.txt")) {
                continue;
            }

            // перед удалением сохраним хеш, потом по нему будем сравнивать
            hashes.put(file.getAbsolutePath(), hash(file));
            file.delete();
        }
        dir.delete();
    }

    private void assertScreen() {
        assertScreen("end");
    }

    private void assertScreen(String name) {
        assertFile("Screenshots", name + ".png",
                file -> video.drawToFile(file));
    }

    private void write(File file, String string) {
        try (FileWriter writer = new FileWriter(file.getAbsolutePath(), false)) {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void assertCpu() {
        assertFile("Cpu state", "cpu.txt",
                file -> write(file, cpu.toStringDetails()));
    }

    private void assertFile(String info, String name, Consumer<File> save) {
        File file = new File(testDir().getAbsolutePath() + "/" + name);
        String hash = hashes.get(file.getAbsolutePath());

        save.accept(file);

        if (!Objects.equals(hash, hash(file))) {
            fail(info + " was changed.\n"
                    + file.getAbsolutePath() + "\n"
                    + "Please check git diff to see differences.\n");
        }
    }

    private String hash(File file) {
        return HashUtils.hashFile(file, "MD5");
    }

    private File testDir() {
        File result = new File(TEST_RESOURCES + test.getMethodName());
        if (!result.exists()) {
            result.mkdir();
        }
        return result;
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

        assertTrace("" +
                "0004  21 12 00  LXI H,0012  (1)  BC:0000  DE:0000  HL:0004  AF:0046  SP:7FFD  PC:0004   \n" +
                "0007  CD 25 00  CALL 0025   (1)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FFD  PC:0007   \n" +
                "0025  C5        PUSH B      (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FFB  PC:0025   \n" +
                "0026  D5        PUSH D      (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FF9  PC:0026   \n" +
                "0027  E5        PUSH H      (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FF7  PC:0027   \n" +
                "0028  F5        PUSH PSW    (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FF5  PC:0028   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0012  AF:0D46  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:000D  DE:0000  HL:0012  AF:0D83  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:000D  DE:0000  HL:0012  AF:0D83  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:000D  DE:0000  HL:0012  AF:0D83  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:000D  DE:0000  HL:0012  AF:0D83  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:000D  DE:0000  HL:0012  AF:0D83  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0012  AF:0D83  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0013  AF:0D83  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0013  AF:0D83  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:000A  DE:0000  HL:0013  AF:0A83  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:000A  DE:0000  HL:0013  AF:0A83  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:000A  DE:0000  HL:0013  AF:0A83  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:000A  DE:0000  HL:0013  AF:0A83  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:000A  DE:0000  HL:0013  AF:0A83  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0013  AF:0A83  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0014  AF:0A83  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0014  AF:0A83  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0014  AF:4883  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0048  DE:0000  HL:0014  AF:4806  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0048  DE:0000  HL:0014  AF:4806  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0048  DE:0000  HL:0014  AF:4806  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0048  DE:0000  HL:0014  AF:4806  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0048  DE:0000  HL:0014  AF:4806  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0014  AF:4806  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0015  AF:4806  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0015  AF:4806  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0045  DE:0000  HL:0015  AF:4506  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0045  DE:0000  HL:0015  AF:4506  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0045  DE:0000  HL:0015  AF:4506  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0045  DE:0000  HL:0015  AF:4506  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0045  DE:0000  HL:0015  AF:4506  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0015  AF:4506  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0016  AF:4506  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0016  AF:4506  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:004C  DE:0000  HL:0016  AF:4C06  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:004C  DE:0000  HL:0016  AF:4C06  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:004C  DE:0000  HL:0016  AF:4C06  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:004C  DE:0000  HL:0016  AF:4C06  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:004C  DE:0000  HL:0016  AF:4C06  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0016  AF:4C06  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:004C  DE:0000  HL:0017  AF:4C06  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:004C  DE:0000  HL:0017  AF:4C06  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:004C  DE:0000  HL:0017  AF:4C06  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:004C  DE:0000  HL:0017  AF:4C06  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:004C  DE:0000  HL:0017  AF:4C06  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0017  AF:4C06  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0018  AF:4C06  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0018  AF:4C06  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:004F  DE:0000  HL:0018  AF:4F06  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:004F  DE:0000  HL:0018  AF:4F06  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:004F  DE:0000  HL:0018  AF:4F06  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:004F  DE:0000  HL:0018  AF:4F06  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:004F  DE:0000  HL:0018  AF:4F06  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0018  AF:4F06  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0019  AF:4F06  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0019  AF:4F06  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0019  AF:2006  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0020  DE:0000  HL:0019  AF:2097  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0020  DE:0000  HL:0019  AF:2097  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0020  DE:0000  HL:0019  AF:2097  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0020  DE:0000  HL:0019  AF:2097  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0020  DE:0000  HL:0019  AF:2097  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:001A  AF:2097  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001A  AF:2097  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001A  AF:5797  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0057  DE:0000  HL:001A  AF:5706  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0057  DE:0000  HL:001A  AF:5706  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0057  DE:0000  HL:001A  AF:5706  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0057  DE:0000  HL:001A  AF:5706  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0057  DE:0000  HL:001A  AF:5706  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:001A  AF:5706  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:001B  AF:5706  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001B  AF:5706  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:004F  DE:0000  HL:001B  AF:4F06  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:004F  DE:0000  HL:001B  AF:4F06  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:004F  DE:0000  HL:001B  AF:4F06  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:004F  DE:0000  HL:001B  AF:4F06  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:004F  DE:0000  HL:001B  AF:4F06  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:001B  AF:4F06  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:001C  AF:4F06  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001C  AF:4F06  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001C  AF:5206  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0052  DE:0000  HL:001C  AF:5216  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0052  DE:0000  HL:001C  AF:5216  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0052  DE:0000  HL:001C  AF:5216  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0052  DE:0000  HL:001C  AF:5216  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0052  DE:0000  HL:001C  AF:5216  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:001C  AF:5216  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:001D  AF:5216  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001D  AF:5216  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001D  AF:4C16  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:004C  DE:0000  HL:001D  AF:4C06  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:004C  DE:0000  HL:001D  AF:4C06  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:004C  DE:0000  HL:001D  AF:4C06  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:004C  DE:0000  HL:001D  AF:4C06  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:004C  DE:0000  HL:001D  AF:4C06  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:001D  AF:4C06  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:001E  AF:4C06  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001E  AF:4C06  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001E  AF:4406  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0044  DE:0000  HL:001E  AF:4402  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0044  DE:0000  HL:001E  AF:4402  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0044  DE:0000  HL:001E  AF:4402  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0044  DE:0000  HL:001E  AF:4402  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0044  DE:0000  HL:001E  AF:4402  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:001E  AF:4402  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:001F  AF:4402  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001F  AF:4402  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001F  AF:0D02  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:000D  DE:0000  HL:001F  AF:0D83  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:000D  DE:0000  HL:001F  AF:0D83  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:000D  DE:0000  HL:001F  AF:0D83  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:000D  DE:0000  HL:001F  AF:0D83  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:000D  DE:0000  HL:001F  AF:0D83  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:001F  AF:0D83  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0020  AF:0D83  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0020  AF:0D83  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FF3  PC:002C   \n" +
                "002F  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FF3  PC:002F   \n" +
                "0030  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FF3  PC:0030   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:000A  DE:0000  HL:0020  AF:0A83  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:000A  DE:0000  HL:0020  AF:0A83  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:000A  DE:0000  HL:0020  AF:0A83  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:000A  DE:0000  HL:0020  AF:0A83  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:000A  DE:0000  HL:0020  AF:0A83  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FF1  PC:0048   \n" +
                "0033  23        INX H       (2)  BC:0000  DE:0000  HL:0020  AF:0A83  SP:7FF3  PC:0033   \n" +
                "0034  C3 29 00  JMP 0029    (2)  BC:0000  DE:0000  HL:0021  AF:0A83  SP:7FF3  PC:0034   \n" +
                "0029  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0021  AF:0A83  SP:7FF3  PC:0029   \n" +
                "002A  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0021  AF:2483  SP:7FF3  PC:002A   \n" +
                "002C  CA 37 00  JZ 0037     (2)  BC:0000  DE:0000  HL:0021  AF:2446  SP:7FF3  PC:002C   \n" +
                "0037  F1        POP PSW     (2)  BC:0000  DE:0000  HL:0021  AF:2446  SP:7FF3  PC:0037   \n" +
                "0038  E1        POP H       (2)  BC:0000  DE:0000  HL:0021  AF:0046  SP:7FF5  PC:0038   \n" +
                "0039  D1        POP D       (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FF7  PC:0039   \n" +
                "003A  C1        POP B       (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FF9  PC:003A   \n" +
                "003B  C9        RET         (2)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FFB  PC:003B   \n" +
                "000A  3E FE     MVI A,FE    (1)  BC:0000  DE:0000  HL:0012  AF:0046  SP:7FFD  PC:000A   \n" +
                "000C  CD 49 00  CALL 0049   (1)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FFD  PC:000C   \n" +
                "0049  C5        PUSH B      (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FFB  PC:0049   \n" +
                "004A  D5        PUSH D      (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF9  PC:004A   \n" +
                "004B  E5        PUSH H      (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF7  PC:004B   \n" +
                "004C  F5        PUSH PSW    (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF5  PC:004C   \n" +
                "004D  F5        PUSH PSW    (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF3  PC:004D   \n" +
                "004E  CD 60 00  CALL 0060   (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF1  PC:004E   \n" +
                "0060  0F        RRC         (3)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FEF  PC:0060   \n" +
                "0061  0F        RRC         (3)  BC:0000  DE:0000  HL:0012  AF:7F46  SP:7FEF  PC:0061   \n" +
                "0062  0F        RRC         (3)  BC:0000  DE:0000  HL:0012  AF:BF47  SP:7FEF  PC:0062   \n" +
                "0063  0F        RRC         (3)  BC:0000  DE:0000  HL:0012  AF:DF47  SP:7FEF  PC:0063   \n" +
                "0064  E6 0F     ANI 0F      (3)  BC:0000  DE:0000  HL:0012  AF:EF47  SP:7FEF  PC:0064   \n" +
                "0066  FE 0A     CPI 0A      (3)  BC:0000  DE:0000  HL:0012  AF:0F16  SP:7FEF  PC:0066   \n" +
                "0068  FA 6D 00  JM 006D     (3)  BC:0000  DE:0000  HL:0012  AF:0F06  SP:7FEF  PC:0068   \n" +
                "006B  C6 07     ADI 07      (3)  BC:0000  DE:0000  HL:0012  AF:0F06  SP:7FEF  PC:006B   \n" +
                "006D  C6 30     ADI 30      (3)  BC:0000  DE:0000  HL:0012  AF:1612  SP:7FEF  PC:006D   \n" +
                "006F  C9        RET         (3)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FEF  PC:006F   \n" +
                "0051  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FF1  PC:0051   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FEF  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FED  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FEB  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FE9  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FE7  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0046  DE:0000  HL:0012  AF:4602  SP:7FE7  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0046  DE:0000  HL:0012  AF:4602  SP:7FE7  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0046  DE:0000  HL:0012  AF:4602  SP:7FE9  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0046  DE:0000  HL:0012  AF:4602  SP:7FEB  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0046  DE:0000  HL:0012  AF:4602  SP:7FED  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FEF  PC:0048   \n" +
                "0054  F1        POP PSW     (2)  BC:0000  DE:0000  HL:0012  AF:4602  SP:7FF1  PC:0054   \n" +
                "0055  CD 64 00  CALL 0064   (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF3  PC:0055   \n" +
                "0064  E6 0F     ANI 0F      (3)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF1  PC:0064   \n" +
                "0066  FE 0A     CPI 0A      (3)  BC:0000  DE:0000  HL:0012  AF:0E12  SP:7FF1  PC:0066   \n" +
                "0068  FA 6D 00  JM 006D     (3)  BC:0000  DE:0000  HL:0012  AF:0E02  SP:7FF1  PC:0068   \n" +
                "006B  C6 07     ADI 07      (3)  BC:0000  DE:0000  HL:0012  AF:0E02  SP:7FF1  PC:006B   \n" +
                "006D  C6 30     ADI 30      (3)  BC:0000  DE:0000  HL:0012  AF:1512  SP:7FF1  PC:006D   \n" +
                "006F  C9        RET         (3)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FF1  PC:006F   \n" +
                "0058  CD 3C 00  CALL 003C   (2)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FF3  PC:0058   \n" +
                "003C  C5        PUSH B      (3)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FF1  PC:003C   \n" +
                "003D  D5        PUSH D      (3)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FEF  PC:003D   \n" +
                "003E  E5        PUSH H      (3)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FED  PC:003E   \n" +
                "003F  F5        PUSH PSW    (3)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FEB  PC:003F   \n" +
                "0040  4F        MOV C,A     (3)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FE9  PC:0040   \n" +
                "0041  CD 37 C0  CALL C037   (3)  BC:0045  DE:0000  HL:0012  AF:4502  SP:7FE9  PC:0041   \n" +
                "0044  F1        POP PSW     (3)  BC:0045  DE:0000  HL:0012  AF:4502  SP:7FE9  PC:0044   \n" +
                "0045  E1        POP H       (3)  BC:0045  DE:0000  HL:0012  AF:4502  SP:7FEB  PC:0045   \n" +
                "0046  D1        POP D       (3)  BC:0045  DE:0000  HL:0012  AF:4502  SP:7FED  PC:0046   \n" +
                "0047  C1        POP B       (3)  BC:0045  DE:0000  HL:0012  AF:4502  SP:7FEF  PC:0047   \n" +
                "0048  C9        RET         (3)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FF1  PC:0048   \n" +
                "005B  F1        POP PSW     (2)  BC:0000  DE:0000  HL:0012  AF:4502  SP:7FF3  PC:005B   \n" +
                "005C  E1        POP H       (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF5  PC:005C   \n" +
                "005D  D1        POP D       (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF7  PC:005D   \n" +
                "005E  C1        POP B       (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FF9  PC:005E   \n" +
                "005F  C9        RET         (2)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FFB  PC:005F   \n" +
                "000F  C3 22 00  JMP 0022    (1)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FFD  PC:000F   \n" +
                "0022  C3 00 C8  JMP C800    (1)  BC:0000  DE:0000  HL:0012  AF:FE46  SP:7FFD  PC:0022   ");
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
                asrtCpu("BC:  4445\n" +
                        "DE:  4647\n" +
                        "HL:  05D5\n" +
                        "AF:  D546\n" +
                        "SP:  06D3\n" +
                        "PC:  0383\n" +
                        "B,C: 44 45\n" +
                        "D,E: 46 47\n" +
                        "H,L: 05 D5\n" +
                        "M:   D5\n" +
                        "A,F: D5 46\n" +
                        "     76543210 76543210\n" +
                        "SP:  00000110 11010011\n" +
                        "PC:  00000011 10000011\n" +
                        "     76543210\n" +
                        "B:   01000100\n" +
                        "C:   01000101\n" +
                        "D:   01000110\n" +
                        "E:   01000111\n" +
                        "H:   00000101\n" +
                        "L:   11010101\n" +
                        "M:   11010101\n" +
                        "A:   11010101\n" +
                        "     sz0h0p1c\n" +
                        "F:   01000110\n" +
                        "ts:  false\n" +
                        "tz:  true\n" +
                        "th:  false\n" +
                        "tp:  true\n" +
                        "tc:  false\n")));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();

        assertTrace("" +
                "0004  21 0D 00  LXI H,000D  (1)  BC:0000  DE:0000  HL:0004  AF:0046  SP:7FFD  PC:0004   \n" +
                "0007  CD 5A 00  CALL 005A   (1)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FFD  PC:0007   \n" +
                "005A  C5        PUSH B      (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FFB  PC:005A   \n" +
                "005B  D5        PUSH D      (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FF9  PC:005B   \n" +
                "005C  E5        PUSH H      (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FF7  PC:005C   \n" +
                "005D  F5        PUSH PSW    (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FF5  PC:005D   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:000D  AF:0D46  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:000E  AF:0D83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:000E  AF:0D83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:000E  AF:0A83  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:000E  AF:0A83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:000E  AF:0A83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:000E  AF:0A83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:000E  AF:0A83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:000F  AF:0A83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:000F  AF:0A83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:000F  AF:4D83  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:000F  AF:4D02  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:000F  AF:4D02  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:000F  AF:4D02  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:000F  AF:4D02  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0010  AF:4D02  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0010  AF:4D02  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0010  AF:4902  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0010  AF:4902  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0010  AF:4902  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0010  AF:4902  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0010  AF:4902  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0011  AF:4902  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0011  AF:4902  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0011  AF:4302  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0011  AF:4312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0011  AF:4312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0011  AF:4312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0011  AF:4312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0012  AF:4312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0012  AF:4312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0012  AF:5212  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0012  AF:5216  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0012  AF:5216  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0012  AF:5216  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0012  AF:5216  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0013  AF:5216  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0013  AF:5216  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0013  AF:4F16  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0013  AF:4F06  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0013  AF:4F06  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0013  AF:4F06  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0013  AF:4F06  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0014  AF:4F06  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0014  AF:4F06  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0014  AF:4306  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0014  AF:4312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0014  AF:4312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0014  AF:4312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0014  AF:4312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0015  AF:4312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0015  AF:4312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0015  AF:4F12  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0015  AF:4F06  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0015  AF:4F06  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0015  AF:4F06  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0015  AF:4F06  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0016  AF:4F06  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0016  AF:4F06  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0016  AF:5306  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0016  AF:5312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0016  AF:5312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0016  AF:5312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0016  AF:5312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0017  AF:5312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0017  AF:5312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0017  AF:4D12  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0017  AF:4D02  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0017  AF:4D02  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0017  AF:4D02  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0017  AF:4D02  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0018  AF:4D02  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0018  AF:4D02  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0018  AF:2002  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0018  AF:2097  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0018  AF:2097  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0018  AF:2097  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0018  AF:2097  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0019  AF:2097  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0019  AF:4197  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0019  AF:4116  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0019  AF:4116  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0019  AF:4116  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0019  AF:4116  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:001A  AF:4116  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001A  AF:4116  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001A  AF:5316  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:001A  AF:5312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001A  AF:5312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:001A  AF:5312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:001A  AF:5312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:001B  AF:5312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001B  AF:5312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001B  AF:5312  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:001B  AF:5312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001B  AF:5312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:001B  AF:5312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:001B  AF:5312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:001C  AF:5312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001C  AF:5312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001C  AF:4F12  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:001C  AF:4F06  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001C  AF:4F06  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:001C  AF:4F06  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:001C  AF:4F06  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:001D  AF:4F06  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001D  AF:4F06  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001D  AF:4306  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:001D  AF:4312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001D  AF:4312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:001D  AF:4312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:001D  AF:4312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:001E  AF:4312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001E  AF:4312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001E  AF:4912  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:001E  AF:4902  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001E  AF:4902  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:001E  AF:4902  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:001E  AF:4902  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:001F  AF:4902  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001F  AF:4902  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:001F  AF:4102  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:001F  AF:4116  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:001F  AF:4116  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:001F  AF:4116  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:001F  AF:4116  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0020  AF:4116  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0020  AF:4116  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0020  AF:5416  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0020  AF:5406  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0020  AF:5406  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0020  AF:5406  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0020  AF:5406  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0021  AF:5406  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0021  AF:5406  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0021  AF:4506  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0021  AF:4506  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0021  AF:4506  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0021  AF:4506  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0021  AF:4506  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0022  AF:4506  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0022  AF:4506  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0022  AF:5306  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0022  AF:5312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0022  AF:5312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0022  AF:5312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0022  AF:5312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0023  AF:5312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0023  AF:5312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0023  AF:0D12  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0023  AF:0D83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0023  AF:0D83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0023  AF:0D83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0023  AF:0D83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0024  AF:0D83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0024  AF:0D83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0024  AF:0A83  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0024  AF:0A83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0024  AF:0A83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0024  AF:0A83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0024  AF:0A83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0025  AF:0A83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0025  AF:0A83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0025  AF:3883  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0025  AF:3806  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0025  AF:3806  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0025  AF:3806  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0025  AF:3806  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0026  AF:3806  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0026  AF:3806  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0026  AF:3006  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0026  AF:3016  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0026  AF:3016  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0026  AF:3016  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0026  AF:3016  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0027  AF:3016  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0027  AF:3016  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0027  AF:3816  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0027  AF:3806  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0027  AF:3806  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0027  AF:3806  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0027  AF:3806  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0028  AF:3806  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0028  AF:3806  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0028  AF:3006  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0028  AF:3016  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0028  AF:3016  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0028  AF:3016  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0028  AF:3016  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0029  AF:3016  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0029  AF:3016  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0029  AF:2F16  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0029  AF:2F02  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0029  AF:2F02  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0029  AF:2F02  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0029  AF:2F02  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:002A  AF:2F02  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002A  AF:2F02  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:002A  AF:3802  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:002A  AF:3806  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002A  AF:3806  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:002A  AF:3806  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:002A  AF:3806  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:002B  AF:3806  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002B  AF:3806  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:002B  AF:3006  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:002B  AF:3016  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002B  AF:3016  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:002B  AF:3016  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:002B  AF:3016  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:002C  AF:3016  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002C  AF:3016  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:002C  AF:3816  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:002C  AF:3806  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002C  AF:3806  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:002C  AF:3806  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:002C  AF:3806  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:002D  AF:3806  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002D  AF:3806  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:002D  AF:3506  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:002D  AF:3506  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002D  AF:3506  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:002D  AF:3506  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:002D  AF:3506  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:002E  AF:3506  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002E  AF:3506  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:002E  AF:2006  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:002E  AF:2097  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002E  AF:2097  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:002E  AF:2097  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:002E  AF:2097  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:002F  AF:2097  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002F  AF:2097  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:002F  AF:4397  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:002F  AF:4312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:002F  AF:4312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:002F  AF:4312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:002F  AF:4312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0030  AF:4312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0030  AF:4312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0030  AF:5012  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0030  AF:5012  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0030  AF:5012  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0030  AF:5012  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0030  AF:5012  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0031  AF:5012  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0031  AF:5012  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0031  AF:5512  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0031  AF:5502  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0031  AF:5502  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0031  AF:5502  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0031  AF:5502  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0032  AF:5502  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0032  AF:5502  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0032  AF:2002  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0032  AF:2097  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0032  AF:2097  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0032  AF:2097  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0032  AF:2097  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0033  AF:2097  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0033  AF:2097  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0033  AF:4497  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0033  AF:4402  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0033  AF:4402  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0033  AF:4402  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0033  AF:4402  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0034  AF:4402  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0034  AF:4402  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0034  AF:4902  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0034  AF:4902  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0034  AF:4902  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0034  AF:4902  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0034  AF:4902  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0035  AF:4902  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0035  AF:4902  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0035  AF:4102  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0035  AF:4116  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0035  AF:4116  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0035  AF:4116  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0035  AF:4116  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0036  AF:4116  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0036  AF:4116  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0036  AF:4716  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0036  AF:4702  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0036  AF:4702  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0036  AF:4702  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0036  AF:4702  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0037  AF:4702  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0037  AF:4702  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0037  AF:4E02  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0037  AF:4E02  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0037  AF:4E02  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0037  AF:4E02  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0037  AF:4E02  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0038  AF:4E02  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0038  AF:4E02  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0038  AF:4F02  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0038  AF:4F06  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0038  AF:4F06  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0038  AF:4F06  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0038  AF:4F06  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0039  AF:4F06  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0039  AF:4F06  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0039  AF:5306  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0039  AF:5312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0039  AF:5312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0039  AF:5312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0039  AF:5312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:003A  AF:5312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003A  AF:5312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:003A  AF:5412  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:003A  AF:5406  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003A  AF:5406  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:003A  AF:5406  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:003A  AF:5406  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:003B  AF:5406  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003B  AF:5406  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:003B  AF:4906  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:003B  AF:4902  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003B  AF:4902  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:003B  AF:4902  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:003B  AF:4902  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:003C  AF:4902  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003C  AF:4902  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:003C  AF:4302  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:003C  AF:4312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003C  AF:4312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:003C  AF:4312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:003C  AF:4312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:003D  AF:4312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003D  AF:4312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:003D  AF:0D12  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:003D  AF:0D83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003D  AF:0D83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:003D  AF:0D83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:003D  AF:0D83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:003E  AF:0D83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003E  AF:0D83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:003E  AF:0A83  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:003E  AF:0A83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003E  AF:0A83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:003E  AF:0A83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:003E  AF:0A83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:003F  AF:0A83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003F  AF:0A83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:003F  AF:5683  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:003F  AF:5602  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:003F  AF:5602  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:003F  AF:5602  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:003F  AF:5602  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0040  AF:5602  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0040  AF:5602  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0040  AF:4502  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0040  AF:4506  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0040  AF:4506  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0040  AF:4506  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0040  AF:4506  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0041  AF:4506  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0041  AF:4506  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0041  AF:5206  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0041  AF:5216  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0041  AF:5216  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0041  AF:5216  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0041  AF:5216  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0042  AF:5216  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0042  AF:5216  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0042  AF:5316  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0042  AF:5312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0042  AF:5312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0042  AF:5312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0042  AF:5312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0043  AF:5312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0043  AF:5312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0043  AF:4912  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0043  AF:4902  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0043  AF:4902  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0043  AF:4902  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0043  AF:4902  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0044  AF:4902  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0044  AF:4902  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0044  AF:4F02  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0044  AF:4F06  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0044  AF:4F06  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0044  AF:4F06  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0044  AF:4F06  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0045  AF:4F06  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0045  AF:4F06  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0045  AF:4E06  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0045  AF:4E02  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0045  AF:4E02  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0045  AF:4E02  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0045  AF:4E02  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0046  AF:4E02  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0046  AF:4E02  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0046  AF:2002  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0046  AF:2097  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0046  AF:2097  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0046  AF:2097  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0046  AF:2097  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0047  AF:2097  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0047  AF:2097  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0047  AF:3197  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0047  AF:3112  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0047  AF:3112  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0047  AF:3112  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0047  AF:3112  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0048  AF:3112  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0048  AF:3112  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0048  AF:2E12  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0048  AF:2E06  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0048  AF:2E06  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0048  AF:2E06  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0048  AF:2E06  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0049  AF:2E06  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0049  AF:2E06  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0049  AF:3006  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0049  AF:3016  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0049  AF:3016  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0049  AF:3016  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0049  AF:3016  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:004A  AF:3016  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004A  AF:3016  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:004A  AF:2016  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:004A  AF:2097  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004A  AF:2097  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:004A  AF:2097  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:004A  AF:2097  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:004B  AF:2097  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004B  AF:2097  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:004B  AF:2097  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:004B  AF:2097  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004B  AF:2097  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:004B  AF:2097  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:004B  AF:2097  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:004C  AF:2097  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004C  AF:2097  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:004C  AF:2897  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:004C  AF:2802  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004C  AF:2802  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:004C  AF:2802  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:004C  AF:2802  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:004D  AF:2802  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004D  AF:2802  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:004D  AF:4302  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:004D  AF:4312  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004D  AF:4312  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:004D  AF:4312  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:004D  AF:4312  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:004E  AF:4312  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004E  AF:4312  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:004E  AF:2912  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:004E  AF:2906  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004E  AF:2906  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:004E  AF:2906  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:004E  AF:2906  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:004F  AF:2906  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004F  AF:2906  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:004F  AF:2006  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:004F  AF:2097  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:004F  AF:2097  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:004F  AF:2097  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:004F  AF:2097  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0050  AF:2097  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0050  AF:2097  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0050  AF:3197  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0050  AF:3112  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0050  AF:3112  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0050  AF:3112  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0050  AF:3112  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0051  AF:3112  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0051  AF:3112  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0051  AF:3912  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0051  AF:3902  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0051  AF:3902  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0051  AF:3902  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0051  AF:3902  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0052  AF:3902  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0052  AF:3902  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0052  AF:3802  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0052  AF:3806  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0052  AF:3806  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0052  AF:3806  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0052  AF:3806  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0053  AF:3806  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0053  AF:3806  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0053  AF:3006  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0053  AF:3016  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0053  AF:3016  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0053  AF:3016  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0053  AF:3016  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0054  AF:3016  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0054  AF:3016  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0054  AF:0D16  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0054  AF:0D83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0054  AF:0D83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0054  AF:0D83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0054  AF:0D83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0055  AF:0D83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0055  AF:0D83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0055  AF:0A83  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0055  AF:0A83  SP:7FF3  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0055  AF:0A83  SP:7FF3  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:0000  DE:0000  HL:0055  AF:0A83  SP:7FF3  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:0000  DE:0000  HL:0055  AF:0A83  SP:7FF3  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:0000  DE:0000  HL:0056  AF:0A83  SP:7FF3  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:0000  DE:0000  HL:0056  AF:0A83  SP:7FF3  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:0000  DE:0000  HL:0056  AF:2483  SP:7FF3  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:0000  DE:0000  HL:0056  AF:2446  SP:7FF3  PC:0061   \n" +
                "006C  F1        POP PSW     (2)  BC:0000  DE:0000  HL:0056  AF:2446  SP:7FF3  PC:006C   \n" +
                "006D  E1        POP H       (2)  BC:0000  DE:0000  HL:0056  AF:0046  SP:7FF5  PC:006D   \n" +
                "006E  D1        POP D       (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FF7  PC:006E   \n" +
                "006F  C1        POP B       (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FF9  PC:006F   \n" +
                "0070  C9        RET         (2)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FFB  PC:0070   \n" +
                "000A  C3 D9 00  JMP 00D9    (1)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FFD  PC:000A   \n" +
                "00D9  31 D5 06  LXI SP,06D5 (1)  BC:0000  DE:0000  HL:000D  AF:0046  SP:7FFD  PC:00D9   \n" +
                "00DC  E6 00     ANI 00      (1)  BC:0000  DE:0000  HL:000D  AF:0046  SP:06D5  PC:00DC   \n" +
                "00DE  CA E4 00  JZ 00E4     (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00DE   \n" +
                "00E4  D2 EA 00  JNC 00EA    (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00E4   \n" +
                "00EA  EA F0 00  JPE 00F0    (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00EA   \n" +
                "00F0  F2 F6 00  JP 00F6     (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00F0   \n" +
                "00F6  C2 05 01  JNZ 0105    (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00F6   \n" +
                "00F9  DA 05 01  JC 0105     (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00F9   \n" +
                "00FC  E2 05 01  JPO 0105    (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00FC   \n" +
                "00FF  FA 05 01  JM 0105     (1)  BC:0000  DE:0000  HL:000D  AF:0056  SP:06D5  PC:00FF   \n" +
                "005A  C5        PUSH B      (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06D3  PC:005A   \n" +
                "005B  D5        PUSH D      (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06D1  PC:005B   \n" +
                "005C  E5        PUSH H      (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06CF  PC:005C   \n" +
                "005D  F5        PUSH PSW    (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06CD  PC:005D   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00A5  AF:0D46  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00A5  AF:0D83  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A5  AF:0D83  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00A5  AF:0D83  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00A5  AF:0D83  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00A6  AF:0D83  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A6  AF:0D83  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00A6  AF:0A83  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00A6  AF:0A83  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A6  AF:0A83  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00A6  AF:0A83  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00A6  AF:0A83  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00A7  AF:0A83  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A7  AF:0A83  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00A7  AF:4383  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00A7  AF:4312  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A7  AF:4312  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00A7  AF:4312  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00A7  AF:4312  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00A8  AF:4312  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A8  AF:4312  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00A8  AF:5012  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00A8  AF:5012  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A8  AF:5012  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00A8  AF:5012  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00A8  AF:5012  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00A9  AF:5012  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A9  AF:5012  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00A9  AF:5512  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00A9  AF:5502  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00A9  AF:5502  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00A9  AF:5502  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00A9  AF:5502  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00AA  AF:5502  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AA  AF:5502  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00AA  AF:2002  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00AA  AF:2097  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AA  AF:2097  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00AA  AF:2097  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00AA  AF:2097  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00AB  AF:2097  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AB  AF:2097  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00AB  AF:4997  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00AB  AF:4902  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AB  AF:4902  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00AB  AF:4902  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00AB  AF:4902  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00AC  AF:4902  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AC  AF:4902  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00AC  AF:5302  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00AC  AF:5312  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AC  AF:5312  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00AC  AF:5312  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00AC  AF:5312  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00AD  AF:5312  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AD  AF:5312  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00AD  AF:2012  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00AD  AF:2097  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AD  AF:2097  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00AD  AF:2097  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00AD  AF:2097  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00AE  AF:2097  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AE  AF:2097  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00AE  AF:4F97  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00AE  AF:4F06  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AE  AF:4F06  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00AE  AF:4F06  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00AE  AF:4F06  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00AF  AF:4F06  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AF  AF:4F06  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00AF  AF:5006  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00AF  AF:5012  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00AF  AF:5012  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00AF  AF:5012  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00AF  AF:5012  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B0  AF:5012  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B0  AF:5012  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B0  AF:4512  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B0  AF:4506  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B0  AF:4506  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B0  AF:4506  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B0  AF:4506  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B1  AF:4506  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B1  AF:4506  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B1  AF:5206  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B1  AF:5216  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B1  AF:5216  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B1  AF:5216  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B1  AF:5216  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B2  AF:5216  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B2  AF:5216  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B2  AF:4116  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B2  AF:4116  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B2  AF:4116  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B2  AF:4116  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B2  AF:4116  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B3  AF:4116  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B3  AF:4116  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B3  AF:5416  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B3  AF:5406  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B3  AF:5406  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B3  AF:5406  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B3  AF:5406  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B4  AF:5406  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B4  AF:5406  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B4  AF:4906  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B4  AF:4902  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B4  AF:4902  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B4  AF:4902  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B4  AF:4902  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B5  AF:4902  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B5  AF:4902  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B5  AF:4F02  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B5  AF:4F06  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B5  AF:4F06  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B5  AF:4F06  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B5  AF:4F06  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B6  AF:4F06  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B6  AF:4F06  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B6  AF:4E06  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B6  AF:4E02  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B6  AF:4E02  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B6  AF:4E02  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B6  AF:4E02  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B7  AF:4E02  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B7  AF:4E02  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B7  AF:4102  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B7  AF:4116  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B7  AF:4116  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B7  AF:4116  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B7  AF:4116  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B8  AF:4116  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B8  AF:4116  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B8  AF:4C16  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B8  AF:4C06  SP:06CB  PC:0061   \n" +
                "0064  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B8  AF:4C06  SP:06CB  PC:0064   \n" +
                "0065  CD 71 00  CALL 0071   (2)  BC:AA55  DE:AAAA  HL:00B8  AF:4C06  SP:06CB  PC:0065   \n" +
                "0068  23        INX H       (2)  BC:AA55  DE:AAAA  HL:00B8  AF:4C06  SP:06CB  PC:0068   \n" +
                "0069  C3 5E 00  JMP 005E    (2)  BC:AA55  DE:AAAA  HL:00B9  AF:4C06  SP:06CB  PC:0069   \n" +
                "005E  7E        MOV A,M     (2)  BC:AA55  DE:AAAA  HL:00B9  AF:4C06  SP:06CB  PC:005E   \n" +
                "005F  FE 24     CPI 24      (2)  BC:AA55  DE:AAAA  HL:00B9  AF:2406  SP:06CB  PC:005F   \n" +
                "0061  CA 6C 00  JZ 006C     (2)  BC:AA55  DE:AAAA  HL:00B9  AF:2446  SP:06CB  PC:0061   \n" +
                "006C  F1        POP PSW     (2)  BC:AA55  DE:AAAA  HL:00B9  AF:2446  SP:06CB  PC:006C   \n" +
                "006D  E1        POP H       (2)  BC:AA55  DE:AAAA  HL:00B9  AF:AA46  SP:06CD  PC:006D   \n" +
                "006E  D1        POP D       (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06CF  PC:006E   \n" +
                "006F  C1        POP B       (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06D1  PC:006F   \n" +
                "0070  C9        RET         (2)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06D3  PC:0070   \n" +
                "0057  C3 00 C8  JMP C800    (1)  BC:AA55  DE:AAAA  HL:00A5  AF:AA46  SP:06D5  PC:0057   ");
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
        debug.showCallBellow(1); // TODO тут надо 3, но получается очень длинно. Надо сохранять в файл
        // последняя команда программы перед выходом в монитор
        cpu.modAdd(new StopWhen(0x004D));

        // when
        hard.reset();
        hard.start();

        // then
        assertCpu();

        assertTrace("");
    }
}
