package fr.elpresidente.game.ressources;

public class ConsumableController {

    private static ConsumableController instance;

    private RessourcesController ressourcesController;

    private final Consumable food;

    private final Consumable treasury;


    private ConsumableController() {
        food = new Food();
        treasury = new Treasury();
    }

    public void setAnnualConsumable() {
        this.food.setAnnualAmount(ressourcesController.getAgriculture());
        this.treasury.setAnnualAmount(ressourcesController.getIndustry());
    }

    public Consumable getFood() {
        return this.food;
    }

    public Consumable getTreasury() {
        return this.treasury;
    }

    public void buyFoodUnits(int amount_food) {

        this.substractAmountTreasuryAccordingToAmountFood(amount_food);
        this.food.addAmount(amount_food);
    }

    public void substractAmountTreasuryAccordingToAmountFood(int amount_food) {
        int amount_to_substract = this.determineAmountForAmount_food(amount_food);
        if(amount_to_substract >= 0) {
            this.treasury.substractAmount(amount_to_substract);
        }
        this.amountToSubstractLessThan0(amount_food);
    }

    private void amountToSubstractLessThan0(int amount_food) {
        throw new Error("you can't buy " + amount_food + " agriculutre yields you have only " +
                this.treasury.getAmount() + "$ and on yield cost " + Treasury.PRICE_ONE_YIELD_AGRICULTURE);
    }


    private int determineAmountForAmount_food(int amount_food) {
        return amount_food * Treasury.PRICE_ONE_YIELD_AGRICULTURE;
    }
}
