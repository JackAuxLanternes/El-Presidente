package fr.elpresidente.game.ressources;

import fr.elpresidente.game.builders.RessourceBuilder;

class Agriculture implements Resource, RessourceBuilder {

    private int size;

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
        return this.size * 40;
    }
}
