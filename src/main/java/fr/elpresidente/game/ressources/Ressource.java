package fr.elpresidente.game.ressources;

public interface Ressource {

    int size = 0;

    int getSize();

    void setSize(int size);

    void addSize(int size);

    int getAnnualYields();
}
