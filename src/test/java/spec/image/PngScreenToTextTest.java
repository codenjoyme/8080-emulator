package spec.image;

import org.junit.Test;
import spec.math.Bites;
import spec.stuff.AbstractTest;

import java.io.File;
import java.util.stream.IntStream;

import static spec.Constants.START_POINT;
import static spec.KeyCode.END;
import static spec.KeyCode.ENTER;
import static spec.stuff.SmartAssert.assertEquals;

public class PngScreenToTextTest extends AbstractTest {

    @Test
    public void testAlphabetically() {
        // given
        lik().loadRom(base, roms);

        memory.all().set(0x1000, Bites.of(
                "31  32  33  34  35  36  37  38\n" +
                "39  30  00  00  00  00  00  00\n" +
                "41  42  43  44  45  46  47  48\n" +
                "49  4A  4B  4C  4D  4E  4F  50\n" +
                "51  52  53  54  55  56  57  58\n" +
                "59  5A  00  00  00  00  00  00\n" +
                "61  62  77  67  64  65  76  7A\n" +
                "69  6A  6B  6C  6D  6E  6F  70\n" +
                "72  73  74  75  66  68  63  7E\n" +
                "78  79  7C  60  71  00  00  00\n" +
                "21  22  23  24  25  26  27  28\n" +
                "29  2D  3D  2B  3B  3A  5B  5D\n" +
                "5E  2A  2F  3F  3C  2C  2E  3E\n" +
                "5F  5C  40  00  00  00  00  00\n" +
                "00  00  00  00  00  00  00  00\n" +
                "00  00  00  00  00  00  00  00"));

        // when then
        assertParseDraw(showMemoryScreen("D1000", "memory"));
    }

    @Test
    public void testAllChars() {
        // given
        lik().loadRom(base, roms);

        memory.all().set(0x1000, Bites.of(
                IntStream.iterate(0, i -> i + 1)
                        .limit(256)
                        .toArray()));

        // when then
        assertParseDraw(showMemoryScreen("D1000", "memory-page1"));

        // when then
        assertParseDraw(showMemoryScreen("D1080", "memory-page2"));
    }

    private void assertParseDraw(String name) {
        String sourceFile = pngPath(name);

        String text = assertFromPng(sourceFile);
        String file = assertToPng(text, new File(sourceFile).getName().replace(".png", "_2.png"));
        assertScreen(file, text);
    }

    private String showMemoryScreen(String command, String name) {
        record.reset()
                .shoot("runcom", it -> it.after(2 * K10))
                .shoot("stop", it -> it.press(END).after(2 * K10))
                .shoot("monitor", it -> it.press(ENTER).after(5 * K10))
                .shoot(name, it -> it.enter(command).press(ENTER).after(30 * K10))
                .stopCpu();

        cpu.reset();
        cpu.PC(START_POINT);
        start();

        return name;
    }

    private String assertToPng(String text, String name) {
        String fileName = testBase() + "/" + name;
        fileAssert.check("Screenshot", fileName,
                file -> {
                    scanner.draw(text, file.getAbsolutePath());
                    return null;
                });
        return fileName;
    }

    private void assertScreen(String file, String expected) {
        String text = scanner.parse(file);
        assertEquals(expected, text);
    }
}