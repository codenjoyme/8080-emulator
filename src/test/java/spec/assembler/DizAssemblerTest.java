package spec.assembler;

import org.junit.Test;
import spec.Logger;
import spec.Range;
import spec.math.Bites;
import spec.mods.WhereIsData;
import spec.platforms.Platform;
import spec.stuff.AbstractTest;

import java.io.File;
import java.util.List;

import static spec.stuff.SmartAssert.fail;

public class DizAssemblerTest extends AbstractTest {

    @Test
    public void testDizAssembly() {
        getAllFiles(".rks")
//                .stream().filter(pair -> pair.getValue().equals("klad"))
                .forEach(pair -> testDizAssembly(pair.getKey(), pair.getValue()));
    }

    private void testDizAssembly(Platform platform, String name) {
        Logger.info("======================================================================");
        String test = String.format("Testing [%s] %s", platform.name(), name);
        Logger.info(test);

        if (List.of("klad2", "pilot", "reversi", "tetris3", "tip-top2", "zoo", "blobcop").contains(name)) {
            Logger.info("Skipping test for %s", name);
            return; // TODO debug all errors
        }

        String fileName = null;
        try {
            // given
            platform.loadRom(base, roms);
            Range range = platform.loadGame(base, roms, name);
            Bites original = memory.read8arr(range);

            WhereIsData data = new WhereIsData(range);
            cpu.modAdd(data);

            // when
            fileName = platform.app(name, ".asm");
            String sourceCode = assertDizAssembly(data, fileName);
            Bites recompiled = assertAssembly(sourceCode, null);

            // then
            assertMemoryChanges(test, "", original, recompiled);
        } catch (Exception exception) {
            exception.printStackTrace();
            fail(String.format("For: %s, we got: %s", test, exception.toString()));

            if (fileName != null) {
                new File(fileName).delete();
                Logger.info("Deleted '%s' because of error", fileName);
            }
        }
    }
}