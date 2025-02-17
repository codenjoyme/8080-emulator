package spec.sound;

import spec.Files;
import spec.Logger;
import spec.state.StateProvider;
import spec.math.Bites;

public class AudioDriver implements StateProvider {

    public static final int SNAPSHOT_AUDIO_STATE_SIZE = 1;

    public static final boolean AUDIO_MODE_LINE_OUT = true;
    public static final boolean AUDIO_MODE_SPEAKER = false;

    private Audio audio;
    private boolean audioMode;

    public AudioDriver() {
        createAudio(AUDIO_MODE_SPEAKER);
    }

    public synchronized boolean audioMode() {
        return audioMode;
    }

    public synchronized void createAudio(boolean mode) {
        // TODO #40 закончить с аудио пока отключил для веб версии - там ошибка
        if (Files.isRunningFromJar()) {
            Logger.debug("Audio is disabled in jar");
            audio = new NoAudio();
        }

        if (audio != null) {
            audio.close();
        }

        audioMode = mode;
        Logger.debug("Switch audio to '%s' mode", audioMode ? "line out" : "speaker");

        if (audioMode) {
            audio = new LineOutAudio();
        } else {
            audio = new SpeakerAudio();
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
                    audio.write(0x80);
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
        createAudio(!audioMode);
    }

    @Override
    public int stateSize() {
        return SNAPSHOT_AUDIO_STATE_SIZE;
    }

    @Override
    public void state(Bites bites) {
        validateState("AudioDriver", bites);

        boolean mode = bites.get(0) == 1;
        createAudio(mode);
    }

    @Override
    public Bites state() {
        Bites bites = new Bites(stateSize());

        bites.set(0, audioMode ? 1 : 0);

        return bites;
    }
}
