package fr.elpresidente.game.tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface JSONContent
{
    JSONObject getJSONObject();
    void setJSONObject(JSONObject jsonObject);

    JSONObject getDate();
    void setDate(JSONObject date);

    JSONArray getResources();
    void setResources(JSONArray resources);

    JSONArray getConsumable();
    void setConsumable(JSONArray consumable);

    JSONArray getFactions();
    void setFactions(JSONArray factions);
}
