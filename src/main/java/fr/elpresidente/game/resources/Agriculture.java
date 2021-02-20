package fr.elpresidente.game.resources;

import fr.elpresidente.game.builders.RessourceBuilder;
import org.json.simple.JSONObject;

class Agriculture implements Resource, RessourceBuilder {

    private int size;
    private static final int PERCENTAGE_MULTIPLIER_AGRICULUTRE_IN_FOOD = 40;
    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_SIZE_KEY = "value";

    private final String name = "agriculture";

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
    public int getAnnualYields() {
        return this.size * PERCENTAGE_MULTIPLIER_AGRICULUTRE_IN_FOOD;
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
