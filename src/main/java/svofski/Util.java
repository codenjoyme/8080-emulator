package svofski;

public class Util {

    public static String char8(int val) {
        if (val > 32 && val < 127) return String.valueOf((char) val);
        return ".";
    }

    public static String hex8(int val) {
        if (val < 0 || val > 255) return "??";

        String hexStr = "0123456789ABCDEF";
        return "" + hexStr.charAt((val & 0xF0) >> 4) + hexStr.charAt(val & 0x0F);
    }

    public static String hex16(int val) {
        return hex8((val & 0xFF00) >> 8) + hex8(val & 0x00FF);
    }

    public static boolean isWhitespace(char c) {
        return c == '\t' || c == ' ';
    }

    public static String toTargetEncoding(String str, String encoding) {
        return str; // TODO skip encoding stuff
    }

    public static String replaceExt(String path, String newext) {
        if (!path.contains(".")) {
            return path + newext;
        }
        return path.substring(0, path.lastIndexOf('.')) + newext;
    }

    public static String formatGutterBrief(int addr, byte[] bytes) {
        int width = 0;
        String hexes = " ";

        if (bytes.length > 0) {
            hexes = Util.hex16(addr) + "  ";
            int len = bytes.length > 4 ? 4 : bytes.length;
            for (int b = 0; b < len; b++) {
                hexes += Util.hex8(bytes[b] & 0xFF) + ' ';
                width += 3;
            }

            if (len < bytes.length) {
                hexes += "…";
                width += 2;
            }
            hexes += "                ".substring(width);
        }

        return hexes;
    }

    public static String formatGutterFull(int addr, byte[] bytes) {
        String hexes = "";
        String chars = "";

        if (bytes.length > 0) {
            int len = (int) Math.floor((bytes.length + 15) / 16) * 16;

            for (int b = 0; b < len; b++) {
                if ((b % 16) == 0) {
                    hexes += Util.hex16(addr + b) + "  ";
                    chars = "";
                }
                String ht = (b < bytes.length) ? Util.hex8(bytes[b] & 0xFF) : "  ";
                hexes += ht + ((b % 16 == 7) ? '-' : ' ');

                chars += (b < bytes.length) ? Util.char8(bytes[b]) : ' ';

                if (((b + 1) % 16) == 0) {
                    hexes += "  " + chars;
                    hexes += "\n";
                }
            }
        }

        return hexes;
    }

    public static String trimStart(String str) {
        return str.replaceAll("^\\s+", "");
    }
}