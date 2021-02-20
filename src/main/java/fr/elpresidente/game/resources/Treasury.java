package fr.elpresidente.game.resources;

import org.json.simple.JSONObject;

public class Treasury implements Consumable {

    public final static int PRICE_ONE_YIELD_AGRICULTURE = 8;
    private int amount;
    private final String name = "treasury";
    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_AMOUNT_KEY = "value";

    public Treasury(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public void subtractAmount(int amount) {
        this.amount -= amount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject consumablesTreasury = new JSONObject();
        consumablesTreasury.put(JSON_NAME_KEY, this.getName());
        consumablesTreasury.put(JSON_AMOUNT_KEY, this.amount);

        return consumablesTreasury;
    }


}
