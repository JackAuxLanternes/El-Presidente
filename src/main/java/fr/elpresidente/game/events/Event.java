package fr.elpresidente.game.events;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Event {

    private JSONObject event;

    private String description;

    private ArrayList<String> choices;

    public Event(JSONObject event) {
        this.event = event;
    }
}
