package fr.elpresidente.game.resources.consumable;

import fr.elpresidente.game.builders.LoadFromSaveBuilder;
import org.json.simple.JSONObject;

public interface Consumable extends LoadFromSaveBuilder {

    int getAmount();

    void setAmount(int amount);

    void addAmount(int amount);

    void updateAmount(int amount);

    void subtractAmount(int amount);

    String getName();

    JSONObject toJSONObject();
}
