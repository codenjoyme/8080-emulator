package spec;

import java.io.PrintStream;

public class Logger {

    public static boolean DEBUG = true;

    public static void debug(String message, Object... params) {
        if (!DEBUG) return;
        info(message, params);
    }

    public static void debugLine(String message, Object... params) {
        if (!DEBUG) return;
        out(message, params);
    }

    public static void info(String message, Object... params) {
        out(message + "\n", params);
    }

    private static PrintStream out(String message, Object[] params) {
        return System.out.printf(message, params);
    }
}
