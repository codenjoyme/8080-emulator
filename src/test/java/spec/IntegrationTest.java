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

import static spec.Constants.START_POINT;
import static spec.KeyCode.*;

public class IntegrationTest extends AbstractCpuTest {

    private static final int TICKS = 10_000_000;

    private static final String TEST_RESOURCES = "src/test/resources/";
    private static final String APP_RESOURCES = "src/main/resources/";

    @Rule
    public TestName test = new TestName();

    private RomLoader roms;
    private IOPorts ports;
    private PngVideo video;
    private KeyRecord record;

    private int tick;
    private int maxTicks;
    private URL base;

    @Before
    public void before() throws Exception {
        super.before();
        roms = new RomLoader(data.memory(), cpu);
        ports = new IOPorts(data.memory(), new Layout(), cpu::tick);
        video = new PngVideo(data.memory());
        data.ports(ports);
        base = new File(APP_RESOURCES).toURI().toURL();
        maxTicks = TICKS;
        record = new KeyRecord(ports, this::screenShoot, data::cpuOff);
        removeTestScreenShots();
        reset();
    }

    private void reset() {
        record.reset();
        ports.reset();
        tick = 0;
        data.cpuOn();
    }

    private void removeTestScreenShots() {
        File dir = testDir();
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.getName().endsWith(".png")) { // на всякий случай
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
        video.drawToFile(testDir().getAbsolutePath() + "/" + name + ".png");
    }

    private File testDir() {
        File result = new File(TEST_RESOURCES + test.getMethodName());
        if (!result.exists()) {
            result.mkdir();
        }
        return result;
    }

    @Override
    protected void interrupt() {
        if (++tick >= maxTicks) {
            data.cpuOff();
        }
        record.accept(tick);
    }

    @Test
    public void testLik_smoke() {
        // given
        Lik.loadRom(base, roms);

        // when
        record.shoot("runcom", it -> it.after(2))
                .shoot("stop", it -> it.press(END).after(2))
                .shoot("monitor", it -> it.press(ENTER).after(5))
                .shoot("assembler", it -> it.enter("AC000").press(ENTER).after(20))
                .shoot("exit", it -> it.down(ENTER).after(5).press(END).after(5).up(ENTER).after(15))
                .shoot("memory", it -> it.enter("D9000").press(ENTER).after(30))
                .shoot("exit", it -> it.press(ESC).after(5))
                .shoot("basic", it -> it.enter("B").press(ENTER).after(10))
                .stopCpu();

        cpu.PC(START_POINT);
        cpu.execute();
    }

    @Test
    public void testLik_klad() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when then
        record.shoot("logo", it -> it.after(200))
                .shoot("logo", it -> it.after(170))
                .shoot("speed", it -> it.after(50))
                .stopCpu();

        cpu.PC(0x0000);
        cpu.execute();

        int LEVELS = 32;
        for (int level = 1; level < LEVELS; level++) {
            // when then
            reset();

            // скорость выбранная в первый раз поменяла тайминги
            int speed = (level == 1) ? 60 : 60 + 13;
            record.shoot(null,
                            it -> it.after(50))
                    .shoot(null,
                            it -> it.down(RIGHT).after(speed).up(RIGHT).after(1))
                    .shoot("level[" + level + "]",
                            it -> it.down(UP).after(30).up(UP).after(170))
                    .stopCpu();

            // этот хак позволяет запускать игру со следующим уровенем
            cpu.PC(0x4567);
            cpu.execute();
        }
    }

    @Test
    public void testSpecialist_monitor() {
        // given
        Specialist.loadRom(base, roms);

        // when
        cpu.PC(START_POINT);
        cpu.execute();

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
        cpu.execute();

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
        cpu.execute();

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
    public void testDiagnostic_case1() {
        // 8080/8085 CPU Diagnostic, version 1.0, by Microcosm Associates
        // https://github.com/begoon/i8080-core/blob/master/TEST.ASM

        // given
        Lik.loadRom(base, roms);
        maxTicks = roms.loadROM(base, "test/test.com", 0x0000) * 10;

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  C0F0\n" +
                "DE:  2800\n" +
                "HL:  C000\n" +
                "AF:  0002\n" +
                "SP:  FFEC\n" +
                "PC:  743E\n" +
                "B,C: C0 F0\n" +
                "D,E: 28 00\n" +
                "H,L: C0 00\n" +
                "M:   C3\n" +
                "A,F: 00 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11101100\n" +
                "PC:  01110100 00111110\n" +
                "     76543210\n" +
                "B:   11000000\n" +
                "C:   11110000\n" +
                "D:   00101000\n" +
                "E:   00000000\n" +
                "H:   11000000\n" +
                "L:   00000000\n" +
                "M:   11000011\n" +
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
    public void testLik_helloWorld() {
        // given
        Lik.loadRom(base, roms);
        maxTicks = roms.loadROM(base, "test/hello_world.com", 0x0000) * 1000;

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  C0C0\n" +
                "DE:  0000\n" +
                "HL:  BFFD\n" +
                "AF:  8242\n" +
                "SP:  FFA8\n" +
                "PC:  9773\n" +
                "B,C: C0 C0\n" +
                "D,E: 00 00\n" +
                "H,L: BF FD\n" +
                "M:   00\n" +
                "A,F: 82 42\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 10101000\n" +
                "PC:  10010111 01110011\n" +
                "     76543210\n" +
                "B:   11000000\n" +
                "C:   11000000\n" +
                "D:   00000000\n" +
                "E:   00000000\n" +
                "H:   10111111\n" +
                "L:   11111101\n" +
                "M:   00000000\n" +
                "A:   10000010\n" +
                "     sz0h0p1c\n" +
                "F:   01000010\n" +
                "ts:  false\n" +
                "tz:  true\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");

        screenShoot();
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
        cpu.execute();

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
        cpu.execute();

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
        cpu.execute();

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
