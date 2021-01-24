package fr.elpresidente.game.ressources;

class Industry implements Ressource {

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
        return this.size * 10;
    }
}
