package spec.resources.lik.apps.klad;

import spec.Point;
import spec.math.Bites;

import static spec.math.WordMath.hih;
import static spec.math.WordMath.loh;

public final class Klad {

    public static final int LEVELS_OFFSET = 0x1300;
    public static final int LEVEL_WIDTH = 32;
    public static final int LEVEL_HEIGHT = 22;
    public static final int ONE_BYTE_PER_TWO_CELL = 2;
    public static final int LEVEL_SETTINGS_PREFIX = 16;
    public static final int LEVEL_LENGTH = LEVEL_SETTINGS_PREFIX + LEVEL_WIDTH * LEVEL_HEIGHT / ONE_BYTE_PER_TWO_CELL;

    public static final String LEGEND = " okew░‾Hlj▒▓ki  ";
    public static final char HERO = '☺';
    public static final char HUNTER = '☻';
    public static final char HUNTER_UNDER_BRIDGE = '♥';
    public static final char BRIDGE = LEGEND.charAt(6);

    public static int levelBegin(int level) {
        return LEVELS_OFFSET + level * LEVEL_LENGTH;
    }

    public static final String[] LEVELS = new String[] {
            // Level 0
            "▓▓☺▒ ☻▒▓H▒▒H▒▒H▒▒H▒▒H▒▒H▓▒ ☻▒ ▓▓\n" +
            "▓▓ ▒▒▒▒▓H▓▒H▓▒H▓▒H▓▒H▓▒H▓▒▒▒▒ ▓▓\n" +
            "▓▓ ▒▒▒▒▒H▒▒H▒▒H▒▒H▒▒H▒▒H▒▒▒▒▒ ▓▓\n" +
            "▓▓      H  H  H  H  H  H      ▓▓\n" +
            "▓▓H‾H▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓H‾H▓▓\n" +
            "▓▓H H▓▒                  ▒▓H H▓▓\n" +
            "▓▓H H▓▒                  ▒▓H H▓▓\n" +
            "▓▓H H▓▒HHHHHHHHHHHHHHHHHH▒▓H H▓▓\n" +
            "▓▓H H▒▒                  ▒▒H H▓▓\n" +
            "▓▓▒H▓‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾▓H▒▓▓\n" +
            "▓▓▒H▓oooooooooooooooooooooo▓H▒▓▓\n" +
            "▓▓▒H▓w▓▓▓w▓▓▓ww▓▓www▓▓▓▓www▓H▒▓▓\n" +
            "▓▓▒H▓░▓▓░▓▓▓░░░▓░░░░░▓▓░░░░▓H▒▓▓\n" +
            "▓▓▒H▓░▓░▓▓▓▓░▓░▓░▓▓▓░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░░▓▓▓▓▓░▓░▓░░░░░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░▓░▓▓▓▓░▓░▓░▓▓▓░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░▓▓░▓▓▓░▓░▓░▓▓▓░▓▓░▓▓░▓H▒▓▓\n" +
            "▓▓▒H▓░▓▓▓░▓░░▓░▓░▓▓▓░▓░░░░░▓H▒▓▓\n" +
            "▓▓▒H▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓H▒▓▓\n" +
            "▓▓▒H                        H▒▓▓\n" +
            "▓▓▒H H▓‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾▓H H▒▓▓\n" +
            "▓▓▓▓▓▓▓wwwwwwwwwwwwwwwwww▓▓▓▓▓▓▓\n",

            // Level 1
            "▓▓☺▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒H▒▓▓\n" +
            "▓▓ ▓             ☻          H ▓▓\n" +
            "▓▓ ▓H▒▒  H▒▒       H▒▒▒▒▒   H ▓▓\n" +
            "▓▓ ▓H▒▒  H▒▒‾▒H▒▒▒▒▒▒▒▒▒▒‾▒▒H▒▓▓\n" +
            "▓▓ ▓H▒▒‾▒H    H         j   H ▓▓\n" +
            "▓▓ ▓H    H    H       H▒▒▒▒▒▒ ▓▓\n" +
            "▓▓ ▓H    H   ▒▒▒▒▒▒▒▒▒H     H ▓▓\n" +
            "▓▓ ▓H    H           ▒▒▒H   H ▓▓\n" +
            "▓▓ ▓H ▒▒▒H▒▒‾▒▒  k      H‾‾‾H ▓▓\n" +
            "▓▓ ▓H    H    ▒▒▒▒▒▒▒▒  H   H ▓▓\n" +
            "▓▓ ▓H    H       o o    H   H ▓▓\n" +
            "▓▓ ▓H  ▒▒▒▒▒‾▒▒H▒▒ ▒▒▒▒ H   H ▓▓\n" +
            "▓▓ ▓H          H        H   H ▓▓\n" +
            "▓▓ ▓▒‾‾▒▒H     H   ▒▒▒  H  H▒ ▓▓\n" +
            "▓▓ ▓     H     H▒▒‾▒▒▒‾▒▒  H  ▓▓\n" +
            "▓▓ ▓e   ▒▒▒▒▒▒‾H           H o▓▓\n" +
            "▓▓ ▓▒▒         H       ▒H▒ H▒▒▓▓\n" +
            "▓▓ ▓o   ▒H▒▒▒▒▒▒▒▒‾▒▒   H  H ▒▓▓\n" +
            "▓▓ ▓▒▒▒  H            ▒▒H▒▒▒ ▒▓▓\n" +
            "▓▓ ▓     H▒▒▒▒▒▒▒▒‾▒    H   o▒▓▓\n" +
            "▓▓       H              H  ▒▒▒▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓wwwwwwwwwwwwww▓▓▓▓▓▓▓▓\n",

            // Level 2
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒☻▒H▒▒▒▓▓\n" +
            "▓▓              e         H   ▓▓\n" +
            "▓▓   H▒▒▒▒‾‾‾▒▒▒▒▒▒‾‾‾▒▒▒▒▒   ▓▓\n" +
            "▓▓   H                        ▓▓\n" +
            "▓▓o  H                        ▓▓\n" +
            "▓▓▒H▒▒   ▒H▒▒▒▒‾‾‾‾‾▒▒▒▒▒H    ▓▓\n" +
            "▓▓ H      H              H    ▓▓\n" +
            "▓▓ H      H              H    ▓▓\n" +
            "▓▓ H o    H    o        oH    ▓▓\n" +
            "▓▓ H ▒H▒▒▒▒   H▒▒▒▒H▒   ▒▒▒H  ▓▓\n" +
            "▓▓ H  H       H    H       H  ▓▓\n" +
            "▓▓ H  H       H    H       H  ▓▓\n" +
            "▓▓ H  H       H    H       H  ▓▓\n" +
            "▓▓▒▒▒▒▒   H▒▒▒▒    H▒▒▒▒H  H  ▓▓\n" +
            "▓▓        H        H    H  H  ▓▓\n" +
            "▓▓        H        H    H  H  ▓▓\n" +
            "▓▓   o    H    o   H    H  H  ▓▓\n" +
            "▓▓   ▒▒H▒▒▒▒▒  ▒▒▒▒▒▒   ▒▒▒▒  ▓▓\n" +
            "▓▓     H                      ▓▓\n" +
            "▓▓     H                      ▓▓\n" +
            "▓▓☺    H                      ▓▓\n" +
            "▓▓▓▓▓▓▓▓▓wwwwwwwwwwwwwwwwwwwww▓▓\n",

            // Level 3
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒☻▒▒▒▒▒▒▒▒▒▒▒▒H▒▓▓\n" +
            "▓▓                    o ▒ j H ▓▓\n" +
            "▓▓  ‾H▒▒▒▒▒▒▒H▒▒▒▒▒▒▒▒▒ ▒H▒▒▒▒▓▓\n" +
            "▓▓ o H     k H       H   H▒H▒e▓▓\n" +
            "▓▓ ▒ H▒ ▒▒▒▒▒▒ ▒ H▒▒▒H▒‾▒▒ H▒▒▓▓\n" +
            "▓▓ ▒ H           H   ▒     H▒ ▓▓\n" +
            "▓▓ ▒ H   ‾▒▒▒▒‾▒‾▒▒▒▒▒H‾‾‾▒ ▒ ▓▓\n" +
            "▓▓ ▒ H▒‾H      ▒      H       ▓▓\n" +
            "▓▓ ▒    H‾▒▒▒▒ ▒ H▒▒▒▒▒‾▒‾▒▒▒▒▓▓\n" +
            "▓▓ ▒‾‾▒ H  o ▒ ▒ H            ▓▓\n" +
            "▓▓ ▒  ▒ H▒▒▒ ▒   ▓‾‾▒▒▒‾▒‾▒▒H ▓▓\n" +
            "▓▓ ▒o ▒ H  ▒ ▒▒▒ ▓www▓ o▒ ▒▒H ▓▓\n" +
            "▓▓ ▒▒ ▒ H      ▒ ▓▓▓▓▓ ▒▒   H ▓▓\n" +
            "▓▓ ▒▒ ▓w▓▒H▒‾▒         ▒▒‾▒▒H ▓▓\n" +
            "▓▓ ▒▒ ▓▓▓▒H▒ ▒▒▒‾▒▒▒▒     ▒▒H ▓▓\n" +
            "▓▓ ▒▒o    H         ▒‾‾▒▒ ▒▒H ▓▓\n" +
            "▓▓ ▒▒▒ ▒H▒▒▒‾▒▒▒▒▒▒       ▒▒H ▓▓\n" +
            "▓▓ ▒▒▒  H      o    ▓H‾▒    H ▓▓\n" +
            "▓▓ ▒▒▒▒▒H▒▒▒‾‾▒▒▒▒‾‾▓H ▒▒‾▒ H ▓▓\n" +
            "▓▓      H           oH ▒▒ ▒ H ▓▓\n" +
            "▓▓☺     H▓wwwwwwwwww▓▓wwww▓▓▓▓▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░▓▓▓▓▓▓\n",

            // Level 4
            "▓▓ ☻H                         ▓▓\n" +
            "▓▓  H         o   ‾‾‾‾‾‾‾‾‾‾H ▓▓\n" +
            "▓▓   H        ‾‾H           H ▓▓\n" +
            "▓▓ ▒▒ H         H  o‾‾‾‾H   H ▓▓\n" +
            "▓▓ ▒▒  H       H         H  H ▓▓\n" +
            "▓▓  j   H     H    H‾‾‾‾‾H  H ▓▓\n" +
            "▓▓ H▒‾‾‾‾H    H    H        H ▓▓\n" +
            "▓▓ H      H    H   H‾‾‾‾H o H ▓▓\n" +
            "▓▓ Hk      H    H       H   H ▓▓\n" +
            "▓▓ H▒       H   H  H‾‾‾‾H o H ▓▓\n" +
            "▓▓ H oooooo  HH  H H        H ▓▓\n" +
            "▓▓ H ▒‾‾‾‾‾ oH H H H‾‾‾‾H o H ▓▓\n" +
            "▓▓ He ooooo ‾‾   H      H   H ▓▓\n" +
            "▓▓  ▒ ▒‾‾‾‾‾‾‾H H ▒‾‾‾‾‾H ▒‾H ▓▓\n" +
            "▓▓             H            H ▓▓\n" +
            "▓▓   H‾‾‾‾‾‾‾H   H‾‾‾‾‾‾‾‾‾‾H ▓▓\n" +
            "▓▓   H        ‾‾‾             ▓▓\n" +
            "▓▓   H‾‾‾‾‾‾H     H‾‾‾‾‾‾‾‾‾H ▓▓\n" +
            "▓▓           ‾‾‾‾‾          H ▓▓\n" +
            "▓▓   H‾‾‾‾‾H       H‾‾‾‾‾‾‾‾  ▓▓\n" +
            "▓▓ ☺ H      ‾‾‾‾‾‾‾           ▓▓\n" +
            "▓▓▒▒▒▒wwwwwwwwwwwwwwwwwwwwwwww▓▓\n",

            // Level 5
            "▓▓    ☻                 ☻   ▓H▓▓\n" +
            "▓▓                          ▓H▓▓\n" +
            "▓▓ oHHHHHoHHHHHkHHHHHoHHHHHejH▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H▒▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ HHHoHHHHHoHHHHHoHHHHHoHHH ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ oHHHHHoHHHHHoHHHHHoHHHHHo ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ HHHoHHHHHoHHHHHoHHHHHoHHH ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ oHHHHHoHHHHHoHHHHHoHHHHHo ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓ HHHoHHHHHoHHHHHoHHHHHoHHH ▓▓▓\n" +
            "▓▓ H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓☺H  H  H  H  H  H  H  H  H ▓▓▓\n" +
            "▓▓▓▓ww▓ww▓ww▓ww▓ww▓ww▓ww▓ww▓w▓▓▓\n" +
            "▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓\n",

            // Level 6
            "▓▓  ☻                      ☻  H▓\n" +
            "▓▓  ▒▒▒▒▒▒▒▒‾H‾‾‾‾H‾▒▒▒▒▒▒▒▒  H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  oo  ▒ H    H ▒  oo  ▒  H▓\n" +
            "▓▓   ▒▒▒▒▒▒  H    H  ▒▒▒▒▒▒   H▓\n" +
            "▓▓  ▒  ko  ▒ H    H ▒  eo  ▒  H▓\n" +
            "▓▓  ▒▒▒▒▒▒▒▒ H    H ▒▒▒▒▒▒▒▒ ▓H▓\n" +
            "▓▓☺ l      l H    H l      l jH▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓wwww▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 7
            "▓▓ ☻           ▒H▓          ☻ ▓▓\n" +
            "▓▓             jH▓            ▓▓\n" +
            "▓▓H‾‾‾‾‾‾‾‾‾‾‾‾▓▓▓‾‾‾‾‾‾‾‾‾‾‾H▓▓\n" +
            "▓▓HoooooooooooooeooooooooooooH▓▓\n" +
            "▓▓H‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾H▓▓\n" +
            "▓▓Ho                        oH▓▓\n" +
            "▓▓Ho▓▓www▓▓▓www▓▓▓www▓▓w▓▓w▓oH▓▓\n" +
            "▓▓Ho▓░░░░░▓░░░░░▓░░░░░▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓░▓▓▓░▓░▓▓▓░▓░▓▓▓░▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓░▓▓▓░▓░▓▓▓▓▓░▓▓▓▓▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓░▓▓▓░▓░░░░░▓░▓▓▓▓▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓░░░░░▓▓▓▓▓░▓░▓▓▓▓▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓░░░░░▓░▓▓▓░▓░▓▓▓░▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓░▓▓▓░▓░░░░░▓░░░░░▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓░▓▓▓░▓▓░░░▓▓▓░░░▓▓░▓▓░▓oH▓▓\n" +
            "▓▓Ho▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓oH▓▓\n" +
            "▓▓HoooooooooooookooooooooooooH▓▓\n" +
            "▓▓H H H H H H H H H H H H H H ▓▓\n" +
            "▓▓ H H H H H H H H H H H H H H▓▓\n" +
            "▓▓H H H H H H H H H H H H H H ▓▓\n" +
            "▓▓☺H H H H H H H H H H H H H H▓▓\n" +
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓\n",

            // Level 8
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓☻☻▓▓▓▓▓▓▓▓▓▓▓H▓▓▓\n" +
            "▓▓     ▒▒▓ k▓       ▒▒▒▒▒   H ▓▓\n" +
            "▓▓H▒▒▒   ▒H▒▓o ▒▒▒H     ▒H▒▒▒▒▓▓\n" +
            "▓▓H▒o▒‾‾ ▒H▒ ‾‾▒▒▒H▒▒▒ ▒▒H    ▓▓\n" +
            "▓▓H▒▒▒ ‾‾▒H       H▒▒  j H   H▓▓\n" +
            "▓▓H  o   ▓H ‾‾‾▓H H ▒▒‾▒▒▒▒▒ H▓▓\n" +
            "▓▓H  ▒   ▓H    ▓H H          H▓▓\n" +
            "▓▓H      ▓H  ▓H▓H H‾‾‾‾‾‾‾‾‾‾H▓▓\n" +
            "▓▓H H▒‾  ▒▒▒‾ H▓H            H▓▓\n" +
            "▓▓H H         H▓H ▒▒▒▒H▒▒▓w▓‾H▓▓\n" +
            "▓▓H H  ▒‾▒▒▒‾▒▒▓H  o▒▓H▒ ▓▓▓ H▓▓\n" +
            "▓▓H H           H ‾▒▒▒H▒ ▒▒▒ H▓▓\n" +
            "▓▓H H ▓▓‾▓▓▓‾▓▓▓▓  ▒▒▒H▒ ▒o▒ H▓▓\n" +
            "▓▓H H               ▒▓H▒‾▒▒▒ H▓▓\n" +
            "▓▓H ▒‾‾‾▒▒▒ ▒▒▒▒▒‾▓   H  ▒▒▒ H▓▓\n" +
            "▓▓H               ▓ e H ▒ ▒▒ H▓▓\n" +
            "▓▓H▓▓wwwwwwwwwwwww▓ ▓ H ▒▒ o H▓▓\n" +
            "▓▓H▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ ▓ H ▒▒▒▒ H▓▓\n" +
            "▓▓H                 ▒ H      H▓▓\n" +
            "▓▓H ▒▒▒▒▒▒▒H▒▒▒▒▒▒▒▒▒▒H‾‾‾‾‾‾H▓▓\n" +
            "▓▓H☺      ▒           H      H▓▓\n" +
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒wwwww▒▓▓\n",

            // Level 9
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓H▓▓\n" +
            "▓▓▒▒▒▒▒▒▒▒▒▒H▒▒▒▒▒▒▒H▒▒▒▒▒▒H▓H▓▓\n" +
            "▓▓   ▓‾‾‾▒‾‾H▓‾‾‾▒‾‾H▓‾‾‾▒‾H▓H▓▓\n" +
            "▓▓   ▓   ▓ ☻H▓   ▓o H▓   ▓ H▓H▓▓\n" +
            "▓▓   ▓ooo▒o H▒ooo▓▒ H▒ooo▓oH▓H▓▓\n" +
            "▓▓  ▓▓▓▓▓▓‾‾‾▓▓▓▓▓‾‾‾▓▓▓▓▓▓H▓H▓▓\n" +
            "▓▓  l                     ☻H▓H▓▓\n" +
            "▓▓ ▓▓▓▓▓▓▓‾‾‾▓▓▓▓▓‾ ‾▓▓▓▓▓▓H▓H▓▓\n" +
            "▓▓ ool       l       l     HjH▓▓\n" +
            "▓ww▓▓▓▓H▓▓▓▓▓H▓▓▓▓▓‾▓▓▓▓▓▓▓▓▓▓▓▓\n" +
            "▓░░▓ ▓ H ▓   H   ▓www▓  o▓   ▓▓▓\n" +
            "▓▓▓▓ ▓ H ▓  ▓▓   ▓▓▓▓▓  H▓   ▓▓▓\n" +
            "▓▓   l H l  ▓    ▒ o    H▓ e ▓▓▓\n" +
            "▓▓▓H▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒H▓▓▓H▓▓▓\n" +
            "▓▓ H ▒   ▓   ▓‾‾‾▓‾‾‾▓▓▓▓▓  H▓▓▓\n" +
            "▓▓‾H‾▒ H ▓   ▓www▓www▓▓o▒▒  H▓▓▓\n" +
            "▓▓ H ▒ Ho▓  o▓░░░▓░░░▓H▒H▒ ▓H▓▓▓\n" +
            "▓▓▓H▓▓▓H▓▓H▓▓▓▓▓▓▓▓▓▓▓H▓H▓▓▓H▓▓▓\n" +
            "▓▓ H ▒ H ▓H     k▓   ▓H▓H▓  H▓▓▓\n" +
            "▓▓‾H‾▒ H ▓ ‾‾‾‾‾ ▓   ▓H▓H▓  H▓▓▓\n" +
            "▓▓☺H l H l ▒▒▒▒▒▒▒   ▒H▓Hl  H▓▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 10
            "▓▓▓▓▓☻▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓☻▓▓H▓▓\n" +
            "▓▓H▓▓HooH▓▓H▓▓H  H▓▓HooH▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H  H▓▓H  H  H▓▓H  H▓▓HooH▓▓H▓▓\n" +
            "▓▓▓▓▓H▓▓▓▓▓▓▓▓H▓▓▓▓▓H▓▓▓▓▓▓▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H  H  H▓▓HooH▓▓H  H  H▓▓H▓▓\n" +
            "▓▓H▓▓▓▓▓H▓▓H▓▓▓▓▓▓▓▓H▓▓▓▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓HooH▓▓H▓▓H  H▓▓HooH▓▓H  H▓▓H▓▓\n" +
            "▓▓H▓▓▓▓▓▓▓▓H▓▓H▓▓▓▓▓▓▓▓H▓▓▓▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H  H▓▓H  H▓ H  H  H▓▓H  H▓▓H▓▓\n" +
            "▓▓H▓▓▓▓▓H▓▓H▓▓H▓▓▓▓▓H▓▓▓▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓H▓▓\n" +
            "▓▓H☺ H  H▓▓HooH  H▓▓HooH▓▓HoeH▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 11
            "▓▓                          H▓▓▓\n" +
            "▓▓H‾‾‾‾‾‾‾‾‾‾‾‾▒            H▓▓▓\n" +
            "▓▓H            ▒            H▓▓▓\n" +
            "▓▓H          ▒▒▒▒▒          H▓▓▓\n" +
            "▓▓H         ▒▒▒ ▒▒▒         H▓▓▓\n" +
            "▓▓H           ▒o▒           H▓▓▓\n" +
            "▓▓H         ▒▒▒H▒▒▒         H▓▓▓\n" +
            "▓▓H        ▒▒▒ H ▒▒▒        H▓▓▓\n" +
            "▓▓H          ▒oHo▒          H▓▓▓\n" +
            "▓▓H         ▒▒▒H▒▒▒         H▓▓▓\n" +
            "▓▓H        ▒▒▒ H ▒▒▒        H▓▓▓\n" +
            "▓▓H          ▒oHo▒          H▓▓▓\n" +
            "▓▓H        ▒▒▒▒H▒▒▒▒        H▓▓▓\n" +
            "▓▓H       ▒▒▒▒ H ▒▒▒▒       H▓▓▓\n" +
            "▓▓H          ▒oHo▒          H▓▓▓\n" +
            "▓▓H       ▒▒▒▒▒H▒▒▒▒▒       H▓▓▓\n" +
            "▓▓H      ▒▒▒▒▒ H ▒▒▒▒▒      H▓▓▓\n" +
            "▓▓H         ▒  H  ▒        ▓H▓▓▓\n" +
            "▓▓H☺▓       ▒☻eHk☻▒        jH▓▓▓\n" +
            "▓▓▓▓▓‾‾‾‾▒▒▒▒▒▒▒▒▒▒▒▒▒‾‾‾‾‾▓▓▓▓▓\n" +
            "▓▓wwwwwww▒▒▒▒▒▒▒▒▒▒▒▒▒wwwwwww▓▓▓\n" +
            "▓▓░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░▓▓▓\n",

            // Level 12
            "wwwwwwwwwwww▓☻▓wwww▓☻▓wwwww▓H▓ww\n" +
            "░░▓▓▓▓▓▓▓░░░▓ ▓░░░░▓ ▓▓▓▓░░▓H▓░░\n" +
            "░░▓o   o▓░▓▓▓ ▓▓▓░░▓e  H▓░░▓H▓░░\n" +
            "░░▓▓H▓▓▓▓░▓  H  ▓░░▓▓▓▓H▓▓▓▓H▓▓░\n" +
            "░░░▓H▓░░░░▓H▓▓▓H▓░░░░░▓H   jH ▓░\n" +
            "░░░▓H▓░░░░▓H▓░▓H▓░░░░░▓▓H▓▓▓▓▓▓░\n" +
            "░░░▓H▓░░░░▓H▓░▓H▓░░░░░░▓H▓░░░░░░\n" +
            "░░░▓H▓▓▓▓▓▓H▓░▓H▓▓▓▓▓▓░▓H▓▓▓▓▓▓░\n" +
            "░░░▓H      H▓░▓H    o▓░▓H   o ▓░\n" +
            "░░░▓H▓▓▓▓▓▓H▓░▓H▓▓▓▓▓▓░▓▓H▓▓▓w▓░\n" +
            "░░░▓H▓░░░░▓H▓░▓H▓░░░░░░░▓H▓░▓▓▓░\n" +
            "░▓▓▓H▓░░░░▓H▓░▓H▓▓▓▓▓░░░▓H▓░░░░░\n" +
            "░▓  H▓░░░░▓H▓░▓H   o▓░░░▓H▓░▓▓▓▓\n" +
            "░▓H▓▓▓▓▓▓▓▓H▓▓▓▓▓▓H▓▓░▓▓▓H▓░▓ k▓\n" +
            "░▓H        H  ▓░░▓H▓░░▓  H▓░▓H▓▓\n" +
            "░▓H▓▓▓▓▓▓▓▓▓▓▓▓░░▓H▓░░▓H▓▓▓░▓H▓░\n" +
            "░▓H▓░░░░░░░░░░░░░▓H▓░░▓H▓░░░▓H▓░\n" +
            "░▓H▓░░░▓▓▓░▓▓▓▓▓▓▓H▓▓▓▓H▓▓░░▓H▓░\n" +
            "░▓H▓░░░▓o▓░▓ o    H    H ▓▓▓▓H▓░\n" +
            "░▓H▓▓▓▓▓H▓░▓w▓▓▓▓▓▓▓▓▓▓▓H    H▓░\n" +
            "░▓H☺    H▓░▓▓▓░░░░░░░░░▓▓▓▓▓▓▓▓░\n" +
            "░▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░\n",

            // Level 13
            "▓▓▓                           H▓\n" +
            "▓▓▓            ▒▒▒‾‾‾‾‾‾‾‾‾‾H H▓\n" +
            "▓▓▓            ▒o▒ ☻  ☻     H H▓\n" +
            "▓▓▓            ▒o▒          H H▓\n" +
            "▓▓▓            ▒o▒          H H▓\n" +
            "▓▓▓            ▒o▒          H H▓\n" +
            "▓▓▓            ▒o▒          H H▓\n" +
            "▓▓▓       ▒▒▒▒▒▒‾▒▒▒▒▒▒     H H▓\n" +
            "▓▓▓       ▒oooeooooooo▒     H H▓\n" +
            "▓▓▓       ▒▒▒▒▒▒‾▒▒▒▒▒▒‾‾‾  H H▓\n" +
            "▓▓▓            ▒k▒          H H▓\n" +
            "▓▓▓            ▒o▒      H‾‾‾‾ H▓\n" +
            "▓▓▓            ▒o▒      H     H▓\n" +
            "▓▓▓            ▒o▒      H ‾▓▓ H▓\n" +
            "▓▓▓            ▒o▒      H  ▒j H▓\n" +
            "▓▓▓            ▒o▒      H ▓▓▓‾H▓\n" +
            "▓▓▓            ▒o▒      H     ▓▓\n" +
            "▓▓▓            ▒o▒      H     ▓▓\n" +
            "▓▓▓            ▒o▒      H     ▓▓\n" +
            "▓▓o            ▒o▒      H     ▓▓\n" +
            "▓▓▓‾‾‾‾‾‾‾H  ☺ ▒o▒    H‾‾     ▓▓\n" +
            "▓▓▓wwwwwww▓▓▓▓▓▓▓▓▓▓▓▓▓wwwwwww▓▓\n",

            // Level 14
            "▓▓▓▓▓▓▓▓▓▓☻▓▓▓▓▓▓▓▓▓▓▓☻▓▓▓▓▓▓H▓▓\n" +
            "▓▓                          ▓H▓▓\n" +
            "▓▓                          ▓H▓▓\n" +
            "▓▓   H‾‾H‾‾H‾‾H‾▒‾H‾‾H‾‾H‾‾H▓H▓▓\n" +
            "▓▓   H  H  H  H ▒ H  H  H  H▓H▓▓\n" +
            "▓▓   H‾‾H‾‾H‾‾H▒ ▒H‾‾H‾‾H‾‾H▓H▓▓\n" +
            "▓▓   H  H  H  H▒o▒H  H  H  H▓H▓▓\n" +
            "▓▓   H‾‾H‾‾H‾‾▒‾‾‾▒‾‾H‾‾H‾‾H▓H▓▓\n" +
            "▓▓   H  H  H  ▒ o ▒  H  H  H▓H▓▓\n" +
            "▓▓   H‾‾H‾ H ▒‾‾‾‾‾▒ H ‾H‾‾H▓H▓▓\n" +
            "▓▓   H  H  H    o    H  H  H▓H▓▓\n" +
            "▓▓   H‾‾H‾ H▒ H ‾‾H ▒H ‾H‾‾H▓H▓▓\n" +
            "▓▓   H  H  H▒ H e H ▒H  H  H▓H▓▓\n" +
            "▓▓   H‾‾H‾ ▒  H‾‾ H  ▒ ‾H‾‾H▓H▓▓\n" +
            "▓▓   H  H  l ‾H k H‾ l  H  H▓H▓▓\n" +
            "▓▓   H‾‾H‾▒▒▒ H ‾‾H ▒▒▒‾H‾‾H▓H▓▓\n" +
            "▓▓   H  H     H o H     H  HjH▓▓\n" +
            "▓▓▓H▓H‾‾H ▒  ‾H‾‾ H‾  ▒ H‾‾H▓▓▓▓\n" +
            "▓▓▓H▓wwwww▒▒▒wwwwwww▒▒▒wwwww▓▓▓▓\n" +
            "▓▓☺H▓░░░░░░░░░░░░░░░░░░░░░░░░░▓▓\n" +
            "▓▓ H▓░░░░░░░░░░░░░░░░░░░░░░░░░▓▓\n" +
            "▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░▓▓\n",

            // Level 15
            "▓▓            ☻   ☻          H▓▓\n" +
            "▓▓              ▒            H▓▓\n" +
            "▓▓                           H▓▓\n" +
            "▓▓ H‾‾‾‾‾‾‾‾‾‾‾▒‾▒           H▓▓\n" +
            "▓▓ H           o o           H▓▓\n" +
            "▓▓ H          ▒▒‾▒▒          H▓▓\n" +
            "▓▓ H           o o           H▓▓\n" +
            "▓▓ H         ▒ ▒ ▒ ▒         H▓▓\n" +
            "▓▓ H           o k           H▓▓\n" +
            "▓▓ H        ▒▒ ▒ ▒ ▒▒        H▓▓\n" +
            "▓▓ H           o o           H▓▓\n" +
            "▓▓ H       ▒▒ ▒▒ ▒▒ ▒▒       H▓▓\n" +
            "▓▓ H           o e           H▓▓\n" +
            "▓▓ H      ▒▒ ▒▒▒ ▒▒▒ ▒▒      H▓▓\n" +
            "▓▓ H           o o           H▓▓\n" +
            "▓▓ H     ▒▒ ▒▒▒▒ ▒▒▒▒ ▒▒     H▓▓\n" +
            "▓▓ H           o o           H▓▓\n" +
            "▓▓ H    ▒▒ ▒▒▒▒▒ ▒▒▒▒▒ ▒▒    H▓▓\n" +
            "▓▓ H           o o           H▓▓\n" +
            "▓▓ H   ▒▒▒▒▒▒▒▒▒ ▒▒▒▒▒▒▒▒▒  ▓H▓▓\n" +
            "▓▓☺H                        jH▓▓\n" +
            "▓▓▓▓▓▓▓wwwwwwwwwwwwwwwwwww▓▓▓▓▓▓\n",

            // Level 16
            "▓▓                          ▓H▓▓\n" +
            "▓▓                  H  oHo  ▓H▓▓\n" +
            "▓▓   H▒▒▒▒▒▒▒▓▓▓▓▓▒▒H  HHH  ▓H▓▓\n" +
            "▓▓ o    k o o▓   ▓  H HH HH ▓H▓▓\n" +
            "▓▓ H    ▒‾▒▒▒▓H▒H▓▓ H H   H ▓H▓▓\n" +
            "▓▓ H          H▒Ho ☻H▒▒   ▓▓▓H▓▓\n" +
            "▓▓ H‾‾‾‾‾‾‾‾‾ H▒▒▒▒‾       H▓H▓▓\n" +
            "▓▓ H          H       ‾‾‾‾‾H▓H▓▓\n" +
            "▓▓HH ▒▒▒▒▒▒▒▒▒▒  oo▓‾▓ ▓ ▓ H▓H▓▓\n" +
            "▓▓H   ▓▓▓▓▓▓▓▓▓‾‾▓▓▓ ▓ ▓w▓ H▓H▓▓\n" +
            "▓▓H   ▓▓▓▓▓▓▓▓▓ww▓░░w▓ ▓▓▓ H▓H▓▓\n" +
            "▓▓H          ▓▓▓░░░░▓▓     H▓H▓▓\n" +
            "▓▓H  ▓▓‾      ▓▓▓▓▓▓▒ ▒  o H▓H▓▓\n" +
            "▓▓H▓wwww▓‾      ▒▒▒  ▓HH▒‾ H▓H▓▓\n" +
            "▓▓H▓░░░░▓ ooooH▓H▓H▒ ▓H▒▓o H▓H▓▓\n" +
            "▓▓H▓▓▓▓░▓ ‾‾‾‾‾‾H H      ‾ H▓H▓▓\n" +
            "▓▓H   ▓░▓  ▓    ▒‾▒‾▒‾▒‾▒  H▓H▓▓\n" +
            "▓▓H   ▓░▓  ▓▓▓▓▓H▒H▒H▒H▒H▒ H▓H▓▓\n" +
            "▓▓HH  ▓░▓  j ▓e☻▒H▒H▒H▒H▒H H▓H▓▓\n" +
            "▓▓ H  ▓░▓w▓▓ ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓H▓▓\n" +
            "▓▓☺H  ▓░░░▓                  H▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 17
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓☻▓☻▓▓▓▓▓▓▓▓▓▓▓▓H▓\n" +
            "▓                            ▓H▓\n" +
            "▓H‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾H▓H▓\n" +
            "▓H              ▒           H▓H▓\n" +
            "▓H              ▒           H▓H▓\n" +
            "▓H             ▒▒▒          H▓H▓\n" +
            "▓H H‾‾‾‾‾‾‾‾‾‾‾▒▒▒‾‾‾‾‾‾‾‾‾‾H▓H▓\n" +
            "▓H H          ▒▒ ▒▒         H▓H▓\n" +
            "▓H  ‾‾‾‾H     ▒▒o▒▒     H‾‾‾ ▓H▓\n" +
            "▓H H‾‾‾‾‾    ▒▒‾‾‾▒▒    ‾‾‾‾H▓H▓\n" +
            "▓H H         ▒▒ o ▒▒        H▓H▓\n" +
            "▓H ‾‾‾‾‾‾‾‾‾▒▒‾‾‾‾‾▒▒‾‾‾‾‾‾‾‾▓H▓\n" +
            "▓H          ▒▒  k  ▒▒        ▓H▓\n" +
            "▓H         ▒▒‾H‾‾ H‾▒▒       ▓H▓\n" +
            "▓H         ▒▒ H e H ▒▒       ▓H▓\n" +
            "▓H        ▒▒ ‾H ‾‾H‾ ▒▒      ▓H▓\n" +
            "▓H☺       ▒   H o H   ▒      jH▓\n" +
            "▓▓‾‾‾‾‾‾‾‾▒ ▒‾H‾‾ H‾▒ ▒‾‾‾‾‾‾▓▓▓\n" +
            "▓▓wwwwwwww▒▒▒wwwwwww▒▒▒wwwwww▓▓▓\n" +
            "▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓\n" +
            "▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓\n" +
            "▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓\n",

            // Level 18
            "▓▓            ☻   ☻          H▓▓\n" +
            "▓▓              ▒            H▓▓\n" +
            "▓▓                           H▓▓\n" +
            "▓▓   H‾‾‾‾‾‾‾‾‾▒▒▒           H▓▓\n" +
            "▓▓   H          o            H▓▓\n" +
            "▓▓   H        ▒‾▒‾▒          H▓▓\n" +
            "▓▓   H                       H▓▓\n" +
            "▓▓   H       ▒ ▒▒▒ ▒         H▓▓\n" +
            "▓▓   H        o   o          H▓▓\n" +
            "▓▓   H      ▒ ▒▒ ▒▒ ▒        H▓▓\n" +
            "▓▓   H       o     o         H▓▓\n" +
            "▓▓   H     ▒ ‾▓   ▓▒‾▒       H▓▓\n" +
            "▓▓   H        ▓www▓          H▓▓\n" +
            "▓▓   H    ▒ H ▓▓▓▓▓ H ▒      H▓▓\n" +
            "▓▓   H     o l       o       H▓▓\n" +
            "▓▓   H   ▒ ▒‾‾▒▒ ▒▒▒▒▒ ▒     H▓▓\n" +
            "▓▓   H    o    k      o      H▓▓\n" +
            "▓▓   H  ▒‾▒▒ ▒▒▒‾ ▒▒ ▒▒‾▒    H▓▓\n" +
            "▓▓  H   l        e           H▓▓\n" +
            "▓▓  H  ▒▒▒ ▒▒ ▒▒ ▒▒▒▒ ▒▒ ▒  ▓H▓▓\n" +
            "▓▓ ☺H                       jH▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 19
            "▓▓     H☻e               ▓☻▓▓▓▓▓\n" +
            "▓▓   o He‾‾‾‾‾‾‾‾‾‾‾H‾▓H lo   ▓▓\n" +
            "▓▓H‾‾‾ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾  H▒▒H▒H ▓▓\n" +
            "▓▓H▓      ‾‾‾‾‾‾‾‾‾‾‾H    o H ▓▓\n" +
            "▓▓H▓H‾‾‾‾‾‾‾‾‾‾‾‾‾‾  H  H▒H▒  ▓▓\n" +
            "▓▓H▓H o▓    o  o   o H  H o   ▓▓\n" +
            "▓▓H▓ ‾‾▓H‾‾‾‾  H‾‾‾H H   ▒H▒H ▓▓\n" +
            "▓▓H▓H o▓H     o o    H    o H ▓▓\n" +
            "▓▓H▓H‾‾▓H  ▓H‾‾H‾    H  H▒H▒  ▓▓\n" +
            "▓▓H▓    ‾‾H▓H o  H‾‾‾‾  H o   ▓▓\n" +
            "▓▓H▓   o  H▓H ▒  H       ▒H▒H ▓▓\n" +
            "▓▓H▓ o▓▓  H▓H o  H H‾o    o H ▓▓\n" +
            "▓▓H▓ H    H▓H ▒  H H ▒  H▒H▒  ▓▓\n" +
            "▓▓H▓w▓H▓‾‾‾‾H    H H o  H o   ▓▓\n" +
            "▓▓H▓▓▓H▓▓‾▓▓▓ H‾▒▒ ▓‾▒‾‾▒▒H▒  ▓▓\n" +
            "▓▓H▓  H▓     H▓‾‾‾   o        ▓▓\n" +
            "▓▓H▓H▓▓▓ l   H▓▓▓▓‾▓‾▒‾‾H▒ ▒H ▓▓\n" +
            "▓▓H▓H▓   ‾‾‾‾jkjo    o   l  H ▓▓\n" +
            "▓▓H▓H▓ ‾‾▓‾‾‾▓H▓▓▓‾▓‾▒   H‾▒▒ ▓▓\n" +
            "▓▓H▓H ☺▓o     H          H    ▓▓\n" +
            "▓▓H ‾‾‾▓‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾▓▓\n" +
            "▓▓▓▓▓▓▓▓wwwwwwwwwwwwwwwwwwwwww▓▓\n",

            // Level 20
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓H▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
            "▓▓▓        ☻    H    ☻        ▓▓\n" +
            "▓▓▓             H             ▓▓\n" +
            "▓▓▓H▒▒▒▒H       H       H▒▒▒▒H▓▓\n" +
            "▓▓▓H▓ooo▓       H       ▓ooo▓H▓▓\n" +
            "▓▓▓H▓ooo▓       H       ▓ooo▓H▓▓\n" +
            "▓▓▓H▓ooo▒       H       ▒ooo▓H▓▓\n" +
            "▓▓▓H▒▒▒▒▒‾‾‾‾‾  H  ‾‾‾‾‾▒▒▒▒▒H▓▓\n" +
            "▓▓▓H▓‾‾‾▓ ▒     H     ▒ ▓‾‾‾▓H▓▓\n" +
            "▓▓▓H▓ooo▓ ▓H    H    H▓ ▓ooo▓H▓▓\n" +
            "▓▓▓H▓ooo▓ ▓H    H    H▓ ▓ooo▓H▓▓\n" +
            "▓▓▓H▓ooo▓ ▓H    H    H▓ ▓ooo▓H▓▓\n" +
            "▓▓▓H      ▓H▓▓  H  ▓▓H▓      H▓▓\n" +
            "▓▓▓H H▓▓▓▓▓▓▓▓▓‾H‾▓▓▓▓▓▓▓▓▓H H▓▓\n" +
            "▓▓▓‾‾H▓░░░░░░░░w▓w░░░░░░░░▓H‾‾▓▓\n" +
            "▓▓▓  H▓░░░░░░░░░░░░░░░░░░░▓H  ▓▓\n" +
            "▓▓▓  H▓░░░░░░░░░░░░░░░░░░░▓H  ▓▓\n" +
            "▓▓▓  H▓░░░░░░░░░░░░░░░░░░░▓H  ▓▓\n" +
            "▓▓▓  H▓░░░░░░░░░░░░░░░░░░░▓H  ▓▓\n" +
            "▓▓▓  H▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓H  ▓▓\n" +
            "▓▓▓  H          ☺          H  ▓▓\n" +
            "▓▓▓ww▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ww▓▓\n",

            // Level 21
            "▓▓▓▓▓☻▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓☻▓H▓▓\n" +
            "▓▓H        ▒     ▒          ▓H▓▓\n" +
            "▓▓H     oo ▒ oo  ▒  H‾      ▓H▓▓\n" +
            "▓▓H    H‾‾H▒ ‾‾H ▓  H   o   ▓H▓▓\n" +
            "▓▓H‾▓‾ H  H▒   H ▓  H   ▒H  ▓H▓▓\n" +
            "▓▓H ▓‾H‾‾‾ ‾‾‾‾ ‾▓  H   H  e▓H▓▓\n" +
            "▓▓H ‾‾‾‾   ▒        H   H ▒▒▒H▓▓\n" +
            "▓▓H‾‾‾ oooo         H   H ▓ jH▓▓\n" +
            "▓▓H   ▒‾‾‾‾▒‾‾‾‾    H o H ▒▒▒▒▓▓\n" +
            "▓▓H▓                H ▓▓▓ ▓▓▓▓▓▓\n" +
            "▓▓H▒    ‾‾‾H    o   H       j ▓▓\n" +
            "▓▓H▒▒▒H▓ o H o ▒▓   ▒‾▒▒▒▒▒‾‾H▓▓\n" +
            "▓▓   ▓H▓ ‾‾H‾‾  ▓wwwwwwwwwww▓H▓▓\n" +
            "▓▓o  ▓H▓   H    ▓░░░░░░░░░░░▓H▓▓\n" +
            "▓▓H  ▓H▓   H    ▓▓▓▓▓▓▓▓▓▓▓▓▓H▓▓\n" +
            "▓▓H  ▓H▓ o H o               H▓▓\n" +
            "▓▓Ho ▓H▓ ‾‾H‾‾ H‾‾ooook      H▓▓\n" +
            "▓▓HH  H▓   H   H  ▒▒▒▒▒▒     H▓▓\n" +
            "▓▓ H‾‾     H   H  H‾‾‾‾H     H▓▓\n" +
            "▓▓ H▒▒▒▒ o H o  ‾‾‾     ‾‾‾‾‾H▓▓\n" +
            "▓▓☺H▓    ‾‾ ‾▒               H▓▓\n" +
            "▓▓▓▓wwwwwwwwwwwwwwwwwwwwwwwww▓▓▓\n",

            // Level 22
            "▓▓  ☻                    ☻  ▓H▓▓\n" +
            "▓▓                          ▓H▓▓\n" +
            "▓▓ HoH oo HoH oo HoH oo HoH ▓H▓▓\n" +
            "▓▓  H ‾‾‾‾ H ‾‾‾‾ H ‾‾‾‾ H  ▓H▓▓\n" +
            "▓▓  H      H      H      H  ▓H▓▓\n" +
            "▓▓  H HooH H HoeH H HooH H  ▓H▓▓\n" +
            "▓▓  H  ‾‾  H  ‾‾  H  ‾‾  H  ▓H▓▓\n" +
            "▓▓ HoH oo HoH oo HoH oo HoH ▓H▓▓\n" +
            "▓▓  H ‾‾‾‾ H ‾‾‾‾ H ‾‾‾‾ H  ▓H▓▓\n" +
            "▓▓  H      H      H      H  ▓H▓▓\n" +
            "▓▓  H HooH H HooH H HooH H  ▓H▓▓\n" +
            "▓▓  H  ‾‾  H  ‾‾  H  ‾‾  H  ▓H▓▓\n" +
            "▓▓ HoH oo HoH oo HoH oo HoH ▓H▓▓\n" +
            "▓▓  H ‾‾‾‾ H ‾‾‾‾ H ‾‾‾‾ H ▒▓H▓▓\n" +
            "▓▓  H      H      H      H ▒▓H▓▓\n" +
            "▓▓  H HooH H HokH H HooH H ▒jH▓▓\n" +
            "▓▓  H  ‾‾  H  ‾‾  H  ‾‾  H ▒▒H▓▓\n" +
            "▓▓ HoH oo HoH oo HoH oo HoH  H▓▓\n" +
            "▓▓  H ‾‾‾‾ H ‾‾‾‾ H ‾‾‾‾ H   H▓▓\n" +
            "▓▓  H      H      H      H   H▓▓\n" +
            "▓▓☺ H      H      H      H   H▓▓\n" +
            "▓▓▓▓▓wwwwwwwwwwwwwwwwwwwwwwww▓▓▓\n",

            // Level 23
            "▓▒▒▒▒▒▒▒▒▒☻▒▒▒▒▒▒▒▒▒▒▒☻▒▒▒▒▒▒H▒▓\n" +
            "▓ Hk  o                  o  oH ▓\n" +
            "▓ H▒‾‾‾                  ‾‾‾▒H ▓\n" +
            "▓ H      o oe ‾▓H▓‾ oo o     H ▓\n" +
            "▓ Ho     ▒‾▒▒▓▒▒H▒▒▓▒▒‾▒    oH ▓\n" +
            "▓ H▒            H           ▒H ▓\n" +
            "▓ H             H            H ▓\n" +
            "▓ Ho    ooooooo H ooooooo   oH ▓\n" +
            "▓ H▒  ‾‾‾‾‾‾‾‾‾‾H‾‾‾‾‾‾‾‾‾  ▒H ▓\n" +
            "▓ H             H            H ▓\n" +
            "▓ Ho       oooo H oooo      oH ▓\n" +
            "▓ H▒      ‾‾‾‾‾‾H‾‾‾‾‾‾     ▒H ▓\n" +
            "▓ Hl            H           jH ▓\n" +
            "ww▓▒H           H          H▒▓ww\n" +
            "░░▓▒H     ‾▓▒▒▒▒H▒▒▒▒▓‾    H▒▓░░\n" +
            "░░▓H       ▒ o ▒H▒ o ▒      H▓░░\n" +
            "░░▓H       ▒▒▒▒▒H▒▒▒▒▒      H▓░░\n" +
            "░░▓H            H           H▓░░\n" +
            "░░▓H☺        oo H oo        H▓░░\n" +
            "░░▓H▒▒‾‾‾‾▒▒▒‾‾▒H▒‾‾▒▒▒‾‾‾▒▒H▓░░\n" +
            "░░░wwwwwwwwwwwwwwwwwwwwwwwwww░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n",

            // Level 24
            "▓▓  o                     H o ▓▓\n" +
            "▓▓  H H‾‾‾‾‾‾‾‾‾          H H ▓▓\n" +
            "▓▓  H‾o       ☻‾▒‾☻       o‾H ▓▓\n" +
            "▓▓  H H      H     H      H H ▓▓\n" +
            "▓▓   ‾H       ‾‾H‾‾       H‾  ▓▓\n" +
            "▓▓    H                   H   ▓▓\n" +
            "▓▓  o H                   H o ▓▓\n" +
            "▓▓  H H        ▒ ▒        H H ▓▓\n" +
            "▓▓  H‾o       jo oj       o‾H ▓▓\n" +
            "▓▓  H H       ▒▒ ▒▒       H H ▓▓\n" +
            "▓▓   ‾H       jk ej       H‾  ▓▓\n" +
            "▓▓    H      ▒▒▒ ▒▒▒      H   ▓▓\n" +
            "▓▓  o H       jo oj       H o ▓▓\n" +
            "▓▓  H H     ▒▒▒▒ ▒▒▒▒     H H ▓▓\n" +
            "▓▓  H‾o       jo oj       o‾H ▓▓\n" +
            "▓▓  H H    ▒▒▒▒▒ ▒▒▒▒▒    H H ▓▓\n" +
            "▓▓   ‾H       jo oj       H‾  ▓▓\n" +
            "▓▓    H   ▒▒▒▒▒▒ ▒▒▒▒▒▒   H   ▓▓\n" +
            "▓▓    H       jo oj       H   ▓▓\n" +
            "▓▓    H▒ ▒▒▒▒▒▒▒ ▒▒▒▒▒▒▒ ▒H   ▓▓\n" +
            "▓▓☺   Hj                 jH   ▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓www▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 25
            "▓o▓☻▓▓  ooe ooo    ▓       ☻▓ H▓\n" +
            "▓H▓ ▓▓ ‾‾‾‾‾‾‾‾H ‾H▓H▓ ▓  ▓ ▓ H▓\n" +
            "▓H▓ ▓▓wwwwwwwww▓▓ H▓H▓ ▓ww▓ ▓ H▓\n" +
            "▓H▓ ▓▓▓▓▓▓▓▓▓▓▓▓▓ H▓H▓ ▓░░▓ ▓ H▓\n" +
            "▓Ho         H    ▓H▓H▓ ▓░░▓ ▓ H▓\n" +
            "▓ ▓‾▓H▓▓▓‾H H▓▓  ▓H▓H▓ ▓░░▓ ▓ H▓\n" +
            "▓ ▓  H▓▓▓ H H▓▓▓H▓H▓H▓ ▓░░▓ ▓ H▓\n" +
            "▓ ▓  H▓▓  H H▓▓▓H▓H▓H▓ ▓░░▓ ▓ H▓\n" +
            "▓ o  H▓▓▓H▓▓▓▓▓▓H▓H▓HH ▓▓▓▓ ▓ H▓\n" +
            "▓ ▓‾▓H ooH▓    ▓H▓H  H      ▓ H▓\n" +
            "▓ ▓ ▓H▓▓▓H▓     H H▓▓H▓H    ▓ H▓\n" +
            "▓ ▓ ▓H▓▓▓H▓H▓ww▓▓▓H▓▓H▓▓▓H‾▓▓ H▓\n" +
            "▓ ▓ ▓H▓▓▓H▓H▓▓▓   H▓ H  ▓H  ▓ H▓\n" +
            "▓ l    ▓ H▓H      ▓▓H▓▓o▓H  ▓ H▓\n" +
            "▓ ▓H▓H▓▓H▓▓H      l H ▓H▓H  ▓ H▓\n" +
            "  ▓HoH▓▓H  H‾▓▓▓▓‾▓▓▓H▓H▓H  ▓ H▓\n" +
            "w▓▓H▓H▓▓H▓▓H ▓k ▓w▓ ▓H▓H▓H  ▓ H▓\n" +
            "▓  H H▓▓H ▓▓▓▓▓HH▓ o▓H▓H▓▓▓ ▓ H▓\n" +
            "▓H▓▓▓HooH▓o   o▓HoH▓ww▓H▓   ▓▓H▓\n" +
            "▓H ▓▓▓▓▓▓▓▓HH▓▓▓▓▓H ▓▓▓H▓o▓  jH▓\n" +
            "▓H☺   oo   H  oo  H  o H H▓ H▓w▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓w▓▓▓\n",

            // Level 26
            "▓▓▒▒▒▒▒☻▒▒▒▒▒▒▒▒▒▒▒▒☻▒▒▒▒▒▒▒▒H▓▓\n" +
            "▓▓                   ▒       H▓▓\n" +
            "▓▓                   ▒   H‾  H▓▓\n" +
            "▓▓        o o  H o e ▒   H ▒▒H▓▓\n" +
            "▓▓     ‾▒▒▒▒▒▒▒H▒▒▒▒▒▒  ▓H ▒jH▓▓\n" +
            "▓▓             H         H ▓▓▓▓▓\n" +
            "▓▓ ‾‾‾H        H          H   ▓▓\n" +
            "▓▓ ▒   ‾‾‾‾‾H  H  H‾‾‾‾‾  ‾‾‾H▓▓\n" +
            "▓▓o▒        H  H  H     o  o▓H▓▓\n" +
            "▓▓o▒    H‾‾‾‾  H  ‾‾‾‾H ▓  ▒▒H▓▓\n" +
            "▓▓o▒    H    o H o    H ▓ww▓▓H▓▓\n" +
            "▓▓o▒    H    o H o    H ▓▓▓▓▓H▓▓\n" +
            "▓▓ ▒    H     H H     H      H▓▓\n" +
            "▓▓ ▒     ‾‾‾‾‾ H ‾‾‾‾‾       H▓▓\n" +
            "▓▓o▒ H‾‾‾‾‾    H    ‾‾‾‾‾H   H▓▓\n" +
            "▓▓o▒ H         H         H   H▓▓\n" +
            "▓▓o▒ H H▓▓▓▓▓▓▓H▓▓▓▓▓▓▓H H   H▓▓\n" +
            "▓▓k▒ H H       H       H H  ▒H▓▓\n" +
            "▓▓▓▒ H ▓ o o o☺H o o o ▓ H  H ▓▓\n" +
            "▓▓▓▓‾‾‾▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓‾‾‾▓▓▓▓▓\n" +
            "▓▓                            ▓▓\n" +
            "▓▓wwwwwwwwwwwwwwwwwwwwwwwwwwww▓▓\n",

            // Level 27
            "▓▓            H               ▓▓\n" +
            "▓▓            H j             ▓▓\n" +
            "▓▓ ‾♥‾♥‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾H▓▓\n" +
            "▓▓H            o   o   o     H▓▓\n" +
            "▓▓H            H   H   ‾‾H   H▓▓\n" +
            "▓▓H‾‾‾‾‾‾H    H HoH H    H   H▓▓\n" +
            "▓▓ ooeo  H   H   H   H   H   H▓▓\n" +
            "▓▓‾‾‾‾‾  H‾‾‾‾       ‾‾‾‾‾   H▓▓\n" +
            "▓▓                 H‾‾‾‾‾    H▓▓\n" +
            "▓▓                 H         H▓▓\n" +
            "▓▓                 H‾‾‾      H▓▓\n" +
            "▓▓                 H     ▓‾‾‾▓▓▓\n" +
            "▓▓  ‾‾‾‾‾‾‾‾‾H     H          ▓▓\n" +
            "▓▓           H ‾‾H H‾‾‾‾‾     ▓▓\n" +
            "▓▓   ▓       H▒  H H      oo  ▓▓\n" +
            "▓▓H▓H▓H▓H▓ ▓   o H H     H▒▒H ▓▓\n" +
            "▓▓H▓H▓H▓H▓w▓   ▓ H H     HooH ▓▓\n" +
            "▓▓H▓H▓H▓H▓░▓www▓ H H▓ooo H▒▒H ▓▓\n" +
            "▓▓H▓H▓H▓Hk▓▓░░░▓ H H▒▒▒▒ HooH ▓▓\n" +
            "▓▓H▓H H▓▓▓▓▓▓▓▓▓ H H▒▒▒▒ H▒▒H ▓▓\n" +
            "▓▓H☺▒▒▒▒         H H     H  H ▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 28
            "▓▓                     ☻   ☻ H▓▓\n" +
            "▓▓   o   o   o   o   o   k   H▓▓\n" +
            "▓▓  H‾H H‾H H‾H H‾H H‾H H‾H  H▓▓\n" +
            "▓▓  H H H H H H H H H H H H  H▓▓\n" +
            "▓▓  H H H H H H H H H H H H  H▓▓\n" +
            "▓▓  H HoH HoH HoH HoH HoH H  H▓▓\n" +
            "▓▓  H  ‾   ‾   ‾   ‾   ‾     H▓▓\n" +
            "▓▓  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓H       H▓▓\n" +
            "▓▓                  ▓ ▓▓▓H▓▓ H▓▓\n" +
            "▓▓  o o o o o o o o o o  H   H▓▓\n" +
            "▓▓ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾H   H▓▓\n" +
            "▓▓     ▒           ▒     H   H▓▓\n" +
            "▓▓     ▒▒▒▒▒▒▒▒▒▒▒▒▒H▒   H   H▓▓\n" +
            "▓▓    o   o   o   o   o  H   H▓▓\n" +
            "▓▓   H▓H H▓H H▓H H▓H H▓H H   H▓▓\n" +
            "▓▓   H H H H H H H H H H H  ▓H▓▓\n" +
            "▓▓   H H H H H H H H H H H  ▓H▓▓\n" +
            "▓▓ ☺ H HoH HoH HoH HoH HoH  jH▓▓\n" +
            "▓▓ ‾‾▓ ▓▓▓ ▓▓▓ ▓▓▓ ▓▓▓ ▓▓▓ ▓▓▓▓▓\n" +
            "▓▓                            ▓▓\n" +
            "▓▓wwwwwwwwwwwwwwwwwwwwwwwwwwww▓▓\n" +
            "▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓\n",

            // Level 29
            "▓▓    ☻         H        ☻    ▓▓\n" +
            "▓▓     H        H   H‾‾‾H     ▓▓\n" +
            "▓▓     ‾‾‾H     H       H     ▓▓\n" +
            "▓▓ H‾H    H ‾‾‾‾ ‾‾‾‾ ‾‾‾‾‾‾H ▓▓\n" +
            "▓▓ H  ‾‾‾‾                  H ▓▓\n" +
            "▓▓ H   ‾‾‾‾‾‾‾‾‾H‾‾‾‾‾‾‾‾   H ▓▓\n" +
            "▓▓ H            H           H ▓▓\n" +
            "▓▓ ‾‾‾‾H        H       H‾‾‾‾ ▓▓\n" +
            "▓▓     H        H       H     ▓▓\n" +
            "▓▓     H        H       H     ▓▓\n" +
            "▓▓     H    ▓▓▓▓H▓▓▓    H     ▓▓\n" +
            "▓▓     H    ▓▓▓▓H▓▒▒    H     ▓▓\n" +
            "▓▓▓▓▓  H     ▒▒▓H▓H▓‾‾‾‾H  ▓▓▓▓▓\n" +
            "▓▓e    H‾‾‾‾▓▓H▒H▒H▓www▓H    o▓▓\n" +
            "▓▓▓▓▓▓ ▓wwww▓▓▓▓H▓▓▓▓▓▓▓▓ ▓▓▓▓▓▓\n" +
            "▓▓     ▓▓▓▓▓▓  ▓Hj            ▓▓\n" +
            "▓▓ k           ▓▓▓▓▓H▓▓▓▓‾▓▓H ▓▓\n" +
            "▓▓H▓▓▓‾▓▓▓▓▓▓       H       H ▓▓\n" +
            "▓▓H         ▓       H         ▓▓\n" +
            "▓▓H▒▒▒▒▒▒▒H▒▒▒▒▒H▒▒▒▒▒▒▒▒▒▒▒▒H▓▓\n" +
            "▓▓ o      H    ☺H o o ▒▒▒    H▓▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n",

            // Level 30
            "▓☻      H                ☻     ▓\n" +
            "▓       H    H‾‾‾‾‾‾H      H‾‾H▓\n" +
            "▓        H j         ‾‾‾‾‾‾   H▓\n" +
            "▓ ▓wwwwww▓‾‾‾‾ wwwwwwwww▓  ▓w▓H▓\n" +
            "▓ ▓▓▓▓▓▓▓▓▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓  ▓▓▓H▓\n" +
            "▓    o    k  o o  o o  e o o oH▓\n" +
            "▓  ooHoo  H  H H  H H  H H H H ▓\n" +
            "▓ H‾‾H‾‾H H  H H  H H  H H H H ▓\n" +
            "▓ H  H  H H  H HooH H  H H H H ▓\n" +
            "▓ H  H  H H oH H‾‾H H oH H H H ▓\n" +
            "▓ HooHooH HoHH H  H HoHH H H H ▓\n" +
            "▓  ‾‾H‾‾ ‾HH H H  H HH H H H H ▓\n" +
            "▓    H    H  H H  H H  H HoHoH ▓\n" +
            "▓ ▒  H ▓   ‾▒ ▒ ▒▒ ▒ ▒▒ ▒ ‾ ‾  ▓\n" +
            "▓H▓▓▓▓▓▓▓▓▓wwwwwwwwwwwwwwwwww▓▓▓\n" +
            "▓H▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
            "▓H                             ▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓H▓\n" +
            "▓                             H▓\n" +
            "▓H▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
            "▓H              ☺              ▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n"
    };

    public static String buildLevel(Bites bites) {
        StringBuilder sb = new StringBuilder();
        int x1 = bites.get(0x00);
        int y1 = bites.get(0x02);
        int x2 = bites.get(0x05);
        int y2 = bites.get(0x07);
        int x3 = bites.get(0x0A);
        int y3 = bites.get(0x0C);
        boolean two = bites.get(0x0F) == 0x18; // 0xFF если один охотник, 0x18 если два

        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x += ONE_BYTE_PER_TWO_CELL) {
                int byteValue = bites.get(y * LEVEL_WIDTH / ONE_BYTE_PER_TWO_CELL + x / ONE_BYTE_PER_TWO_CELL + LEVEL_SETTINGS_PREFIX);
                int firstCell = hih(byteValue);
                int secondCell = loh(byteValue);
                sb.append(LEGEND.charAt(firstCell));
                sb.append(LEGEND.charAt(secondCell));
            }
            sb.append('\n');
        }
        int lineWidth = LEVEL_WIDTH + 1;
        sb.setCharAt(y1 * lineWidth + x1, HERO);
        setHunter(sb, y2 * lineWidth + x2);
        if (two) {
            setHunter(sb, y3 * lineWidth + x3);
        }

        return sb.toString();
    }

    private static void setHunter(StringBuilder sb, int index) {
        if (sb.charAt(index) == BRIDGE) {
            sb.setCharAt(index, HUNTER_UNDER_BRIDGE);
        } else {
            sb.setCharAt(index, HUNTER);
        }
    }

    public static Bites parseLevel(String level) {
        Point player = null;
        Point hunter1 = null;
        Point hunter2 = null;

        Bites bites = new Bites(LEVEL_LENGTH);

        String[] lines = level.split("\n");
        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            String line = lines[y];
            for (int x = 0; x < LEVEL_WIDTH; x++) {
                Point point = new Point(x, y);
                char cell = line.charAt(x);
                if (cell == HERO) {
                    player = point;
                } else if (cell == HUNTER || cell == HUNTER_UNDER_BRIDGE) {
                    if (hunter1 == null) {
                        hunter1 = point;
                    } else {
                        hunter2 = point;
                        if (hunter2.getX() < hunter1.getX()) {
                            Point temp = hunter1;
                            hunter1 = hunter2;
                            hunter2 = temp;
                        }
                    }
                }
            }
            lines[y] = line.replace(HERO, ' ')
                    .replace(HUNTER, ' ')
                    .replace(HUNTER_UNDER_BRIDGE, LEGEND.charAt(6));
        }

        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            String line = lines[y];
            for (int x = 0; x < LEVEL_WIDTH; x += ONE_BYTE_PER_TWO_CELL) {
                char firstCell = line.charAt(x);
                char secondCell = line.charAt(x + 1);

                int firstCharacter = LEGEND.indexOf(firstCell);
                int secondCharacter = LEGEND.indexOf(secondCell);

                int byteValue = (firstCharacter << 4) | secondCharacter;
                bites.set(y * LEVEL_WIDTH / ONE_BYTE_PER_TWO_CELL + x / ONE_BYTE_PER_TWO_CELL + LEVEL_SETTINGS_PREFIX, byteValue);
            }
        }

        // TODO #48 Я пока не понимаю что это за данные, как пойму - смогу их установить разумно
        bites.set(0x00, player == null ? 0 : player.getX());
        bites.set(0x01, 0x00);
        bites.set(0x02, player == null ? 0 : player.getY());
        bites.set(0x03, 0x00);
        bites.set(0x04, 0x02); // TODO #48
        bites.set(0x05, hunter1 == null ? 0 : hunter1.getX());
        bites.set(0x06, 0x00);
        bites.set(0x07, hunter1 == null ? 0 : hunter1.getY());
        bites.set(0x08, 0x00);
        bites.set(0x09, 0x04); // TODO #48
        bites.set(0x0A, hunter2 == null ? 0 : hunter2.getX());
        bites.set(0x0B, 0x00);
        bites.set(0x0C, hunter2 == null ? 0 : hunter2.getY());
        bites.set(0x0D, 0x00);
        bites.set(0x0E, 0x04); // TODO #48
        bites.set(0x0F, (hunter2 == null) ? 0xFF : 0x18); // 0xFF если один охотник, 0x18 если два
        return bites;
    }

}
