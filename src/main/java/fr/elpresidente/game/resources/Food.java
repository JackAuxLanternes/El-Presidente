package fr.elpresidente.game.resources;

class Food implements Consumable {

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
    public void setAnnualAmount(Resource resource) {

    }

    @Override
    public void substractAmount(int amount) {

    }
}
