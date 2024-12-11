package spec.assembler;

import org.junit.Ignore;
import org.junit.Test;
import spec.Logger;
import spec.Range;
import spec.math.Bites;
import spec.mods.WhereIsData;
import spec.platforms.Platform;
import spec.stuff.AbstractTest;

import java.io.File;
import java.util.List;

import static spec.platforms.Platform.RESOURCES;
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
            fileName = platform.app(name, ".asm");

            // when then
            assertDizAssembly(fileName, range);
        } catch (Exception exception) {
            exception.printStackTrace();
            fail(String.format("For: %s, we got: %s", test, exception.toString()));

            if (fileName != null) {
                new File(fileName).delete();
                Logger.info("Deleted '%s' because of error", fileName);
            }
        }
    }

    @Test
    @Ignore
    public void testDecompileTest() {
        // given
        lik().loadRom(base, roms);
        String path = RESOURCES + "test/cputest/cputest.com";
        Range range = roms.loadROM(base, path, 0x0000);
        path = path.replaceAll(".com", ".asm");

        // TODO по какой-то причине он пытается воспринимать блок данных как команды
        // TODO я не уверен так же, что его стоит загружать в 0x0000 но в 0x0100 он не работает точно
        // TODO так же при дизассемблировании переполняется 0xFFFF что странно

        // when then
        assertDizAssembly(path, range);
    }

    private void assertDizAssembly(String fileName, Range range) {
        Bites original = memory.read8arr(range);

        WhereIsData data = new WhereIsData(range);
        cpu.modAdd(data);

        // when

        String sourceCode = assertDizAssembly(data, fileName);
        Bites recompiled = assertAssembly(sourceCode, null);

        // then
        assertMemoryChanges("", "", original, recompiled);
    }
}