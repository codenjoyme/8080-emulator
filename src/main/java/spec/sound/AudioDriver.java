package spec.sound;

import spec.Files;
import spec.Logger;

public class AudioDriver {

    public static final boolean AUDIO_MODE_LINE_OUT = true;
    public static final boolean AUDIO_MODE_SPEAKER = false;

    private Audio audio;
    private boolean audioMode;

    public AudioDriver() {
        audio = createAudio(AUDIO_MODE_SPEAKER);
    }

    public synchronized Audio createAudio(boolean mode) {
        // TODO #39 закончить с аудио пока отключил для веб версии - там ошибка
        if (Files.isRunningFromJar()) {
            Logger.debug("Audio is disabled in jar");
            return new NoAudio();
        }

        if (audio != null) {
            audio.close();
        }

        audioMode = mode;
        Logger.debug("Switch audio to '%s' mode", audioMode ? "line out" : "speaker");

        // TODO #39 Добавить поддержку звука. Закончить попытки и сделать звук красивым.
        if (audioMode) {
            return new OldAudio();
        } else {
            return new NewAudio();
        }
    }

    public synchronized void write(int bite) {
        if (bite == 0x82 || bite == 0x91 ||  // непонятное
            bite == 0x0E || bite == 0x0F ||  // запись на магнитофон
            bite == 0x0A || bite == 0x0B)    // вывод звука на динамик
        {
            if (audioMode) { // звучит запись на магнитофон
                if (bite == 0x0E) {
                    audio.write(0x00);
                } else if (bite == 0x0F) {
                    audio.write(0xFF);
                }
            } else { // звучит вывод на динамик
                if (bite == 0x0A) {
                    audio.write(128);
                } else if (bite == 0x0B) {
                    audio.write(0xFF);
                }
            }
        }
    }

    public synchronized void tick() {
        audio.tick();
    }

    public synchronized void switchOut() {
        audio = createAudio(!audioMode);
    }
}
