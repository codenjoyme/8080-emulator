package spec;

import static spec.Constants.*;

public abstract class HardwareData implements Data {

    private Hardware hard;

    public HardwareData(Hardware hard) {
        this.hard = hard;
    }

    @Override
    public abstract void out8(int port, int bite);

    @Override
    public int read8(int addr) {
        if (PORTS.includes(addr)) {
            return hard.ports().read8(addr);
        }
        return hard.memory().read8(addr);
    }

    @Override
    public void write8(int addr, int bite) {
        if (ROM.includes(addr)) {
            // в ПЗУ не пишем
            return;
        }

        if (SCREEN.includes(addr)) {
            if (hard.memory().read8(addr) != bite) {
                // было изменение ячейки видеопамяти
                hard.video().plot(addr, bite);
            }
        }

        if (PORTS.includes(addr)) {
            hard.ports().write8(addr, bite);
        }

        hard.memory().write8(addr, bite);
    }

}
