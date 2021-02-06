package fr.elpresidente.game.tools;

import fr.elpresidente.game.turn.Seasons;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class JSONTools {

    public static int extractIntFromJSONObject(JSONObject jsonObject, String key) {
        return Math.toIntExact((long) jsonObject.get(key));
    }

    public static String extractStringFromJSONObject(JSONObject jsonObject, String key) {
        return jsonObject.get(key).toString();
    }

    public static Seasons extractSeasonFromJSONObject(JSONObject jsonObject) {
        String season = (String) jsonObject.get("season");

        switch (season){
            case "WINTER":
                return Seasons.WINTER;

            case "SPRING":
                return Seasons.SPRING;

            case "SUMMER":
                return Seasons.SUMMER;

            case "AUTUMN":
                return Seasons.AUTUMN;
        }

        return null;
    }

    public static JSONObject findJSONObjectInJSONArrayWithKeyValue(JSONArray jsonArray, Object key, Object value) {
        for (Object element : jsonArray) {
            JSONObject jsonObject = (JSONObject) element;
            if (doesJSONObjectContainsKeyValue(jsonObject, key, value)) {
                return jsonObject;
            }
        }

        return null;
    }

    public static JSONObject findJSONObjectForScriptedEvent(JSONArray scenarioEvents, int yearValue, Seasons seasonValue) {
        for (Object element : scenarioEvents) {
            JSONObject eventJSONObject = (JSONObject) element;
            if (doesJSONObjectDateMatch(eventJSONObject, yearValue, seasonValue)) {
                return eventJSONObject;
            }
        }
        return null;
    }

    public static ArrayList<JSONObject> collectJSONArrayChilrenAsArrayList(JSONArray jsonArray) {
        ArrayList<JSONObject> values = new ArrayList<>();
        for (Object element : jsonArray) {
            values.add((JSONObject) element);
        }
        return values;
    }

    public static boolean doesJSONObjectContainsKeyValue(JSONObject jsonObject, Object key, Object value) {
        return jsonObject.containsKey(key) && jsonObject.containsValue(value);
    }

    public static boolean doesJSONObjectDateMatch(JSONObject eventObject, int year, Seasons season) {
        return extractIntFromJSONObject(eventObject, "year") == year && extractSeasonFromJSONObject(eventObject) == season;
    }

    public static boolean isJSONObjectNull(JSONObject jsonObject) {
        return jsonObject == null;
    }
}
