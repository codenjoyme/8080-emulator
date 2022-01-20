package spec;

import org.junit.Before;
import org.junit.Test;
import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.io.File;
import java.net.URL;

import static spec.Constants.START_POINT;

public class IntegrationTest extends AbstractCpuTest {

    public RomLoader roms;
    public int ticks;
    public int maxTicks;

    @Before
    public void before() {
        super.before();
        roms = new RomLoader(data.memory(), cpu);
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
        maxTicks = 100_000;

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
        maxTicks = 100_000;

        URL base = new File("src/main/resources/").toURI().toURL();
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  4360\n" +
                "DE:  9276\n" +
                "HL:  0800\n" +
                "AF:  0102\n" +
                "SP:  FFFA\n" +
                "PC:  4138\n" +
                "B,C: 43 60\n" +
                "D,E: 92 76\n" +
                "H,L: 08 00\n" +
                "M:   21\n" +
                "A,F: 01 02\n" +
                "     76543210 76543210\n" +
                "SP:  11111111 11111010\n" +
                "PC:  01000001 00111000\n" +
                "     76543210\n" +
                "B:   01000011\n" +
                "C:   01100000\n" +
                "D:   10010010\n" +
                "E:   01110110\n" +
                "H:   00001000\n" +
                "L:   00000000\n" +
                "M:   00100001\n" +
                "A:   00000001\n" +
                "     sz0h0p1c\n" +
                "F:   00000010\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  false\n" +
                "tc:  false\n");
    }

    @Test
    public void testSpecialist_monitor() throws Exception {
        // given
        maxTicks = 100_000;

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
        maxTicks = 100_000;

        URL base = new File("src/main/resources/").toURI().toURL();
        Specialist.loadRom(base, roms);
        Specialist.loadGame(base, roms, "blobcop");

        // when
        cpu.PC(0x0000);
        cpu.execute();

        // then
        asrtCpu("BC:  7CC7\n" +
                "DE:  F77F\n" +
                "HL:  F79E\n" +
                "AF:  EF87\n" +
                "SP:  8F9B\n" +
                "PC:  0FA9\n" +
                "B,C: 7C C7\n" +
                "D,E: F7 7F\n" +
                "H,L: F7 9E\n" +
                "M:   00\n" +
                "A,F: EF 87\n" +
                "     76543210 76543210\n" +
                "SP:  10001111 10011011\n" +
                "PC:  00001111 10101001\n" +
                "     76543210\n" +
                "B:   01111100\n" +
                "C:   11000111\n" +
                "D:   11110111\n" +
                "E:   01111111\n" +
                "H:   11110111\n" +
                "L:   10011110\n" +
                "M:   00000000\n" +
                "A:   11101111\n" +
                "     sz0h0p1c\n" +
                "F:   10000111\n" +
                "ts:  true\n" +
                "tz:  false\n" +
                "th:  false\n" +
                "tp:  true\n" +
                "tc:  true\n");
    }
}
