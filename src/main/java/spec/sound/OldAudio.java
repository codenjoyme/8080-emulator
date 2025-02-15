package spec.sound;

import javax.sound.sampled.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;

public class OldAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 2500;
    private static final int BUFFER_SIZE = 1024;

    private final SourceDataLine line;
    private final AudioFormat format;
    private final ExecutorService executor;
    private final ArrayBlockingQueue<Byte> audioQueue = new ArrayBlockingQueue<>(1024);

    public OldAudio() {
        try {
            format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, BUFFER_SIZE);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        line.start();
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void write(int bite) {
        audioQueue.offer((byte) (bite - 128)); // Преобразование примера в знаковый тип, если требуется
    }

    @Override
    public void tick() {
        if (!audioQueue.isEmpty()) {
            byte[] buffer = new byte[Math.min(BUFFER_SIZE, audioQueue.size())];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = audioQueue.poll();
            }
            line.write(buffer, 0, buffer.length);
        }
    }

    @Override
    public void play() {
        // Не требуется для непрерывного вывода
    }

    public void stop() {
        executor.shutdown();
        line.drain();
        line.stop();
        line.close();
    }
}