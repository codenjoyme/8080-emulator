package spec;

import org.apache.commons.io.IOUtils;
import spec.platforms.Lik;
import spec.platforms.Specialist;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Files {

    public static final String SNAPSHOTS = "snapshots/";
    public static final String SCREENSHOTS = "screenshots/";
    public static final String RECORDS = "records/";

    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");

    public static String newRecord(String base) {
        return fix(new File(base + RECORDS + "record_" + DATE_FORMAT.format(new Date()) + ".rec"));
    }

    public static void createFolders(String base) {
        new File(base + SNAPSHOTS).mkdirs();
        new File(base + SCREENSHOTS).mkdirs();
        new File(base + RECORDS).mkdirs();

        if (isRunningFromJar()) {
            try {
                copyResourceDirectory(base, Lik.NAME);
                copyResourceDirectory(base, Specialist.NAME);
                copyResourceDirectory(base, "test");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyResourceDirectory(String basePath, String resourceDirPath) throws IOException {
        URL dirURL = Files.class.getResource("/" + resourceDirPath);
        String path = dirURL.getPath();
        String jarPath = path.substring(5, path.indexOf("!")); //strip out only the JAR file

        try (ZipFile zip = new ZipFile(URLDecoder.decode(jarPath, "UTF-8"))) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if(entry.getName().startsWith(resourceDirPath) && !entry.isDirectory()) {
                    File targetFile = new File(basePath, entry.getName());
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs(); //make directory structure if doesn't exist
                    }
                    try (InputStream is = zip.getInputStream(entry)) {
                        try (OutputStream os = new FileOutputStream(targetFile)) {
                            IOUtils.copy(is, os);
                        }
                    }
                }
            }
        }
    }

    public static boolean isRunningFromJar() {
        URL classResource = Files.class.getResource(Files.class.getSimpleName() + ".class");
        Logger.debug("Running from: " + classResource);
        return classResource != null && classResource.getProtocol().equals("jar");
    }

    public static String newScreenshot(String base) {
        return fix(new File(base + SCREENSHOTS + "screen_" + DATE_FORMAT.format(new Date()) + ".png"));
    }

    public static String newSnapshot(String base) {
        return fix(new File(base + SNAPSHOTS + "snapshot_" + DATE_FORMAT.format(new Date()) + ".snp"));
    }

    private static String fix(File path) {
        return path.getAbsolutePath()
                .replace("//", "/")
                .replace("\\\\", "\\");
    }
}
