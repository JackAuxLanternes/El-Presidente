package fr.elpresidente.game.ressources;

class Treasury implements Consumable {

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
}
