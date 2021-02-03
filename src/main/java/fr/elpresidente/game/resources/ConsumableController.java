package fr.elpresidente.game.resources;

public class ConsumableController {

    private static ConsumableController instance;

    private ResourcesController resourcesController;

    private final Consumable food;

    private static final int STARTING_QUANTITY_OF_FOOD = 1000;

    private final Consumable treasury;

    private static final int STARTING_QUANTITY_OF_TREASURY = 400;


    private ConsumableController() {
        food = new Food(STARTING_QUANTITY_OF_FOOD);
        treasury = new Treasury(STARTING_QUANTITY_OF_TREASURY);
    }

    public static ConsumableController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new ConsumableController();
        }

        return instance;
    }

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public Consumable getFood() {
        return this.food;
    }

    public Consumable getTreasury() {
        return this.treasury;
    }

    public void buyFoodUnits(int amount_food) {
        if(buyMaximumFoodPossible(amount_food) != amount_food)
        {
            this.errorAmountToSubstractLessThan0(amount_food);
        }
    }

    public int buyMaximumFoodPossible(int amount_food)
    {
        int amount_to_substract = this.determineAmountForAmountFood(amount_food);
        if(amount_to_substract < this.treasury.getAmount())
        {
            this.treasury.substractAmount(amount_to_substract);
            this.food.addAmount(amount_food);
        }
        else
        {
            amount_to_substract = this.treasury.getAmount();
            this.treasury.substractAmount(amount_to_substract);
            this.food.addAmount(amount_to_substract);
        }

        return amount_to_substract;
    }

    private void errorAmountToSubstractLessThan0(int amount_food) {
        throw new Error("you can't buy " + amount_food + " agriculutre yields you have only " +
                this.treasury.getAmount() + "$ and on yield cost " + Treasury.PRICE_ONE_YIELD_AGRICULTURE);
    }

    private int determineAmountForAmountFood(int amount_food) {
        return amount_food * Treasury.PRICE_ONE_YIELD_AGRICULTURE;
    }
}
