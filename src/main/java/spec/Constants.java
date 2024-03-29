package spec;

public class Constants {

    public static final int x01 = 0b0000_0001;
    public static final int x02 = 0b0000_0010;
    public static final int x04 = 0b0000_0100;
    public static final int x06 = 0b0000_0110;
    public static final int x07 = 0b0000_0111;
    public static final int x08 = 0b0000_1000;
    public static final int x09 = 0b0000_1001;
    public static final int x0F = 0b0000_1111;
    public static final int x10 = 0b0001_0000;
    public static final int x20 = 0b0010_0000;
    public static final int x40 = 0b0100_0000;
    public static final int x60 = 0b0110_0000;
    public static final int x80 = 0b1000_0000;
    public static final int x88 = 0b1000_1000;
    public static final int xFF = 0b1111_1111;
    public static final int x100 = 0x100;
    public static final int x10000 = 0x10000;

    public static final int START_POINT = 0xC000;  // старт процессора после перезапуска
    public static final Range SCREEN = new Range(0x9000, 0xBFFF); // область экрана
    public static final Range ROM = new Range(0xC000, 0xF7FF);    // ПЗУ
    public static final Range PORTS = new Range(0xF800, 0xFFFE);  // порты

    public static final int BORDER_WIDTH = 10;
    public static final int BORDER_PORT = 254;

    public static final int SCREEN_WIDTH = 384;
    public static final int SCREEN_HEIGHT = 256;

    public static final String RECORD_LOG_FILE = "./keys.rec";

    // подогнано экспериментально, чтобы в игре Клад отрисовка была максимально близкой
    public static final int CPU_TICKS_PER_INTERRUPT = 2000;
    public static final int CPU_INTERRUPT_DELAY = 35 * CPU_TICKS_PER_INTERRUPT / 10000;
    public static final int REFRESH_RATE = 1;      // refresh screen every 'n' interrupts
    public static final int MAX_REFRESH_RATE = 100;
}
