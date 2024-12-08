package spec;

import static spec.Constants.*;

public abstract class HardwareData implements Data {

    private Memory memory;
    private IOPorts ports;
    private Video video;

    public HardwareData(Memory memory, IOPorts ports, Video video) {
        this.memory = memory;
        this.ports = ports;
        this.video = video;
    }

    @Override
    public abstract int in8(int port);

    @Override
    public abstract void out8(int port, int bite);

    @Override
    public int read8(int addr) {
        if (PORTS.includes(addr)) {
            return ports.read8(addr);
        }
        return memory.read8(addr);
    }

    @Override
    public void write8(int addr, int bite) {
        if (ROM.includes(addr)) {
            // в ПЗУ не пишем
            return;
        }

        if (SCREEN.includes(addr)) {
            if (memory.read8(addr) != bite) {
                // было изменение ячейки видеопамяти
                video.plot(addr - SCREEN.begin(), bite);
            }
        }

        if (PORTS.includes(addr)) {
            ports.write8(addr, bite);
        }

        memory.write8(addr, bite);
    }
}