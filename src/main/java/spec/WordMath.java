package spec;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WordMath {

    public static final int WORD = 0xFFFF;
    public static final int BITE = 0xFF;

    public static int lo(int word) {
        return word & BITE;
    }

    public static int hi(int word) {
        return (word >> 8) & BITE;
    }

    public static int merge(int bite1, int bite2) {
        return (bite1 << 8) | bite2;
    }

    public static int word(int word) {
        return word & WORD;
    }

    public static String hex8(int bite) {
        return hex(bite, 2);
    }

    public static String canonical(String biteOrWord) {
        return "0" + biteOrWord + "h";
    }

    private static String hex(int bite, int length) {
        return padLeft(Integer.toString(bite, 16).toUpperCase(), length, '0');
    }

    public static String hex16(int bite) {
        return hex(bite, 4);
    }

    public static List<Integer> hex8(String bites) {
        return new LinkedList<Integer>() {{
            for (int i = 0; i < bites.length() / 2; i++) {
                add(Integer.parseInt(bites.substring(i * 2, (i + 1) * 2), 16));
            }
        }};
    }

    public static List<Integer> reverse(List<Integer> bites) {
        Collections.reverse(bites);
        return bites;
    }

    public static String bits(int bite) {
        return String.format("%8s", Integer.toBinaryString(bite)).replaceAll(" ", "0");
    }

    public static int inc16(int word) {
        return word(++word);
    }

    public static int dec16(int word) {
        return word(--word);
    }

    public static String hex(List<Integer> bites) {
        StringBuilder result = new StringBuilder();
        hex(result, bites);
        return result.toString();
    }

    public static void hex(StringBuilder result, List<Integer> bites) {
        boolean first = true;
        for (Integer bite : bites) {
            if (first) {
                first = false;
            } else {
                result.append(' ');
            }
            result.append(hex8(bite));
        }
    }

    public static int[] toArray(String bites) {
        bites = bites.replace(" ", "");
        int[] array = new int[bites.length() / 2];
        for (int i = 0; i < array.length; i++) {
            String hex = bites.substring(i * 2, (i + 1) * 2);
            int bite = Integer.parseInt(hex, 16);
            array[i] = bite;
        }
        return array;
    }

    public static String padRight(String string, int length, char ch) {
        return pad(string, length, ch, false);
    }

    public static String padLeft(String string, int length, char ch) {
        return pad(string, length, ch, true);
    }

    public static String pad(String string, int length, char ch, boolean leftOtRight) {
        while (string.length() < length) {
            if (leftOtRight) {
                string = ch + string;
            } else {
                string = string + ch;
            }
        }
        return string;
    }
}