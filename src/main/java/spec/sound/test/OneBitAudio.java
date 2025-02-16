package spec.sound.test;

import javax.sound.sampled.*;

/**
 * Пример генерации звука с использованием Java Sound API.
 * Пишет в линию звука однобитный аудио-сигнал но проигрывает его в формате 8 бит / 44100 Гц.
 * Класс для экспериментов с форматами аудио.
 */
public class OneBitAudio {

    public static void main(String[] args) {
        int sampleRate = 44100; // Частота дискретизации
        int sampleSizeInBits = 8; // Размер семпла
        int numChannels = 1; // Моно
        boolean signed = true; // Подписанные данные
        boolean bigEndian = true; // Большой порядок байт

        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, numChannels, signed, bigEndian);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            int duration = 20; // Время воспроизведения в секундах
            int toneDuration = sampleRate / 4000; // Длительность тона (1/4 секунды)
            byte[] buffer = new byte[sampleRate * duration];

            for (int second = 0; second < duration; second++) {
                for (int i = 0; i < sampleRate; i++) {
                    // Генерируем чёткие периоды звука и тишины
                    int timeIndex = i + second * sampleRate;
                    if ((i / toneDuration) % 2 == 0) {
                        buffer[timeIndex] = (byte) 127; // Тон
                    } else {
                        buffer[timeIndex] = -128; // Тишина
                    }
                }
            }

            line.write(buffer, 0, buffer.length);
            line.drain(); // Дождаться окончания воспроизведения
            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}