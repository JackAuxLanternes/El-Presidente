package fr.elpresidente.game.ressources;

public class Treasury implements Consumable{

    public final static int PRICE_ONE_YIELD_AGRICULTURE = 8;
    private int amount;

    @Override
    public int getAmount() {
        return amount;
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
        this.amount -= amount;
    }


}
