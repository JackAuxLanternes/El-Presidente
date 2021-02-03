package fr.elpresidente.game.resources;

public class Treasury implements Consumable{

    public final static int PRICE_ONE_YIELD_AGRICULTURE = 8;
    private int amount;

    public Treasury(int amount) {
        this.amount = amount;
    }

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
    public void substractAmount(int amount) {
        this.amount -= amount;
    }


}
