package spec;

public class Constants {

    public static final int b0000_0000 = 0b0000_0000;
    public static final int b0000_0001 = 0b0000_0001;
    public static final int b0000_0010 = 0b0000_0010;
    public static final int b0000_0100 = 0b0000_0100;
    public static final int b0000_1000 = 0b0000_1000;
    public static final int b0001_0000 = 0b0001_0000;
    public static final int b0010_0000 = 0b0010_0000;
    public static final int b0100_0000 = 0b0100_0000;
    public static final int b1000_0000 = 0b1000_0000;

    public static final int x01 = b0000_0001;
    public static final int x02 = b0000_0010;
    public static final int x04 = b0000_0100;
    public static final int x06 = 0b0000_0110;
    public static final int x07 = 0b0000_0111;
    public static final int x08 = b0000_1000;
    public static final int x09 = 0b0000_1001;
    public static final int x0F = 0b0000_1111;
    public static final int x10 = b0001_0000;
    public static final int x20 = b0010_0000;
    public static final int x40 = b0100_0000;
    public static final int x60 = 0b0110_0000;
    public static final int x80 = b1000_0000;
    public static final int x88 = 0b1000_1000;
    public static final int xFF = 0b1111_1111;
    public static final int x100 = 0x100;
    public static final int x10000 = 0x10000;

    public static final int SCREEN_WIDTH_IN_PATTERNS = 48;

    public static final int SCREEN_WIDTH = SCREEN_WIDTH_IN_PATTERNS * Video.PATTERN; // 384
    public static final int SCREEN_HEIGHT = 256;

    public static final int SCREEN_CHAR_WIDTH = 6;
    public static final int SCREEN_CHAR_HEIGHT = 10;

    public static final int SCREEN_WIDTH_IN_SYMBOLS = SCREEN_WIDTH / SCREEN_CHAR_WIDTH;
    public static final int SCREEN_HEIGHT_IN_SYMBOLS = SCREEN_HEIGHT / SCREEN_CHAR_HEIGHT;

    public static final int BASIC_LIK_V2_PROGRAM_START = 0x1E60;
    public static final int BASIC_V1_PROGRAM_START = 0x2000;

    public static final int SCREEN_MEMORY_START = 0x9000;
    public static final int SCREEN_MEMORY_LENGTH = SCREEN_WIDTH_IN_PATTERNS * SCREEN_HEIGHT; // 0xC000

    public static final int START_POINT = 0xC000;  // старт процессора после перезапуска
    public static final Range SCREEN = new Range(SCREEN_MEMORY_START, -SCREEN_MEMORY_LENGTH); // область экрана [0x9000 : 0xBFFF]
    public static final Range ROM = new Range(0xC000, 0xF7FF);    // ПЗУ
    public static final Range PORTS = new Range(0xF800, 0xFFFE);  // порты

    public static final int BORDER_WIDTH = 10;
    public static final int BORDER_PORT = 254;

    /**
     * подогнано экспериментально:
     * - чтобы в игре `КЛАД` отрисовка была максимально близкой
     * - а в игре `BUDY` звук воспроизводился без искажений как в `Emu80`
     */

    // частота с которой происходит звучание
    public static final int AUDIO_SAMPLE_RATE = 44100;

    // 1 миллион наносекунд в секунде
    public static final long NANOS = 1_000_000;

    // магическая константа - во время `LineOutAudio.write()` каждый байт мы мы пишем
    // `LINE_OUT_AUDIO_BYTES_PER_WRITE` раз, чтобы получить `AUDIO_SAMPLE_RATE` частоту на выходе
    public static final int FREQUENCY = 2450;
    public static final int LINE_OUT_AUDIO_BYTES_PER_WRITE = AUDIO_SAMPLE_RATE / FREQUENCY;

    // а это на каждый interrupt мы пишем `AUDIO_BYTES_PER_TICK` байт
    // взятое из запписанного ранее значения байта в `SpeakerAudio.write()`
    public static final int AUDIO_BYTES_PER_TICK = 4;

    // `CPU_TICKS_PER_INTERRUPT` число тиков процессора на один interrupt
    // подогнано экспериментально вместе с `CPU_INTERRUPT_DELAY`
    // чтобы звучание было без искажений
    // TODO #46 кажется эти все константы между собой связаны, `покурить` как именно
    public static final int CPU_TICKS_PER_INTERRUPT = 170;
    public static final long CPU_INTERRUPT_DELAY = (long) (157_000 * 1.0);

    // normal refresh screen every `n` interrupts
    public static final int NORMAL_SCREEN_EACH_INTERRUPT = 10;

    // minimal (for fastest speed) refresh screen every `n` interrupts
    public static final int MINIMAL_SCREEN_EACH_INTERRUPT = 100;

    // sleep every `n` interrupts
    public static final int SLEEP_EACH_INTERRUPT = 1;

    // logging every `n` interrupts
    public static final int LOG_EACH_INTERRUPT = 10_000;

    // насколько будет увеличиваться/уменьшаться `CPU_INTERRUPT_DELAY`
    // при увеличении/уменьшении скорости эмуляции
    public static final double INCREASE_DELAY = 0.8;
}
