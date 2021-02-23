package fr.elpresidente.game.mode;

import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONObject;

public class SandboxMode implements GameMode {
    @Override
    public JSONObject toJSONObject() {
        JSONObject gamemode = new JSONObject();
        gamemode.put(JSONKeys.CONSUMABLE_KEY_VALUE, "sandbox");

        return gamemode;
    }

    @Override
    public String toString() {
        return "sandbox";
    }
}
