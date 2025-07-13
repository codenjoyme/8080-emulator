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

        int level = 1;

        Main.onLoad = (Application app) -> {
            app.hardware().memory()
                    .write8arr(Klad.levelBegin(level), Klad.parseLevel(Klad.UPDATED_LEVEL));
        };
        Main.main(new String[] {
                "./" + MAIN_RESOURCES, Lik.NAME, path
        });
    }
}
