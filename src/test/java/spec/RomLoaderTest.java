package spec;

import org.junit.Before;
import org.junit.Test;
import spec.math.Bites;
import spec.platforms.Lik;
import spec.platforms.Specialist;
import spec.stuff.AbstractTest;
import spec.stuff.FileAssert;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static spec.stuff.SmartAssert.assertEquals;
import static spec.stuff.SmartAssert.assertNotSame;

public class RomLoaderTest extends AbstractTest {

    private AtomicLong time = new AtomicLong(1733699155599L);

    @Test
    public void testLoadLikRom() {
        // when
        Lik.loadRom(base, roms);

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadLikGame() {
        // when
        Lik.loadGame(base, roms, "reversi");

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadSpecialistRom() {
        // when
        Specialist.loadRom(base, roms);

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadSpecialistGame() {
        // when
        Specialist.loadGame(base, roms, "babnik");

        // then
        assertMemoryChanges();
    }

    @Override
    protected Timings createTimings(Hardware hard) {
        return new Timings(hard){
            @Override
            protected long now() {
                return time.addAndGet(1234);
            }
        };
    }

    @Override
    protected GraphicControl createGraphicControl(Container inputParent) {
        Container parent = mock(Container.class);
        when(parent.getKeyListeners()).thenReturn(new KeyListener[0]);
        Image image = mock(Image.class);
        when(parent.createImage(anyInt(), anyInt())).thenReturn(image);
        when(image.getGraphics()).thenReturn(mock(Graphics.class));

        graphic = new GraphicControl(parent);
        return graphic;
    }

    @Test
    public void testSaveLoadSnapshots() {
        // given
        Lik.loadRom(base, roms);
        Lik.loadGame(base, roms, "klad");

        // random values
        cpu.AF(0x1234);
        cpu.BC(0x5678);
        cpu.DE(0x9ABC);
        cpu.HL(0xDEF0);
        cpu.PC(0x2345);
        cpu.SP(0x6789);

        cpu.tact = 0x12345678;
        cpu.tick = 0x1ABCDEF0;
        cpu.interrupt = 0x1216;

        ports.reset();
        ports.state(new Bites(new int[]{
                0b1010_0101,
                0b1100_0011,
                0b1001_0110,
                0b1101_1011,
                0b1111_0000,
                0b1000_0001,
                0b0100_0010,
                0b0001_1000,
                0b0000_1111,
                0b1011_1101,
                0b0110_1001,
                0b0101_1010,
                0b1010_0101 // flags
        }));

        graphic.nextDrawMode();
        graphic.nextDrawMode();
        graphic.nextDrawMode();

        timings.changeFullSpeed();
        timings.decreaseDelay();
        for (int i = 0; i < 1234567; i++) {
            timings.updateState();
            if (i % 3 == 1) {
                timings.sleep();
            }
            if (i % 3 == 0) {
                timings.profiling();
            }
        }

        romSwitcher.lik = false;

        // then
        String expectedCpu =
                "tick:      448585456\n" +
                "tact:      305419896\n" +
                "interrupt: 4630\n" +
                "BC:  5678\n" +
                "DE:  9ABC\n" +
                "HL:  DEF0\n" +
                "AF:  1216\n" +
                "SP:  6789\n" +
                "PC:  2345\n" +
                "B,C: 56 78\n" +
                "D,E: 9A BC\n" +
                "H,L: DE F0\n" +
                "M:   CD\n" +
                "A,F: 12 16\n" +
                "     76543210 76543210\n" +
                "SP:  01100111 10001001\n" +
                "PC:  00100011 01000101\n" +
                "     76543210\n" +
                "B:   01010110\n" +
                "C:   01111000\n" +
                "D:   10011010\n" +
                "E:   10111100\n" +
                "H:   11011110\n" +
                "L:   11110000\n" +
                "M:   11001101\n" +
                "A:   00010010\n" +
                "     sz0h0p1c\n" +
                "F:   00010110\n" +
                "ts:  false\n" +
                "tz:  false\n" +
                "th:  true\n" +
                "tp:  true\n" +
                "tc:  false\n";

        String expectedPorts =
                "Ain   : +\n" +
                "Bin   : +\n" +
                "C0in  : -\n" +
                "C1in  : +\n" +
                "\n" +
                "shift : -\n" +
                "alt   : +\n" +
                "ctrl  : -\n" +
                "\n" +
                "keyStatus:\n" +
                "       | | | | | | | | | | |1|1|\n" +
                "       |0|1|2|3|4|5|6|7|8|9|0|1|\n" +
                "    |0| + + - + - + - - + + + -\n" +
                "    |1| - + + + - - + - + - - +\n" +
                "    |2| + - + - - - - - + + - -\n" +
                "    |3| - - - + - - - + + + + +\n" +
                "    |4| - - + + + - - + - + - +\n" +
                "    |5| + - - - + - - - - + + -\n";

        String expectedTimings =
                "interrupt   : 1234567\n" +
                "refreshRate : 100\n" +
                "willReset   : false\n" +
                "last        : 1733830170613\n" +
                "delay       : 5\n" +
                "fullSpeed   : true\n" +
                "time        : 1733830164443\n" +
                "iterations  : 1646090\n";

        assertEquals(expectedCpu, cpu.toStringDetails(true));
        assertEquals(expectedPorts, ports.toStringDetails());
        assertEquals(3, graphic.ioDrawMode());
        assertEquals(expectedTimings, timings.toStringDetails());
        assertEquals("specialist", romSwitcher.current());

        // when
        roms.saveSnapshot(targetBase, "snapshot.bin");

        // given
        // reset all states
        before();

        // then
        assertNotSame(expectedCpu, cpu.toStringDetails(true));
        assertNotSame(expectedPorts, ports.toStringDetails());
        assertNotSame(3, graphic.ioDrawMode());
        assertNotSame(expectedTimings, timings.toStringDetails());
        assertEquals("lik", romSwitcher.current());

        // when
        roms.loadSnapshot(targetBase, "snapshot.bin");

        // then
        assertEquals(expectedCpu, cpu.toStringDetails(true));
        assertEquals(expectedPorts, ports.toStringDetails());
        assertEquals(3, graphic.ioDrawMode());
        assertEquals(expectedTimings, timings.toStringDetails());
        assertEquals("specialist", romSwitcher.current());
    }

    private void assertMemoryChanges() {
        fileAssert.check("Cpu state", "memory-changes.log",
                file -> fileAssert.write(file, memory.detailsTable()));
        memory.resetChanges();
    }
}