package fr.elpresidente.game.ressources;

import java.util.Collections;

public class Food implements Consumable {

    private int amount;

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public void setAnnualAmount(Ressource ressource) {
        this.amount = ressource.getAnnualYields();
    }

    @Override
    public void substractAmount(int amount) {

    }


}
