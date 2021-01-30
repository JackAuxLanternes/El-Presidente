package fr.elpresidente.game.resources;

import fr.elpresidente.game.builders.RessourceBuilder;

class Industry implements Resource, RessourceBuilder {

    private int size;
    private final int PERCENTAGE_MULTIPLIER_INDUSTRY_IN_TREASURY = 10;

    @Override
    public void init() {
        
    }

    @Override
    public void loadFromSave() {

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addSize(int size) {
        this.size += size;
    }

    public int getAnnualYields() {
        return this.size * this.PERCENTAGE_MULTIPLIER_INDUSTRY_IN_TREASURY;
    }

}