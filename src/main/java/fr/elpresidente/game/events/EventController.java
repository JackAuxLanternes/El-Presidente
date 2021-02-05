package fr.elpresidente.game.events;

import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.Seasons;
import org.json.simple.JSONArray;

public class EventController {

    private static EventController instance;

    private JSONArray scriptedEvents;

    private JSONArray conditionalEvents;

    public Event findEvent(int year, Seasons season) {
        //To search event in JSON: JSONTools.findJSONObjectForScriptedEvent(scriptedEvents, year, season));

        return null;
    }

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public static EventController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new EventController();
        }

        return instance;
    }

    public void setScriptedEvents(JSONArray scriptedEvents) {
        this.scriptedEvents = scriptedEvents;
    }

    public void setConditionalEvents(JSONArray conditionalEvents) {
        this.conditionalEvents = conditionalEvents;
    }
}
