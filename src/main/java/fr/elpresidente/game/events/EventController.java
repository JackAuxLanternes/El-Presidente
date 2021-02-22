package fr.elpresidente.game.events;

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

    public void findEvent(int year, Seasons season) {
        if (currentEvent != null && currentEvent.getTriggerEvent() != null) {
            setCurrentEvent(new Event(JSONTools.findJSONObjectInJSONArrayWithKeyValue(conditionalEvents, "id", currentEvent.getTriggerEvent())));
            return;
        }
        resetCurrentEvent();
        Event event = searchScriptedEvent(year, season);
        if (!isEventNull(event)) {
            setCurrentEvent(event);
        } else {
            setCurrentEvent(fillWithGenericEvent());
        }
    }

    private Event searchScriptedEvent(int year, Seasons season) {
        JSONObject jsonEvent = JSONTools.findJSONObjectForScriptedEvent(scriptedEvents, year, season);

        if (JSONTools.isJSONObjectNull(jsonEvent)) {
            return null;
        }

        return new Event(jsonEvent);
    }

    private Event fillWithGenericEvent() {
        Random random = new Random();
        return new Event((JSONObject) genericEvents.get(random.nextInt(genericEvents.size())));
    }

    private boolean isEventNull(Event event) {
        return event == null;
    }

    public void setScriptedEvents(JSONArray scriptedEvents) {
        this.scriptedEvents = scriptedEvents;
    }

    public void setConditionalEvents(JSONArray conditionalEvents) {
        this.conditionalEvents = conditionalEvents;
    }

    public void setGenericEvents(JSONArray genericEvents) {
        this.genericEvents = genericEvents;
    }

    private void resetCurrentEvent() {
        this.currentEvent = null;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    private void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }
}
