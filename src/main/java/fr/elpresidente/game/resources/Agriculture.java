package fr.elpresidente.game.resources;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONObject;

class Agriculture implements Resource {

    private static final int PERCENTAGE_MULTIPLIER_AGRICULTURE_IN_FOOD = 40;
    private final String name = "agriculture";
    private int size;

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
    public void subtractSize(int size) {
        this.size -= this.updateSizeToSubtractWithDifficulty(size);
    }

    @Override
    public void updateSize(int size) {
        if (this.size > 0)
            addSize(size);
        else
            subtractSize(size);
    }

    private int updateSizeToSubtractWithDifficulty(int value) {
        return (int) (value * DifficultyController.getInstance().getDifficulty().getDifficultyEventMultiplier());
    }

    @Override
    public int getAnnualYields() {
        return this.size * PERCENTAGE_MULTIPLIER_AGRICULTURE_IN_FOOD;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject resourcesAgriculture = new JSONObject();
        resourcesAgriculture.put(JSONKeys.RESOURCE_KEY_NAME, this.getName());
        resourcesAgriculture.put(JSONKeys.RESOURCE_KEY_VALUE, this.size);

        return resourcesAgriculture;
    }


}
