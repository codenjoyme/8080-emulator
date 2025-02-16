package spec.sound;

import spec.math.WordMath;

import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NewAudio2 implements Audio {

    private static final int CPU_SAMPLE_RATE = 8000;
    private static final int BUFFER_CAPACITY = 128;

    private final DataLine.Info info;
    private final AudioFormat format;

    private ScheduledExecutorService scheduler;

    private final byte[] buffer = new byte[BUFFER_CAPACITY];
    private SourceDataLine line;
    private int index = 0;
    private AtomicInteger curent;

    public NewAudio2() {
        format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
        info = new DataLine.Info(SourceDataLine.class, format);
        createLine();
        curent = new AtomicInteger(128);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        int period = 1280 * 35 / 48 / 5;
        scheduler.scheduleAtFixedRate(this::tact, 0, period, TimeUnit.MICROSECONDS);
    }

    private void createLine() {
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(int bite) {
        curent.set(bite);
    }

    private void soundBuffer() {
        synchronized (this) {
            if (line == null) return;
            line.flush();
            line.write(buffer, 0, buffer.length);
            clearBuffer();
        }
    }

    @Override
    public void tick() {
        // do nothing
    }

    public void tact() {
        int bite = curent.get();

        output.add(bite);
        if (output.size() > 20) {
            System.out.println(
                    output.stream().map(WordMath::hex8).reduce("", (a, b) -> a + " " + b));
            output.clear();
        }

        buffer[index++] = (byte) (bite - 128);
        if (index >= buffer.length) {
            soundBuffer();
        }
    }

    List<Integer> output = new ArrayList<>(20);

    private void clearBuffer() {
        java.util.Arrays.fill(buffer, (byte) 0); // Полная очистка содержимого буфера
        index = 0;
    }

    @Override
    public void play() {
        // Не используется
    }

    @Override
    public void close() {
        synchronized (this) {
            scheduler.shutdown();
            line.drain();
            line.close();
            line = null;
        }
    }

}