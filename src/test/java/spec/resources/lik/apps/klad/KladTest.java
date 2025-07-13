package spec.resources.lik.apps.klad;

import org.junit.Test;
import spec.math.Bites;

import static org.junit.Assert.assertEquals;

public class KladTest {

    @Test
    public void testMap() {
        // given
        String mem = "      00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F \n" +
                     "0000: 02 00 00 00 02 05 00 00 00 04 1B 00 00 00 04 18 \n" +
                     "0010: BB 0A 00 AB 7A A7 AA 7A A7 AA 7A A7 BA 00 A0 BB \n" +
                     "0020: BB 0A AA AB 7B A7 BA 7B A7 BA 7B A7 BA AA A0 BB \n" +
                     "0030: BB 0A AA AA 7A A7 AA 7A A7 AA 7A A7 AA AA A0 BB \n" +
                     "0040: BB 00 00 00 70 07 00 70 07 00 70 07 00 00 00 BB \n" +
                     "0050: BB 76 7B AA AA AA AA AA AA AA AA AA AA B7 67 BB \n" +
                     "0060: BB 70 7B A0 00 00 00 00 00 00 00 00 0A B7 07 BB \n" +
                     "0070: BB 70 7B A0 00 00 00 00 00 00 00 00 0A B7 07 BB \n" +
                     "0080: BB 70 7B A7 77 77 77 77 77 77 77 77 7A B7 07 BB \n" +
                     "0090: BB 70 7A A0 00 00 00 00 00 00 00 00 0A A7 07 BB \n" +
                     "00A0: BB A7 B6 66 66 66 66 66 66 66 66 66 66 6B 7A BB \n" +
                     "00B0: BB A7 B1 11 11 11 11 11 11 11 11 11 11 1B 7A BB \n" +
                     "00C0: BB A7 B4 BB B4 BB B4 4B B4 44 BB BB 44 4B 7A BB \n" +
                     "00D0: BB A7 B5 BB 5B BB 55 5B 55 55 5B B5 55 5B 7A BB \n" +
                     "00E0: BB A7 B5 B5 BB BB 5B 5B 5B BB 5B B5 BB 5B 7A BB \n" +
                     "00F0: BB A7 B5 5B BB BB 5B 5B 55 55 5B B5 BB 5B 7A BB \n" +
                     "0100: BB A7 B5 B5 BB BB 5B 5B 5B BB 5B B5 BB 5B 7A BB \n" +
                     "0110: BB A7 B5 BB 5B BB 5B 5B 5B BB 5B B5 BB 5B 7A BB \n" +
                     "0120: BB A7 B5 BB B5 B5 5B 5B 5B BB 5B 55 55 5B 7A BB \n" +
                     "0130: BB A7 BB BB BB BB BB BB BB BB BB BB BB BB 7A BB \n" +
                     "0140: BB A7 00 00 00 00 00 00 00 00 00 00 00 00 7A BB \n" +
                     "0150: BB A7 07 B6 66 66 66 66 66 66 66 66 6B 70 7A BB \n" +
                     "0160: BB BB BB B4 44 44 44 44 44 44 44 44 4B BB BB BB";

        Bites bites = Bites.ofClean(mem);
        assertEquals(mem, bites.toString());

        String level = Klad.buildLevel(bites);
        assertEquals("▓▓☺▒ ☻▒▓H▒▒H▒▒H▒▒H▒▒H▒▒H▓▒ ☻▒ ▓▓\n" +
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
                     "▓▓▓▓▓▓▓wwwwwwwwwwwwwwwwww▓▓▓▓▓▓▓\n", level);

        assertEquals(mem, Klad.parseLevel(level).toString());
    }
}