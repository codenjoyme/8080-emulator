package spec.sound;

import spec.sound.Audio;

import javax.sound.sampled.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class BaseAudio implements Audio {

    private static final int CPU_SAMPLE_RATE = 44100;
    private static final int BUFFER_COUNT = 2;

    private ArrayBlockingQueue<Byte> audioQueue;
    private SourceDataLine line;
    private Thread audio;
    private volatile boolean running = true;
    private final int bufferSize;

    public BaseAudio(int bufferSize) {
        this.bufferSize = bufferSize;
        audioQueue = new ArrayBlockingQueue<>(bufferSize * BUFFER_COUNT);

        AudioFormat format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            // Увеличиваем размер внутреннего буфера для снижения нагрузки на воспроизведение
            line.open(format, bufferSize * 4);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        audio = new Thread(this::processAudio);
        audio.start();
    }

    private void processAudio() {
        try {
            while (running || !audioQueue.isEmpty()) {
                if (!audioQueue.isEmpty()) {
                    byte[] buffer = new byte[Math.min(bufferSize, audioQueue.size())];
                    for (int i = 0; i < buffer.length; i++) {
                        Byte data = audioQueue.poll(10, TimeUnit.MILLISECONDS); // Снижаем время ожидания
                        if (data != null) {
                            buffer[i] = data;
                        }
                    }
                    line.write(buffer, 0, buffer.length);
                } else {
                    Thread.sleep(10); // Минимизируем CPU использование если очередь пуста
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void writeTimes(int bite, int times) {
        byte signed = (byte) (bite - 128);
        try {
            for (int i = 0; i < times; i++) {
                audioQueue.put(signed);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        running = false;
        try {
            audio.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (line != null) {
            line.drain();
            line.close();
        }
    }
}