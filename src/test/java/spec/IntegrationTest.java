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

public class IntegrationTest extends AbstractCpuTest {

    public static final int TICKS = 10_000_000;

    @Rule
    public TestName test = new TestName();

    private RomLoader roms;
    private int ticks;
    private int maxTicks;
    private PngVideo video;
    private URL base;

    @Before
    public void before() throws Exception {
        super.before();
        roms = new RomLoader(data.memory(), cpu);
        video = new PngVideo(data.memory());
        base = new File("src/main/resources/").toURI().toURL();
    }

    @After
    public void after() throws Exception {
        video.drawToFile(new File("src/test/resources/"
                + test.getMethodName() + ".png"));
        SmartAssert.checkResult();
    }

    @Override
    protected void onInterrupt() {
        if (++ticks >= maxTicks) {
            data.stopCpu();
        }
    }

    @Test
    public void testLik_runCom() throws Exception {
        // given
        maxTicks = TICKS;

        Lik.loadRom(base, roms);

        // when
        cpu.PC(START_POINT);
        cpu.execute();

        // then
        asrtCpu("BC:  90FF\n" +
                "DE:  FF00\n" +
                "HL:  18A0\n" +
                "AF:  0313\n" +
                "SP:  7FE5\n" +
                "PC:  C254\n" +
                "B,C: 90 FF\n" +
                "D,E: FF 00\n" +
                "H,L: 18 A0\n" +
                "M:   00\n" +
                "A,F: 03 13\n" +
                "     76543210 76543210\n" +
                "SP:  01111111 11100101\n" +
                "PC:  11000010 01010100\n" +
                "     76543210\n" +
                "B:   10010000\n" +
                "C:   11111111\n" +
                "D:   11111111\n" +
                "E:   00000000\n" +
                "H:   00011000\n" +
                "L:   10100000\n" +
                "M:   00000000\n" +
                "A:   00000011\n" +
                "     sz0h0p1c\n" +
                "F:   00010011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void testLik_klad() throws Exception {
        // given
        maxTicks = TICKS;

        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  0100\n" +
                "DE:  0700\n" +
                "HL:  0148\n" +
                "AF:  0193\n" +
                "SP:  3FF5\n" +
                "PC:  C427\n" +
                "B,C: 01 00\n" +
                "D,E: 07 00\n" +
                "H,L: 01 48\n" +
                "M:   E1\n" +
                "A,F: 01 93\n" +
                "     76543210 76543210\n" +
                "SP:  00111111 11110101\n" +
                "PC:  11000100 00100111\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00000000\n" +
                "D:   00000111\n" +
                "E:   00000000\n" +
                "H:   00000001\n" +
                "L:   01001000\n" +
                "M:   11100001\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   10010011\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void testSpecialist_monitor() throws Exception {
        // given
        maxTicks = TICKS;

        Specialist.loadRom(base, roms);

        // when
        cpu.PC(START_POINT);
        cpu.execute();

        // then
        asrtCpu("BC:  8FFF\n" +
                "DE:  8F60\n" +
                "HL:  C196\n" +
                "AF:  8213\n" +
                "SP:  8F36\n" +
                "PC:  C256\n" +
                "B,C: 8F FF\n" +
                "D,E: 8F 60\n" +
                "H,L: C1 96\n" +
                "M:   C5\n" +
                "A,F: 82 13\n" +
                "     76543210 76543210\n" +
                "SP:  10001111 00110110\n" +
                "PC:  11000010 01010110\n" +
                "     76543210\n" +
                "B:   10001111\n" +
                "C:   11111111\n" +
                "D:   10001111\n" +
                "E:   01100000\n" +
                "H:   11000001\n" +
                "L:   10010110\n" +
                "M:   11000101\n" +
                "A:   10000010\n" +
                "     sz0h0p1c\n" +
                "F:   00010011\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  false\n" +
                "tc:  true\n");
    }

    @Test
    public void testSpecialist_blobcop() throws Exception {
        // given
        maxTicks = TICKS;

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
    }

    @Test
    public void testSpecialist_babnik() throws Exception {
        // given
        maxTicks = TICKS;

        Specialist.loadRom(base, roms);
        Specialist.loadGame(base, roms, "babnik");

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  0108\n" +
                "DE:  0E5A\n" +
                "HL:  ADB0\n" +
                "AF:  0482\n" +
                "SP:  3FEB\n" +
                "PC:  052D\n" +
                "B,C: 01 08\n" +
                "D,E: 0E 5A\n" +
                "H,L: AD B0\n" +
                "M:   FF\n" +
                "A,F: 04 82\n" +
                "     76543210 76543210\n" +
                "SP:  00111111 11101011\n" +
                "PC:  00000101 00101101\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00001000\n" +
                "D:   00001110\n" +
                "E:   01011010\n" +
                "H:   10101101\n" +
                "L:   10110000\n" +
                "M:   11111111\n" +
                "A:   00000100\n" +
                "     sz0h0p1c\n" +
                "F:   10000010\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void testDiagnostic_case1() throws Exception {
        // 8080/8085 CPU Diagnostic, version 1.0, by Microcosm Associates
        // https://github.com/begoon/i8080-core/blob/master/TEST.ASM

        // given
        Lik.loadRom(base, roms);
        maxTicks = roms.loadROM(base, "test/test.com", 0x0000) * 10;

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  0109\n" +
                "DE:  000F\n" +
                "HL:  4D0F\n" +
                "AF:  4D02\n" +
                "SP:  0102\n" +
                "PC:  4E74\n" +
                "B,C: 01 09\n" +
                "D,E: 00 0F\n" +
                "H,L: 4D 0F\n" +
                "M:   4D\n" +
                "A,F: 4D 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000001 00000010\n" +
                "PC:  01001110 01110100\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00001001\n" +
                "D:   00000000\n" +
                "E:   00001111\n" +
                "H:   01001101\n" +
                "L:   00001111\n" +
                "M:   01001101\n" +
                "A:   01001101\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void testLik_helloWorld() throws Exception {
        // given
        Lik.loadRom(base, roms);
        maxTicks = roms.loadROM(base, "test/hello_world.com", 0x0000) * 1000;

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  0109\n" +
                "DE:  000F\n" +
                "HL:  4D0F\n" +
                "AF:  4D02\n" +
                "SP:  0102\n" +
                "PC:  4E74\n" +
                "B,C: 01 09\n" +
                "D,E: 00 0F\n" +
                "H,L: 4D 0F\n" +
                "M:   4D\n" +
                "A,F: 4D 02\n" +
                "     76543210 76543210\n" +
                "SP:  00000001 00000010\n" +
                "PC:  01001110 01110100\n" +
                "     76543210\n" +
                "B:   00000001\n" +
                "C:   00001001\n" +
                "D:   00000000\n" +
                "E:   00001111\n" +
                "H:   01001101\n" +
                "L:   00001111\n" +
                "M:   01001101\n" +
                "A:   01001101\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void testDiagnostic_case2() throws Exception {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The basic excerciser
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080EX1.MAC

        // given
        maxTicks = TICKS;

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
    }

    @Test
    public void testDiagnostic_case3() throws Exception {
        // 8080/8085 CPU Exerciser by Ian Bartholomew and Frank Cringles
        // The preliminary test
        // https://raw.githubusercontent.com/begoon/i8080-core/master/8080PRE.MAC

        // given
        maxTicks = TICKS;

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
    }

    @Test
    public void testDiagnostic_case4() throws Exception {
        // Diagnostics II, version 1.2, CPU test by Supersoft Associates
        // https://raw.githubusercontent.com/begoon/i8080-core/master/CPUTEST.MAC

        // given
        maxTicks = TICKS;

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
    }
}
