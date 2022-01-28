package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
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
        record.reset().after(2000).stopCpu();
        debug.enable();
        debug.showCallBellow(2);

        // when
        cpu.SP(0x7FFF); // мы не запускаем C000, а потому надо самим указать, где стек
        cpu.PC(start);
        start();

        // then
        assertCpuDebug(
                "0004  21 0D 00  LXI H,000D  (0)  BC:0000  DE:0000  HL:0000  AF:0002  SP:7FFF  PC:0004   \n" +
                "0007  CD 58 00  CALL 0058   (0)  BC:0000  DE:0000  HL:000D  AF:0002  SP:7FFF  PC:0007   \n" +
                "0058  7E        MOV A,M     (1)  BC:0000  DE:0000  HL:000D  AF:0002  SP:7FFD  PC:0058   \n" +
                "0059  FE 24     CPI 24      (1)  BC:0000  DE:0000  HL:000D  AF:0D02  SP:7FFD  PC:0059   \n" +
                "005B  C8        RZ          (1)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FFD  PC:005B   \n" +
                "005C  CD 63 00  CALL 0063   (1)  BC:0000  DE:0000  HL:000D  AF:0D83  SP:7FFD  PC:005C   \n" +
                "005F  23        INX H       (1)  BC:000D  DE:0000  HL:000D  AF:0D83  SP:7FFD  PC:005F   \n" +
                "0060  C3 58 00  JMP 0058    (1)  BC:000D  DE:0000  HL:000E  AF:0D83  SP:7FFD  PC:0060   \n" +
                "0058  7E        MOV A,M     (1)  BC:000D  DE:0000  HL:000E  AF:0D83  SP:7FFD  PC:0058   \n" +
                "0059  FE 24     CPI 24      (1)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:0059   \n" +
                "005B  C8        RZ          (1)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:005B   \n" +
                "005C  CD 63 00  CALL 0063   (1)  BC:000D  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:005C   \n" +
                "005F  23        INX H       (1)  BC:000A  DE:0000  HL:000E  AF:0A83  SP:7FFD  PC:005F   \n" +
                "0060  C3 58 00  JMP 0058    (1)  BC:000A  DE:0000  HL:000F  AF:0A83  SP:7FFD  PC:0060   \n" +
                "0058  7E        MOV A,M     (1)  BC:000A  DE:0000  HL:000F  AF:0A83  SP:7FFD  PC:0058   \n" +
                "0059  FE 24     CPI 24      (1)  BC:000A  DE:0000  HL:000F  AF:4D83  SP:7FFD  PC:0059   \n" +
                "005B  C8        RZ          (1)  BC:000A  DE:0000  HL:000F  AF:4D02  SP:7FFD  PC:005B   \n" +
                "005C  CD 63 00  CALL 0063   (1)  BC:000A  DE:0000  HL:000F  AF:4D02  SP:7FFD  PC:005C   \n" +
                "005F  23        INX H       (1)  BC:004D  DE:0000  HL:000F  AF:4D02  SP:7FFD  PC:005F   \n" +
                "0060  C3 58 00  JMP 0058    (1)  BC:004D  DE:0000  HL:0010  AF:4D02  SP:7FFD  PC:0060   \n" +
                "0058  7E        MOV A,M     (1)  BC:004D  DE:0000  HL:0010  AF:4D02  SP:7FFD  PC:0058   \n" +
                "0059  FE 24     CPI 24      (1)  BC:004D  DE:0000  HL:0010  AF:4902  SP:7FFD  PC:0059   \n" +
                "005B  C8        RZ          (1)  BC:004D  DE:0000  HL:0010  AF:4902  SP:7FFD  PC:005B   \n" +
                "005C  CD 63 00  CALL 0063   (1)  BC:004D  DE:0000  HL:0010  AF:4902  SP:7FFD  PC:005C   \n" +
                "005F  23        INX H       (1)  BC:0049  DE:0000  HL:0010  AF:4902  SP:7FFD  PC:005F   \n" +
                "0060  C3 58 00  JMP 0058    (1)  BC:0049  DE:0000  HL:0011  AF:4902  SP:7FFD  PC:0060   \n" +
                "0058  7E        MOV A,M     (1)  BC:0049  DE:0000  HL:0011  AF:4902  SP:7FFD  PC:0058   \n" +
                "0059  FE 24     CPI 24      (1)  BC:0049  DE:0000  HL:0011  AF:4302  SP:7FFD  PC:0059   \n" +
                "005B  C8        RZ          (1)  BC:0049  DE:0000  HL:0011  AF:4312  SP:7FFD  PC:005B   \n" +
                "005C  CD 63 00  CALL 0063   (1)  BC:0049  DE:0000  HL:0011  AF:4312  SP:7FFD  PC:005C   \n" +
                "005F  23        INX H       (1)  BC:0043  DE:0000  HL:0011  AF:4312  SP:7FFD  PC:005F   \n" +
                "0060  C3 58 00  JMP 0058    (1)  BC:0043  DE:0000  HL:0012  AF:4312  SP:7FFD  PC:0060   \n" +
                "0058  7E        MOV A,M     (1)  BC:0043  DE:0000  HL:0012  AF:4312  SP:7FFD  PC:0058   \n" +
                "0059  FE 24     CPI 24      (1)  BC:0043  DE:0000  HL:0012  AF:5212  SP:7FFD  PC:0059   \n" +
                "005B  C8        RZ          (1)  BC:0043  DE:0000  HL:0012  AF:5212  SP:7FFD  PC:005B   \n" +
                "005C  CD 63 00  CALL 0063   (1)  BC:0043  DE:0000  HL:0012  AF:5212  SP:7FFD  PC:005C   \n" +
                "005F  23        INX H       (1)  BC:0052  DE:0000  HL:0012  AF:5212  SP:7FFD  PC:005F   \n" +
                "0060  C3 58 00  JMP 0058    (1)  BC:0052  DE:0000  HL:0013  AF:5212  SP:7FFD  PC:0060   \n" +
                "0058  7E        MOV A,M     (1)  BC:0052  DE:0000  HL:0013  AF:5212  SP:7FFD  PC:0058   \n" +
                "0059  FE 24     CPI 24      (1)  BC:0052  DE:0000  HL:0013  AF:4F12  SP:7FFD  PC:0059   \n" +
                "005B  C8        RZ          (1)  BC:0052  DE:0000  HL:0013  AF:4F02  SP:7FFD  PC:005B   \n" +
                "005C  CD 63 00  CALL 0063   (1)  BC:0052  DE:0000  HL:0013  AF:4F02  SP:7FFD  PC:005C   ");
    }

    @Test
    public void testDiagnostic_case1() {
        // 8080/8085 CPU Diagnostic, version 1.0, by Microcosm Associates
        // https://github.com/begoon/i8080-core/blob/master/TEST.ASM

        // given
        Lik.loadRom(base, roms);
        hard.loadData(APP_RESOURCES + "test/test.rks");
        record.after(500_000).stopCpu();

        // when
        hard.reset();
        hard.start();

        // then
        asrtCpu("BC:  FF21\n" +
                "DE:  0038\n" +
                "HL:  18A0\n" +
                "AF:  9112\n" +
                "SP:  7FEB\n" +
                "PC:  C25C\n" +
                "B,C: FF 21\n" +
                "D,E: 00 38\n" +
                "H,L: 18 A0\n" +
                "M:   00\n" +
                "A,F: 91 12\n" +
                "     76543210 76543210\n" +
                "SP:  01111111 11101011\n" +
                "PC:  11000010 01011100\n" +
                "     76543210\n" +
                "B:   11111111\n" +
                "C:   00100001\n" +
                "D:   00000000\n" +
                "E:   00111000\n" +
                "H:   00011000\n" +
                "L:   10100000\n" +
                "M:   00000000\n" +
                "A:   10010001\n" +
                "     sz0h0p1c\n" +
                "F:   00010010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
    }

    @Test
    public void testLik_helloWorld() {
        // given
        Lik.loadRom(base, roms);
        hard.loadData(APP_RESOURCES + "test/hello_world.rks");
        record.after(400_000).stopCpu();

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
        record.reset().after(300).stopCpu();
        debug.enable();

        // when
        cpu.SP(0x7FFF); // мы не запускаем C000, а потому надо самим указать, где стек
        cpu.PC(start);
        start();

        // then
        assertCpuDebug(
                "0004  21 0D 00  LXI H,000D  (0)  BC:0000  DE:0000  HL:0000  AF:0002  SP:7FFF  PC:0004   \n" +
                "0007  CD 1B 00  CALL 001B   (0)  BC:0000  DE:0000  HL:000D  AF:0002  SP:7FFF  PC:0007   \n" +
                "001B  7E        MOV A,M     (1)  BC:0000  DE:0000  HL:000D  AF:0002  SP:7FFD  PC:001B   \n" +
                "001C  FE 24     CPI 24      (1)  BC:0000  DE:0000  HL:000D  AF:4802  SP:7FFD  PC:001C   \n" +
                "001E  C8        RZ          (1)  BC:0000  DE:0000  HL:000D  AF:4802  SP:7FFD  PC:001E   \n" +
                "001F  CD 26 00  CALL 0026   (1)  BC:0000  DE:0000  HL:000D  AF:4802  SP:7FFD  PC:001F   \n" +
                "0026  F5        PUSH SP     (2)  BC:0000  DE:0000  HL:000D  AF:4802  SP:7FFB  PC:0026   \n" +
                "0027  D5        PUSH D      (2)  BC:0000  DE:0000  HL:000D  AF:4802  SP:7FF9  PC:0027   \n" +
                "0028  E5        PUSH H      (2)  BC:0000  DE:0000  HL:000D  AF:4802  SP:7FF7  PC:0028   \n" +
                "0029  4E        MOV C,M     (2)  BC:0000  DE:0000  HL:000D  AF:4802  SP:7FF5  PC:0029   \n" +
                "002A  CD 37 C0  CALL C037   (2)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FF5  PC:002A   \n" +
                "C037  E5        PUSH H      (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FF3  PC:C037   \n" +
                "C038  D5        PUSH D      (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FF1  PC:C038   \n" +
                "C039  C5        PUSH B      (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FEF  PC:C039   \n" +
                "C03A  F5        PUSH SP     (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FED  PC:C03A   \n" +
                "C03B  79        MOV A,C     (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FEB  PC:C03B   \n" +
                "C03C  FE 21     CPI 21      (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FEB  PC:C03C   \n" +
                "C03E  DA D4 C0  JC C0D4     (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FEB  PC:C03E   \n" +
                "C041  2A FC 8F  LHLD 8FFC   (3)  BC:0048  DE:0000  HL:000D  AF:4802  SP:7FEB  PC:C041   \n" +
                "C044  7C        MOV A,H     (3)  BC:0048  DE:0000  HL:0000  AF:4802  SP:7FEB  PC:C044   \n" +
                "C045  FE BE     CPI BE      (3)  BC:0048  DE:0000  HL:0000  AF:0002  SP:7FEB  PC:C045   \n" +
                "C047  D2 B2 C0  JNC C0B2    (3)  BC:0048  DE:0000  HL:0000  AF:0013  SP:7FEB  PC:C047   \n" +
                "C04A  C6 03     ADI 03      (3)  BC:0048  DE:0000  HL:0000  AF:0013  SP:7FEB  PC:C04A   \n" +
                "C04C  32 FD 8F  STA 8FFD    (3)  BC:0048  DE:0000  HL:0000  AF:0302  SP:7FEB  PC:C04C   \n" +
                "C04F  EB        XCHG        (3)  BC:0048  DE:0000  HL:0000  AF:0302  SP:7FEB  PC:C04F   \n" +
                "C050  79        MOV A,C     (3)  BC:0048  DE:0000  HL:0000  AF:0302  SP:7FEB  PC:C050   \n" +
                "C051  32 E9 8F  STA 8FE9    (3)  BC:0048  DE:0000  HL:0000  AF:4802  SP:7FEB  PC:C051   \n" +
                "C054  D6 20     SUI 20      (3)  BC:0048  DE:0000  HL:0000  AF:4802  SP:7FEB  PC:C054   \n" +
                "C056  2A E7 8F  LHLD 8FE7   (3)  BC:0048  DE:0000  HL:0000  AF:2802  SP:7FEB  PC:C056   \n" +
                "C059  85        ADD L       (3)  BC:0048  DE:0000  HL:0000  AF:2802  SP:7FEB  PC:C059   \n" +
                "C05A  6F        MOV L,A     (3)  BC:0048  DE:0000  HL:0000  AF:2802  SP:7FEB  PC:C05A   \n" +
                "C05B  29        DAD H       (3)  BC:0048  DE:0000  HL:0028  AF:2802  SP:7FEB  PC:C05B   \n" +
                "C05C  29        DAD H       (3)  BC:0048  DE:0000  HL:0050  AF:2802  SP:7FEB  PC:C05C   \n" +
                "C05D  29        DAD H       (3)  BC:0048  DE:0000  HL:00A0  AF:2802  SP:7FEB  PC:C05D   \n" +
                "C05E  EB        XCHG        (3)  BC:0048  DE:0000  HL:0140  AF:2802  SP:7FEB  PC:C05E   \n" +
                "C05F  00        NOP         (3)  BC:0048  DE:0140  HL:0000  AF:2802  SP:7FEB  PC:C05F   \n" +
                "C060  7C        MOV A,H     (3)  BC:0048  DE:0140  HL:0000  AF:2802  SP:7FEB  PC:C060   \n" +
                "C061  E6 03     ANI 03      (3)  BC:0048  DE:0140  HL:0000  AF:0002  SP:7FEB  PC:C061   \n" +
                "C063  4F        MOV C,A     (3)  BC:0048  DE:0140  HL:0000  AF:0056  SP:7FEB  PC:C063   \n" +
                "C064  3E 05     MVI A,05    (3)  BC:0000  DE:0140  HL:0000  AF:0056  SP:7FEB  PC:C064   \n" +
                "C066  91        SUB C       (3)  BC:0000  DE:0140  HL:0000  AF:0556  SP:7FEB  PC:C066   \n" +
                "C067  4F        MOV C,A     (3)  BC:0000  DE:0140  HL:0000  AF:0502  SP:7FEB  PC:C067   \n" +
                "C068  7C        MOV A,H     (3)  BC:0005  DE:0140  HL:0000  AF:0502  SP:7FEB  PC:C068   \n" +
                "C069  E6 FC     ANI FC      (3)  BC:0005  DE:0140  HL:0000  AF:0002  SP:7FEB  PC:C069   \n" +
                "C06B  0F        RLC         (3)  BC:0005  DE:0140  HL:0000  AF:0056  SP:7FEB  PC:C06B   \n" +
                "C06C  0F        RLC         (3)  BC:0005  DE:0140  HL:0000  AF:0046  SP:7FEB  PC:C06C   \n" +
                "C06D  C6 90     ADI 90      (3)  BC:0005  DE:0140  HL:0000  AF:0046  SP:7FEB  PC:C06D   \n" +
                "C06F  67        MOV H,A     (3)  BC:0005  DE:0140  HL:0000  AF:9082  SP:7FEB  PC:C06F   \n" +
                "C070  22 F8 8F  SHLD 8FF8   (3)  BC:0005  DE:0140  HL:9000  AF:9082  SP:7FEB  PC:C070   \n" +
                "C073  06 08     MVI B,08    (3)  BC:0005  DE:0140  HL:9000  AF:9082  SP:7FEB  PC:C073   \n" +
                "C075  00        NOP         (3)  BC:0805  DE:0140  HL:9000  AF:9082  SP:7FEB  PC:C075   \n" +
                "C076  1A        LDAX D      (3)  BC:0805  DE:0140  HL:9000  AF:9082  SP:7FEB  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0805  DE:0140  HL:9000  AF:0082  SP:7FEB  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0805  DE:0140  HL:9000  AF:0082  SP:7FEB  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0805  DE:0140  HL:0000  AF:0082  SP:7FEB  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0582  SP:7FEB  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0582  SP:7FEB  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0805  DE:0140  HL:0000  AF:0582  SP:7FEB  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0805  DE:0140  HL:0000  AF:0402  SP:7FEB  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0402  SP:7FEB  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0402  SP:7FEB  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0805  DE:0140  HL:0000  AF:0402  SP:7FEB  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0805  DE:0140  HL:0000  AF:0302  SP:7FEB  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0302  SP:7FEB  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0302  SP:7FEB  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0805  DE:0140  HL:0000  AF:0302  SP:7FEB  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0805  DE:0140  HL:0000  AF:0202  SP:7FEB  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0202  SP:7FEB  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0202  SP:7FEB  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0805  DE:0140  HL:0000  AF:0202  SP:7FEB  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0805  DE:0140  HL:0000  AF:0102  SP:7FEB  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0102  SP:7FEB  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0805  DE:0140  HL:0000  AF:0102  SP:7FEB  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0805  DE:0140  HL:0000  AF:0102  SP:7FEB  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0805  DE:0140  HL:0000  AF:0042  SP:7FEB  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0805  DE:0140  HL:0000  AF:0042  SP:7FEB  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0805  DE:0140  HL:0000  AF:0042  SP:7FE9  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0805  DE:0141  HL:0000  AF:0042  SP:7FE9  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0705  DE:0141  HL:0000  AF:0002  SP:7FE9  PC:C084   \n" +
                "C076  1A        LDAX D      (3)  BC:0705  DE:0141  HL:0000  AF:0002  SP:7FE9  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0705  DE:0141  HL:0000  AF:0002  SP:7FE9  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0705  DE:0141  HL:0000  AF:0002  SP:7FE9  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0705  DE:0141  HL:0000  AF:0002  SP:7FE9  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0502  SP:7FE9  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0502  SP:7FE9  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0705  DE:0141  HL:0000  AF:0502  SP:7FE9  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0705  DE:0141  HL:0000  AF:0402  SP:7FE9  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0402  SP:7FE9  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0402  SP:7FE9  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0705  DE:0141  HL:0000  AF:0402  SP:7FE9  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0705  DE:0141  HL:0000  AF:0302  SP:7FE9  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0302  SP:7FE9  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0302  SP:7FE9  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0705  DE:0141  HL:0000  AF:0302  SP:7FE9  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0705  DE:0141  HL:0000  AF:0202  SP:7FE9  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0202  SP:7FE9  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0202  SP:7FE9  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0705  DE:0141  HL:0000  AF:0202  SP:7FE9  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0705  DE:0141  HL:0000  AF:0102  SP:7FE9  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0102  SP:7FE9  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0705  DE:0141  HL:0000  AF:0102  SP:7FE9  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0705  DE:0141  HL:0000  AF:0102  SP:7FE9  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0705  DE:0141  HL:0000  AF:0042  SP:7FE9  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0705  DE:0141  HL:0000  AF:0042  SP:7FE9  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0705  DE:0141  HL:0000  AF:0042  SP:7FE7  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0705  DE:0142  HL:0000  AF:0042  SP:7FE7  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0605  DE:0142  HL:0000  AF:0002  SP:7FE7  PC:C084   \n" +
                "C076  1A        LDAX D      (3)  BC:0605  DE:0142  HL:0000  AF:0002  SP:7FE7  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0605  DE:0142  HL:0000  AF:0002  SP:7FE7  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0605  DE:0142  HL:0000  AF:0002  SP:7FE7  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0605  DE:0142  HL:0000  AF:0002  SP:7FE7  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0502  SP:7FE7  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0502  SP:7FE7  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0605  DE:0142  HL:0000  AF:0502  SP:7FE7  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0605  DE:0142  HL:0000  AF:0402  SP:7FE7  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0402  SP:7FE7  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0402  SP:7FE7  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0605  DE:0142  HL:0000  AF:0402  SP:7FE7  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0605  DE:0142  HL:0000  AF:0302  SP:7FE7  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0302  SP:7FE7  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0302  SP:7FE7  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0605  DE:0142  HL:0000  AF:0302  SP:7FE7  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0605  DE:0142  HL:0000  AF:0202  SP:7FE7  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0202  SP:7FE7  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0202  SP:7FE7  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0605  DE:0142  HL:0000  AF:0202  SP:7FE7  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0605  DE:0142  HL:0000  AF:0102  SP:7FE7  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0102  SP:7FE7  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0605  DE:0142  HL:0000  AF:0102  SP:7FE7  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0605  DE:0142  HL:0000  AF:0102  SP:7FE7  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0605  DE:0142  HL:0000  AF:0042  SP:7FE7  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0605  DE:0142  HL:0000  AF:0042  SP:7FE7  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0605  DE:0142  HL:0000  AF:0042  SP:7FE5  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0605  DE:0143  HL:0000  AF:0042  SP:7FE5  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0505  DE:0143  HL:0000  AF:0002  SP:7FE5  PC:C084   \n" +
                "C076  1A        LDAX D      (3)  BC:0505  DE:0143  HL:0000  AF:0002  SP:7FE5  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0505  DE:0143  HL:0000  AF:0002  SP:7FE5  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0505  DE:0143  HL:0000  AF:0002  SP:7FE5  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0505  DE:0143  HL:0000  AF:0002  SP:7FE5  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0502  SP:7FE5  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0502  SP:7FE5  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0505  DE:0143  HL:0000  AF:0502  SP:7FE5  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0505  DE:0143  HL:0000  AF:0402  SP:7FE5  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0402  SP:7FE5  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0402  SP:7FE5  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0505  DE:0143  HL:0000  AF:0402  SP:7FE5  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0505  DE:0143  HL:0000  AF:0302  SP:7FE5  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0302  SP:7FE5  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0302  SP:7FE5  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0505  DE:0143  HL:0000  AF:0302  SP:7FE5  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0505  DE:0143  HL:0000  AF:0202  SP:7FE5  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0202  SP:7FE5  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0202  SP:7FE5  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0505  DE:0143  HL:0000  AF:0202  SP:7FE5  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0505  DE:0143  HL:0000  AF:0102  SP:7FE5  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0102  SP:7FE5  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0505  DE:0143  HL:0000  AF:0102  SP:7FE5  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0505  DE:0143  HL:0000  AF:0102  SP:7FE5  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0505  DE:0143  HL:0000  AF:0042  SP:7FE5  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0505  DE:0143  HL:0000  AF:0042  SP:7FE5  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0505  DE:0143  HL:0000  AF:0042  SP:7FE3  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0505  DE:0144  HL:0000  AF:0042  SP:7FE3  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0405  DE:0144  HL:0000  AF:0002  SP:7FE3  PC:C084   \n" +
                "C076  1A        LDAX D      (3)  BC:0405  DE:0144  HL:0000  AF:0002  SP:7FE3  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0405  DE:0144  HL:0000  AF:0002  SP:7FE3  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0405  DE:0144  HL:0000  AF:0002  SP:7FE3  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0405  DE:0144  HL:0000  AF:0002  SP:7FE3  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0502  SP:7FE3  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0502  SP:7FE3  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0405  DE:0144  HL:0000  AF:0502  SP:7FE3  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0405  DE:0144  HL:0000  AF:0402  SP:7FE3  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0402  SP:7FE3  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0402  SP:7FE3  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0405  DE:0144  HL:0000  AF:0402  SP:7FE3  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0405  DE:0144  HL:0000  AF:0302  SP:7FE3  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0302  SP:7FE3  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0302  SP:7FE3  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0405  DE:0144  HL:0000  AF:0302  SP:7FE3  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0405  DE:0144  HL:0000  AF:0202  SP:7FE3  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0202  SP:7FE3  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0202  SP:7FE3  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0405  DE:0144  HL:0000  AF:0202  SP:7FE3  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0405  DE:0144  HL:0000  AF:0102  SP:7FE3  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0102  SP:7FE3  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0405  DE:0144  HL:0000  AF:0102  SP:7FE3  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0405  DE:0144  HL:0000  AF:0102  SP:7FE3  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0405  DE:0144  HL:0000  AF:0042  SP:7FE3  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0405  DE:0144  HL:0000  AF:0042  SP:7FE3  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0405  DE:0144  HL:0000  AF:0042  SP:7FE1  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0405  DE:0145  HL:0000  AF:0042  SP:7FE1  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0305  DE:0145  HL:0000  AF:0002  SP:7FE1  PC:C084   \n" +
                "C076  1A        LDAX D      (3)  BC:0305  DE:0145  HL:0000  AF:0002  SP:7FE1  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0305  DE:0145  HL:0000  AF:0002  SP:7FE1  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0305  DE:0145  HL:0000  AF:0002  SP:7FE1  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0305  DE:0145  HL:0000  AF:0002  SP:7FE1  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0502  SP:7FE1  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0502  SP:7FE1  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0305  DE:0145  HL:0000  AF:0502  SP:7FE1  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0305  DE:0145  HL:0000  AF:0402  SP:7FE1  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0402  SP:7FE1  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0402  SP:7FE1  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0305  DE:0145  HL:0000  AF:0402  SP:7FE1  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0305  DE:0145  HL:0000  AF:0302  SP:7FE1  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0302  SP:7FE1  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0302  SP:7FE1  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0305  DE:0145  HL:0000  AF:0302  SP:7FE1  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0305  DE:0145  HL:0000  AF:0202  SP:7FE1  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0202  SP:7FE1  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0202  SP:7FE1  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0305  DE:0145  HL:0000  AF:0202  SP:7FE1  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0305  DE:0145  HL:0000  AF:0102  SP:7FE1  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0102  SP:7FE1  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0305  DE:0145  HL:0000  AF:0102  SP:7FE1  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0305  DE:0145  HL:0000  AF:0102  SP:7FE1  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0305  DE:0145  HL:0000  AF:0042  SP:7FE1  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0305  DE:0145  HL:0000  AF:0042  SP:7FE1  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0305  DE:0145  HL:0000  AF:0042  SP:7FDF  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0305  DE:0146  HL:0000  AF:0042  SP:7FDF  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0205  DE:0146  HL:0000  AF:0002  SP:7FDF  PC:C084   \n" +
                "C076  1A        LDAX D      (3)  BC:0205  DE:0146  HL:0000  AF:0002  SP:7FDF  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0205  DE:0146  HL:0000  AF:0002  SP:7FDF  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0205  DE:0146  HL:0000  AF:0002  SP:7FDF  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0205  DE:0146  HL:0000  AF:0002  SP:7FDF  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0502  SP:7FDF  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0502  SP:7FDF  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0205  DE:0146  HL:0000  AF:0502  SP:7FDF  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0205  DE:0146  HL:0000  AF:0402  SP:7FDF  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0402  SP:7FDF  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0402  SP:7FDF  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0205  DE:0146  HL:0000  AF:0402  SP:7FDF  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0205  DE:0146  HL:0000  AF:0302  SP:7FDF  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0302  SP:7FDF  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0302  SP:7FDF  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0205  DE:0146  HL:0000  AF:0302  SP:7FDF  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0205  DE:0146  HL:0000  AF:0202  SP:7FDF  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0202  SP:7FDF  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0202  SP:7FDF  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0205  DE:0146  HL:0000  AF:0202  SP:7FDF  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0205  DE:0146  HL:0000  AF:0102  SP:7FDF  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0102  SP:7FDF  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0205  DE:0146  HL:0000  AF:0102  SP:7FDF  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0205  DE:0146  HL:0000  AF:0102  SP:7FDF  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0205  DE:0146  HL:0000  AF:0042  SP:7FDF  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0205  DE:0146  HL:0000  AF:0042  SP:7FDF  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0205  DE:0146  HL:0000  AF:0042  SP:7FDD  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0205  DE:0147  HL:0000  AF:0042  SP:7FDD  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0105  DE:0147  HL:0000  AF:0002  SP:7FDD  PC:C084   \n" +
                "C076  1A        LDAX D      (3)  BC:0105  DE:0147  HL:0000  AF:0002  SP:7FDD  PC:C076   \n" +
                "C077  6F        MOV L,A     (3)  BC:0105  DE:0147  HL:0000  AF:0002  SP:7FDD  PC:C077   \n" +
                "C078  26 00     MVI H,00    (3)  BC:0105  DE:0147  HL:0000  AF:0002  SP:7FDD  PC:C078   \n" +
                "C07A  79        MOV A,C     (3)  BC:0105  DE:0147  HL:0000  AF:0002  SP:7FDD  PC:C07A   \n" +
                "C07B  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0502  SP:7FDD  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0502  SP:7FDD  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0105  DE:0147  HL:0000  AF:0502  SP:7FDD  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0105  DE:0147  HL:0000  AF:0402  SP:7FDD  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0402  SP:7FDD  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0402  SP:7FDD  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0105  DE:0147  HL:0000  AF:0402  SP:7FDD  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0105  DE:0147  HL:0000  AF:0302  SP:7FDD  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0302  SP:7FDD  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0302  SP:7FDD  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0105  DE:0147  HL:0000  AF:0302  SP:7FDD  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0105  DE:0147  HL:0000  AF:0202  SP:7FDD  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0202  SP:7FDD  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0202  SP:7FDD  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0105  DE:0147  HL:0000  AF:0202  SP:7FDD  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0105  DE:0147  HL:0000  AF:0102  SP:7FDD  PC:C07E   \n" +
                "C07B  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0102  SP:7FDD  PC:C07B   \n" +
                "C07C  29        DAD H       (3)  BC:0105  DE:0147  HL:0000  AF:0102  SP:7FDD  PC:C07C   \n" +
                "C07D  3D        DCR A       (3)  BC:0105  DE:0147  HL:0000  AF:0102  SP:7FDD  PC:C07D   \n" +
                "C07E  C2 7B C0  JNZ C07B    (3)  BC:0105  DE:0147  HL:0000  AF:0042  SP:7FDD  PC:C07E   \n" +
                "C081  E5        PUSH H      (3)  BC:0105  DE:0147  HL:0000  AF:0042  SP:7FDD  PC:C081   \n" +
                "C082  13        INX D       (3)  BC:0105  DE:0147  HL:0000  AF:0042  SP:7FDB  PC:C082   \n" +
                "C083  05        DCR B       (3)  BC:0105  DE:0148  HL:0000  AF:0042  SP:7FDB  PC:C083   \n" +
                "C084  C2 76 C0  JNZ C076    (3)  BC:0005  DE:0148  HL:0000  AF:0042  SP:7FDB  PC:C084   \n" +
                "C087  06 08     MVI B,08    (3)  BC:0005  DE:0148  HL:0000  AF:0042  SP:7FDB  PC:C087   \n" +
                "C089  2A F8 8F  LHLD 8FF8   (3)  BC:0805  DE:0148  HL:0000  AF:0042  SP:7FDB  PC:C089   \n" +
                "C08C  D1        POP D       (3)  BC:0805  DE:0148  HL:9000  AF:0042  SP:7FDB  PC:C08C   \n" +
                "C08D  7A        MOV A,D     (3)  BC:0805  DE:0000  HL:9000  AF:0042  SP:7FDD  PC:C08D   \n" +
                "C08E  CD 63 C1  CALL C163   (3)  BC:0805  DE:0000  HL:9000  AF:0042  SP:7FDD  PC:C08E   \n" +
                "C163  4F        MOV C,A     (4)  BC:0805  DE:0000  HL:9000  AF:0042  SP:7FDB  PC:C163   \n" +
                "C164  3A E9 8F  LDA 8FE9    (4)  BC:0800  DE:0000  HL:9000  AF:0042  SP:7FDB  PC:C164   \n" +
                "C167  FE 7F     CPI 7F      (4)  BC:0800  DE:0000  HL:9000  AF:4842  SP:7FDB  PC:C167   \n" +
                "C169  79        MOV A,C     (4)  BC:0800  DE:0000  HL:9000  AF:4893  SP:7FDB  PC:C169   \n" +
                "C16A  CA A3 C0  JZ C0A3     (4)  BC:0800  DE:0000  HL:9000  AF:0093  SP:7FDB  PC:C16A   \n" +
                "C16D  AE        XRA M       (4)  BC:0800  DE:0000  HL:9000  AF:0093  SP:7FDB  PC:C16D   \n" +
                "C16E  C9        RET         (4)  BC:0800  DE:0000  HL:9000  AF:0046  SP:7FDB  PC:C16E   \n" +
                "C091  77        MOV M,A     (3)  BC:0800  DE:0000  HL:9000  AF:0046  SP:7FDD  PC:C091   \n" +
                "C092  24        INR H       (3)  BC:0800  DE:0000  HL:9000  AF:0046  SP:7FDD  PC:C092   \n" +
                "C093  7B        MOV A,E     (3)  BC:0800  DE:0000  HL:9100  AF:0082  SP:7FDD  PC:C093   \n" +
                "C094  CD 63 C1  CALL C163   (3)  BC:0800  DE:0000  HL:9100  AF:0082  SP:7FDD  PC:C094   \n" +
                "C163  4F        MOV C,A     (4)  BC:0800  DE:0000  HL:9100  AF:0082  SP:7FDB  PC:C163   \n" +
                "C164  3A E9 8F  LDA 8FE9    (4)  BC:0800  DE:0000  HL:9100  AF:0082  SP:7FDB  PC:C164   \n" +
                "C167  FE 7F     CPI 7F      (4)  BC:0800  DE:0000  HL:9100  AF:4882  SP:7FDB  PC:C167   \n" +
                "C169  79        MOV A,C     (4)  BC:0800  DE:0000  HL:9100  AF:4893  SP:7FDB  PC:C169   \n" +
                "C16A  CA A3 C0  JZ C0A3     (4)  BC:0800  DE:0000  HL:9100  AF:0093  SP:7FDB  PC:C16A   \n" +
                "C16D  AE        XRA M       (4)  BC:0800  DE:0000  HL:9100  AF:0093  SP:7FDB  PC:C16D   \n" +
                "C16E  C9        RET         (4)  BC:0800  DE:0000  HL:9100  AF:0046  SP:7FDB  PC:C16E   \n" +
                "C097  77        MOV M,A     (3)  BC:0800  DE:0000  HL:9100  AF:0046  SP:7FDD  PC:C097   \n" +
                "C098  25        DCR H       (3)  BC:0800  DE:0000  HL:9100  AF:0046  SP:7FDD  PC:C098   ");

        asrtCpu("BC:  0800\n" +
                "DE:  0000\n" +
                "HL:  9000\n" +
                "AF:  0082\n" +
                "SP:  7FDD\n" +
                "PC:  C099\n" +
                "B,C: 08 00\n" +
                "D,E: 00 00\n" +
                "H,L: 90 00\n" +
                "M:   00\n" +
                "A,F: 00 82\n" +
                "     76543210 76543210\n" +
                "SP:  01111111 11011101\n" +
                "PC:  11000000 10011001\n" +
                "     76543210\n" +
                "B:   00001000\n" +
                "C:   00000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   10010000\n" +
                "L:   00000000\n" +
                "M:   00000000\n" +
                "A:   00000000\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
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
