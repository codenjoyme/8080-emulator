package spec.image;

import org.junit.Test;
import spec.stuff.AbstractTest;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testTextToPngScreen() {
        // given
        String text =
                "        0   1   2   3   4   5   6   7     01234567\n" +
                "        8   9   A   B   C   D   E   F     89ABCDEF\n" +
                "9000:   FF  FF  FF  FF  FF  FF  FF  FF    QWERTYUI\n" +
                "9008:   00  00  00  00  00  00  00  00    ASDFGHJK\n" +
                "9010:   FF  FF  FF  FF  FF  FF  FF  FF    ZXCVBNM.\n" +
                "9018:   00  00  00  00  00  00  00  00    ЙЦУКЕНГШ\n" +
                "9020:   FF  FF  FF  FF  FF  FF  FF  FF    ЩЗХФЫВАП\n" +
                "9028:   00  00  00  00  00  00  00  00    РОЛДЖЭЯЧ\n" +
                "9030:   FF  FF  FF  FF  FF  FF  FF  FF    СМИТЬБЮ.\n" +
                "9038:   00  00  00  00  00  00  00  00    12345678\n" +
                "9040:   FF  FF  FF  FF  FF  FF  FF  FF    90!\"#$%\n" +
                "9048:   00  00  00  00  00  00  00  00    ^&*()-=+\n" +
                "9050:   FF  FF  FF  FF  FF  FF  FF  FF    []'\\/.,\n" +
                "9058:   00  00  00  00  00  00  00  00    ><?:;...\n" +
                "9060:   FF  FF  FF  FF  FF  FF  FF  FF    ЁЪ█ ....\n" +
                "9068:   00  00  00  00  00  00  00  00    ........\n" +
                "9070:   FF  FF  FF  FF  FF  FF  FF  FF    ........\n" +
                "9078:   00  00  00  00  00  00  00  00    ........\n" +
                "===>\n" +
                "* МОНИТОР-1M *\n" +
                "===>█";

        // when
        assertPng(text);

        // then
        assertScreen(getTestResultFolder() + "/screen.png", text);
    }

    private void assertPng(String text) {
        fileAssert.check("Screenshot", testBase() + "/screen.png",
                file -> {
                    scanner.drawPng(text, file.getAbsolutePath());
                    return null;
                });
    }

    private void assertScreen(String file, String expected) {
        String text = scanner.parse(TEST_RESOURCES + file);
        assertEquals(expected, text);
    }
}