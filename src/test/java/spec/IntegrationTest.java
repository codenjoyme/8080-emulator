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
                .shoot("basic", it -> it.enter("B").press(ENTER).after(20 * K10))
                .shoot("line1", it -> it.enter("10 CLS 2").press(ENTER).after(10 * K10))
                .shoot("line2", it -> it.enter("20 CLS 1").press(ENTER).after(10 * K10))
                .shoot("line2", it -> it.enter("30 GOTO 10").press(ENTER).after(10 * K10))
                .shoot("list", it -> it.enter("LIST").press(ENTER).after(50 * K10))
                .shoot("run", it -> it.enter("RUN").press(ENTER).after(50 * K10))
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
                "00F6  C2 FC 00  JNZ 00FC    (0)  BC:000A  DE:0000  HL:0054  AF:0606  SP:06BE  PC:00F6   \n" +
                "00FC  DA 05 01  JC 0105     (0)  BC:000A  DE:0000  HL:0054  AF:0606  SP:06BE  PC:00FC   \n" +
                "00FF  E2 05 01  JPO 0105    (0)  BC:000A  DE:0000  HL:0054  AF:0606  SP:06BE  PC:00FF   \n" +
                "0102  F2 08 01  JP 0108     (0)  BC:000A  DE:0000  HL:0054  AF:0606  SP:06BE  PC:0102   \n" +
                "0108  C6 70     ADI 70      (0)  BC:000A  DE:0000  HL:0054  AF:0606  SP:06BE  PC:0108   \n" +
                "010A  E2 10 01  JPO 0110    (0)  BC:000A  DE:0000  HL:0054  AF:7602  SP:06BE  PC:010A   \n" +
                "0110  FA 19 01  JM 0119     (0)  BC:000A  DE:0000  HL:0054  AF:7602  SP:06BE  PC:0110   \n" +
                "0113  CA 19 01  JZ 0119     (0)  BC:000A  DE:0000  HL:0054  AF:7602  SP:06BE  PC:0113   \n" +
                "0116  D2 1C 01  JNC 011C    (0)  BC:000A  DE:0000  HL:0054  AF:7602  SP:06BE  PC:0116   \n" +
                "011C  C6 81     ADI 81      (0)  BC:000A  DE:0000  HL:0054  AF:7602  SP:06BE  PC:011C   \n" +
                "011E  FA 24 01  JM 0124     (0)  BC:000A  DE:0000  HL:0054  AF:F782  SP:06BE  PC:011E   \n" +
                "0124  CA 2D 01  JZ 012D     (0)  BC:000A  DE:0000  HL:0054  AF:F782  SP:06BE  PC:0124   \n" +
                "0127  DA 2D 01  JC 012D     (0)  BC:000A  DE:0000  HL:0054  AF:F782  SP:06BE  PC:0127   \n" +
                "012A  E2 30 01  JPO 0130    (0)  BC:000A  DE:0000  HL:0054  AF:F782  SP:06BE  PC:012A   \n" +
                "0130  C6 FE     ADI FE      (0)  BC:000A  DE:0000  HL:0054  AF:F782  SP:06BE  PC:0130   \n" +
                "0132  DA 38 01  JC 0138     (0)  BC:000A  DE:0000  HL:0054  AF:F597  SP:06BE  PC:0132   \n" +
                "0138  CA 41 01  JZ 0141     (0)  BC:000A  DE:0000  HL:0054  AF:F597  SP:06BE  PC:0138   \n" +
                "013B  E2 41 01  JPO 0141    (0)  BC:000A  DE:0000  HL:0054  AF:F597  SP:06BE  PC:013B   \n" +
                "013E  FA 44 01  JM 0144     (0)  BC:000A  DE:0000  HL:0054  AF:F597  SP:06BE  PC:013E   \n" +
                "0144  FE 00     CPI 00      (0)  BC:000A  DE:0000  HL:0054  AF:F597  SP:06BE  PC:0144   \n" +
                "0146  DA 5C 01  JC 015C     (0)  BC:000A  DE:0000  HL:0054  AF:F582  SP:06BE  PC:0146   \n" +
                "0149  CA 5C 01  JZ 015C     (0)  BC:000A  DE:0000  HL:0054  AF:F582  SP:06BE  PC:0149   \n" +
                "014C  FE F5     CPI F5      (0)  BC:000A  DE:0000  HL:0054  AF:F582  SP:06BE  PC:014C   \n" +
                "014E  DA 5C 01  JC 015C     (0)  BC:000A  DE:0000  HL:0054  AF:F542  SP:06BE  PC:014E   \n" +
                "0151  C2 5C 01  JNZ 015C    (0)  BC:000A  DE:0000  HL:0054  AF:F542  SP:06BE  PC:0151   \n" +
                "0154  FE FF     CPI FF      (0)  BC:000A  DE:0000  HL:0054  AF:F542  SP:06BE  PC:0154   \n" +
                "0156  CA 5C 01  JZ 015C     (0)  BC:000A  DE:0000  HL:0054  AF:F593  SP:06BE  PC:0156   \n" +
                "0159  DA 5F 01  JC 015F     (0)  BC:000A  DE:0000  HL:0054  AF:F593  SP:06BE  PC:0159   \n" +
                "015F  CE 0A     ACI 0A      (0)  BC:000A  DE:0000  HL:0054  AF:F593  SP:06BE  PC:015F   \n" +
                "0161  CE 0A     ACI 0A      (0)  BC:000A  DE:0000  HL:0054  AF:0053  SP:06BE  PC:0161   \n" +
                "0163  FE 0B     CPI 0B      (0)  BC:000A  DE:0000  HL:0054  AF:0B02  SP:06BE  PC:0163   \n" +
                "0165  CA 6B 01  JZ 016B     (0)  BC:000A  DE:0000  HL:0054  AF:0B42  SP:06BE  PC:0165   \n" +
                "016B  D6 0C     SUI 0C      (0)  BC:000A  DE:0000  HL:0054  AF:0B42  SP:06BE  PC:016B   \n" +
                "016D  D6 0F     SUI 0F      (0)  BC:000A  DE:0000  HL:0054  AF:FF93  SP:06BE  PC:016D   \n" +
                "016F  FE F0     CPI F0      (0)  BC:000A  DE:0000  HL:0054  AF:F082  SP:06BE  PC:016F   \n" +
                "0171  CA 77 01  JZ 0177     (0)  BC:000A  DE:0000  HL:0054  AF:F042  SP:06BE  PC:0171   \n" +
                "0177  DE F1     SBI F1      (0)  BC:000A  DE:0000  HL:0054  AF:F042  SP:06BE  PC:0177   \n" +
                "0179  DE 0E     SBI 0E      (0)  BC:000A  DE:0000  HL:0054  AF:FF93  SP:06BE  PC:0179   \n" +
                "017B  FE F0     CPI F0      (0)  BC:000A  DE:0000  HL:0054  AF:F082  SP:06BE  PC:017B   \n" +
                "017D  CA 83 01  JZ 0183     (0)  BC:000A  DE:0000  HL:0054  AF:F042  SP:06BE  PC:017D   \n" +
                "0183  E6 55     ANI 55      (0)  BC:000A  DE:0000  HL:0054  AF:F042  SP:06BE  PC:0183   \n" +
                "0185  FE 50     CPI 50      (0)  BC:000A  DE:0000  HL:0054  AF:5016  SP:06BE  PC:0185   \n" +
                "0187  CA 8D 01  JZ 018D     (0)  BC:000A  DE:0000  HL:0054  AF:5042  SP:06BE  PC:0187   \n" +
                "018D  F6 3A     ORI 3A      (0)  BC:000A  DE:0000  HL:0054  AF:5042  SP:06BE  PC:018D   \n" +
                "018F  FE 7A     CPI 7A      (0)  BC:000A  DE:0000  HL:0054  AF:7A02  SP:06BE  PC:018F   \n" +
                "0191  CA 97 01  JZ 0197     (0)  BC:000A  DE:0000  HL:0054  AF:7A42  SP:06BE  PC:0191   \n" +
                "0197  EE 0F     XRI 0F      (0)  BC:000A  DE:0000  HL:0054  AF:7A42  SP:06BE  PC:0197   \n" +
                "0199  FE 75     CPI 75      (0)  BC:000A  DE:0000  HL:0054  AF:7502  SP:06BE  PC:0199   \n" +
                "019B  CA A1 01  JZ 01A1     (0)  BC:000A  DE:0000  HL:0054  AF:7542  SP:06BE  PC:019B   \n" +
                "01A1  E6 00     ANI 00      (0)  BC:000A  DE:0000  HL:0054  AF:7542  SP:06BE  PC:01A1   \n" +
                "01A3  DC A3 05  CC 05A3     (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:01A3   \n" +
                "01A6  E4 A3 05  CPO 05A3    (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:01A6   \n" +
                "01A9  FC A3 05  CM 05A3     (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:01A9   \n" +
                "01AC  C4 A3 05  CNZ 05A3    (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:01AC   \n" +
                "01AF  FE 00     CPI 00      (0)  BC:000A  DE:0000  HL:0054  AF:0056  SP:06BE  PC:01AF   \n" +
                "01B1  CA B7 01  JZ 01B7     (0)  BC:000A  DE:0000  HL:0054  AF:0042  SP:06BE  PC:01B1   \n" +
                "01B7  D6 77     SUI 77      (0)  BC:000A  DE:0000  HL:0054  AF:0042  SP:06BE  PC:01B7   \n" +
                "01B9  D4 A3 05  CNC 05A3    (0)  BC:000A  DE:0000  HL:0054  AF:8993  SP:06BE  PC:01B9   \n" +
                "01BC  EC A3 05  CPE 05A3    (0)  BC:000A  DE:0000  HL:0054  AF:8993  SP:06BE  PC:01BC   \n" +
                "01BF  F4 A3 05  CP 05A3     (0)  BC:000A  DE:0000  HL:0054  AF:8993  SP:06BE  PC:01BF   \n" +
                "01C2  CC A3 05  CZ 05A3     (0)  BC:000A  DE:0000  HL:0054  AF:8993  SP:06BE  PC:01C2   \n" +
                "01C5  FE 89     CPI 89      (0)  BC:000A  DE:0000  HL:0054  AF:8993  SP:06BE  PC:01C5   \n" +
                "01C7  CA CD 01  JZ 01CD     (0)  BC:000A  DE:0000  HL:0054  AF:8942  SP:06BE  PC:01C7   \n" +
                "01CD  E6 FF     ANI FF      (0)  BC:000A  DE:0000  HL:0054  AF:8942  SP:06BE  PC:01CD   \n" +
                "01CF  E4 DA 01  CPO 01DA    (0)  BC:000A  DE:0000  HL:0054  AF:8992  SP:06BE  PC:01CF   \n" +
                "01D2  FE D9     CPI D9      (0)  BC:000A  DE:0000  HL:0054  AF:D982  SP:06BE  PC:01D2   \n" +
                "01D4  CA 37 02  JZ 0237     (0)  BC:000A  DE:0000  HL:0054  AF:D942  SP:06BE  PC:01D4   \n" +
                "0237  3E 77     MVI A,77    (0)  BC:000A  DE:0000  HL:0054  AF:D942  SP:06BE  PC:0237   \n" +
                "0239  3C        INR A       (0)  BC:000A  DE:0000  HL:0054  AF:7742  SP:06BE  PC:0239   \n" +
                "023A  47        MOV B,A     (0)  BC:000A  DE:0000  HL:0054  AF:7802  SP:06BE  PC:023A   \n" +
                "023B  04        INR B       (0)  BC:780A  DE:0000  HL:0054  AF:7802  SP:06BE  PC:023B   \n" +
                "023C  48        MOV C,B     (0)  BC:790A  DE:0000  HL:0054  AF:7802  SP:06BE  PC:023C   \n" +
                "023D  0D        DCR C       (0)  BC:7979  DE:0000  HL:0054  AF:7802  SP:06BE  PC:023D   \n" +
                "023E  51        MOV D,C     (0)  BC:7978  DE:0000  HL:0054  AF:7802  SP:06BE  PC:023E   \n" +
                "023F  5A        MOV E,D     (0)  BC:7978  DE:7800  HL:0054  AF:7802  SP:06BE  PC:023F   \n" +
                "0240  63        MOV H,E     (0)  BC:7978  DE:7878  HL:0054  AF:7802  SP:06BE  PC:0240   \n" +
                "0241  6C        MOV L,H     (0)  BC:7978  DE:7878  HL:7854  AF:7802  SP:06BE  PC:0241   \n" +
                "0242  7D        MOV A,L     (0)  BC:7978  DE:7878  HL:7878  AF:7802  SP:06BE  PC:0242   \n" +
                "0243  3D        DCR A       (0)  BC:7978  DE:7878  HL:7878  AF:7802  SP:06BE  PC:0243   \n" +
                "0244  4F        MOV C,A     (0)  BC:7978  DE:7878  HL:7878  AF:7702  SP:06BE  PC:0244   \n" +
                "0245  59        MOV E,C     (0)  BC:7977  DE:7878  HL:7878  AF:7702  SP:06BE  PC:0245   \n" +
                "0246  6B        MOV L,E     (0)  BC:7977  DE:7877  HL:7878  AF:7702  SP:06BE  PC:0246   \n" +
                "0247  45        MOV B,L     (0)  BC:7977  DE:7877  HL:7877  AF:7702  SP:06BE  PC:0247   \n" +
                "0248  50        MOV D,B     (0)  BC:7777  DE:7877  HL:7877  AF:7702  SP:06BE  PC:0248   \n" +
                "0249  62        MOV H,D     (0)  BC:7777  DE:7777  HL:7877  AF:7702  SP:06BE  PC:0249   \n" +
                "024A  7C        MOV A,H     (0)  BC:7777  DE:7777  HL:7777  AF:7702  SP:06BE  PC:024A   \n" +
                "024B  57        MOV D,A     (0)  BC:7777  DE:7777  HL:7777  AF:7702  SP:06BE  PC:024B   \n" +
                "024C  14        INR D       (0)  BC:7777  DE:7777  HL:7777  AF:7702  SP:06BE  PC:024C   \n" +
                "024D  6A        MOV L,D     (0)  BC:7777  DE:7877  HL:7777  AF:7702  SP:06BE  PC:024D   \n" +
                "024E  4D        MOV C,L     (0)  BC:7777  DE:7877  HL:7778  AF:7702  SP:06BE  PC:024E   \n" +
                "024F  0C        INR C       (0)  BC:7778  DE:7877  HL:7778  AF:7702  SP:06BE  PC:024F   \n" +
                "0250  61        MOV H,C     (0)  BC:7779  DE:7877  HL:7778  AF:7702  SP:06BE  PC:0250   \n" +
                "0251  44        MOV B,H     (0)  BC:7779  DE:7877  HL:7978  AF:7702  SP:06BE  PC:0251   \n" +
                "0252  05        DCR B       (0)  BC:7979  DE:7877  HL:7978  AF:7702  SP:06BE  PC:0252   \n" +
                "0253  58        MOV E,B     (0)  BC:7879  DE:7877  HL:7978  AF:7702  SP:06BE  PC:0253   \n" +
                "0254  7B        MOV A,E     (0)  BC:7879  DE:7878  HL:7978  AF:7702  SP:06BE  PC:0254   \n" +
                "0255  5F        MOV E,A     (0)  BC:7879  DE:7878  HL:7978  AF:7802  SP:06BE  PC:0255   \n" +
                "0256  1C        INR E       (0)  BC:7879  DE:7878  HL:7978  AF:7802  SP:06BE  PC:0256   \n" +
                "0257  43        MOV B,E     (0)  BC:7879  DE:7879  HL:7978  AF:7802  SP:06BE  PC:0257   \n" +
                "0258  60        MOV H,B     (0)  BC:7979  DE:7879  HL:7978  AF:7802  SP:06BE  PC:0258   \n" +
                "0259  24        INR H       (0)  BC:7979  DE:7879  HL:7978  AF:7802  SP:06BE  PC:0259   \n" +
                "025A  4C        MOV C,H     (0)  BC:7979  DE:7879  HL:7A78  AF:7802  SP:06BE  PC:025A   \n" +
                "025B  69        MOV L,C     (0)  BC:797A  DE:7879  HL:7A78  AF:7802  SP:06BE  PC:025B   \n" +
                "025C  55        MOV D,L     (0)  BC:797A  DE:7879  HL:7A7A  AF:7802  SP:06BE  PC:025C   \n" +
                "025D  15        DCR D       (0)  BC:797A  DE:7A79  HL:7A7A  AF:7802  SP:06BE  PC:025D   \n" +
                "025E  7A        MOV A,D     (0)  BC:797A  DE:7979  HL:7A7A  AF:7802  SP:06BE  PC:025E   \n" +
                "025F  67        MOV H,A     (0)  BC:797A  DE:7979  HL:7A7A  AF:7902  SP:06BE  PC:025F   \n" +
                "0260  25        DCR H       (0)  BC:797A  DE:7979  HL:797A  AF:7902  SP:06BE  PC:0260   \n" +
                "0261  54        MOV D,H     (0)  BC:797A  DE:7979  HL:787A  AF:7902  SP:06BE  PC:0261   \n" +
                "0262  42        MOV B,D     (0)  BC:797A  DE:7879  HL:787A  AF:7902  SP:06BE  PC:0262   \n" +
                "0263  68        MOV L,B     (0)  BC:787A  DE:7879  HL:787A  AF:7902  SP:06BE  PC:0263   \n" +
                "0264  2C        INR L       (0)  BC:787A  DE:7879  HL:7878  AF:7902  SP:06BE  PC:0264   \n" +
                "0265  5D        MOV E,L     (0)  BC:787A  DE:7879  HL:7879  AF:7902  SP:06BE  PC:0265   \n" +
                "0266  1D        DCR E       (0)  BC:787A  DE:7879  HL:7879  AF:7902  SP:06BE  PC:0266   \n" +
                "0267  4B        MOV C,E     (0)  BC:787A  DE:7878  HL:7879  AF:7902  SP:06BE  PC:0267   \n" +
                "0268  79        MOV A,C     (0)  BC:7878  DE:7878  HL:7879  AF:7902  SP:06BE  PC:0268   \n" +
                "0269  6F        MOV L,A     (0)  BC:7878  DE:7878  HL:7879  AF:7802  SP:06BE  PC:0269   \n" +
                "026A  2D        DCR L       (0)  BC:7878  DE:7878  HL:7878  AF:7802  SP:06BE  PC:026A   \n" +
                "026B  65        MOV H,L     (0)  BC:7878  DE:7878  HL:7877  AF:7802  SP:06BE  PC:026B   \n" +
                "026C  5C        MOV E,H     (0)  BC:7878  DE:7878  HL:7777  AF:7802  SP:06BE  PC:026C   \n" +
                "026D  53        MOV D,E     (0)  BC:7878  DE:7877  HL:7777  AF:7802  SP:06BE  PC:026D   \n" +
                "026E  4A        MOV C,D     (0)  BC:7878  DE:7777  HL:7777  AF:7802  SP:06BE  PC:026E   \n" +
                "026F  41        MOV B,C     (0)  BC:7877  DE:7777  HL:7777  AF:7802  SP:06BE  PC:026F   \n" +
                "0270  78        MOV A,B     (0)  BC:7777  DE:7777  HL:7777  AF:7802  SP:06BE  PC:0270   \n" +
                "0271  FE 77     CPI 77      (0)  BC:7777  DE:7777  HL:7777  AF:7702  SP:06BE  PC:0271   \n" +
                "0273  C4 A3 05  CNZ 05A3    (0)  BC:7777  DE:7777  HL:7777  AF:7742  SP:06BE  PC:0273   \n" +
                "0276  AF        XRA A       (0)  BC:7777  DE:7777  HL:7777  AF:7742  SP:06BE  PC:0276   \n" +
                "0277  06 01     MVI B,01    (0)  BC:7777  DE:7777  HL:7777  AF:0046  SP:06BE  PC:0277   \n" +
                "0279  0E 03     MVI C,03    (0)  BC:0177  DE:7777  HL:7777  AF:0046  SP:06BE  PC:0279   \n" +
                "027B  16 07     MVI D,07    (0)  BC:0103  DE:7777  HL:7777  AF:0046  SP:06BE  PC:027B   \n" +
                "027D  1E 0F     MVI E,0F    (0)  BC:0103  DE:0777  HL:7777  AF:0046  SP:06BE  PC:027D   \n" +
                "027F  26 1F     MVI H,1F    (0)  BC:0103  DE:070F  HL:7777  AF:0046  SP:06BE  PC:027F   \n" +
                "0281  2E 3F     MVI L,3F    (0)  BC:0103  DE:070F  HL:1F77  AF:0046  SP:06BE  PC:0281   \n" +
                "0283  80        ADD B       (0)  BC:0103  DE:070F  HL:1F3F  AF:0046  SP:06BE  PC:0283   \n" +
                "0284  81        ADD C       (0)  BC:0103  DE:070F  HL:1F3F  AF:0102  SP:06BE  PC:0284   \n" +
                "0285  82        ADD D       (0)  BC:0103  DE:070F  HL:1F3F  AF:0402  SP:06BE  PC:0285   \n" +
                "0286  83        ADD E       (0)  BC:0103  DE:070F  HL:1F3F  AF:0B02  SP:06BE  PC:0286   \n" +
                "0287  84        ADD H       (0)  BC:0103  DE:070F  HL:1F3F  AF:1A12  SP:06BE  PC:0287   \n" +
                "0288  85        ADD L       (0)  BC:0103  DE:070F  HL:1F3F  AF:3912  SP:06BE  PC:0288   \n" +
                "0289  87        ADD A       (0)  BC:0103  DE:070F  HL:1F3F  AF:7812  SP:06BE  PC:0289   \n" +
                "028A  FE F0     CPI F0      (0)  BC:0103  DE:070F  HL:1F3F  AF:F096  SP:06BE  PC:028A   \n" +
                "028C  C4 A3 05  CNZ 05A3    (0)  BC:0103  DE:070F  HL:1F3F  AF:F042  SP:06BE  PC:028C   \n" +
                "028F  90        SUB B       (0)  BC:0103  DE:070F  HL:1F3F  AF:F042  SP:06BE  PC:028F   \n" +
                "0290  91        SUB C       (0)  BC:0103  DE:070F  HL:1F3F  AF:EF92  SP:06BE  PC:0290   \n" +
                "0291  92        SUB D       (0)  BC:0103  DE:070F  HL:1F3F  AF:EC82  SP:06BE  PC:0291   \n" +
                "0292  93        SUB E       (0)  BC:0103  DE:070F  HL:1F3F  AF:E582  SP:06BE  PC:0292   \n" +
                "0293  94        SUB H       (0)  BC:0103  DE:070F  HL:1F3F  AF:D692  SP:06BE  PC:0293   \n" +
                "0294  95        SUB L       (0)  BC:0103  DE:070F  HL:1F3F  AF:B792  SP:06BE  PC:0294   \n" +
                "0295  FE 78     CPI 78      (0)  BC:0103  DE:070F  HL:1F3F  AF:7816  SP:06BE  PC:0295   \n" +
                "0297  C4 A3 05  CNZ 05A3    (0)  BC:0103  DE:070F  HL:1F3F  AF:7842  SP:06BE  PC:0297   \n" +
                "029A  97        SUB A       (0)  BC:0103  DE:070F  HL:1F3F  AF:7842  SP:06BE  PC:029A   \n" +
                "029B  C4 A3 05  CNZ 05A3    (0)  BC:0103  DE:070F  HL:1F3F  AF:0042  SP:06BE  PC:029B   \n" +
                "029E  3E 80     MVI A,80    (0)  BC:0103  DE:070F  HL:1F3F  AF:0042  SP:06BE  PC:029E   \n" +
                "02A0  87        ADD A       (0)  BC:0103  DE:070F  HL:1F3F  AF:8042  SP:06BE  PC:02A0   \n" +
                "02A1  06 01     MVI B,01    (0)  BC:0103  DE:070F  HL:1F3F  AF:0047  SP:06BE  PC:02A1   \n" +
                "02A3  0E 02     MVI C,02    (0)  BC:0103  DE:070F  HL:1F3F  AF:0047  SP:06BE  PC:02A3   \n" +
                "02A5  16 03     MVI D,03    (0)  BC:0102  DE:070F  HL:1F3F  AF:0047  SP:06BE  PC:02A5   \n" +
                "02A7  1E 04     MVI E,04    (0)  BC:0102  DE:030F  HL:1F3F  AF:0047  SP:06BE  PC:02A7   \n" +
                "02A9  26 05     MVI H,05    (0)  BC:0102  DE:0304  HL:1F3F  AF:0047  SP:06BE  PC:02A9   \n" +
                "02AB  2E 06     MVI L,06    (0)  BC:0102  DE:0304  HL:053F  AF:0047  SP:06BE  PC:02AB   \n" +
                "02AD  88        ADC B       (0)  BC:0102  DE:0304  HL:0506  AF:0047  SP:06BE  PC:02AD   \n" +
                "02AE  06 80     MVI B,80    (0)  BC:0102  DE:0304  HL:0506  AF:0202  SP:06BE  PC:02AE   \n" +
                "02B0  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:0202  SP:06BE  PC:02B0   \n" +
                "02B1  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:8282  SP:06BE  PC:02B1   \n" +
                "02B2  89        ADC C       (0)  BC:8002  DE:0304  HL:0506  AF:0207  SP:06BE  PC:02B2   \n" +
                "02B3  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:0502  SP:06BE  PC:02B3   \n" +
                "02B4  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:8582  SP:06BE  PC:02B4   \n" +
                "02B5  8A        ADC D       (0)  BC:8002  DE:0304  HL:0506  AF:0507  SP:06BE  PC:02B5   \n" +
                "02B6  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:0902  SP:06BE  PC:02B6   \n" +
                "02B7  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:8982  SP:06BE  PC:02B7   \n" +
                "02B8  8B        ADC E       (0)  BC:8002  DE:0304  HL:0506  AF:0907  SP:06BE  PC:02B8   \n" +
                "02B9  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:0E02  SP:06BE  PC:02B9   \n" +
                "02BA  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:8E82  SP:06BE  PC:02BA   \n" +
                "02BB  8C        ADC H       (0)  BC:8002  DE:0304  HL:0506  AF:0E07  SP:06BE  PC:02BB   \n" +
                "02BC  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:1412  SP:06BE  PC:02BC   \n" +
                "02BD  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:9482  SP:06BE  PC:02BD   \n" +
                "02BE  8D        ADC L       (0)  BC:8002  DE:0304  HL:0506  AF:1407  SP:06BE  PC:02BE   \n" +
                "02BF  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:1B02  SP:06BE  PC:02BF   \n" +
                "02C0  80        ADD B       (0)  BC:8002  DE:0304  HL:0506  AF:9B82  SP:06BE  PC:02C0   \n" +
                "02C1  8F        ADC A       (0)  BC:8002  DE:0304  HL:0506  AF:1B07  SP:06BE  PC:02C1   \n" +
                "02C2  FE 37     CPI 37      (0)  BC:8002  DE:0304  HL:0506  AF:3712  SP:06BE  PC:02C2   \n" +
                "02C4  C4 A3 05  CNZ 05A3    (0)  BC:8002  DE:0304  HL:0506  AF:3742  SP:06BE  PC:02C4   \n" +
                "02C7  3E 80     MVI A,80    (0)  BC:8002  DE:0304  HL:0506  AF:3742  SP:06BE  PC:02C7   \n" +
                "02C9  87        ADD A       (0)  BC:8002  DE:0304  HL:0506  AF:8042  SP:06BE  PC:02C9   \n" +
                "02CA  06 01     MVI B,01    (0)  BC:8002  DE:0304  HL:0506  AF:0047  SP:06BE  PC:02CA   \n" +
                "02CC  98        SBB B       (0)  BC:0102  DE:0304  HL:0506  AF:0047  SP:06BE  PC:02CC   \n" +
                "02CD  06 FF     MVI B,FF    (0)  BC:0102  DE:0304  HL:0506  AF:FE93  SP:06BE  PC:02CD   \n" +
                "02CF  80        ADD B       (0)  BC:FF02  DE:0304  HL:0506  AF:FE93  SP:06BE  PC:02CF   \n" +
                "02D0  99        SBB C       (0)  BC:FF02  DE:0304  HL:0506  AF:FD93  SP:06BE  PC:02D0   \n" +
                "02D1  80        ADD B       (0)  BC:FF02  DE:0304  HL:0506  AF:FA82  SP:06BE  PC:02D1   \n" +
                "02D2  9A        SBB D       (0)  BC:FF02  DE:0304  HL:0506  AF:F993  SP:06BE  PC:02D2   \n" +
                "02D3  80        ADD B       (0)  BC:FF02  DE:0304  HL:0506  AF:F582  SP:06BE  PC:02D3   \n" +
                "02D4  9B        SBB E       (0)  BC:FF02  DE:0304  HL:0506  AF:F493  SP:06BE  PC:02D4   \n" +
                "02D5  80        ADD B       (0)  BC:FF02  DE:0304  HL:0506  AF:EF92  SP:06BE  PC:02D5   \n" +
                "02D6  9C        SBB H       (0)  BC:FF02  DE:0304  HL:0506  AF:EE93  SP:06BE  PC:02D6   \n" +
                "02D7  80        ADD B       (0)  BC:FF02  DE:0304  HL:0506  AF:E882  SP:06BE  PC:02D7   \n" +
                "02D8  9D        SBB L       (0)  BC:FF02  DE:0304  HL:0506  AF:E793  SP:06BE  PC:02D8   \n" +
                "02D9  FE E0     CPI E0      (0)  BC:FF02  DE:0304  HL:0506  AF:E082  SP:06BE  PC:02D9   \n" +
                "02DB  C4 A3 05  CNZ 05A3    (0)  BC:FF02  DE:0304  HL:0506  AF:E042  SP:06BE  PC:02DB   \n" +
                "02DE  3E 80     MVI A,80    (0)  BC:FF02  DE:0304  HL:0506  AF:E042  SP:06BE  PC:02DE   \n" +
                "02E0  87        ADD A       (0)  BC:FF02  DE:0304  HL:0506  AF:8042  SP:06BE  PC:02E0   \n" +
                "02E1  9F        SBB A       (0)  BC:FF02  DE:0304  HL:0506  AF:0047  SP:06BE  PC:02E1   \n" +
                "02E2  FE FF     CPI FF      (0)  BC:FF02  DE:0304  HL:0506  AF:FF93  SP:06BE  PC:02E2   \n" +
                "02E4  C4 A3 05  CNZ 05A3    (0)  BC:FF02  DE:0304  HL:0506  AF:FF42  SP:06BE  PC:02E4   \n" +
                "02E7  3E FF     MVI A,FF    (0)  BC:FF02  DE:0304  HL:0506  AF:FF42  SP:06BE  PC:02E7   \n" +
                "02E9  06 FE     MVI B,FE    (0)  BC:FF02  DE:0304  HL:0506  AF:FF42  SP:06BE  PC:02E9   \n" +
                "02EB  0E FC     MVI C,FC    (0)  BC:FE02  DE:0304  HL:0506  AF:FF42  SP:06BE  PC:02EB   \n" +
                "02ED  16 EF     MVI D,EF    (0)  BC:FEFC  DE:0304  HL:0506  AF:FF42  SP:06BE  PC:02ED   \n" +
                "02EF  1E 7F     MVI E,7F    (0)  BC:FEFC  DE:EF04  HL:0506  AF:FF42  SP:06BE  PC:02EF   \n" +
                "02F1  26 F4     MVI H,F4    (0)  BC:FEFC  DE:EF7F  HL:0506  AF:FF42  SP:06BE  PC:02F1   \n" +
                "02F3  2E BF     MVI L,BF    (0)  BC:FEFC  DE:EF7F  HL:F406  AF:FF42  SP:06BE  PC:02F3   \n" +
                "02F5  A7        ANA A       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:FF42  SP:06BE  PC:02F5   \n" +
                "02F6  A1        ANA C       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:FF96  SP:06BE  PC:02F6   \n" +
                "02F7  A2        ANA D       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:FC96  SP:06BE  PC:02F7   \n" +
                "02F8  A3        ANA E       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:EC92  SP:06BE  PC:02F8   \n" +
                "02F9  A4        ANA H       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:6C16  SP:06BE  PC:02F9   \n" +
                "02FA  A5        ANA L       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:6412  SP:06BE  PC:02FA   \n" +
                "02FB  A7        ANA A       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:2416  SP:06BE  PC:02FB   \n" +
                "02FC  FE 24     CPI 24      (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:2416  SP:06BE  PC:02FC   \n" +
                "02FE  C4 A3 05  CNZ 05A3    (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:2442  SP:06BE  PC:02FE   \n" +
                "0301  AF        XRA A       (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:2442  SP:06BE  PC:0301   \n" +
                "0302  06 01     MVI B,01    (0)  BC:FEFC  DE:EF7F  HL:F4BF  AF:0046  SP:06BE  PC:0302   \n" +
                "0304  0E 02     MVI C,02    (0)  BC:01FC  DE:EF7F  HL:F4BF  AF:0046  SP:06BE  PC:0304   \n" +
                "0306  16 04     MVI D,04    (0)  BC:0102  DE:EF7F  HL:F4BF  AF:0046  SP:06BE  PC:0306   \n" +
                "0308  1E 08     MVI E,08    (0)  BC:0102  DE:047F  HL:F4BF  AF:0046  SP:06BE  PC:0308   \n" +
                "030A  26 10     MVI H,10    (0)  BC:0102  DE:0408  HL:F4BF  AF:0046  SP:06BE  PC:030A   \n" +
                "030C  2E 20     MVI L,20    (0)  BC:0102  DE:0408  HL:10BF  AF:0046  SP:06BE  PC:030C   \n" +
                "030E  B0        ORA B       (0)  BC:0102  DE:0408  HL:1020  AF:0046  SP:06BE  PC:030E   \n" +
                "030F  B1        ORA C       (0)  BC:0102  DE:0408  HL:1020  AF:0102  SP:06BE  PC:030F   \n" +
                "0310  B2        ORA D       (0)  BC:0102  DE:0408  HL:1020  AF:0306  SP:06BE  PC:0310   \n" +
                "0311  B3        ORA E       (0)  BC:0102  DE:0408  HL:1020  AF:0702  SP:06BE  PC:0311   \n" +
                "0312  B4        ORA H       (0)  BC:0102  DE:0408  HL:1020  AF:0F06  SP:06BE  PC:0312   \n" +
                "0313  B5        ORA L       (0)  BC:0102  DE:0408  HL:1020  AF:1F02  SP:06BE  PC:0313   \n" +
                "0314  B7        ORA A       (0)  BC:0102  DE:0408  HL:1020  AF:3F06  SP:06BE  PC:0314   \n" +
                "0315  FE 3F     CPI 3F      (0)  BC:0102  DE:0408  HL:1020  AF:3F06  SP:06BE  PC:0315   \n" +
                "0317  C4 A3 05  CNZ 05A3    (0)  BC:0102  DE:0408  HL:1020  AF:3F42  SP:06BE  PC:0317   \n" +
                "031A  3E 00     MVI A,00    (0)  BC:0102  DE:0408  HL:1020  AF:3F42  SP:06BE  PC:031A   \n" +
                "031C  26 8F     MVI H,8F    (0)  BC:0102  DE:0408  HL:1020  AF:0042  SP:06BE  PC:031C   \n" +
                "031E  2E 4F     MVI L,4F    (0)  BC:0102  DE:0408  HL:8F20  AF:0042  SP:06BE  PC:031E   \n" +
                "0320  A8        XRA B       (0)  BC:0102  DE:0408  HL:8F4F  AF:0042  SP:06BE  PC:0320   \n" +
                "0321  A9        XRA C       (0)  BC:0102  DE:0408  HL:8F4F  AF:0102  SP:06BE  PC:0321   \n" +
                "0322  AA        XRA D       (0)  BC:0102  DE:0408  HL:8F4F  AF:0306  SP:06BE  PC:0322   \n" +
                "0323  AB        XRA E       (0)  BC:0102  DE:0408  HL:8F4F  AF:0702  SP:06BE  PC:0323   \n" +
                "0324  AC        XRA H       (0)  BC:0102  DE:0408  HL:8F4F  AF:0F06  SP:06BE  PC:0324   \n" +
                "0325  AD        XRA L       (0)  BC:0102  DE:0408  HL:8F4F  AF:8082  SP:06BE  PC:0325   \n" +
                "0326  FE CF     CPI CF      (0)  BC:0102  DE:0408  HL:8F4F  AF:CF86  SP:06BE  PC:0326   \n" +
                "0328  C4 A3 05  CNZ 05A3    (0)  BC:0102  DE:0408  HL:8F4F  AF:CF42  SP:06BE  PC:0328   \n" +
                "032B  AF        XRA A       (0)  BC:0102  DE:0408  HL:8F4F  AF:CF42  SP:06BE  PC:032B   \n" +
                "032C  C4 A3 05  CNZ 05A3    (0)  BC:0102  DE:0408  HL:8F4F  AF:0046  SP:06BE  PC:032C   \n" +
                "032F  06 44     MVI B,44    (0)  BC:0102  DE:0408  HL:8F4F  AF:0046  SP:06BE  PC:032F   \n" +
                "0331  0E 45     MVI C,45    (0)  BC:4402  DE:0408  HL:8F4F  AF:0046  SP:06BE  PC:0331   \n" +
                "0333  16 46     MVI D,46    (0)  BC:4445  DE:0408  HL:8F4F  AF:0046  SP:06BE  PC:0333   \n" +
                "0335  1E 47     MVI E,47    (0)  BC:4445  DE:4608  HL:8F4F  AF:0046  SP:06BE  PC:0335   \n" +
                "0337  26 05     MVI H,05    (0)  BC:4445  DE:4647  HL:8F4F  AF:0046  SP:06BE  PC:0337   \n" +
                "0339  2E C0     MVI L,C0    (0)  BC:4445  DE:4647  HL:054F  AF:0046  SP:06BE  PC:0339   \n" +
                "033B  70        MOV M,B     (0)  BC:4445  DE:4647  HL:05C0  AF:0046  SP:06BE  PC:033B   \n" +
                "033C  06 00     MVI B,00    (0)  BC:4445  DE:4647  HL:05C0  AF:0046  SP:06BE  PC:033C   \n" +
                "033E  46        MOV B,M     (0)  BC:0045  DE:4647  HL:05C0  AF:0046  SP:06BE  PC:033E   \n" +
                "033F  3E 44     MVI A,44    (0)  BC:4445  DE:4647  HL:05C0  AF:0046  SP:06BE  PC:033F   \n" +
                "0341  B8        CMP B       (0)  BC:4445  DE:4647  HL:05C0  AF:4446  SP:06BE  PC:0341   \n" +
                "0342  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:4442  SP:06BE  PC:0342   \n" +
                "0345  72        MOV M,D     (0)  BC:4445  DE:4647  HL:05C0  AF:4442  SP:06BE  PC:0345   \n" +
                "0346  16 00     MVI D,00    (0)  BC:4445  DE:4647  HL:05C0  AF:4442  SP:06BE  PC:0346   \n" +
                "0348  56        MOV D,M     (0)  BC:4445  DE:0047  HL:05C0  AF:4442  SP:06BE  PC:0348   \n" +
                "0349  3E 46     MVI A,46    (0)  BC:4445  DE:4647  HL:05C0  AF:4442  SP:06BE  PC:0349   \n" +
                "034B  BA        CMP D       (0)  BC:4445  DE:4647  HL:05C0  AF:4642  SP:06BE  PC:034B   \n" +
                "034C  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:4642  SP:06BE  PC:034C   \n" +
                "034F  73        MOV M,E     (0)  BC:4445  DE:4647  HL:05C0  AF:4642  SP:06BE  PC:034F   \n" +
                "0350  1E 00     MVI E,00    (0)  BC:4445  DE:4647  HL:05C0  AF:4642  SP:06BE  PC:0350   \n" +
                "0352  5E        MOV E,M     (0)  BC:4445  DE:4600  HL:05C0  AF:4642  SP:06BE  PC:0352   \n" +
                "0353  3E 47     MVI A,47    (0)  BC:4445  DE:4647  HL:05C0  AF:4642  SP:06BE  PC:0353   \n" +
                "0355  BB        CMP E       (0)  BC:4445  DE:4647  HL:05C0  AF:4742  SP:06BE  PC:0355   \n" +
                "0356  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:4742  SP:06BE  PC:0356   \n" +
                "0359  74        MOV M,H     (0)  BC:4445  DE:4647  HL:05C0  AF:4742  SP:06BE  PC:0359   \n" +
                "035A  26 05     MVI H,05    (0)  BC:4445  DE:4647  HL:05C0  AF:4742  SP:06BE  PC:035A   \n" +
                "035C  2E C0     MVI L,C0    (0)  BC:4445  DE:4647  HL:05C0  AF:4742  SP:06BE  PC:035C   \n" +
                "035E  66        MOV H,M     (0)  BC:4445  DE:4647  HL:05C0  AF:4742  SP:06BE  PC:035E   \n" +
                "035F  3E 05     MVI A,05    (0)  BC:4445  DE:4647  HL:05C0  AF:4742  SP:06BE  PC:035F   \n" +
                "0361  BC        CMP H       (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:0361   \n" +
                "0362  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:0362   \n" +
                "0365  75        MOV M,L     (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:0365   \n" +
                "0366  26 05     MVI H,05    (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:0366   \n" +
                "0368  2E C0     MVI L,C0    (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:0368   \n" +
                "036A  6E        MOV L,M     (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:036A   \n" +
                "036B  3E C0     MVI A,C0    (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:036B   \n" +
                "036D  BD        CMP L       (0)  BC:4445  DE:4647  HL:05C0  AF:C042  SP:06BE  PC:036D   \n" +
                "036E  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:C042  SP:06BE  PC:036E   \n" +
                "0371  26 05     MVI H,05    (0)  BC:4445  DE:4647  HL:05C0  AF:C042  SP:06BE  PC:0371   \n" +
                "0373  2E C0     MVI L,C0    (0)  BC:4445  DE:4647  HL:05C0  AF:C042  SP:06BE  PC:0373   \n" +
                "0375  3E 32     MVI A,32    (0)  BC:4445  DE:4647  HL:05C0  AF:C042  SP:06BE  PC:0375   \n" +
                "0377  77        MOV M,A     (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:0377   \n" +
                "0378  BE        CMP M       (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:0378   \n" +
                "0379  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:0379   \n" +
                "037C  86        ADD M       (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:037C   \n" +
                "037D  FE 64     CPI 64      (0)  BC:4445  DE:4647  HL:05C0  AF:6402  SP:06BE  PC:037D   \n" +
                "037F  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:6442  SP:06BE  PC:037F   \n" +
                "0382  AF        XRA A       (0)  BC:4445  DE:4647  HL:05C0  AF:6442  SP:06BE  PC:0382   \n" +
                "0383  7E        MOV A,M     (0)  BC:4445  DE:4647  HL:05C0  AF:0046  SP:06BE  PC:0383   \n" +
                "0384  FE 32     CPI 32      (0)  BC:4445  DE:4647  HL:05C0  AF:3246  SP:06BE  PC:0384   \n" +
                "0386  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:0386   \n" +
                "0389  26 05     MVI H,05    (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:0389   \n" +
                "038B  2E C0     MVI L,C0    (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:038B   \n" +
                "038D  7E        MOV A,M     (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:038D   \n" +
                "038E  96        SUB M       (0)  BC:4445  DE:4647  HL:05C0  AF:3242  SP:06BE  PC:038E   \n" +
                "038F  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:0042  SP:06BE  PC:038F   \n" +
                "0392  3E 80     MVI A,80    (0)  BC:4445  DE:4647  HL:05C0  AF:0042  SP:06BE  PC:0392   \n" +
                "0394  87        ADD A       (0)  BC:4445  DE:4647  HL:05C0  AF:8042  SP:06BE  PC:0394   \n" +
                "0395  8E        ADC M       (0)  BC:4445  DE:4647  HL:05C0  AF:0047  SP:06BE  PC:0395   \n" +
                "0396  FE 33     CPI 33      (0)  BC:4445  DE:4647  HL:05C0  AF:3302  SP:06BE  PC:0396   \n" +
                "0398  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:3342  SP:06BE  PC:0398   \n" +
                "039B  3E 80     MVI A,80    (0)  BC:4445  DE:4647  HL:05C0  AF:3342  SP:06BE  PC:039B   \n" +
                "039D  87        ADD A       (0)  BC:4445  DE:4647  HL:05C0  AF:8042  SP:06BE  PC:039D   \n" +
                "039E  9E        SBB M       (0)  BC:4445  DE:4647  HL:05C0  AF:0047  SP:06BE  PC:039E   \n" +
                "039F  FE CD     CPI CD      (0)  BC:4445  DE:4647  HL:05C0  AF:CD93  SP:06BE  PC:039F   \n" +
                "03A1  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:CD42  SP:06BE  PC:03A1   \n" +
                "03A4  A6        ANA M       (0)  BC:4445  DE:4647  HL:05C0  AF:CD42  SP:06BE  PC:03A4   \n" +
                "03A5  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:0056  SP:06BE  PC:03A5   \n" +
                "03A8  3E 25     MVI A,25    (0)  BC:4445  DE:4647  HL:05C0  AF:0056  SP:06BE  PC:03A8   \n" +
                "03AA  B6        ORA M       (0)  BC:4445  DE:4647  HL:05C0  AF:2556  SP:06BE  PC:03AA   \n" +
                "03AB  FE 37     CPI 37      (0)  BC:4445  DE:4647  HL:05C0  AF:3702  SP:06BE  PC:03AB   \n" +
                "03AD  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:3742  SP:06BE  PC:03AD   \n" +
                "03B0  AE        XRA M       (0)  BC:4445  DE:4647  HL:05C0  AF:3742  SP:06BE  PC:03B0   \n" +
                "03B1  FE 05     CPI 05      (0)  BC:4445  DE:4647  HL:05C0  AF:0506  SP:06BE  PC:03B1   \n" +
                "03B3  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:03B3   \n" +
                "03B6  36 55     MVI M,55    (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:03B6   \n" +
                "03B8  34        INR M       (0)  BC:4445  DE:4647  HL:05C0  AF:0542  SP:06BE  PC:03B8   \n" +
                "03B9  35        DCR M       (0)  BC:4445  DE:4647  HL:05C0  AF:0502  SP:06BE  PC:03B9   \n" +
                "03BA  86        ADD M       (0)  BC:4445  DE:4647  HL:05C0  AF:0502  SP:06BE  PC:03BA   \n" +
                "03BB  FE 5A     CPI 5A      (0)  BC:4445  DE:4647  HL:05C0  AF:5A02  SP:06BE  PC:03BB   \n" +
                "03BD  C4 A3 05  CNZ 05A3    (0)  BC:4445  DE:4647  HL:05C0  AF:5A42  SP:06BE  PC:03BD   \n" +
                "03C0  01 FF 12  LXI B,12FF  (0)  BC:4445  DE:4647  HL:05C0  AF:5A42  SP:06BE  PC:03C0   \n" +
                "03C3  11 FF 12  LXI D,12FF  (0)  BC:12FF  DE:4647  HL:05C0  AF:5A42  SP:06BE  PC:03C3   \n" +
                "03C6  21 FF 12  LXI H,12FF  (0)  BC:12FF  DE:12FF  HL:05C0  AF:5A42  SP:06BE  PC:03C6   \n" +
                "03C9  03        INX B       (0)  BC:12FF  DE:12FF  HL:12FF  AF:5A42  SP:06BE  PC:03C9   \n" +
                "03CA  13        INX D       (0)  BC:1300  DE:12FF  HL:12FF  AF:5A42  SP:06BE  PC:03CA   \n" +
                "03CB  23        INX H       (0)  BC:1300  DE:1300  HL:12FF  AF:5A42  SP:06BE  PC:03CB   \n" +
                "03CC  3E 13     MVI A,13    (0)  BC:1300  DE:1300  HL:1300  AF:5A42  SP:06BE  PC:03CC   \n" +
                "03CE  B8        CMP B       (0)  BC:1300  DE:1300  HL:1300  AF:1342  SP:06BE  PC:03CE   \n" +
                "03CF  C4 A3 05  CNZ 05A3    (0)  BC:1300  DE:1300  HL:1300  AF:1342  SP:06BE  PC:03CF   \n" +
                "03D2  BA        CMP D       (0)  BC:1300  DE:1300  HL:1300  AF:1342  SP:06BE  PC:03D2   \n" +
                "03D3  C4 A3 05  CNZ 05A3    (0)  BC:1300  DE:1300  HL:1300  AF:1342  SP:06BE  PC:03D3   \n" +
                "03D6  BC        CMP H       (0)  BC:1300  DE:1300  HL:1300  AF:1342  SP:06BE  PC:03D6   \n" +
                "03D7  C4 A3 05  CNZ 05A3    (0)  BC:1300  DE:1300  HL:1300  AF:1342  SP:06BE  PC:03D7   \n" +
                "03DA  3E 00     MVI A,00    (0)  BC:1300  DE:1300  HL:1300  AF:1342  SP:06BE  PC:03DA   \n" +
                "03DC  B9        CMP C       (0)  BC:1300  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03DC   \n" +
                "03DD  C4 A3 05  CNZ 05A3    (0)  BC:1300  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03DD   \n" +
                "03E0  BB        CMP E       (0)  BC:1300  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03E0   \n" +
                "03E1  C4 A3 05  CNZ 05A3    (0)  BC:1300  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03E1   \n" +
                "03E4  BD        CMP L       (0)  BC:1300  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03E4   \n" +
                "03E5  C4 A3 05  CNZ 05A3    (0)  BC:1300  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03E5   \n" +
                "03E8  0B        DCX B       (0)  BC:1300  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03E8   \n" +
                "03E9  1B        DCX D       (0)  BC:12FF  DE:1300  HL:1300  AF:0042  SP:06BE  PC:03E9   \n" +
                "03EA  2B        DCX H       (0)  BC:12FF  DE:12FF  HL:1300  AF:0042  SP:06BE  PC:03EA   \n" +
                "03EB  3E 12     MVI A,12    (0)  BC:12FF  DE:12FF  HL:12FF  AF:0042  SP:06BE  PC:03EB   \n" +
                "03ED  B8        CMP B       (0)  BC:12FF  DE:12FF  HL:12FF  AF:1242  SP:06BE  PC:03ED   \n" +
                "03EE  C4 A3 05  CNZ 05A3    (0)  BC:12FF  DE:12FF  HL:12FF  AF:1242  SP:06BE  PC:03EE   \n" +
                "03F1  BA        CMP D       (0)  BC:12FF  DE:12FF  HL:12FF  AF:1242  SP:06BE  PC:03F1   \n" +
                "03F2  C4 A3 05  CNZ 05A3    (0)  BC:12FF  DE:12FF  HL:12FF  AF:1242  SP:06BE  PC:03F2   \n" +
                "03F5  BC        CMP H       (0)  BC:12FF  DE:12FF  HL:12FF  AF:1242  SP:06BE  PC:03F5   \n" +
                "03F6  C4 A3 05  CNZ 05A3    (0)  BC:12FF  DE:12FF  HL:12FF  AF:1242  SP:06BE  PC:03F6   \n" +
                "03F9  3E FF     MVI A,FF    (0)  BC:12FF  DE:12FF  HL:12FF  AF:1242  SP:06BE  PC:03F9   \n" +
                "03FB  B9        CMP C       (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:03FB   \n" +
                "03FC  C4 A3 05  CNZ 05A3    (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:03FC   \n" +
                "03FF  BB        CMP E       (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:03FF   \n" +
                "0400  C4 A3 05  CNZ 05A3    (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:0400   \n" +
                "0403  BD        CMP L       (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:0403   \n" +
                "0404  C4 A3 05  CNZ 05A3    (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:0404   \n" +
                "0407  32 C0 05  STA 05C0    (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:0407   \n" +
                "040A  AF        XRA A       (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:040A   \n" +
                "040B  3A C0 05  LDA 05C0    (0)  BC:12FF  DE:12FF  HL:12FF  AF:0046  SP:06BE  PC:040B   \n" +
                "040E  FE FF     CPI FF      (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF46  SP:06BE  PC:040E   \n" +
                "0410  C4 A3 05  CNZ 05A3    (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:0410   \n" +
                "0413  2A BE 05  LHLD 05BE   (0)  BC:12FF  DE:12FF  HL:12FF  AF:FF42  SP:06BE  PC:0413   \n" +
                "0416  22 C0 05  SHLD 05C0   (0)  BC:12FF  DE:12FF  HL:05C0  AF:FF42  SP:06BE  PC:0416   \n" +
                "0419  3A BE 05  LDA 05BE    (0)  BC:12FF  DE:12FF  HL:05C0  AF:FF42  SP:06BE  PC:0419   \n" +
                "041C  47        MOV B,A     (0)  BC:12FF  DE:12FF  HL:05C0  AF:C042  SP:06BE  PC:041C   \n" +
                "041D  3A C0 05  LDA 05C0    (0)  BC:C0FF  DE:12FF  HL:05C0  AF:C042  SP:06BE  PC:041D   \n" +
                "0420  B8        CMP B       (0)  BC:C0FF  DE:12FF  HL:05C0  AF:C042  SP:06BE  PC:0420   \n" +
                "0421  C4 A3 05  CNZ 05A3    (0)  BC:C0FF  DE:12FF  HL:05C0  AF:C042  SP:06BE  PC:0421   \n" +
                "0424  3A BF 05  LDA 05BF    (0)  BC:C0FF  DE:12FF  HL:05C0  AF:C042  SP:06BE  PC:0424   \n" +
                "0427  47        MOV B,A     (0)  BC:C0FF  DE:12FF  HL:05C0  AF:0542  SP:06BE  PC:0427   \n" +
                "0428  3A C1 05  LDA 05C1    (0)  BC:05FF  DE:12FF  HL:05C0  AF:0542  SP:06BE  PC:0428   \n" +
                "042B  B8        CMP B       (0)  BC:05FF  DE:12FF  HL:05C0  AF:0542  SP:06BE  PC:042B   \n" +
                "042C  C4 A3 05  CNZ 05A3    (0)  BC:05FF  DE:12FF  HL:05C0  AF:0542  SP:06BE  PC:042C   \n" +
                "042F  3E AA     MVI A,AA    (0)  BC:05FF  DE:12FF  HL:05C0  AF:0542  SP:06BE  PC:042F   \n" +
                "0431  32 C0 05  STA 05C0    (0)  BC:05FF  DE:12FF  HL:05C0  AF:AA42  SP:06BE  PC:0431   \n" +
                "0434  44        MOV B,H     (0)  BC:05FF  DE:12FF  HL:05C0  AF:AA42  SP:06BE  PC:0434   \n" +
                "0435  4D        MOV C,L     (0)  BC:05FF  DE:12FF  HL:05C0  AF:AA42  SP:06BE  PC:0435   \n" +
                "0436  AF        XRA A       (0)  BC:05C0  DE:12FF  HL:05C0  AF:AA42  SP:06BE  PC:0436   \n" +
                "0437  0A        LDAX B      (0)  BC:05C0  DE:12FF  HL:05C0  AF:0046  SP:06BE  PC:0437   \n" +
                "0438  FE AA     CPI AA      (0)  BC:05C0  DE:12FF  HL:05C0  AF:AA46  SP:06BE  PC:0438   \n" +
                "043A  C4 A3 05  CNZ 05A3    (0)  BC:05C0  DE:12FF  HL:05C0  AF:AA42  SP:06BE  PC:043A   \n" +
                "043D  3C        INR A       (0)  BC:05C0  DE:12FF  HL:05C0  AF:AA42  SP:06BE  PC:043D   \n" +
                "043E  02        STAX B      (0)  BC:05C0  DE:12FF  HL:05C0  AF:AB82  SP:06BE  PC:043E   \n" +
                "043F  3A C0 05  LDA 05C0    (0)  BC:05C0  DE:12FF  HL:05C0  AF:AB82  SP:06BE  PC:043F   \n" +
                "0442  FE AB     CPI AB      (0)  BC:05C0  DE:12FF  HL:05C0  AF:AB82  SP:06BE  PC:0442   \n" +
                "0444  C4 A3 05  CNZ 05A3    (0)  BC:05C0  DE:12FF  HL:05C0  AF:AB42  SP:06BE  PC:0444   \n" +
                "0447  3E 77     MVI A,77    (0)  BC:05C0  DE:12FF  HL:05C0  AF:AB42  SP:06BE  PC:0447   \n" +
                "0449  32 C0 05  STA 05C0    (0)  BC:05C0  DE:12FF  HL:05C0  AF:7742  SP:06BE  PC:0449   \n" +
                "044C  2A BE 05  LHLD 05BE   (0)  BC:05C0  DE:12FF  HL:05C0  AF:7742  SP:06BE  PC:044C   \n" +
                "044F  11 00 00  LXI D,0000  (0)  BC:05C0  DE:12FF  HL:05C0  AF:7742  SP:06BE  PC:044F   \n" +
                "0452  EB        XCHG        (0)  BC:05C0  DE:0000  HL:05C0  AF:7742  SP:06BE  PC:0452   \n" +
                "0453  AF        XRA A       (0)  BC:05C0  DE:05C0  HL:0000  AF:7742  SP:06BE  PC:0453   \n" +
                "0454  1A        LDAX D      (0)  BC:05C0  DE:05C0  HL:0000  AF:0046  SP:06BE  PC:0454   \n" +
                "0455  FE 77     CPI 77      (0)  BC:05C0  DE:05C0  HL:0000  AF:7746  SP:06BE  PC:0455   \n" +
                "0457  C4 A3 05  CNZ 05A3    (0)  BC:05C0  DE:05C0  HL:0000  AF:7742  SP:06BE  PC:0457   \n" +
                "045A  AF        XRA A       (0)  BC:05C0  DE:05C0  HL:0000  AF:7742  SP:06BE  PC:045A   \n" +
                "045B  84        ADD H       (0)  BC:05C0  DE:05C0  HL:0000  AF:0046  SP:06BE  PC:045B   \n" +
                "045C  85        ADD L       (0)  BC:05C0  DE:05C0  HL:0000  AF:0042  SP:06BE  PC:045C   \n" +
                "045D  C4 A3 05  CNZ 05A3    (0)  BC:05C0  DE:05C0  HL:0000  AF:0042  SP:06BE  PC:045D   \n" +
                "0460  3E CC     MVI A,CC    (0)  BC:05C0  DE:05C0  HL:0000  AF:0042  SP:06BE  PC:0460   \n" +
                "0462  12        STAX D      (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:0462   \n" +
                "0463  3A C0 05  LDA 05C0    (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:0463   \n" +
                "0466  FE CC     CPI CC      (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:0466   \n" +
                "0468  12        STAX D      (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:0468   \n" +
                "0469  3A C0 05  LDA 05C0    (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:0469   \n" +
                "046C  FE CC     CPI CC      (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:046C   \n" +
                "046E  C4 A3 05  CNZ 05A3    (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:046E   \n" +
                "0471  21 77 77  LXI H,7777  (0)  BC:05C0  DE:05C0  HL:0000  AF:CC42  SP:06BE  PC:0471   \n" +
                "0474  29        DAD H       (0)  BC:05C0  DE:05C0  HL:7777  AF:CC42  SP:06BE  PC:0474   \n" +
                "0475  3E EE     MVI A,EE    (0)  BC:05C0  DE:05C0  HL:EEEE  AF:CC42  SP:06BE  PC:0475   \n" +
                "0477  BC        CMP H       (0)  BC:05C0  DE:05C0  HL:EEEE  AF:EE42  SP:06BE  PC:0477   \n" +
                "0478  C4 A3 05  CNZ 05A3    (0)  BC:05C0  DE:05C0  HL:EEEE  AF:EE42  SP:06BE  PC:0478   \n" +
                "047B  BD        CMP L       (0)  BC:05C0  DE:05C0  HL:EEEE  AF:EE42  SP:06BE  PC:047B   \n" +
                "047C  C4 A3 05  CNZ 05A3    (0)  BC:05C0  DE:05C0  HL:EEEE  AF:EE42  SP:06BE  PC:047C   \n" +
                "047F  21 55 55  LXI H,5555  (0)  BC:05C0  DE:05C0  HL:EEEE  AF:EE42  SP:06BE  PC:047F   \n" +
                "0482  01 FF FF  LXI B,FFFF  (0)  BC:05C0  DE:05C0  HL:5555  AF:EE42  SP:06BE  PC:0482   \n" +
                "0485  09        DAD B       (0)  BC:FFFF  DE:05C0  HL:5555  AF:EE42  SP:06BE  PC:0485   \n" +
                "0486  3E 55     MVI A,55    (0)  BC:FFFF  DE:05C0  HL:5554  AF:EE43  SP:06BE  PC:0486   \n" +
                "0488  D4 A3 05  CNC 05A3    (0)  BC:FFFF  DE:05C0  HL:5554  AF:5543  SP:06BE  PC:0488   \n" +
                "048B  BC        CMP H       (0)  BC:FFFF  DE:05C0  HL:5554  AF:5543  SP:06BE  PC:048B   \n" +
                "048C  C4 A3 05  CNZ 05A3    (0)  BC:FFFF  DE:05C0  HL:5554  AF:5542  SP:06BE  PC:048C   \n" +
                "048F  3E 54     MVI A,54    (0)  BC:FFFF  DE:05C0  HL:5554  AF:5542  SP:06BE  PC:048F   \n" +
                "0491  BD        CMP L       (0)  BC:FFFF  DE:05C0  HL:5554  AF:5442  SP:06BE  PC:0491   \n" +
                "0492  C4 A3 05  CNZ 05A3    (0)  BC:FFFF  DE:05C0  HL:5554  AF:5442  SP:06BE  PC:0492   \n" +
                "0495  21 AA AA  LXI H,AAAA  (0)  BC:FFFF  DE:05C0  HL:5554  AF:5442  SP:06BE  PC:0495   \n" +
                "0498  11 33 33  LXI D,3333  (0)  BC:FFFF  DE:05C0  HL:AAAA  AF:5442  SP:06BE  PC:0498   \n" +
                "049B  19        DAD D       (0)  BC:FFFF  DE:3333  HL:AAAA  AF:5442  SP:06BE  PC:049B   \n" +
                "049C  3E DD     MVI A,DD    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5442  SP:06BE  PC:049C   \n" +
                "049E  BC        CMP H       (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD42  SP:06BE  PC:049E   \n" +
                "049F  C4 A3 05  CNZ 05A3    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD42  SP:06BE  PC:049F   \n" +
                "04A2  BD        CMP L       (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD42  SP:06BE  PC:04A2   \n" +
                "04A3  C4 A3 05  CNZ 05A3    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD42  SP:06BE  PC:04A3   \n" +
                "04A6  37        STC         (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD42  SP:06BE  PC:04A6   \n" +
                "04A7  D4 A3 05  CNC 05A3    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD43  SP:06BE  PC:04A7   \n" +
                "04AA  3F        CMC         (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD43  SP:06BE  PC:04AA   \n" +
                "04AB  DC A3 05  CC 05A3     (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD42  SP:06BE  PC:04AB   \n" +
                "04AE  3E AA     MVI A,AA    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:DD42  SP:06BE  PC:04AE   \n" +
                "04B0  2F        CMA         (0)  BC:FFFF  DE:3333  HL:DDDD  AF:AA42  SP:06BE  PC:04B0   \n" +
                "04B1  FE 55     CPI 55      (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5552  SP:06BE  PC:04B1   \n" +
                "04B3  C4 A3 05  CNZ 05A3    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5542  SP:06BE  PC:04B3   \n" +
                "04B6  B7        ORA A       (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5542  SP:06BE  PC:04B6   \n" +
                "04B7  27        DAA         (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5506  SP:06BE  PC:04B7   \n" +
                "04B8  FE 55     CPI 55      (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5506  SP:06BE  PC:04B8   \n" +
                "04BA  C4 A3 05  CNZ 05A3    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5542  SP:06BE  PC:04BA   \n" +
                "04BD  3E 88     MVI A,88    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:5542  SP:06BE  PC:04BD   \n" +
                "04BF  87        ADD A       (0)  BC:FFFF  DE:3333  HL:DDDD  AF:8842  SP:06BE  PC:04BF   \n" +
                "04C0  27        DAA         (0)  BC:FFFF  DE:3333  HL:DDDD  AF:1017  SP:06BE  PC:04C0   \n" +
                "04C1  FE 76     CPI 76      (0)  BC:FFFF  DE:3333  HL:DDDD  AF:AA97  SP:06BE  PC:04C1   \n" +
                "04C3  C4 A3 05  CNZ 05A3    (0)  BC:FFFF  DE:3333  HL:DDDD  AF:AA06  SP:06BE  PC:04C3   ");
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
        asrtCpu("BC:  FF36\n" +
                "DE:  3333\n" +
                "HL:  04C6\n" +
                "AF:  3606\n" +
                "SP:  06BC\n" +
                "PC:  C800\n" +
                "B,C: FF 36\n" +
                "D,E: 33 33\n" +
                "H,L: 04 C6\n" +
                "M:   36\n" +
                "A,F: 36 06\n" +
                "     76543210 76543210\n" +
                "SP:  00000110 10111100\n" +
                "PC:  11001000 00000000\n" +
                "     76543210\n" +
                "B:   11111111\n" +
                "C:   00110110\n" +
                "D:   00110011\n" +
                "E:   00110011\n" +
                "H:   00000100\n" +
                "L:   11000110\n" +
                "M:   00110110\n" +
                "A:   00110110\n" +
                "     sz0h0p1c\n" +
                "F:   00000110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
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
