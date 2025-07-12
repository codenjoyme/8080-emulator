package spec.resources.lik.apps.klad;

import spec.math.Bites;

public final class Klad {

    public static final int LEVELS_OFFSET = 0x1300;
    public static final int LEVEL_WIDTH = 32;
    public static final int LEVEL_HEIGHT = 22;
    public static final int ONE_BYTE_PER_TWO_CELL = 2;
    public static final int DATA_BETWEEN_LEVELS = 16;
    public static final int LEVEL_LENGTH = LEVEL_WIDTH * LEVEL_HEIGHT / ONE_BYTE_PER_TWO_CELL;

    public static final String LEGEND = " OK0w░─HIJ░▓kixy";

    public static int levelBegin(int level) {
        return LEVELS_OFFSET + DATA_BETWEEN_LEVELS + level * (LEVEL_LENGTH + DATA_BETWEEN_LEVELS);
    }


    public static String buildLevel(Bites bites) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x += ONE_BYTE_PER_TWO_CELL) {
                int byteValue = bites.get(y * LEVEL_WIDTH / ONE_BYTE_PER_TWO_CELL + x / ONE_BYTE_PER_TWO_CELL);
                int firstCell = (byteValue >> 4) & 0x0F;
                int secondCell = byteValue & 0x0F;
                sb.append(LEGEND.charAt(firstCell));
                sb.append(LEGEND.charAt(secondCell));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
