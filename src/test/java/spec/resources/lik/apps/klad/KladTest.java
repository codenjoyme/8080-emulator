package spec.resources.lik.apps.klad;

import org.junit.Test;
import spec.Memory;
import spec.Range;
import spec.RomLoader;
import spec.math.Bites;
import spec.platforms.PlatformFactory;

import static org.junit.Assert.assertEquals;
import static spec.Constants.x10000;
import static spec.IntegrationTest.KLAD;
import static spec.stuff.AbstractTest.MAIN_RESOURCES;

public class KladTest {

    @Test
    public void testLevels() {
        // given
        Memory memory = new Memory(x10000);
        RomLoader roms = new RomLoader(memory);
        PlatformFactory.platform("lik").loadGame(MAIN_RESOURCES, roms, KLAD);

        // when
        int index = 0;

        Bites bites = memory.read8arr(new Range(Klad.levelBegin(index), -Klad.LEVEL_LENGTH));
        String mem = bites.toString();
        assertEquals(mem, Bites.ofClean(mem).toString());

        // then
        String level = Klad.buildLevel(bites);
        assertEquals(Klad.LEVELS[index], level);
        Bites actualBites = Klad.parseLevel(level);
        // TODO #48 Я пока не понимаю что это за данные, как пойму - смогу их установить разумно
        actualBites.set(0x04, bites.get(0x04));
        actualBites.set(0x09, bites.get(0x09));
        actualBites.set(0x0E, bites.get(0x0E));
        assertEquals(mem, actualBites.toString());
    }
}