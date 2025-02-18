package spec;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spec.platforms.Lik;
import spec.platforms.Platform;
import spec.platforms.PlatformFactory;
import spec.state.JsonState;

public class RomSwitcher implements JsonState {

    protected String platform = Lik.NAME;

    private final Hardware hard;

    public RomSwitcher(Hardware hard) {
        this.hard = hard;
    }

    public void load(String base, String rom, String command) {
        hard.loadData(base, rom, platform, command);
    }

    public void loadRoms(String base) {
        Platform platform = PlatformFactory.platform(this.platform);
        Logger.debug("Load ROMs for platform '%s'", platform.name());
        platform.loadRom(base, hard.roms());
        hard.reset();
    }

    public void nextRom(String base) {
        selectRom(base, PlatformFactory.next(platform));
    }

    public void selectRom(String base, String platform) {
        this.platform = platform;
        Logger.debug("Switch platform to %s", platform);
        hard.pause();
        loadRoms(base);
        hard.reset();
        Logger.debug("Platform switched");
    }

    public Platform current() {
        return PlatformFactory.platform(platform);
    }

    @Override
    public JsonElement toJson() {
        JsonObject result = new JsonObject();

        result.addProperty("platform", platform);

        return result;
    }

    @Override
    public void fromJson(JsonElement element) {
        JsonObject json = element.getAsJsonObject();

        platform = json.get("platform").getAsString();
    }
}