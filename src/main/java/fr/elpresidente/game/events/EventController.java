package fr.elpresidente.game.events;

import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.Seasons;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EventController {

    private static EventController instance;

    private JSONArray scriptedEvents;

    private JSONArray conditionalEvents;

    private Event currentEvent;

    public void findEvent(int year, Seasons season) {
        resetCurrentEvent();
        Event event = searchScriptedEvent(year, season);

        if (!isEventNull(event)) {
            setCurrentEvent(event);
        }
    }

    private Event searchScriptedEvent(int year, Seasons season) {
        JSONObject jsonEvent = JSONTools.findJSONObjectForScriptedEvent(scriptedEvents, year, season);

        if (JSONTools.isJSONObjectNull(jsonEvent)) {
            return null;
        }

        return new Event(jsonEvent);
    }

    private boolean isEventNull(Event event) {
        return event == null;
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

    private void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    private void resetCurrentEvent() {
        this.currentEvent = null;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }
}
