package spec.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static spec.Constants.SCREEN_HEIGHT_IN_SYMBOLS;
import static spec.Constants.SCREEN_WIDTH_IN_SYMBOLS;

public class ScreenUtils {

    public static String addSpaces(String text) {
        List<String> lines = Arrays.stream(text.split("\n"))
                .map(line -> StringUtils.rightPad(line, SCREEN_WIDTH_IN_SYMBOLS, " "))
                .collect(toCollection(ArrayList::new));
        // add empty lines
        for (int i = 0; i < SCREEN_HEIGHT_IN_SYMBOLS - lines.size(); i++) {
            lines.add(StringUtils.rightPad("", SCREEN_WIDTH_IN_SYMBOLS, " "));
        }
        return String.join("\n", lines);
    }

    public static String putInBorder(String text) {
        text = addSpaces(text);
        String separator = StringUtils.leftPad("", SCREEN_WIDTH_IN_SYMBOLS, "═");
        text = Arrays.stream(text.split("\n"))
                .map(line -> "║" + line + "║")
                .collect(joining("\n"));
        return String.format("╔%s╗\n%s\n╚%s╝", separator, text, separator);
    }
}
