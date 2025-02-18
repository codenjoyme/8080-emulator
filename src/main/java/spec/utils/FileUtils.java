package spec.utils;

import java.io.File;
import java.nio.file.Paths;

public class FileUtils {

    public static String toRelative(String base, File file) {
        try {
            String result = Paths.get(base).toAbsolutePath().relativize(Paths.get(file.getPath())).toString();
            // TODO это надо только для того, чтобы иметь возможность запускать приложение в веб версии, там с путями не все просто
            if (result.startsWith("../")) {
                return result.substring(3);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
