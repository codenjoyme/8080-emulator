package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.math.Bites;
import spec.platforms.Lik;
import spec.platforms.Specialist;
import spec.stuff.FileAssert;
import spec.stuff.SmartAssert;
import spec.stuff.TrackUpdatedMemory;

import static spec.Constants.CPU_TICKS_PER_INTERRUPT;
import static spec.Constants.x10000;
import static spec.IntegrationTest.TEST_RESOURCES;
import static spec.stuff.FileAssert.write;
import static spec.stuff.SmartAssert.assertEquals;
import static spec.stuff.TrackUpdatedMemory.TRACK_ALL_CHANGES;

public class RomLoaderTest {

    private RomLoader roms;
    private TrackUpdatedMemory memory;
    private Cpu cpu;
    private IOPorts ports;

    @Rule
    public TestName test = new TestName();
    private FileAssert fileAssert;

    @Before
    public void setup() {
        SmartAssert.setup();

        memory = new TrackUpdatedMemory(x10000, TRACK_ALL_CHANGES);

        ports = new IOPorts(memory, new Layout(), null);
        memory.resetChanges();

        cpu = new Cpu(CPU_TICKS_PER_INTERRUPT, new HardwareData(memory, ports, null) {
            @Override
            public int in8(int port) {
                return 0xFF;
            }

            @Override
            public void out8(int port, int bite) {
                // do nothing
            }
        }, null, null);
        roms = new RomLoader(memory, cpu, ports);

        fileAssert = new FileAssert(TEST_RESOURCES + "/RomLoaderTest/" + test.getMethodName());
        fileAssert.removeTestsData();
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    @Test
    public void testLoadLikRom() {
        // when
        Lik.loadRom(IntegrationTest.getBase(), roms);

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadLikGame() {
        // when
        Lik.loadGame(IntegrationTest.getBase(), roms, "reversi");

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadSpecialistRom() {
        // when
        Specialist.loadRom(IntegrationTest.getBase(), roms);

        // then
        assertMemoryChanges();
    }

    @Test
    public void testLoadSpecialistGame() {
        // when
        Specialist.loadGame(IntegrationTest.getBase(), roms, "babnik");

        // then
        assertMemoryChanges();
    }

    @Test
    public void tesSaveLoadSnapshots() {
        // given
        Lik.loadRom(IntegrationTest.getBase(), roms);
        Lik.loadGame(IntegrationTest.getBase(), roms, "klad");
        cpu.AF(0x1234); // random values
        cpu.BC(0x5678);
        cpu.DE(0x9ABC);
        cpu.HL(0xDEF0);
        cpu.PC(0x2345);
        cpu.SP(0x6789);
        ports.reset();
        ports.state(0b1010_0101);
        ports.keyState(new Bites(new int[]{
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
                0b0101_1010
        }));

        // then
        String expectedCpu =
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

        assertEquals(expectedCpu, cpu.toStringDetails());
        assertEquals(expectedPorts, ports.toStringDetails());

        // when
        roms.saveSnapshot(IntegrationTest.getTargetBase(), "snapshot.bin");

        // when
        setup();
        roms.loadSnapshot(IntegrationTest.getTargetBase(), "snapshot.bin");

        // then
        assertEquals(expectedCpu, cpu.toStringDetails());
        assertEquals(expectedPorts, ports.toStringDetails());
    }

    private void assertMemoryChanges() {
        fileAssert.check("Cpu state", "memory-changes.log",
                file -> write(file, memory.detailsTable()));
        memory.resetChanges();
    }
}