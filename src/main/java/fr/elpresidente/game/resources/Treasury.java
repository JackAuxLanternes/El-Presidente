package fr.elpresidente.game.resources;

import fr.elpresidente.game.difficulty.DifficultyController;
import org.json.simple.JSONObject;

class Treasury implements Consumable {

    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_AMOUNT_KEY = "value";
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
        this.amount -= this.updateAmountToSubstractWithDiffciulty(amount);
    }

    private int updateAmountToSubstractWithDiffciulty(int amount) {
        return (int) (amount * DifficultyController.getInstance().getDifficulty().getDifficultyEventMultiplier());
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
