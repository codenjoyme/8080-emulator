package spec;

import org.junit.After;
import org.junit.Before;
import spec.assembler.Assembler;

import static spec.SmartAssert.assertEquals;

public abstract class AbstractCpuTest {

    public static int START = 0x0000;

    protected TestData data;
    protected Cpu cpu;
    private int stopWhen;
    private Assembler asm;
    private boolean init;

    @Before
    public void before() throws Exception {
        data = new TestData(this::interrupt);
        init = false;
        cpu = new Cpu(50.1 * 1e-6, data);
        cpu.PC(START);
        asm = new Assembler();
    }

    protected void interrupt() {
        if (cpu.PC() >= stopWhen) {
            data.stopCpu();
        }
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    public void givenPr(String program) {
        givenMm(asm.parse(program));
    }

    /**
     * Либо метод запишет данные в память без валидации (если еще никто не делал этого),
     * либо будет валидировать уже измененную ранее область памяти.
     */
    public void givenMm(String bites) {
        if (!init) {
            init = true;
            bites = bites.replace("\n", " ");
            stopWhen = bites.split(" ").length;
            data.memory().write8str(START, bites);
        } else {
            assertEquals(bites, asm.split(data.updatedMemory()));
        }
    }

    public void asrtCpu(String expected) {
        assertEquals(expected, cpu.toStringDetails());
    }

    public void cpuShort(String expected) {
        assertEquals(expected, cpu.toString());
    }

    public void assertMem(int addr, String expected) {
        assertEquals(expected, WordMath.hex8(cpu.data.read8(addr)));
    }

    public void assertMem(int begin, int endOrLength, String expected) {
        int end = (begin < endOrLength)
                ? endOrLength
                : begin + endOrLength - 1;
        assertEquals(expected, data.memory().read8srt(new Range(begin, end)));
    }

}
