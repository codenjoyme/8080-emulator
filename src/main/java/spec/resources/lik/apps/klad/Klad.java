package spec.resources.lik.apps.klad;

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

    public static final String LEGEND = " okew░‾Hlj▒▓ki ";
    public static final String CHARACTERS = "☺☻♣";

    public static int levelBegin(int level) {
        return LEVELS_OFFSET + level * LEVEL_LENGTH;
    }

    // этот метод работает так что на вход ожидается только прямоульник 32x22
    // но я сделал небольшой апдейт, теперь перед тим прямоульником передается область длинной LEVEL_SETTINGS_PREFIX
    // и она содержит настройки уровня, а именно координаты героя и охотников
    // ее надо попрсить соответственно и нарисовать в нужном месте CHARACTERS, где ☺ - герой (x1/y1), а ☻ - охотники (x2/y2, x3/y3),
    // вот формат:
    // 00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F
    // x1 -- y1 -- -- x2 -- y2 -- -- x3 -- y3 -- -- --
    // вот старый метод:
//    public static String buildLevel(Bites bites) {
//        StringBuilder sb = new StringBuilder();
//        for (int y = 0; y < LEVEL_HEIGHT; y++) {
//            for (int x = 0; x < LEVEL_WIDTH; x += ONE_BYTE_PER_TWO_CELL) {
//                int byteValue = bites.get(y * LEVEL_WIDTH / ONE_BYTE_PER_TWO_CELL + x / ONE_BYTE_PER_TWO_CELL);
//                int firstCell = (byteValue >> 4) & 0x0F;
//                int secondCell = byteValue & 0x0F;
//                sb.append(LEGEND.charAt(firstCell));
//                sb.append(LEGEND.charAt(secondCell));
//            }
//            sb.append('\n');
//        }
//        return sb.toString();
//    }
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
        sb.setCharAt(y1 * lineWidth + x1, CHARACTERS.charAt(0));
        if (y2 == y3 && x2 == x3 && two) {
            sb.setCharAt(y2 * lineWidth + x2, CHARACTERS.charAt(2));
        } else {
            sb.setCharAt(y2 * lineWidth + x2, CHARACTERS.charAt(1));
            if (two) {
                sb.setCharAt(y3 * lineWidth + x3, CHARACTERS.charAt(1));
            }
        }

        return sb.toString();
    }

}
