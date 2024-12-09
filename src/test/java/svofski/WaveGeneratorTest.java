package svofski;

import org.junit.Test;
import spec.Logger;
import spec.Range;
import spec.math.Bites;
import spec.platforms.PlatformFactory;
import spec.stuff.AbstractTest;

public class WaveGeneratorTest extends AbstractTest {

    @Test
    public void testLik_generateWave() {
        getAllFiles(".rks")
                .forEach(pair -> testLik_generateWave(pair.getKey(), pair.getValue()));
    }

    private void testLik_generateWave(String platform, String name) {
        // given
        Range range = PlatformFactory.platform(platform).loadGame(base, roms, name);

        // when
        byte[] mem = memory.all().byteArray(range);
        Bites wave = new TapeFormat("specialist-rks", false)
                .format(mem, 0, name + ".rks").makewav();

        // then
        String fileName = APP_RESOURCES + platform + "/apps/" + name + "/" + name + ".wav";
        fileAssert.check(fileName, fileName,
                file -> {
                    Logger.info("Wrote '%s'\n", file);
                    return fileAssert.write(file, wave.byteArray());
                });
    }
}
