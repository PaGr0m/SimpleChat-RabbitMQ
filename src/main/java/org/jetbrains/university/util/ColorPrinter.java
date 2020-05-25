package org.jetbrains.university.util;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ColorPrinter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private final static Map<Level, String> color = new HashMap<>();

    static {
        color.put(Level.INFO, ANSI_CYAN);
        color.put(Level.WARNING, ANSI_RED);
        color.put(Level.FINE, ANSI_GREEN);
        color.put(Level.SEVERE, ANSI_YELLOW);
        color.put(Level.FINER, ANSI_PURPLE);
        color.put(Level.OFF, ANSI_WHITE);
    }

    private final PrintStream output;

    public ColorPrinter(PrintStream otuput) {
        this.output = otuput;
    }

    public void log(Level level, String msg) {
        output.println(color.get(level) + msg + ANSI_WHITE);
    }

    public void println(String msg) {
        log(Level.OFF, msg);
    }
}
