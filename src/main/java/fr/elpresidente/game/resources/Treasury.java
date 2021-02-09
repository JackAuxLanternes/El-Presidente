package fr.elpresidente.game.resources;

import org.json.simple.JSONObject;

public class Treasury implements Consumable{

    public final static int PRICE_ONE_YIELD_AGRICULTURE = 8;
    private int amount;

    private final String name = "treasury";

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
        consumablesTreasury.put("name", this.getName());
        consumablesTreasury.put("value", this.amount);

        return consumablesTreasury;
    }


}
