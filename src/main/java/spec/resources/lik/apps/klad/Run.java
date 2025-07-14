package spec.resources.lik.apps.klad;

import spec.Application;
import spec.Main;
import spec.platforms.Lik;
import spec.platforms.PlatformFactory;

import static spec.resources.lik.apps.klad.Klad.NAME;

public class Run {

    public static final String MAIN_RESOURCES = "src/main/resources/";

    public static void main(String[] args) {
        String path = PlatformFactory.platform(Lik.NAME).app(NAME, ".rks");

        Main.onLoad = (Application app) -> {
            app.hardware().memory()
                    .write8arr(Klad.levelBegin(1), Klad.parseLevel("▓▓☺▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒H▒▓▓\n" +
                                                                   "▓▓ ▓ o ☻              o  ☻  H ▓▓\n" +
                                                                   "▓  ▓H▒▒  H▒▒    o  H▒▒▒▒▒   H ▓▓\n" +
                                                                   "▓ ▒▓H▒▒  H▒▒‾▒▒▒▒▒▒▒▒▒▒▒▒‾▒▒H▒▓▓\n" +
                                                                   "▓▓ ▓H▒▒‾▒H    H         j  oH ▓▓\n" +
                                                                   "▓  ▓H    H    H k     H▒▒‾▒▒▒ ▓▓\n" +
                                                                   "▓ ▒▓H    H ‾‾▒▒▒▒▒ ▒▒▒H     H ▓▓\n" +
                                                                   "▓▓ ▓H  o H           ▒▒▒H   H ▓▓\n" +
                                                                   "▓  ▓H ▒▒▒H‾▒‾▒▒         H‾‾‾H ▓▓\n" +
                                                                   "▓ ▒▓H    H    ▒‾▒▒‾▒▒▒‾‾H   H ▓▓\n" +
                                                                   "▓▓ ▓H    H   o   o o    H   H ▓▓\n" +
                                                                   "▓  ▓H  ▒▒▒‾H‾▒▒▒▒▒ ▒▒▒▒ H   H ▓▓\n" +
                                                                   "▓ ▒▓H   o  H   H        H   H ▓▓\n" +
                                                                   "▓▓ ▓▒‾‾▒▒H ‾‾‾‾H   ▒▒▒  H  H▒ ▓▓\n" +
                                                                   "▓  ▓     H     H▒▒‾▒▒▒‾▒▒  H  ▓▓\n" +
                                                                   "▓ ▒▓e   ▒▒‾▒‾▒‾H           H o▓▓\n" +
                                                                   "▓▓ ▓▒‾        ▒H     ‾‾▒H▒ H▒▒▓▓\n" +
                                                                   "▓  ▓o   ▒H‾▒‾▒▒▒▒▒‾▒▒   Ho H ▒▓▓\n" +
                                                                   "▓ ▒▓▒‾▒  H    o       ▒▒H▒▒▒ ▒▓▓\n" +
                                                                   "▓▓ ▓     H‾▒▒▒▒▒▒▒‾▒    H   o▒▓▓\n" +
                                                                   "▓▓       H              H  ▒▒▒▓▓\n" +
                                                                   "▓▓▓▓▓▓▓▓▓▓wwwwwwwwwwwwww▓▓▓▓▓▓▓▓\n"));
        };
        Main.main(new String[] {
                "./" + MAIN_RESOURCES, Lik.NAME, path
        });
    }
}
