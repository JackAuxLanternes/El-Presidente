package fr.elpresidente.game.mode;

import fr.elpresidente.game.events.Event;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.Seasons;
import org.json.simple.JSONObject;

public class ScenarioMode implements GameMode
{
    @Override
    public void setEvent(int year, Seasons season) {
        EventController controller = EventController.getInstance();

        if (controller.isCurrentEventAndTriggerNotNull()) {
            controller.setCurrentEvent(new Event(JSONTools.findJSONObjectInJSONArrayWithKeyValue(controller.getConditionalEvents(), JSONKeys.EVENT_TRIGGER_ID_KEY, controller.getCurrentEvent().getTriggerEvent())));
            return;
        }
        controller.resetCurrentEvent();
        Event event = controller.searchScriptedEvent(year, season);
        if (!controller.isEventNull(event)) {
            controller.setCurrentEvent(event);
        } else {
            controller.setCurrentEvent(controller.fillWithGenericEvent());
        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject game_mode = new JSONObject();
        game_mode.put(JSONKeys.CONSUMABLE_KEY_VALUE, "scenario");

        return game_mode;
    }

    @Override
    public String toString() {
        return "scenario";
    }
}
