package spec.image;

import org.junit.Test;
import spec.stuff.AbstractTest;

import static org.junit.Assert.*;

public class PngScreenToTextTest extends AbstractTest {

    @Test
    public void testPngScreenToText() {
        assertScreen("/IntegrationTest/testLik/smoke/7_exit.png",
                "        0   1   2   3   4   5   6   7     01234567\n" +
                "        8   9   A   B   C   D   E   F     89ABCDEF\n" +
                "9000:   00  00  00  00  00  00  00  00    ........\n" +
                "9008:   00  00  00  00  00  00  00  00    ........\n" +
                "9010:   00  00  00  00  00  00  00  00    ........\n" +
                "9018:   00  00  00  00  00  00  00  38    .......8\n" +
                "9020:   45  45  3D  05  09  70  00  00    EE=..П..\n" +
                "9028:   00  38  45  45  3D  05  09  70    .8EE=..П\n" +
                "9030:   00  00  00  38  45  45  3D  05    ...8EE=.\n" +
                "9038:   09  70  00  00  00  38  45  45    .П...8EE\n" +
                "9040:   3D  05  09  70  00  00  00  38    =..П...8\n" +
                "9048:   45  45  3D  05  09  70  00  00    EE=..П..\n" +
                "9050:   00  38  45  45  3D  05  09  70    .8EE=..П\n" +
                "9058:   00  00  00  38  45  45  3D  05    ...8EE=.\n" +
                "9060:   09  70  00  00  00  38  45  45    .П...8EE\n" +
                "9068:   3D  05  09  70  00  00  00  38    =..П...8\n" +
                "9070:   45  45  3D  05  09  70  00  00    EE=..П..\n" +
                "9078:   00  38  45  45  3D  05  09  70    .8EE=..П\n" +
                "===>\n" +
                "* МОНИТОР-1M *\n" +
                "===>█");
    }

    private void assertScreen(String s, String expected) {
        String text = scanner.parse(TEST_RESOURCES + s);
        assertEquals(expected, text);
    }
}