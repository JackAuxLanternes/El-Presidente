package fr.elpresidente.game.resources.consumable;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import org.json.simple.JSONObject;

class Food implements Consumable {

    private final String name = "food";
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
    public void updateAmount(int amount) {
        if (amount > 0)
            this.addAmount(amount);
        else
            this.subtractAmount(Math.abs(amount));
    }

    @Override
    public void subtractAmount(int amount) {
        this.amount -= this.updateAmountToSubtractWithDifficulty(amount);
    }

    private int updateAmountToSubtractWithDifficulty(int amount) {
        return (int) (amount * DifficultyController.getInstance().getDifficulty().getDifficultyEventMultiplier());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject consumablesFood = new JSONObject();
        consumablesFood.put(JSONKeys.CONSUMABLE_KEY_NAME, this.getName());
        consumablesFood.put(JSONKeys.CONSUMABLE_KEY_VALUE, this.amount);

        return consumablesFood;
    }

    @Override
    public void loadFromJSON(JSONObject jsonObject) {
        this.amount = JSONTools.extractIntFromJSONObject(jsonObject, JSONKeys.CONSUMABLE_KEY_VALUE);
    }
}
