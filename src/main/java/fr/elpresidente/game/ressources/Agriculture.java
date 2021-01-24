package fr.elpresidente.game.ressources;

class Agriculture implements Ressource {

    private int size;

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
