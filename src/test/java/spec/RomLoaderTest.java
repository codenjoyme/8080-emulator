package spec;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import spec.platforms.Lik;
import spec.platforms.Specialist;
import spec.stuff.FileAssert;
import spec.stuff.SmartAssert;
import spec.stuff.TrackUpdatedMemory;

import static spec.Constants.CPU_TICKS_PER_INTERRUPT;
import static spec.Constants.x10000;
import static spec.IntegrationTest.TEST_RESOURCES;
import static spec.stuff.FileAssert.write;

public class RomLoaderTest {

    private FileAssert fileAssert;

    private RomLoader roms;
    private TrackUpdatedMemory memory;
    private Cpu cpu;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup() {
        memory = new TrackUpdatedMemory(x10000);
        cpu = new Cpu(CPU_TICKS_PER_INTERRUPT, null, null, null);
        roms = new RomLoader(memory, cpu);

        fileAssert = new FileAssert(TEST_RESOURCES + test.getMethodName());
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

    private void assertMemoryChanges() {
        fileAssert.check("Cpu state", "memory-changes.log",
                file -> write(file, memory.detailsTable()));
        memory.resetChanges();
    }
}