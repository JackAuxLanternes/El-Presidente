package fr.elpresidente.game.scenario;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Scenario {

    private JSONObject jsonObject;

    private JSONObject date;

    private JSONArray resources;

    private JSONArray consumable;

    private JSONArray factions;

    private JSONArray scriptedEvents;

    private JSONArray conditionalEvents;

    public Scenario(String jsonFileContent) {
        setJsonObject((JSONObject) JSONValue.parse(jsonFileContent));
        setDate((JSONObject) getJsonObject().get("date"));
        setResources((JSONArray) getJsonObject().get("resources"));
        setConsumable((JSONArray) getJsonObject().get("consumable"));
        setFactions((JSONArray) getJsonObject().get("factions"));
        setScriptedEvents((JSONArray) getJsonObject().get("events"));
        setConditionalEvents((JSONArray) getJsonObject().get("conditional_events"));
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

    public JSONArray getResources() {
        return resources;
    }

    public void setResources(JSONArray resources) {
        this.resources = resources;
    }

    public JSONArray getConsumable() {
        return consumable;
    }

    public void setConsumable(JSONArray consumable) {
        this.consumable = consumable;
    }

    public JSONArray getFactions() {
        return factions;
    }

    public void setFactions(JSONArray factions) {
        this.factions = factions;
    }

    public JSONArray getScriptedEvents() {
        return scriptedEvents;
    }

    public void setScriptedEvents(JSONArray scriptedEvents) {
        this.scriptedEvents = scriptedEvents;
    }

    public JSONArray getConditionalEvents() {
        return conditionalEvents;
    }

    public void setConditionalEvents(JSONArray conditionalEvents) {
        this.conditionalEvents = conditionalEvents;
    }
}
