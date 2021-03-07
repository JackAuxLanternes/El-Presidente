package fr.elpresidente.game.mode;

import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.turn.Seasons;
import org.json.simple.JSONObject;

public class SandboxMode implements GameMode {
    @Override
    public void setEvent(int year, Seasons season) {
        EventController controller = EventController.getInstance();

        controller.setCurrentEvent(controller.fillWithGenericEvent());
    }

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
