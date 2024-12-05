package spec.stuff;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static spec.stuff.SmartAssert.fail;

public class FileAssert {

    private String base;
    private Map<String, String> hashes = new HashMap<>();

    public FileAssert(String base) {
        this.base = base;
    }

    private File testDir() {
        File result = new File(base);
        if (!result.exists()) {
            result.mkdir();
        }
        return result;
    }

    public void removeTestsData() {
        File dir = testDir();
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            String name = file.getName();
            // на всякий случай чтобы не удалить лишнего
            if (!name.endsWith(".png")
                    && !name.endsWith(".log"))
            {
                continue;
            }

            // перед удалением сохраним хеш, потом по нему будем сравнивать
            hashes.put(file.getAbsolutePath(), hash(file));
            file.delete();
        }
        dir.delete();
    }

    public void check(String info, String name, Consumer<File> save) {
        File file = new File(testDir().getAbsolutePath() + "/" + name);
        String hash = hashes.get(file.getAbsolutePath());

        save.accept(file);

        if (!Objects.equals(hash, hash(file))) {
            fail(info + " was changed.\n"
                    + file.getAbsolutePath() + "\n"
                    + "Please check git diff to see differences.\n");
        }
    }

    private String hash(File file) {
        return HashUtils.hashFile(file, "MD5");
    }

    public static void write(File file, String string) {
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file.getAbsolutePath(), false)) {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(File file) {
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
