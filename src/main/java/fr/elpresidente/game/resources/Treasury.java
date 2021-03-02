package fr.elpresidente.game.resources;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import org.json.simple.JSONObject;

class Treasury implements Consumable {

    private final String name = "treasury";
    private int amount;

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
        JSONObject consumablesTreasury = new JSONObject();
        consumablesTreasury.put(JSONKeys.CONSUMABLE_KEY_NAME, this.getName());
        consumablesTreasury.put(JSONKeys.CONSUMABLE_KEY_NAME, this.amount);

        return consumablesTreasury;
    }

    @Override
    public void loadFromJSON(JSONObject jsonObject) {
        this.amount = JSONTools.extractIntFromJSONObject(jsonObject, JSONKeys.CONSUMABLE_KEY_VALUE);
    }
}
