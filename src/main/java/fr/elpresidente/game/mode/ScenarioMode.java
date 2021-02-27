package fr.elpresidente.game.mode;

import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONObject;

public class ScenarioMode implements GameMode {
    @Override
    public JSONObject toJSONObject() {
        JSONObject gamemode = new JSONObject();
        gamemode.put(JSONKeys.CONSUMABLE_KEY_VALUE, "scenario");

        return gamemode;
    }

    @Override
    public String toString() {
        return "scenario";
    }
}
