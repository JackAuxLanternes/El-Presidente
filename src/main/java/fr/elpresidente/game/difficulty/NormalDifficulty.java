package fr.elpresidente.game.difficulty;

import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONObject;

public class NormalDifficulty implements Difficulty {

    private final double EVENT_MULTIPLIER = 1;
    private final int PERCENTAGE_LOOSE = 30;

    @Override
    public double getDifficultyEventMultiplier() {
        return EVENT_MULTIPLIER;
    }

    @Override
    public int getPercentageLoose() {
        return PERCENTAGE_LOOSE;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject difficulty = new JSONObject();
        difficulty.put(JSONKeys.CONSUMABLE_KEY_VALUE, "normal");

        return difficulty;
    }

    @Override
    public String toString() {
        return "normal";
    }
}
