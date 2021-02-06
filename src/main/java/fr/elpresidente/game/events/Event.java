package fr.elpresidente.game.events;

import fr.elpresidente.game.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Event {

    private JSONObject event;

    private String description;

    private ArrayList<JSONObject> choices;

    public Event(JSONObject event) {
        this.event = event;
        this.initEvent();
    }

    private void initEvent() {
        this.description = JSONTools.extractStringFromJSONObject(this.event, "description");
        this.choices = JSONTools.collectJSONArrayChilrenAsArrayList((JSONArray) this.event.get("choices"));
    }
}
