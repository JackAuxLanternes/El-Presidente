package fr.elpresidente.game.resources;

public interface Consumable {

    int getAmount();

    void setAmount(int amount);

    void addAmount(int amount);

    void substractAmount(int amount);
}
