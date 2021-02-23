package fr.elpresidente.game.difficulty;

import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONObject;

public class HardDifficulty implements Difficulty {

    private final double EVENT_MULTIPLIER = 2;
    private final int PERCENTAGE_LOOSE = 50;

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
        difficulty.put(JSONKeys.CONSUMABLE_KEY_VALUE, "hard");

        return difficulty;
    }

    @Override
    public String toString() {
        return "hard";
    }
}
