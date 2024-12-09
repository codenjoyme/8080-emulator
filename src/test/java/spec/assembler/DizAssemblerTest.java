package spec.assembler;

import org.junit.Before;
import org.junit.Test;
import spec.Range;
import spec.math.Bites;
import spec.mods.WhereIsData;
import spec.platforms.Lik;
import spec.stuff.AbstractTest;
import spec.stuff.TrackUpdatedMemory;

import static org.junit.Assert.*;

public class DizAssemblerTest extends AbstractTest {

    @Override
    @Before
    public void before() {
        super.before();

        reset();
        record.after(TICKS).stopCpu();
    }

    @Test
    public void testDizAssembly() {
        // given
        Lik.loadRom(base, roms);
        Range range = Lik.loadGame(base, roms, "klad");
        Bites original = memory.read8arr(range);

        WhereIsData data = new WhereIsData(range);
        cpu.modAdd(data);

        // when
        String sourceCode = assertDizAssembly(data, "klad.asm");
        Bites recompiled = assertAssembly(sourceCode, "klad-recompiled.mem");

        // then
        TrackUpdatedMemory tracker = new TrackUpdatedMemory(original.size(), true);
        tracker.state(original);
        tracker.resetChanges();
        tracker.state(recompiled);
        assertEquals("", tracker.detailsTable());
    }

}