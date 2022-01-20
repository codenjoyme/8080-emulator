package spec;

import org.junit.Before;
import org.junit.Test;
import spec.platforms.Lik;

import java.io.File;
import java.net.URL;

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
    public void testKlad() throws Exception {
        // given
        maxTicks = 100_000;

        URL base = new File("src/main/resources/").toURI().toURL();
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // when
        cpu.PC(0000);
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
}
