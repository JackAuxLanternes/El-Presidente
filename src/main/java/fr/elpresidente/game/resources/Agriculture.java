package fr.elpresidente.game.resources;

import fr.elpresidente.game.builders.RessourceBuilder;

class Agriculture implements Resource, RessourceBuilder {

    private int size;
    private final int PERCENTAGE_MULTIPLIER_AGRICULUTRE_IN_FOOD = 40;

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
        return this.size * this.PERCENTAGE_MULTIPLIER_AGRICULUTRE_IN_FOOD;
    }


}
