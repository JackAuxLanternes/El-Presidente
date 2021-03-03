package fr.elpresidente.game.mode;

import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONObject;

public class SandboxMode implements GameMode {
    @Override
    public JSONObject toJSONObject() {
        JSONObject game_mode = new JSONObject();
        game_mode.put(JSONKeys.CONSUMABLE_KEY_VALUE, "sandbox");

        return game_mode;
    }

    @Override
    public String toString() {
        return "sandbox";
    }
}
