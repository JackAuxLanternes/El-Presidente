package fr.elpresidente.game.JSON.save;

import fr.elpresidente.game.JSON.JSONContent;
import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Save implements JSONContent {

    private JSONObject jsonObject;

    private JSONObject date;

    private JSONObject gameMode;

    private JSONObject difficulty;

    private JSONObject lastEvent;

    private JSONArray resources;

    private JSONArray consumable;

    private JSONArray factions;

    public Save(String jsonFileContent) {
        setJSONObject((JSONObject) JSONValue.parse(jsonFileContent));
        setDate((JSONObject) getJSONObject().get(JSONKeys.SAVE_DATE_KEY));
        setDifficulty((JSONObject) getJSONObject().get(JSONKeys.SAVE_DIFFICULTY_KEY));
        setLastEvent((JSONObject) getJSONObject().get(JSONKeys.SAVE_LAST_EVENT_KEY));
        setGameMode((JSONObject) getJSONObject().get(JSONKeys.SAVE_GAMEMODE_KEY));
        setResources((JSONArray) getJSONObject().get(JSONKeys.SAVE_RESOURCES_KEY));
        setConsumable((JSONArray) getJSONObject().get(JSONKeys.SAVE_CONSUMABLE_KEY));
        setFactions((JSONArray) getJSONObject().get(JSONKeys.SAVE_FACTION_KEY));
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

    public JSONObject getGameMode() {
        return gameMode;
    }

    public void setGameMode(JSONObject gameMode) {
        this.gameMode = gameMode;
    }

    public JSONObject getLastEvent() {
        return this.lastEvent;
    }

    public void setLastEvent(JSONObject lastEvent) {
        this.lastEvent = lastEvent;
    }

    public JSONObject getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(JSONObject difficulty) {
        this.difficulty = difficulty;
    }


}
