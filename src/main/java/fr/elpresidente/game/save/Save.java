package fr.elpresidente.game.save;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Save {

    private JSONObject jsonObject;

    private JSONObject date;

    private JSONObject gameMode;

    private JSONObject difficulty;

    private JSONArray resources;

    private JSONArray consumable;

    private JSONArray factions;

    public Save(String jsonFileContent) {
        setJsonObject((JSONObject) JSONValue.parse(jsonFileContent));
        setDate((JSONObject) getJsonObject().get("date"));
        setDifficulty((JSONObject) getJsonObject().get("difficulty"));
        setGameMode((JSONObject) getJsonObject().get("gamemode"));
        setResources((JSONArray) getJsonObject().get("resources"));
        setConsumable((JSONArray) getJsonObject().get("consumable"));
        setFactions((JSONArray) getJsonObject().get("factions"));
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

    public JSONObject getGameMode() {
        return gameMode;
    }

    public void setGameMode(JSONObject gameMode) {
        this.gameMode = gameMode;
    }

    public JSONObject getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(JSONObject difficulty) {
        this.difficulty = difficulty;
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
}
