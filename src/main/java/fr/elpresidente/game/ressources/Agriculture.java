package fr.elpresidente.game.ressources;

import fr.elpresidente.game.builders.RessourceBuilder;

class Agriculture implements Ressource, RessourceBuilder {

    private int size;
    private final int PERCENTAGE_MULTIPLIER_AGRICULUTRE_IN_FOOD = 40;

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

    @Override
    public int getAnnualYields() {
        return this.size * this.PERCENTAGE_MULTIPLIER_AGRICULUTRE_IN_FOOD;
    }


}
