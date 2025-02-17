package spec;

import spec.math.Bites;
import spec.state.StateProvider;

import java.awt.*;

import static spec.Constants.*;
import static spec.Video.COLORS;

public class GraphicControl implements StateProvider {

    public static final int SNAPSHOT_GRAPHIC_STATE_SIZE = 1;

    private final Graphic graphic;

    public static String[] IO_DRAW_MODE_INFO = new String[] {
            "0: Border highlights active window (and audio mode)",
            "1: Border highlights writing byte to Port A",
            "2: Border highlights writing byte to Port B",
            "3: Border highlights writing byte to Port C",
            "4: Border highlights writing byte to Port RgSYS"
    };
    private int ioDrawMode = 0;

    public GraphicControl(Container parent) {
        graphic = new Graphic(SCREEN_WIDTH, SCREEN_HEIGHT, BORDER_WIDTH, parent);
    }

    public Video.Drawer getDrawer() {
        return graphic;
    }

    public void printIO(int port, int bite) {
        switch (ioDrawMode) {
            case 0 : {
                if (port != BORDER_PORT) {
                    return;
                }
            } break;

            case 1 : {
                if (port != IOPorts.PortA) {
                    return;
                }
            } break;

            case 2 : {
                if (port != IOPorts.PortB) {
                    return;
                }
            } break;

            case 3 : {
                if (port != IOPorts.PortC) {
                    return;
                }
            } break;

            case 4 : {
                if (port != IOPorts.RgRGB) {
                    return;
                }
            } break;

        }

        graphic.refreshBorder();
        graphic.changeColor(COLORS[bite]);
    }

    public void nextDrawMode() {
        int mode = ioDrawMode() + 1;
        if (mode == 5) {
            mode = 0;
        }
        ioDrawMode(mode);
    }

    public int ioDrawMode() {
        return ioDrawMode;
    }

    public void ioDrawMode(int mode) {
        ioDrawMode = mode;
        if (ioDrawMode == 0) {
            printIO(BORDER_PORT, 0x30);
        } else {
            printIO(BORDER_PORT, 0x00);
        }
        graphic.refreshBorder();
        Logger.debug("IO Draw Mode: %s", IO_DRAW_MODE_INFO[ioDrawMode]);
    }

    @Override
    public int stateSize() {
        return SNAPSHOT_GRAPHIC_STATE_SIZE;
    }

    @Override
    public Bites state() {
        Bites bites = new Bites(stateSize());

        bites.set(0, ioDrawMode());

        return bites;
    }

    @Override
    public void state(Bites bites) {
        validateState("graphic", bites);

        ioDrawMode(bites.get(0));
    }
}
