package fr.elpresidente.game.scenario;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Scenario {

    private JSONObject jsonObject;

    private JSONObject date;

    private JSONArray factions;

    private JSONArray events;

    public Scenario(String jsonFileContent) {
        setJsonObject((JSONObject) JSONValue.parse(jsonFileContent));
        setDate((JSONObject) getJsonObject().get("date"));
        setFactions((JSONArray) getJsonObject().get("factions"));
        setEvents((JSONArray) getJsonObject().get("events"));
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getDate() {
        return date;
    }

    public void setDate(JSONObject date) {
        this.date = date;
    }

    public JSONArray getFactions() {
        return factions;
    }

    public void setFactions(JSONArray factions) {
        this.factions = factions;
    }

    public JSONArray getEvents() {
        return events;
    }

    public void setEvents(JSONArray events) {
        this.events = events;
    }
}
