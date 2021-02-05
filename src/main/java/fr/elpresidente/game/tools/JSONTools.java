package fr.elpresidente.game.tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONTools {

    public static int extractIntFromJSONObject(JSONObject jsonObject, String key) {
        return Math.toIntExact((long) jsonObject.get(key));
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

    public static boolean doesJSONObjectContainsKeyValue(JSONObject jsonObject, Object key, Object value) {
        return jsonObject.containsKey(key) && jsonObject.containsValue(value);
    }
}
