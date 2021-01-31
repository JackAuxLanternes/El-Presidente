package fr.elpresidente.game.resources;

public class ConsumableController {

    private static ConsumableController instance;

    private ResourcesController resourcesController;

    private final Consumable food;

    private final Consumable treasury;


    private ConsumableController() {
        food = new Food();
        treasury = new Treasury();
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

    public void setAnnualConsumable() {
        this.food.setAnnualAmount(resourcesController.getAgriculture());
        this.treasury.setAnnualAmount(resourcesController.getIndustry());
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
        int amount_to_substract = this.determineAmountForAmountFood(amount_food);
        if(amount_to_substract < this.treasury.getAmount()) {
            this.treasury.substractAmount(amount_to_substract);
        }else {
            this.errorAmountToSubstractLessThan0(amount_food);
        }
    }

    private void errorAmountToSubstractLessThan0(int amount_food) {
        throw new Error("you can't buy " + amount_food + " agriculutre yields you have only " +
                this.treasury.getAmount() + "$ and on yield cost " + Treasury.PRICE_ONE_YIELD_AGRICULTURE);
    }


    private int determineAmountForAmountFood(int amount_food) {
        return amount_food * Treasury.PRICE_ONE_YIELD_AGRICULTURE;
    }
}
