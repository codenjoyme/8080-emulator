package spec;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Files {

    public static final String SNAPSHOTS = "snapshots/";
    public static final String SCREENSHOTS = "screenshots/";
    public static final String RECORDS = "records/";

    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");

    static {
        Files.createFolders();
    }

    public static File newRecord() {
        return new File(RECORDS + "record_" + DATE_FORMAT.format(new Date()) + ".rec");
    }

    public static void createFolders() {
        new File(SNAPSHOTS).mkdirs();
        new File(SCREENSHOTS).mkdirs();
        new File(RECORDS).mkdirs();
    }

    public static File newScreenshot() {
        return new File(SCREENSHOTS + "screen_" + DATE_FORMAT.format(new Date()) + ".png");
    }

    public static File newSnapshot() {
        return new File(SNAPSHOTS + "snapshot_" + DATE_FORMAT.format(new Date()) + ".snp");
    }
}
