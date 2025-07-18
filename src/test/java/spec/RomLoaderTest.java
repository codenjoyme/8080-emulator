package spec;

import org.junit.Test;
import spec.platforms.Specialist;
import spec.resources.lik.apps.klad.Klad;
import spec.sound.AudioDriver;
import spec.state.JsonState;
import spec.stuff.AbstractTest;
import spec.utils.JsonUtils;

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
        lik().loadRom(base, roms);

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadLikGame() {
        // when
        lik().loadGame(base, roms, "reversi");

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadSpecialistRom() {
        // when
        specialist().loadRom(base, roms);

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadSpecialistGame() {
        // when
        specialist().loadGame(base, roms, "babnik");

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
    protected AudioDriver createAudioDriver() {
        return new AudioDriver();
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
        lik().loadRom(base, roms);
        lik().loadGame(base, roms, Klad.NAME);

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

        String keyboardJson = "{\n" +
                "  'keyStatus': [\n" +
                "    '110101001110',\n" +
                "    '011100101001',\n" +
                "    '101000001100',\n" +
                "    '000100011111',\n" +
                "    '001110010101',\n" +
                "    '100010000110'\n" +
                "  ],\n" +
                "  'shift': 1,\n" +
                "  'alt': 0,\n" +
                "  'ctrl': 1,\n" +
                "  'shiftEmu': 0,\n" +
                "  'cyrLat': 1\n" +
                "}";

        String portsJson = "{\n" +
                "  'Ain': 0,\n" +
                "  'Bin': 0,\n" +
                "  'C0in': 1,\n" +
                "  'C1in': 0\n" +
                "}";

        String cpuJson = "{\n" +
                "  'PC': '2345',\n" +
                "  'SP': '6789',\n" +
                "  'AF': '1216',\n" +
                "  'BC': '5678',\n" +
                "  'DE': '9ABC',\n" +
                "  'HL': 'DEF0',\n" +
                "  'interrupt': 4630,\n" +
                "  'tick': 448585456,\n" +
                "  'tact': 305419896\n" +
                "}";

        String graphicJson = "{\n" +
                "  'ioDrawMode': 3\n" +
                "}";

        String audioJson = "{\n" +
                "  'audioMode': 1,\n" +
                "  'allowDataSkip': 1\n" +
                "}";

        String romJson = "{\n" +
                "  'platform': 'specialist'\n" +
                "}";

        String timingsJson = "{\n" +
                "  'interrupt': 1234,\n" +
                "  'refreshRate': 100,\n" +
                "  'willReset': 0,\n" +
                "  'last': 1733699664007,\n" +
                "  'delay': 125600,\n" +
                "  'fullSpeed': 1,\n" +
                "  'time': 1733699156833\n" +
                "}";

        keyboard.fromJson(JsonUtils.parse(keyboardJson));

        ports.fromJson(JsonUtils.parse(portsJson));

        graphic.nextDrawMode();
        graphic.nextDrawMode();
        graphic.nextDrawMode();

        hard.audio().switchOut();
        hard.audio().switchAllowDataSkip();

        timings.changeFullSpeed();
        timings.decreaseDelay();
        for (int i = 0; i < 1234; i++) {
            timings.updateState();
            if (i % 3 == 1) {
                timings.sleep();
            }
            if (i % 3 == 0) {
                timings.profiling();
            }
        }

        romSwitcher.platform = Specialist.NAME;

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
                "Ain   : -\n" +
                "Bin   : -\n" +
                "C0in  : +\n" +
                "C1in  : -\n" +
                "\n" +
                "shift : +\n" +
                "alt   : -\n" +
                "ctrl  : +\n" +
                "shiftEmu : -\n" +
                "cyrLat : +\n" +
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
                "interrupt   : 1234\n" +
                "refreshRate : 100\n" +
                "willReset   : false\n" +
                "last        : 1733699664007\n" +
                "delay       : 125600\n" +
                "fullSpeed   : true\n" +
                "time        : 1733699156833\n";

        assertEquals(expectedCpu, cpu.toStringDetails(true));
        assertEquals(expectedPorts, ports.toStringDetails());
        assertEquals(3, graphic.ioDrawMode());
        assertEquals(expectedTimings, timings.toStringDetails());
        assertEquals("specialist", romSwitcher.current().name());
        assertEquals(true, hard.audio().audioMode());
        assertEquals(keyboardJson, pretty(keyboard));
        assertEquals(cpuJson, pretty(cpu));
        assertEquals(graphicJson, pretty(graphic));
        assertEquals(audioJson, pretty(hard.audio()));
        assertEquals(portsJson, pretty(ports));
        assertEquals(romJson, pretty(romSwitcher));
        assertEquals(timingsJson, pretty(timings));

        // when
        roms.saveSnapshot(targetBase, "snapshot.json");

        // given
        // reset all states
        before();

        // then
        assertNotSame(expectedCpu, cpu.toStringDetails(true));
        assertNotSame(expectedPorts, ports.toStringDetails());
        assertNotSame(3, graphic.ioDrawMode());
        assertNotSame(expectedTimings, timings.toStringDetails());
        assertEquals("lik", romSwitcher.current().name());
        assertEquals(false, hard.audio().audioMode());

        assertEquals("{\n" +
                "  'keyStatus': [\n" +
                "    '000000000000',\n" +
                "    '000000000000',\n" +
                "    '000000000000',\n" +
                "    '000000000000',\n" +
                "    '000000000000',\n" +
                "    '000000000000'\n" +
                "  ],\n" +
                "  'shift': 0,\n" +
                "  'alt': 0,\n" +
                "  'ctrl': 0,\n" +
                "  'shiftEmu': 0,\n" +
                "  'cyrLat': 0\n" +
                "}", pretty(keyboard));

        assertEquals("{\n" +
                "  'PC': '0000',\n" +
                "  'SP': '0000',\n" +
                "  'AF': '0002',\n" +
                "  'BC': '0000',\n" +
                "  'DE': '0000',\n" +
                "  'HL': '0000',\n" +
                "  'interrupt': 1,\n" +
                "  'tick': 0,\n" +
                "  'tact': 0\n" +
                "}", pretty(cpu));

        assertEquals("{\n" +
                "  'ioDrawMode': 0\n" +
                "}", pretty(graphic));

        assertEquals("{\n" +
                "  'audioMode': 0,\n" +
                "  'allowDataSkip': 0\n" +
                "}", pretty(hard.audio()));

        assertEquals("{\n" +
                "  'Ain': 1,\n" +
                "  'Bin': 1,\n" +
                "  'C0in': 1,\n" +
                "  'C1in': 1\n" +
                "}", pretty(ports));

        assertEquals("{\n" +
                "  'platform': 'lik'\n" +
                "}", pretty(romSwitcher));

        assertEquals("{\n" +
                "  'interrupt': 0,\n" +
                "  'refreshRate': 10,\n" +
                "  'willReset': 0,\n" +
                "  'last': 0,\n" +
                "  'delay': 157000,\n" +
                "  'fullSpeed': 0,\n" +
                "  'time': 0\n" +
                "}", pretty(timings));

        // when
        roms.loadSnapshot(targetBase, "snapshot.json");

        // then
        assertEquals(expectedCpu, cpu.toStringDetails(true));
        assertEquals(expectedPorts, ports.toStringDetails());
        assertEquals(3, graphic.ioDrawMode());
        assertEquals(expectedTimings, timings.toStringDetails());
        assertEquals("specialist", romSwitcher.current().name());
        assertEquals(true, hard.audio().audioMode());
        assertEquals(keyboardJson, pretty(keyboard));
        assertEquals(cpuJson, pretty(cpu));
        assertEquals(graphicJson, pretty(graphic));
        assertEquals(audioJson, pretty(hard.audio()));
        assertEquals(portsJson, pretty(ports));
        assertEquals(romJson, pretty(romSwitcher));
        assertEquals(timingsJson, pretty(timings));
    }

    private String pretty(JsonState state) {
        return JsonUtils.asString(state.toJson())
                .replace("\"", "'");
    }

    private void assertMemoryChanges() {
        fileAssert.check("Cpu state", "memory-changes.log",
                file -> fileAssert.write(file, memory.detailsTable()));
        memory.resetChanges();
    }

    @Test
    public void testLoadKladAssembler() {
        // when
        Range range = lik().loadAsm(base, roms, Klad.NAME);

        // then
        assertMemoryChanges();
    }
}