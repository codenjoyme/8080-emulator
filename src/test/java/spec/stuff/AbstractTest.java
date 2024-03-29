package spec.stuff;

import org.junit.After;
import org.junit.Before;
import spec.*;
import spec.assembler.Assembler;

import java.io.File;

import static spec.Constants.*;
import static spec.WordMath.hex8;
import static spec.assembler.Assembler.asString;
import static spec.stuff.SmartAssert.assertEquals;

public abstract class AbstractTest {

    public static int START = 0x0000;

    protected boolean memoryInit;

    protected Hardware hard;
    protected TrackUpdatedMemory memory;
    protected Cpu cpu;
    protected Assembler asm;
    protected KeyRecord record;
    protected RomLoader roms;
    protected FileRecorder fileRecorder;
    protected CpuDebug debug;
    protected KeyLogger keyLogger;

    @Before
    public void before() throws Exception {
        SmartAssert.setup();

        Logger.DEBUG = false;

        hard = new Hardware(SCREEN_WIDTH, SCREEN_HEIGHT, null) {

            @Override
            protected Memory createMemory() {
                return memory = new TrackUpdatedMemory(x10000);
            }

            @Override
            protected Cpu createCpu(int ticksPerInterrupt) {
                // interrupt будет проходить каждый тик
                return cpu = super.createCpu(1);
            }

            @Override
            protected FileRecorder createFileRecorder(File logFile) {
                return fileRecorder = super.createFileRecorder(logFile);
            }
        };

        fileRecorder.stopWriting();
        keyLogger = hard.keyLogger();
        keyLogger.console(false);
        roms = hard.roms();
        record = hard.record();
        asm = hard.cpu().asm();
        memory.resetChanges();
        debug = cpu.debug();
        debug.disable();
        debug.console(false);
        memoryInit = false;
        cpu.PC(START);
    }

    @After
    public void after() throws Exception {
        SmartAssert.checkResult();
    }

    public void givenPr(String program) {
        String bites = asString(asm.split(asm.assembly(program)));
        givenMm(bites);
        assertEquals(program, asm.dizAssembly(bites));
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
        String split = asString(asm.split(memory.changedBites()));
        if (!split.endsWith("\n")) {
            split += "\n";
        }
        if (!bites.endsWith("\n")) {
            bites += "\n";
        }
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

    public void asrtMem(int addr, String expected) {
        assertEquals(expected, hex8(cpu.data().read8(addr)));
    }

    public void asrtMem(int begin, int endOrLength, String expected) {
        int end = (begin < endOrLength)
                ? endOrLength
                : begin + endOrLength - 1;
        assertEquals(expected, memory.read8srt(new Range(begin, end)));
    }

    public void assertTrace(String expected) {
        assertEquals(expected, trace());
    }

    public String trace() {
        return String.join("\n", debug.lines());
    }

    public void start() {
        memory.resetChanges();
        hard.start();
    }

    public void asrtMem(String expected) {
        assertEquals(expected, memory.details());
        memory.resetChanges();
    }
}