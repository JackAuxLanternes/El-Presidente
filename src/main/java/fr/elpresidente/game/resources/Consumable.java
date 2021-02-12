package fr.elpresidente.game.resources;

import org.json.simple.JSONObject;

public interface Consumable {

    int getAmount();

    void setAmount(int amount);

    void addAmount(int amount);

    void subtractAmount(int amount);

    String getName();

    JSONObject toJSONObject();
}
