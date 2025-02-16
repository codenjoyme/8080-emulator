package spec;

import spec.math.Bites;

import static spec.Constants.*;
import static spec.math.WordMath.*;

public class Timings implements StateProvider{

    public static final int SNAPSHOT_TIMINGS_SIZE = 34;

    private final Hardware hard;

    private int interrupt = 0;
    private int refreshRate = REFRESH_RATE;

    // TODO #26 а точно тут надо так заморачиваться с многопоточностью?
    private boolean willReset = false;

    private long last = 0;
    private long delay = CPU_INTERRUPT_DELAY;
    private boolean fullSpeed = false;

    private long time;
    private int iterations;

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

        hard.audio().tick();
        hard.keyboard().tick();

        if (!fullSpeed) {
            sleep();
        }
    }

    protected void profiling() {
        if (++iterations % 1000 == 0) {
            if (time != 0) {
                Logger.debug("Time per 10k interrupts: %s ms", 1.0D * (now() - time) / 1000_000);
            }
            time = now();
        }
    }

    protected long now() {
        return System.nanoTime();
    }

    protected void sleep() {
        // Trying to slow to 100%, browsers resolution on the system
        // time is not accurate enough to check every interrurpt. So
        // we check every 4 interrupts.
        if ((interrupt % 4) == 0) {
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
        try {
            Thread.sleep(nanos / 1_000_000, (int) (nanos % 1_000_000));
        } catch (Exception ignored) {
            // do nothing
        }
    }

    public void changeFullSpeed() {
        fullSpeed = !fullSpeed;
        if (fullSpeed) {
            Logger.debug("Full speed mode");
            refreshRate = MAX_REFRESH_RATE;
        } else {
            Logger.debug("Normal speed mode");
            refreshRate = REFRESH_RATE;
        }
    }

    public void willReset() {
        willReset = true;
    }

    public void increaseDelay() {
        if (delay < 10) {
            delay++;
        } else {
            delay = (int) (delay / 0.8);
        }
        Logger.debug("Delay increased: %s", delay);
    }

    public void decreaseDelay() {
        delay = (int) (delay * 0.8);
        Logger.debug("Delay decreased: %s", delay);
    }

    @Override
    public int stateSize() {
        return SNAPSHOT_TIMINGS_SIZE;
    }

    @Override
    public void state(Bites bites) {
        validateState("Timings", bites);

        interrupt = (int) joinBites(bites, new Range(0, 3));
        refreshRate = (int) joinBites(bites, new Range(4, 7));
        willReset = bites.get(8) == 1;
        last = joinBites(bites, new Range(9, 16));
        delay = (int) joinBites(bites, new Range(17, 20));
        fullSpeed = bites.get(21) == 1;
        time = joinBites(bites, new Range(22, 29));
        iterations = (int) joinBites(bites, new Range(30, 33));
    }

    @Override
    public Bites state() {
        Bites bites = new Bites(stateSize());

        bites.set(new Range(0, 3), splitBites(interrupt, 4));
        bites.set(new Range(4, 7), splitBites(refreshRate, 4));
        bites.set(8, willReset ? 1 : 0);
        bites.set(new Range(9, 16), splitBites(last, 8));
        bites.set(new Range(17, 20), splitBites(delay, 4));
        bites.set(21, fullSpeed ? 1 : 0);
        bites.set(new Range(22, 29), splitBites(time, 8));
        bites.set(new Range(30, 33), splitBites(iterations, 4));

        return bites;
    }

    public String toStringDetails() {
        return String.format(
                "interrupt   : %s\n" +
                "refreshRate : %s\n" +
                "willReset   : %s\n" +
                "last        : %s\n" +
                "delay       : %s\n" +
                "fullSpeed   : %s\n" +
                "time        : %s\n" +
                "iterations  : %s\n",
                interrupt, refreshRate, willReset, last, delay, fullSpeed, time, iterations);
    }
}
