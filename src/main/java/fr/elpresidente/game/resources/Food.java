package fr.elpresidente.game.resources;

import org.json.simple.JSONObject;

class Food implements Consumable {

    private int amount;

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
    public void substractAmount(int amount) {
        this.amount -= amount;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject consumablesFood = new JSONObject();
        consumablesFood.put("name", "food");
        consumablesFood.put("value", this.amount);

        return consumablesFood;
    }
}
