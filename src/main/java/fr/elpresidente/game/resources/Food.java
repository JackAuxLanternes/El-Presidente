package fr.elpresidente.game.resources;

import org.json.simple.JSONObject;

class Food implements Consumable {

    private int amount;
    private final String name = "food";
    private static final String  JSON_NAME_KEY = "name";
    private static final String JSON_AMOUNT_KEY = "value";

    public Food(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return this.amount;
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
        JSONObject consumablesFood = new JSONObject();
        consumablesFood.put(JSON_NAME_KEY, this.getName());
        consumablesFood.put(JSON_AMOUNT_KEY, this.amount);

        return consumablesFood;
    }
}
