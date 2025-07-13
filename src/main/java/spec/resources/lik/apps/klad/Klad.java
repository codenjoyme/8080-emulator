package spec.resources.lik.apps.klad;

import spec.Point;
import spec.math.Bites;

import static spec.math.WordMath.hih;
import static spec.math.WordMath.loh;

public final class Klad {

    public static final int LEVELS_OFFSET = 0x1300;
    public static final int LEVEL_WIDTH = 32;
    public static final int LEVEL_HEIGHT = 22;
    public static final int ONE_BYTE_PER_TWO_CELL = 2;
    public static final int LEVEL_SETTINGS_PREFIX = 16;
    public static final int LEVEL_LENGTH = LEVEL_SETTINGS_PREFIX + LEVEL_WIDTH * LEVEL_HEIGHT / ONE_BYTE_PER_TWO_CELL;

    public static final String LEGEND = " okew░‾Hlj▒▓ki  ";
    public static final char HERO = '☺';
    public static final char HUNTER = '☻';

    public static int levelBegin(int level) {
        return LEVELS_OFFSET + level * LEVEL_LENGTH;
    }

    public static final String[] LEVELS = new String[] {
            "▓▓☺▒ ☻▒▓H▒▒H▒▒H▒▒H▒▒H▒▒H▓▒ ☻▒ ▓▓\n" +
            "▓▓ ▒▒▒▒▓H▓▒H▓▒H▓▒H▓▒H▓▒H▓▒▒▒▒ ▓▓\n" +
            "▓▓ ▒▒▒▒▒H▒▒H▒▒H▒▒H▒▒H▒▒H▒▒▒▒▒ ▓▓\n" +
            "▓▓      H  H  H  H  H  H      ▓▓\n" +
            "▓▓H‾H▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓H‾H▓▓\n" +
            "▓▓H H▓▒                  ▒▓H H▓▓\n" +
            "▓▓H H▓▒                  ▒▓H H▓▓\n" +
            "▓▓H H▓▒HHHHHHHHHHHHHHHHHH▒▓H H▓▓\n" +
            "▓▓H H▒▒                  ▒▒H H▓▓\n" +
            "▓▓▒H▓‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾▓H▒▓▓\n" +
            "▓▓▒H▓oooooooooooooooooooooo▓H▒▓▓\n" +
            "▓▓▒H▓w▓▓▓w▓▓▓ww▓▓www▓▓▓▓www▓H▒▓▓\n" +
            "▓▓▒H▓░▓▓░▓▓▓░░░▓░░░░░▓▓░░░░▓H▒▓▓\n" +
            "▓▓▒H▓░▓░▓▓▓▓░▓░▓░▓▓▓░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░░▓▓▓▓▓░▓░▓░░░░░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░▓░▓▓▓▓░▓░▓░▓▓▓░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░▓▓░▓▓▓░▓░▓░▓▓▓░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░▓▓▓░▓░░▓░▓░▓▓▓░▓░░░░░▓H▒▓▓\n" +
            "▓▓▒H▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓H▒▓▓\n" +
            "▓▓▒H                        H▒▓▓\n" +
            "▓▓▒H H▓‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾▓H H▒▓▓\n" +
            "▓▓▓▓▓▓▓wwwwwwwwwwwwwwwwww▓▓▓▓▓▓▓\n"
    };

    public static String buildLevel(Bites bites) {
        StringBuilder sb = new StringBuilder();
        int x1 = bites.get(0x00);
        int y1 = bites.get(0x02);
        int x2 = bites.get(0x05);
        int y2 = bites.get(0x07);
        int x3 = bites.get(0x0A);
        int y3 = bites.get(0x0C);
        boolean two = bites.get(0x0F) == 0x18; // 0xFF если один охотник, 0x18 если два

        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x += ONE_BYTE_PER_TWO_CELL) {
                int byteValue = bites.get(y * LEVEL_WIDTH / ONE_BYTE_PER_TWO_CELL + x / ONE_BYTE_PER_TWO_CELL + LEVEL_SETTINGS_PREFIX);
                int firstCell = hih(byteValue);
                int secondCell = loh(byteValue);
                sb.append(LEGEND.charAt(firstCell));
                sb.append(LEGEND.charAt(secondCell));
            }
            sb.append('\n');
        }
        int lineWidth = LEVEL_WIDTH + 1;
        sb.setCharAt(y1 * lineWidth + x1, HERO);
        sb.setCharAt(y2 * lineWidth + x2, HUNTER);
        if (two) {
            sb.setCharAt(y3 * lineWidth + x3, HUNTER);
        }

        return sb.toString();
    }

    public static Bites parseLevel(String level) {
        Point player = null;
        Point hunter1 = null;
        Point hunter2 = null;

        Bites bites = new Bites(LEVEL_LENGTH);

        String[] lines = level.split("\n");
        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            String line = lines[y];
            for (int x = 0; x < LEVEL_WIDTH; x++) {
                Point point = new Point(x, y);
                char cell = line.charAt(x);
                if (cell == HERO) {
                    player = point;
                } else if (cell == HUNTER) {
                    if (hunter1 == null) {
                        hunter1 = point;
                    } else {
                        hunter2 = point;
                    }
                }
            }
            lines[y] = line.replace(HERO, ' ')
                    .replace(HUNTER, ' ');
        }

        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            String line = lines[y];
            for (int x = 0; x < LEVEL_WIDTH; x += ONE_BYTE_PER_TWO_CELL) {
                char firstCell = line.charAt(x);
                char secondCell = line.charAt(x + 1);

                int firstCharacter = LEGEND.indexOf(firstCell);
                int secondCharacter = LEGEND.indexOf(secondCell);

                int byteValue = (firstCharacter << 4) | secondCharacter;
                bites.set(y * LEVEL_WIDTH / ONE_BYTE_PER_TWO_CELL + x / ONE_BYTE_PER_TWO_CELL + LEVEL_SETTINGS_PREFIX, byteValue);
            }
        }

        // TODO #48 Я пока не понимаю что это за данные, как пойму - смогу их установить разумно
        bites.set(0x00, player == null ? 0 : player.getX());
        bites.set(0x01, 0x00);
        bites.set(0x02, player == null ? 0 : player.getY());
        bites.set(0x03, 0x00);
        bites.set(0x04, 0x02); // TODO #48
        bites.set(0x05, hunter1 == null ? 0 : hunter1.getX());
        bites.set(0x06, 0x00);
        bites.set(0x07, hunter1 == null ? 0 : hunter1.getY());
        bites.set(0x08, 0x00);
        bites.set(0x09, 0x04); // TODO #48
        bites.set(0x0A, hunter2 == null ? 0 : hunter2.getX());
        bites.set(0x0B, 0x00);
        bites.set(0x0C, hunter2 == null ? 0 : hunter2.getY());
        bites.set(0x0D, 0x00);
        bites.set(0x0E, 0x04); // TODO #48
        bites.set(0x0F, (hunter2 == null) ? 0xFF : 0x18); // 0xFF если один охотник, 0x18 если два
        return bites;
    }

}
