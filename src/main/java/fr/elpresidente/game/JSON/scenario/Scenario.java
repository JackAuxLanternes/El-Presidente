package fr.elpresidente.game.JSON.scenario;

import fr.elpresidente.game.JSON.JSONContent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Scenario implements JSONContent {

    private JSONObject jsonObject;

    private JSONObject date;

    private JSONArray resources;

    private JSONArray consumable;

    private JSONArray factions;

    private JSONArray scriptedEvents;

    private JSONArray conditionalEvents;

    private JSONArray genericEvents;

    private JSONObject supportersDistribution;

    public Scenario(String jsonFileContent) {
        setJSONObject((JSONObject) JSONValue.parse(jsonFileContent));
        setDate((JSONObject) getJSONObject().get("date"));
        setResources((JSONArray) getJSONObject().get("resources"));
        setConsumable((JSONArray) getJSONObject().get("consumable"));
        setFactions((JSONArray) getJSONObject().get("factions"));
        setScriptedEvents((JSONArray) getJSONObject().get("events"));
        setConditionalEvents((JSONArray) getJSONObject().get("conditional_events"));
        setGenericEvents((JSONArray) getJSONObject().get("generic_events"));
        setSupportersDistribution((JSONObject) getJSONObject().get("supporters_distribution"));
    }

    @Override
    public JSONObject getJSONObject() {
        return jsonObject;
    }

    @Override
    public void setJSONObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public JSONObject getDate() {
        return date;
    }

    @Override
    public void setDate(JSONObject date) {
        this.date = date;
    }

    @Override
    public JSONArray getResources() {
        return resources;
    }

    @Override
    public void setResources(JSONArray resources) {
        this.resources = resources;
    }

    @Override
    public JSONArray getConsumable() {
        return consumable;
    }

    @Override
    public void setConsumable(JSONArray consumable) {
        this.consumable = consumable;
    }

    @Override
    public JSONArray getFactions() {
        return factions;
    }

    @Override
    public void setFactions(JSONArray factions) {
        this.factions = factions;
    }

    public JSONObject getSupportersDistribution() {
        return supportersDistribution;
    }

    public void setSupportersDistribution(JSONObject supportersDistribution) {
        this.supportersDistribution = supportersDistribution;
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

    public JSONArray getGenericEvents() {
        return genericEvents;
    }

    public void setGenericEvents(JSONArray genericEvents) {
        this.genericEvents = genericEvents;
    }
}
