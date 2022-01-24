package spec;

import org.junit.After;
import org.junit.Before;
import spec.assembler.Assembler;

import java.awt.*;

import static spec.Constants.x10000;
import static spec.SmartAssert.assertEquals;
import static spec.WordMath.hex8;

public abstract class AbstractTest {

    public static int START = 0x0000;

    private boolean init;
    protected Hardware hardware;
    private TestMemory memory;

    @Before
    public void before() throws Exception {
        memory = new TestMemory(x10000);

        // interrupt будет проходить каждый тик
        Hardware.CLOCK = 50.1 * 1e-6;

        hardware = new Hardware() {
            @Override
            protected void outb(int port, int bite) {
                // do nothing
            }

            @Override
            protected void drawPixel(Point point, Color color) {
                // do nothing
            }

            @Override
            protected Memory createMemory() {
                return memory;
            }
        };

        memory.clear();
        init = false;
        cpu().PC(START);
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    public void givenPr(String program) {
        givenMm(asm().split(asm().parse(program)));
    }

    /**
     * Либо метод запишет данные в память без валидации (если еще никто не делал этого),
     * либо будет валидировать уже измененную ранее область памяти.
     */
    public void givenMm(String bites) {
        if (!init) {
            init = true;
            memory().write8str(START, bites.replace("\n", " "));
        }
        String split = asm().split(memory.changes());
        assertEquals(bites, split);
        int ticks = split.split("\n").length;
        record().after(ticks).stopCpu();
    }

    public void asrtCpu(String expected) {
        assertEquals(expected, cpu().toStringDetails());
    }

    public void cpuShort(String expected) {
        assertEquals(expected, cpu().toString());
    }

    public void assertMem(int addr, String expected) {
        assertEquals(expected, hex8(cpu().data.read8(addr)));
    }

    public void assertMem(int begin, int endOrLength, String expected) {
        int end = (begin < endOrLength)
                ? endOrLength
                : begin + endOrLength - 1;
        assertEquals(expected, memory().read8srt(new Range(begin, end)));
    }

    public void start() {
        hardware.start();
    }

    public Cpu cpu() {
        return hardware.cpu();
    }

    public RomLoader roms() {
        return hardware.roms();
    }

    public KeyRecord record() {
        return hardware.record();
    }

    public Memory memory() {
        return memory;
    }

    private Assembler asm() {
        return hardware.cpu().asm();
    }
}