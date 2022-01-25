package spec;

import org.junit.After;
import org.junit.Before;
import spec.assembler.Assembler;

import static spec.Constants.*;
import static spec.SmartAssert.assertEquals;
import static spec.WordMath.hex8;

public abstract class AbstractTest {

    public static int RECORD_PRECISION = 1;
    public static int START = 0x0000;

    private boolean memoryInit;

    protected Hardware hardware;
    protected TestMemory memory;
    protected Cpu cpu;
    protected Assembler asm;
    protected KeyRecord record;
    protected RomLoader roms;

    @Before
    public void before() throws Exception {
        hardware = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT) {

            @Override
            protected Memory createMemory() {
                return memory = new TestMemory(x10000);
            }

            @Override
            protected Cpu createCpu(double clock) {
                // interrupt будет проходить каждый тик
                clock = 50.1 * 1e-6;
                return cpu = super.createCpu(clock);
            }

            @Override
            protected int recordPrecision() {
                return RECORD_PRECISION;
            }
        };

        roms = hardware.roms();
        record = hardware.record();
        asm = hardware.cpu().asm();
        memory.clear();
        memoryInit = false;
        cpu.PC(START);
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    public void givenPr(String program) {
        givenMm(asm.split(asm.parse(program)));
    }

    /**
     * Либо метод запишет данные в память без валидации (если еще никто не делал этого),
     * либо будет валидировать уже измененную ранее область памяти.
     */
    public void givenMm(String bites) {
        if (!memoryInit) {
            memoryInit = true;
            memory.write8str(START, bites.replace("\n", " "));
        }
        String split = asm.split(memory.changes());
        assertEquals(bites, split);
        int ticks = split.split("\n").length;
        record.after(ticks).stopCpu();
    }

    public void asrtCpu(String expected) {
        assertEquals(expected, cpu.toStringDetails());
    }

    public void cpuShort(String expected) {
        assertEquals(expected, cpu.toString());
    }

    public void assertMem(int addr, String expected) {
        assertEquals(expected, hex8(cpu.data.read8(addr)));
    }

    public void assertMem(int begin, int endOrLength, String expected) {
        int end = (begin < endOrLength)
                ? endOrLength
                : begin + endOrLength - 1;
        assertEquals(expected, memory.read8srt(new Range(begin, end)));
    }

    public void start() {
        hardware.start();
    }
}