package fr.elpresidente.game.ressources;

import fr.elpresidente.game.builders.RessourceBuilder;

class Agriculture implements Ressource, RessourceBuilder {

    private int size;
    private int yields;

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
        return this.size * 40;
    }

    public void setAnnualYields() {
        this.yields = this.getAnnualYields();
    }

    public int getYields() {
        return this.yields;
    }

    public void addYields(int number_yields) {
        this.yields += (number_yields);
    }


}
