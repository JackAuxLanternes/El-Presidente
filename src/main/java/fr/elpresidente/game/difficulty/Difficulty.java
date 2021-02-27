package fr.elpresidente.game.difficulty;

import org.json.simple.JSONObject;

public interface Difficulty {

    double getDifficultyEventMultiplier();

    int getPercentageLoose();

    JSONObject toJSONObject();

    String toString();
}
