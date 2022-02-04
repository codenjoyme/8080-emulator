package spec;

public class Constants {

    private static final int   i1_b00000001 = 1;
    private static final int   i2_b00000010 = 2;
    private static final int   i4_b00000100 = 4;
    private static final int   i6_b00000110 = 6;
    private static final int   i8_b00001000 = 8;
    private static final int   i9_b00001001 = 9;
    private static final int  i15_b00001111 = 15;
    private static final int  i16_b00010000 = 16;
    private static final int  i32_b00100000 = 32;
    private static final int  i64_b01000000 = 64;
    private static final int  i96_b01100000 = 96;
    private static final int i128_b10000000 = 128;

    public static final int x01 =   i1_b00000001;
    public static final int x02 =   i2_b00000010;
    public static final int x04 =   i4_b00000100;
    public static final int x06 =   i6_b00000110;
    public static final int x08 =   i8_b00001000;
    public static final int x09 =   i9_b00001001;
    public static final int x0F =  i15_b00001111;
    public static final int x10 =  i16_b00010000;
    public static final int x20 =  i32_b00100000;
    public static final int x40 =  i64_b01000000;
    public static final int x60 =  i96_b01100000;
    public static final int x80 = i128_b10000000;
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
