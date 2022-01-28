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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    private Map<String, String> pngHashes = new HashMap<>();

    @Before
    public void before() throws Exception {
        super.before();

        video = new PngVideo(hard.video(), hard.memory());
        base = new File(APP_RESOURCES).toURI().toURL();
        record.screenShoot(this::screenShoot);
        removeTestScreenShots();
        reset();
        record.after(TICKS).stopCpu();
    }

    private void reset() {
        record.reset();
        hard.reset();
    }

    private void removeTestScreenShots() {
        File dir = testDir();
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.getName().endsWith(".png")) { // на всякий случай
                // перед удалением сохраним хеш, потом сравним
                pngHashes.put(file.getAbsolutePath(), hash(file));
                file.delete();
            }
        }
        dir.delete();
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    private void screenShoot() {
        screenShoot("end");
    }

    private void screenShoot(String name) {
        File file = new File(testDir().getAbsolutePath() + "/" + name + ".png");
        String hash = pngHashes.get(file.getAbsolutePath());

        video.drawToFile(file);

        if (!Objects.equals(hash, hash(file))) {
            fail("Screenshots was changed.\n"
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
                .shoot("basic", it -> it.enter("B").press(ENTER).after(10 * K10))
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

        int LEVELS = 32;
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

        // then
        screenShoot();
    }

    @Test
    public void testLik_reversi_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi");

        // when
        assertRecord("reversi.rec");

        // then
        screenShoot();
    }

    @Test
    public void testLik_reversi2_recording() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "reversi2");

        // when
        assertRecord("reversi2.rec");

        // then
        screenShoot();
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

        // then
        screenShoot();
    }

    @Test
    public void testSpecialist_monitor() {
        // given
        Specialist.loadRom(base, roms);

        // when
        cpu.PC(START_POINT);
        start();

        // then
        asrtCpu("BC:  0E80\n" +
                "DE:  8F60\n" +
                "HL:  C196\n" +
                "AF:  FF02\n" +
                "SP:  8F30\n" +
                "PC:  C191\n" +
                "B,C: 0E 80\n" +
                "D,E: 8F 60\n" +
                "H,L: C1 96\n" +
                "M:   C5\n" +
                "A,F: FF 02\n" +
                "     76543210 76543210\n" +
                "SP:  10001111 00110000\n" +
                "PC:  11000001 10010001\n" +
                "     76543210\n" +
                "B:   00001110\n" +
                "C:   10000000\n" +
                "D:   10001111\n" +
                "E:   01100000\n" +
                "H:   11000001\n" +
                "L:   10010110\n" +
                "M:   11000101\n" +
                "A:   11111111\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
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
        asrtCpu("BC:  0000\n" +
                "DE:  9D21\n" +
                "HL:  C4C9\n" +
                "AF:  8987\n" +
                "SP:  8F99\n" +
                "PC:  0FA9\n" +
                "B,C: 00 00\n" +
                "D,E: 9D 21\n" +
                "H,L: C4 C9\n" +
                "M:   76\n" +
                "A,F: 89 87\n" +
                "     76543210 76543210\n" +
                "SP:  10001111 10011001\n" +
                "PC:  00001111 10101001\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000000\n" +
                "D:   10011101\n" +
                "E:   00100001\n" +
                "H:   11000100\n" +
                "L:   11001001\n" +
                "M:   01110110\n" +
                "A:   10001001\n" +
                "     sz0h0p1c\n" +
                "F:   10000111\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  true\n");

        screenShoot();
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
        asrtCpu("BC:  125C\n" +
                "DE:  0000\n" +
                "HL:  201F\n" +
                "AF:  0052\n" +
                "SP:  3FFB\n" +
                "PC:  04FE\n" +
                "B,C: 12 5C\n" +
                "D,E: 00 00\n" +
                "H,L: 20 1F\n" +
                "M:   20\n" +
                "A,F: 00 52\n" +
                "     76543210 76543210\n" +
                "SP:  00111111 11111011\n" +
                "PC:  00000100 11111110\n" +
                "     76543210\n" +
                "B:   00010010\n" +
                "C:   01011100\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00100000\n" +
                "L:   00011111\n" +
                "M:   00100000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   01010010\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testDiagnostic_case1_trace() {
        // given
        Lik.loadRom(base, roms);
        int start = roms.loadRKS(base, "test/test.rks");
        cpu.modAdd(new StopWhen(0x0055));
        cpu.modAdd(new DebugWhen(0x00C8, () -> {
            System.out.println(cpu.toStringDetails());
        }));
        debug.enable();
        debug.showCallBellow(1);

        // when
        cpu.SP(0x7FFF); // мы не запускаем C000, а потому надо самим указать, где стек
        cpu.PC(start);
        start();

        // then
        assertCpuDebug(
                "0004  21 0D 00  LXI H,000D  (0)  BC:0000  DE:0000  HL:0000  AF:0002  SP:7FFF  PC:0004   \n" +
                "0007  CD 58 00  CALL 0058   (0)  BC:0000  DE:0000  HL:000D  AF:0002  SP:7FFF  PC:0007   \n" +
                "000A  C3 C5 00  JMP 00C5    (0)  BC:000A  DE:0000  HL:0054  AF:2442  SP:7FFF  PC:000A   \n" +
                "00C5  31 BE 06  LXI SP,06BE (0)  BC:000A  DE:0000  HL:0054  AF:2442  SP:7FFF  PC:00C5   \n" +
                "00C8  E6 00     ANI 00      (0)  BC:000A  DE:0000  HL:0054  AF:2442  SP:06BE  PC:00C8   \n" +
                "00CA  CA D0 00  JZ 00D0     (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00CA   \n" +
                "00D0  D2 D6 00  JNC 00D6    (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00D0   \n" +
                "00D6  EA DC 00  JPE 00DC    (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00D6   \n" +
                "00DC  F2 E2 00  JP 00E2     (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00DC   \n" +
                "00E2  C2 F1 00  JNZ 00F1    (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00E2   \n" +
                "00E5  DA F1 00  JC 00F1     (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00E5   \n" +
                "00E8  E2 F1 00  JPO 00F1    (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00E8   \n" +
                "00EB  FA F1 00  JM 00F1     (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00EB   \n" +
                "00EE  C3 F4 00  JMP 00F4    (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00EE   \n" +
                "00F4  C6 06     ADI 06      (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:00F4   \n" +
                "00F6  C2 FC 00  JNZ 00FC    (0)  BC:000A  DE:0000  HL:0054  AF:0602  SP:06BE  PC:00F6   \n" +
                "00FC  DA 05 01  JC 0105     (0)  BC:000A  DE:0000  HL:0054  AF:0602  SP:06BE  PC:00FC   \n" +
                "00FF  E2 05 01  JPO 0105    (0)  BC:000A  DE:0000  HL:0054  AF:0602  SP:06BE  PC:00FF   \n" +
                "0105  CD A3 05  CALL 05A3   (0)  BC:000A  DE:0000  HL:0054  AF:0602  SP:06BE  PC:0105   ");
    }

    @Test
    public void testDiagnostic_case1() {
        // 8080/8085 CPU Diagnostic, version 1.0, by Microcosm Associates
        // https://github.com/begoon/i8080-core/blob/master/TEST.ASM

        // given
        Lik.loadRom(base, roms);
        hard.loadData(APP_RESOURCES + "test/test.rks");
        cpu.modAdd(new StopWhen(0x0055));

        // when
        hard.reset();
        hard.start();

        // then
        asrtCpu("BC:  0038\n" +
                "DE:  0000\n" +
                "HL:  0108\n" +
                "AF:  3802\n" +
                "SP:  06BC\n" +
                "PC:  C800\n" +
                "B,C: 00 38\n" +
                "D,E: 00 00\n" +
                "H,L: 01 08\n" +
                "M:   38\n" +
                "A,F: 38 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000110 10111100\n" +
                "PC:  11001000 00000000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00111000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000001\n" +
                "L:   00001000\n" +
                "M:   00111000\n" +
                "A:   00111000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testLik_helloWorld() {
        // given
        Lik.loadRom(base, roms);
        hard.loadData(APP_RESOURCES + "test/hello_world.rks");
        cpu.modAdd(new StopWhen(0x000A)); // последняя команда перед выходом в монитор

        // when
        hard.reset();
        hard.start();

        // then
        screenShoot();
    }

    @Test
    public void testLik_helloWorld_trace() {
        // given
        Lik.loadRom(base, roms);
        int start = roms.loadRKS(base, "test/hello_world.rks");
        cpu.modAdd(new StopWhen(0x000A)); // последняя команда перед выходом в монитор
        debug.enable(new Range(0x0000, 0x0100));

        // when
        cpu.SP(0x7FFF); // мы не запускаем C000, а потому надо самим указать, где стек
        cpu.PC(start);
        start();

        // then
        assertCpuDebug(
                "0004  21 0D 00  LXI H,000D  (0)  BC:0000  DE:0000  HL:0000  AF:0002  SP:7FFF  PC:0004   \n" +
                "0007  CD 1D 00  CALL 001D   (0)  BC:0000  DE:0000  HL:000D  AF:0002  SP:7FFF  PC:0007   \n" +
                "001D  7E        MOV A,M     (1)  BC:0000  DE:0000  HL:000D  AF:0002  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:0000  DE:0000  HL:000D  AF:0D02  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:000D  DE:0000  HL:000D  AF:0D83  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:000D  DE:0000  HL:000D  AF:0D83  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:000D  DE:0000  HL:000D  AF:0D83  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:000D  DE:0000  HL:000D  AF:0D83  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:000D  DE:0000  HL:000D  AF:0D83  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:000D  DE:0000  HL:000D  AF:0D83  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:000D  DE:0000  HL:000E  AF:0D83  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:000D  DE:0000  HL:000E  AF:0D83  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:000A  DE:0000  HL:000E  AF:0A83  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:000A  DE:0000  HL:000E  AF:0A83  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:000A  DE:0000  HL:000E  AF:0A83  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:000A  DE:0000  HL:000E  AF:0A83  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:000A  DE:0000  HL:000E  AF:0A83  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:000A  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:000A  DE:0000  HL:000F  AF:0A83  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:000A  DE:0000  HL:000F  AF:0A83  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:000A  DE:0000  HL:000F  AF:4883  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:000A  DE:0000  HL:000F  AF:4802  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:000A  DE:0000  HL:000F  AF:4802  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:000A  DE:0000  HL:000F  AF:4802  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:000A  DE:0000  HL:000F  AF:4802  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:000A  DE:0000  HL:000F  AF:4802  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:000A  DE:0000  HL:000F  AF:4802  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:0048  DE:0000  HL:000F  AF:4802  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:0048  DE:0000  HL:000F  AF:4802  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:0048  DE:0000  HL:000F  AF:4802  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:0048  DE:0000  HL:000F  AF:4802  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:0048  DE:0000  HL:000F  AF:4802  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:0048  DE:0000  HL:000F  AF:4802  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:0048  DE:0000  HL:0010  AF:4802  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:0048  DE:0000  HL:0010  AF:4802  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:0048  DE:0000  HL:0010  AF:4502  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:0048  DE:0000  HL:0010  AF:4502  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:0048  DE:0000  HL:0010  AF:4502  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:0048  DE:0000  HL:0010  AF:4502  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:0048  DE:0000  HL:0010  AF:4502  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:0048  DE:0000  HL:0010  AF:4502  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:0048  DE:0000  HL:0010  AF:4502  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:0045  DE:0000  HL:0010  AF:4502  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:0045  DE:0000  HL:0010  AF:4502  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:0045  DE:0000  HL:0010  AF:4502  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:0045  DE:0000  HL:0010  AF:4502  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:0045  DE:0000  HL:0010  AF:4502  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:0045  DE:0000  HL:0010  AF:4502  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:0045  DE:0000  HL:0011  AF:4502  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:0045  DE:0000  HL:0011  AF:4502  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:0045  DE:0000  HL:0011  AF:4C02  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:0045  DE:0000  HL:0011  AF:4C02  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:0045  DE:0000  HL:0011  AF:4C02  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:0045  DE:0000  HL:0011  AF:4C02  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:0045  DE:0000  HL:0011  AF:4C02  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:0045  DE:0000  HL:0011  AF:4C02  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:0045  DE:0000  HL:0011  AF:4C02  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:004C  DE:0000  HL:0011  AF:4C02  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:004C  DE:0000  HL:0011  AF:4C02  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:004C  DE:0000  HL:0011  AF:4C02  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:004C  DE:0000  HL:0011  AF:4C02  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:004C  DE:0000  HL:0011  AF:4C02  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:004C  DE:0000  HL:0011  AF:4C02  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:004C  DE:0000  HL:0012  AF:4C02  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:004C  DE:0000  HL:0013  AF:4C02  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:004C  DE:0000  HL:0013  AF:4C02  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:004C  DE:0000  HL:0013  AF:4F02  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:004C  DE:0000  HL:0013  AF:4F02  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:004C  DE:0000  HL:0013  AF:4F02  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:004C  DE:0000  HL:0013  AF:4F02  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:004C  DE:0000  HL:0013  AF:4F02  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:004C  DE:0000  HL:0013  AF:4F02  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:004C  DE:0000  HL:0013  AF:4F02  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:004F  DE:0000  HL:0013  AF:4F02  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:004F  DE:0000  HL:0013  AF:4F02  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:004F  DE:0000  HL:0013  AF:4F02  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:004F  DE:0000  HL:0013  AF:4F02  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:004F  DE:0000  HL:0013  AF:4F02  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:004F  DE:0000  HL:0013  AF:4F02  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:004F  DE:0000  HL:0014  AF:4F02  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:004F  DE:0000  HL:0014  AF:4F02  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:004F  DE:0000  HL:0014  AF:2002  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:004F  DE:0000  HL:0014  AF:2093  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:004F  DE:0000  HL:0014  AF:2093  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:004F  DE:0000  HL:0014  AF:2093  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:004F  DE:0000  HL:0014  AF:2093  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:004F  DE:0000  HL:0014  AF:2093  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:004F  DE:0000  HL:0014  AF:2093  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:0020  DE:0000  HL:0014  AF:2093  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:0020  DE:0000  HL:0014  AF:2093  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:0020  DE:0000  HL:0014  AF:2093  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:0020  DE:0000  HL:0014  AF:2093  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:0020  DE:0000  HL:0014  AF:2093  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:0020  DE:0000  HL:0014  AF:2093  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:0020  DE:0000  HL:0015  AF:2093  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:0020  DE:0000  HL:0015  AF:2093  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:0020  DE:0000  HL:0015  AF:5793  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:0020  DE:0000  HL:0015  AF:5702  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:0020  DE:0000  HL:0015  AF:5702  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:0020  DE:0000  HL:0015  AF:5702  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:0020  DE:0000  HL:0015  AF:5702  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:0020  DE:0000  HL:0015  AF:5702  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:0020  DE:0000  HL:0015  AF:5702  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:0057  DE:0000  HL:0015  AF:5702  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:0057  DE:0000  HL:0015  AF:5702  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:0057  DE:0000  HL:0015  AF:5702  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:0057  DE:0000  HL:0015  AF:5702  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:0057  DE:0000  HL:0015  AF:5702  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:0057  DE:0000  HL:0015  AF:5702  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:0057  DE:0000  HL:0016  AF:5702  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:0057  DE:0000  HL:0016  AF:5702  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:0057  DE:0000  HL:0016  AF:4F02  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:0057  DE:0000  HL:0016  AF:4F02  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:0057  DE:0000  HL:0016  AF:4F02  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:0057  DE:0000  HL:0016  AF:4F02  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:0057  DE:0000  HL:0016  AF:4F02  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:0057  DE:0000  HL:0016  AF:4F02  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:0057  DE:0000  HL:0016  AF:4F02  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:004F  DE:0000  HL:0016  AF:4F02  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:004F  DE:0000  HL:0016  AF:4F02  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:004F  DE:0000  HL:0016  AF:4F02  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:004F  DE:0000  HL:0016  AF:4F02  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:004F  DE:0000  HL:0016  AF:4F02  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:004F  DE:0000  HL:0016  AF:4F02  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:004F  DE:0000  HL:0017  AF:4F02  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:004F  DE:0000  HL:0017  AF:4F02  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:004F  DE:0000  HL:0017  AF:5202  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:004F  DE:0000  HL:0017  AF:5212  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:004F  DE:0000  HL:0017  AF:5212  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:004F  DE:0000  HL:0017  AF:5212  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:004F  DE:0000  HL:0017  AF:5212  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:004F  DE:0000  HL:0017  AF:5212  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:004F  DE:0000  HL:0017  AF:5212  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:0052  DE:0000  HL:0017  AF:5212  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:0052  DE:0000  HL:0017  AF:5212  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:0052  DE:0000  HL:0017  AF:5212  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:0052  DE:0000  HL:0017  AF:5212  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:0052  DE:0000  HL:0017  AF:5212  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:0052  DE:0000  HL:0017  AF:5212  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:0052  DE:0000  HL:0018  AF:5212  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:0052  DE:0000  HL:0018  AF:5212  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:0052  DE:0000  HL:0018  AF:4C12  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:0052  DE:0000  HL:0018  AF:4C02  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:0052  DE:0000  HL:0018  AF:4C02  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:0052  DE:0000  HL:0018  AF:4C02  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:0052  DE:0000  HL:0018  AF:4C02  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:0052  DE:0000  HL:0018  AF:4C02  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:0052  DE:0000  HL:0018  AF:4C02  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:004C  DE:0000  HL:0018  AF:4C02  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:004C  DE:0000  HL:0018  AF:4C02  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:004C  DE:0000  HL:0018  AF:4C02  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:004C  DE:0000  HL:0018  AF:4C02  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:004C  DE:0000  HL:0018  AF:4C02  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:004C  DE:0000  HL:0018  AF:4C02  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:004C  DE:0000  HL:0019  AF:4C02  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:004C  DE:0000  HL:0019  AF:4C02  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:004C  DE:0000  HL:0019  AF:4402  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:004C  DE:0000  HL:0019  AF:4402  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:004C  DE:0000  HL:0019  AF:4402  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:004C  DE:0000  HL:0019  AF:4402  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:004C  DE:0000  HL:0019  AF:4402  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:004C  DE:0000  HL:0019  AF:4402  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:004C  DE:0000  HL:0019  AF:4402  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:0044  DE:0000  HL:0019  AF:4402  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:0044  DE:0000  HL:0019  AF:4402  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:0044  DE:0000  HL:0019  AF:4402  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:0044  DE:0000  HL:0019  AF:4402  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:0044  DE:0000  HL:0019  AF:4402  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:0044  DE:0000  HL:0019  AF:4402  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:0044  DE:0000  HL:001A  AF:4402  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:0044  DE:0000  HL:001A  AF:4402  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:0044  DE:0000  HL:001A  AF:0D02  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:0044  DE:0000  HL:001A  AF:0D83  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:0044  DE:0000  HL:001A  AF:0D83  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:0044  DE:0000  HL:001A  AF:0D83  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:0044  DE:0000  HL:001A  AF:0D83  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:0044  DE:0000  HL:001A  AF:0D83  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:0044  DE:0000  HL:001A  AF:0D83  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:000D  DE:0000  HL:001A  AF:0D83  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:000D  DE:0000  HL:001A  AF:0D83  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:000D  DE:0000  HL:001A  AF:0D83  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:000D  DE:0000  HL:001A  AF:0D83  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:000D  DE:0000  HL:001A  AF:0D83  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:000D  DE:0000  HL:001A  AF:0D83  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:000D  DE:0000  HL:001B  AF:0D83  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:000D  DE:0000  HL:001B  AF:0D83  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:000D  DE:0000  HL:001B  AF:0A83  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:000D  DE:0000  HL:001B  AF:0A83  SP:7FFD  PC:0020   \n" +
                "0021  CD 28 00  CALL 0028   (1)  BC:000D  DE:0000  HL:001B  AF:0A83  SP:7FFD  PC:0021   \n" +
                "0028  F5        PUSH SP     (2)  BC:000D  DE:0000  HL:001B  AF:0A83  SP:7FFB  PC:0028   \n" +
                "0029  D5        PUSH D      (2)  BC:000D  DE:0000  HL:001B  AF:0A83  SP:7FF9  PC:0029   \n" +
                "002A  E5        PUSH H      (2)  BC:000D  DE:0000  HL:001B  AF:0A83  SP:7FF7  PC:002A   \n" +
                "002B  4E        MOV C,M     (2)  BC:000D  DE:0000  HL:001B  AF:0A83  SP:7FF5  PC:002B   \n" +
                "002C  CD 37 C0  CALL C037   (2)  BC:000A  DE:0000  HL:001B  AF:0A83  SP:7FF5  PC:002C   \n" +
                "002F  E1        POP H       (2)  BC:000A  DE:0000  HL:001B  AF:0A83  SP:7FF5  PC:002F   \n" +
                "0030  D1        POP D       (2)  BC:000A  DE:0000  HL:001B  AF:0A83  SP:7FF7  PC:0030   \n" +
                "0031  F1        POP SP      (2)  BC:000A  DE:0000  HL:001B  AF:0A83  SP:7FF9  PC:0031   \n" +
                "0032  C9        RET         (2)  BC:000A  DE:0000  HL:001B  AF:0A83  SP:7FFB  PC:0032   \n" +
                "0024  23        INX H       (1)  BC:000A  DE:0000  HL:001B  AF:0A83  SP:7FFD  PC:0024   \n" +
                "0025  C3 1D 00  JMP 001D    (1)  BC:000A  DE:0000  HL:001C  AF:0A83  SP:7FFD  PC:0025   \n" +
                "001D  7E        MOV A,M     (1)  BC:000A  DE:0000  HL:001C  AF:0A83  SP:7FFD  PC:001D   \n" +
                "001E  FE 24     CPI 24      (1)  BC:000A  DE:0000  HL:001C  AF:2483  SP:7FFD  PC:001E   \n" +
                "0020  C8        RZ          (1)  BC:000A  DE:0000  HL:001C  AF:2442  SP:7FFD  PC:0020   \n" +
                "000A  C3 00 C8  JMP C800    (0)  BC:000A  DE:0000  HL:001C  AF:2442  SP:7FFF  PC:000A   ");

        asrtCpu("BC:  000A\n" +
                "DE:  0000\n" +
                "HL:  001C\n" +
                "AF:  2442\n" +
                "SP:  7FFF\n" +
                "PC:  C800\n" +
                "B,C: 00 0A\n" +
                "D,E: 00 00\n" +
                "H,L: 00 1C\n" +
                "M:   24\n" +
                "A,F: 24 42\n" +
                "     76543210 76543210\n" +
                "SP:  01111111 11111111\n" +
                "PC:  11001000 00000000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00001010\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   00011100\n" +
                "M:   00100100\n" +
                "A:   00100100\n" +
                "     sz0h0p1c\n" +
                "F:   01000010\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void testDiagnostic_case2() {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The basic excerciser
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080EX1.MAC

        // given
        Lik.loadRom(base, roms);
        roms.loadROM(base, "test/8080EX1.COM", 0x0100);

        // when
        cpu.PC(0x0100);
        start();

        // then
        asrtCpu("BC:  0009\n" +
                "DE:  0DF6\n" +
                "HL:  0000\n" +
                "AF:  0002\n" +
                "SP:  FFF4\n" +
                "PC:  00F0\n" +
                "B,C: 00 09\n" +
                "D,E: 0D F6\n" +
                "H,L: 00 00\n" +
                "M:   00\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11110100\n" +
                "PC:  00000000 11110000\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00001001\n" +
                "D:   00001101\n" +
                "E:   11110110\n" +
                "H:   00000000\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testDiagnostic_case3() {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The preliminary test
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080PRE.MAC

        // given
        Lik.loadRom(base, roms);
        roms.loadROM(base, "test/8080PRE.COM", 0x0100);

        // when
        cpu.PC(0x0100);
        start();

        // then
        asrtCpu("BC:  EC34\n" +
                "DE:  0C0A\n" +
                "HL:  0014\n" +
                "AF:  6A82\n" +
                "SP:  0500\n" +
                "PC:  0316\n" +
                "B,C: EC 34\n" +
                "D,E: 0C 0A\n" +
                "H,L: 00 14\n" +
                "M:   00\n" +
                "A,F: 6A 82\n" +
                "     76543210 76543210\n" +
                "SP:  00000101 00000000\n" +
                "PC:  00000011 00010110\n" +
                "     76543210\n" +
                "B:   11101100\n" +
                "C:   00110100\n" +
                "D:   00001100\n" +
                "E:   00001010\n" +
                "H:   00000000\n" +
                "L:   00010100\n" +
                "M:   00000000\n" +
                "A:   01101010\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testDiagnostic_case4() {
        // Diagnostics II, version 1.2, CPU test by Supersoft Associates
        // https://raw.githubusercontent.com/begoon/i8080-core/master/CPUTEST.MAC

        // given
        Lik.loadRom(base, roms);
        roms.loadROM(base, "test/CPUTEST.COM", 0x0100);

        // when
        cpu.PC(0x0100);
        start();

        // then
        asrtCpu("BC:  0002\n" +
                "DE:  0000\n" +
                "HL:  0080\n" +
                "AF:  0D93\n" +
                "SP:  2FEF\n" +
                "PC:  004E\n" +
                "B,C: 00 02\n" +
                "D,E: 00 00\n" +
                "H,L: 00 80\n" +
                "M:   00\n" +
                "A,F: 0D 93\n" +
                "     76543210 76543210\n" +
                "SP:  00101111 11101111\n" +
                "PC:  00000000 01001110\n" +
                "     76543210\n" +
                "B:   00000000\n" +
                "C:   00000010\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   00000000\n" +
                "L:   10000000\n" +
                "M:   00000000\n" +
                "A:   00001101\n" +
                "     sz0h0p1c\n" +
                "F:   10010011\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  true\n");

        screenShoot();
    }
}
