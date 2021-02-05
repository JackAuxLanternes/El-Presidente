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


}
