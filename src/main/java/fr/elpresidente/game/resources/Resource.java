package fr.elpresidente.game.resources;

public interface Resource {

    int size = 0;

    int getSize();

    void setSize(int size);

    void addSize(int size);

    int getAnnualYields();
}
