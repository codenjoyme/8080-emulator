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

    public static final int MAX_TICKS = 10_000_000;
    @Rule
    public TestName test = new TestName();

    private RomLoader roms;
    private int ticks;
    private int maxTicks;
    private PngVideo video;

    @Before
    public void before() {
        super.before();
        roms = new RomLoader(data.memory(), cpu);
        video = new PngVideo(data.memory());
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
        maxTicks = MAX_TICKS;

        URL base = new File("src/main/resources/").toURI().toURL();
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
        maxTicks = MAX_TICKS;

        URL base = new File("src/main/resources/").toURI().toURL();
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
        maxTicks = MAX_TICKS;

        URL base = new File("src/main/resources/").toURI().toURL();
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
        maxTicks = MAX_TICKS;

        URL base = new File("src/main/resources/").toURI().toURL();
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
}
