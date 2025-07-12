package spec.resources.lik.apps.klad;

public final class Klad {

    public static final int LEVELS_OFFSET = 0x1300;
    public static final int LEVEL_WIDTH = 32;
    public static final int LEVEL_HEIGHT = 22;
    public static final int ONE_BYTE_PER_TWO_CELL = 2;
    public static final int DATA_BETWEEN_LEVELS = 16;
    public static final int LEVEL_LENGTH = LEVEL_WIDTH * LEVEL_HEIGHT / ONE_BYTE_PER_TWO_CELL;

    public static int levelBegin(int level) {
        return LEVELS_OFFSET + DATA_BETWEEN_LEVELS + level * (LEVEL_LENGTH + DATA_BETWEEN_LEVELS);
    }
}
