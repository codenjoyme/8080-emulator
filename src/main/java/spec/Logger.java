package spec;

public class Logger {

    public static final boolean DEBUG = true;

    public static void debug(String message, Object... params) {
        if (!DEBUG) return;
        info(message, params);
    }

    public static void info(String message, Object... params) {
        System.out.printf(message + "\n", params);
    }
}
