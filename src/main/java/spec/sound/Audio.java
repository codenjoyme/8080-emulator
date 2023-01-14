package spec.sound;
import javax.sound.sampled.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Audio implements Runnable {

    public static final int CPU_SAMPLE_RATE = 2500;

    private ScheduledThreadPoolExecutor executor;
    private ScheduledFuture<?> future;

    private final SourceDataLine line;
    private final AudioFormat format;

    private byte[] buffer = new byte[100];
    private int index = 0;
    private AtomicBoolean set = new AtomicBoolean();
    private AtomicInteger state = new AtomicInteger();

    private volatile boolean paused;
    private volatile long period;

    public Audio() {
        try {
            // Thanks to the https://stackoverflow.com/a/32891220
            // select audio format parameters
            format = new AudioFormat(CPU_SAMPLE_RATE, 8, 1, false, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        start();
    }

    public void start() {
        period = 100;
        paused = false;
        executor = new ScheduledThreadPoolExecutor(1);
        schedule();
    }

    private void schedule() {
        future = executor.scheduleAtFixedRate(this, period, period, TimeUnit.MICROSECONDS);
    }

    @Override
    public void run() {
        if (paused) {
            return;
        }

        try {
            if (set.get()) {
                set.set(false);
                return;
            }
            tick();
            System.out.print("!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public void changePeriod(long period) {
        this.period = period;
        if (period > 0){
            if (future != null){
                future.cancel(true);
            }

            schedule();
        }
    }

    public long getPeriod() {
        return period;
    }

    public void write(int bite) {
        set.set(true);
        state.set(bite);
        tick();
    }

    public void tick() {
        buffer[index++] = state.byteValue();
        if (index >= buffer.length) {
            index = 0;
            play();
        }
    }

    public void play() {
        // prepare audio output
        try {
            line.open(format, buffer.length);
            line.start();
            // output wave form repeatedly
            line.write(buffer, 0, buffer.length);
            line.drain();
            line.stop();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}