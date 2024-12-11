package spec.math;

import spec.Range;

import static spec.Constants.x10000;

public class WordMath {

    public static final int WORD = 0xFFFF;
    public static final int BITE = 0xFF;

    public static int lo(int word) {
        return word & BITE;
    }

    public static int hi(int word) {
        return (word >> 8) & BITE;
    }

    public static Bites splitBites(long value, int count) {
        Bites result = new Bites(count);
        for (int i = 0; i < count; i++) {
            result.set(i, (int) value & BITE);
            value >>= 8;
        }
        return result;
    }

    public static long joinBites(Bites bites, Range range) {
        long result = 0;
        for (int i = range.end(); i >= range.begin(); i--) {
            result <<= 8;
            result |= bites.get(i);
        }
        return result;
    }

    public static int merge(int bite1, int bite2) {
        return (bite1 << 8) | bite2;
    }

    public static int word(int word) {
        return word & WORD;
    }

    public static int wordShift(int word) {
        if (word < 0) {
            return word + x10000;
        } else if (word > WORD) {
            return word - x10000;
        } else {
            return word;
        }
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

    public static Bites hex8(String bites) {
        Bites result = new Bites(bites.length() / 2);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, Integer.parseInt(bites.substring(i * 2, (i + 1) * 2), 16));
        }
        return result;
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

    public static String hex(Bites bites) {
        StringBuilder result = new StringBuilder();
        hex(result, bites);
        return result.toString();
    }

    public static void hex(StringBuilder result, Bites bites) {
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

    public static Bites toBites(String bites) {
        bites = bites.replace(" ", "");
        Bites result = new Bites(bites.length() / 2);
        for (int i = 0; i < result.size(); i++) {
            String hex = bites.substring(i * 2, (i + 1) * 2);
            int bite = Integer.parseInt(hex, 16);
            result.set(i, bite);
        }
        return result;
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