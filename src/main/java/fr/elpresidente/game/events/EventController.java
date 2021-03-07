package fr.elpresidente.game.events;

import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.Seasons;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;

public class EventController {

    private static EventController instance;

    private JSONArray scriptedEvents;

    private JSONArray conditionalEvents;

    private JSONArray genericEvents;

    private Event currentEvent;

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public static EventController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new EventController();
        }

        return instance;
    }

    public Event searchScriptedEvent(int year, Seasons season) {
        JSONObject jsonEvent = JSONTools.findJSONObjectForScriptedEvent(scriptedEvents, year, season);

        if (JSONTools.isJSONObjectNull(jsonEvent)) {
            return null;
        }

        return new Event(jsonEvent, JSONKeys.EVENT_TYPE_SCENARIO);
    }

    public Event fillWithGenericEvent() {
        Random random = new Random();
        return new Event((JSONObject) genericEvents.get(random.nextInt(genericEvents.size())), JSONKeys.EVENT_TYPE_GENERIC);
    }

    public boolean isCurrentEventAndTriggerNotNull() {
        return currentEvent != null && currentEvent.getTriggerEvent() != null;
    }

    public boolean isEventNull(Event event) {
        return event == null;
    }

    public JSONArray getConditionalEvents() {
        return conditionalEvents;
    }

    public void setConditionalEvents(JSONArray conditionalEvents) {
        this.conditionalEvents = conditionalEvents;
    }

    public JSONArray getScriptedEvents() {
        return scriptedEvents;
    }

    public void setScriptedEvents(JSONArray scriptedEvents) {
        this.scriptedEvents = scriptedEvents;
    }

    public JSONArray getGenericEvents() {
        return genericEvents;
    }

    public void setGenericEvents(JSONArray genericEvents) {
        this.genericEvents = genericEvents;
    }

    public void resetCurrentEvent() {
        this.currentEvent = null;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public JSONArray getEventsByName(String name) {
        switch (name) {
            case JSONKeys.EVENT_TYPE_CONDITIONAL:
                return getConditionalEvents();

            case JSONKeys.EVENT_TYPE_GENERIC:
                return getGenericEvents();

            default:
                return getScriptedEvents();
        }
    }
}
