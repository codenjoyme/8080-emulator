package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.state.JsonState;

import static spec.Constants.*;

public class Timings implements JsonState {

    private final Hardware hard;

    private int interrupt = 0;
    private int refreshRate = NORMAL_SCREEN_EACH_INTERRUPT;

    // TODO #26 а точно тут надо так заморачиваться с многопоточностью?
    private boolean willReset = false;

    private long last = 0;
    private long delay = CPU_INTERRUPT_DELAY;
    private boolean fullSpeed = false;

    private long time;

    public Timings(Hardware hard) {
        this.hard = hard;
    }

    public void updateState() {
        profiling();

        if (willReset) {
            Logger.info("Reset Hardware!");
            willReset = false;
            hard.reset();
        }

        interrupt++;

        // Обновлять экран каждое прерывание по умолчанию
        if ((interrupt % refreshRate) == 0) {
            hard.video().screenPaint();
        }

        hard.keyboard().tick();

        if (!fullSpeed) {
            hard.audio().tick();
            sleep();
        }
    }

    protected void profiling() {
        if (interrupt % LOG_EACH_INTERRUPT == 0) {
            if (time != 0) {
                Logger.debug("Time per %sk interrupts: %s ms",
                        LOG_EACH_INTERRUPT / 1000,
                        1.0D * (now() - time) / NANOS);
            }
            time = now();
        }
    }

    protected long now() {
        return System.nanoTime();
    }

    protected void sleep() {
        if ((interrupt % SLEEP_EACH_INTERRUPT) == 0) {
            long time = now();
            long duration = time - last;
            last = time;
            // запомним текущее время, как предыдущее.
            if (duration < delay) {
                sleep(delay - duration);
            }
        }
    }

    public void sleep(long nanos) {
        // this sleep method works better than Thread.sleep(0)
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (end - start < nanos);
    }

    public void changeFullSpeed() {
        fullSpeed = !fullSpeed;
        if (fullSpeed) {
            Logger.debug("Full speed mode");
            refreshRate = MINIMAL_SCREEN_EACH_INTERRUPT;
        } else {
            Logger.debug("Normal speed mode");
            refreshRate = NORMAL_SCREEN_EACH_INTERRUPT;
        }
    }

    public void willReset() {
        willReset = true;
    }

    public void increaseDelay() {
        if (delay < 10) {
            delay++;
        } else {
            delay = (int) (delay / INCREASE_DELAY);
        }
        Logger.debug("Delay increased: %s", delay);
    }

    public void decreaseDelay() {
        if (delay < 10) {
            delay--;
        } else {
            delay = (int) (delay * INCREASE_DELAY);
        }
        Logger.debug("Delay decreased: %s", delay);
    }

    @Override
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("interrupt", interrupt);
        result.addProperty("refreshRate", refreshRate);
        result.addProperty("willReset", willReset ? 1 : 0);
        result.addProperty("last", last);
        result.addProperty("delay", delay);
        result.addProperty("fullSpeed", fullSpeed ? 1 : 0);
        result.addProperty("time", time);

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        interrupt = json.get("interrupt").getAsInt();
        refreshRate = json.get("refreshRate").getAsInt();
        willReset = json.get("willReset").getAsInt() == 1;
        last = json.get("last").getAsLong();
        delay = json.get("delay").getAsInt();
        fullSpeed = json.get("fullSpeed").getAsInt() == 1;
        time = json.get("time").getAsLong();
    }

    public String toStringDetails() {
        return String.format(
                "interrupt   : %s\n" +
                "refreshRate : %s\n" +
                "willReset   : %s\n" +
                "last        : %s\n" +
                "delay       : %s\n" +
                "fullSpeed   : %s\n" +
                "time        : %s\n",
                interrupt, refreshRate, willReset, last, delay, fullSpeed, time);
    }
}
