package fr.elpresidente.game.resources;

import fr.elpresidente.game.builders.RessourceBuilder;
import fr.elpresidente.game.difficulty.DifficultyController;
import org.json.simple.JSONObject;

class Industry implements Resource, RessourceBuilder {

    private int size;
    private static final int PERCENTAGE_MULTIPLIER_INDUSTRY_IN_TREASURY = 10;
    private final String name = "industry";
    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_SIZE_KEY = "value";

    @Override
    public void init() {

    }

    @Override
    public void loadFromSave() {

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void addSize(int size) {
        this.size += size;
    }

    @Override
    public void substractSize(int size) {

        this.size -= this.updateSizeToSubstractWithDiffciulty(size);
    }

    @Override
    public void updateSize(int size) {
        if (this.size > 0)
            addSize(size);
        else
            substractSize(size);
    }

    private int updateSizeToSubstractWithDiffciulty(int value) {
        return (int) (value * DifficultyController.getInstance().getDifficulty().getDifficultyEventMultiplier());
    }

    @Override
    public int getAnnualYields() {
        return this.size * PERCENTAGE_MULTIPLIER_INDUSTRY_IN_TREASURY;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject resourcesAgriculture = new JSONObject();
        resourcesAgriculture.put(JSON_NAME_KEY, this.getName());
        resourcesAgriculture.put(JSON_SIZE_KEY, this.size);

        return resourcesAgriculture;
    }

}
