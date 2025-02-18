package spec.sound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.Files;
import spec.Logger;
import spec.state.JsonState;

public class AudioDriver implements JsonState {

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
            disable();
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

    public void disable() {
        Logger.debug("Turn off audio");
        audio = new NoAudio();
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
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("audioMode", audioMode ? 1 : 0);

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        boolean mode = json.get("audioMode").getAsInt() == 1;

        createAudio(mode);
    }
}
