package fr.elpresidente.game.ressources;

public interface Consumable {

    int getAmount();

    void setAmount(int amount);

    void addAmount(int amount);

    void setAnnualAmount(Ressource ressource);

    public void substractAmount(int amount);
}
