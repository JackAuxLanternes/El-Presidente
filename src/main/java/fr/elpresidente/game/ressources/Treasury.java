package fr.elpresidente.game.ressources;

public class Treasury {

    private final int PRICE_ONE_YIELD = 8;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void substractAmountAccordingToYields(int number_agriculture_yields) {
        int amount_to_substract = this.determineAmountForYields(number_agriculture_yields);
        if(amount_to_substract >= 0) {
            this.substractAmount(amount_to_substract);
        }
        this.amountToSubstractLessThan0(number_agriculture_yields);
    }

    private void amountToSubstractLessThan0(int number_agriculture_yields) {
        throw new Error("you can't buy " + number_agriculture_yields + " agriculutre yields you have only " +
                this.amount + "$ and on yield cost " + this.PRICE_ONE_YIELD);
    }


    private int determineAmountForYields(int number_agriculture_yields) {
        return number_agriculture_yields * this.PRICE_ONE_YIELD;
    }

    public void substractAmount(int amount) {
        this.amount -= amount;
    }
}
