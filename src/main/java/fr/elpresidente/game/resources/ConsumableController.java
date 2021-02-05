package fr.elpresidente.game.resources;

public class ConsumableController {

    private static ConsumableController instance;

    private ResourcesController resourcesController;

    private final Consumable food;

    private final Consumable treasury;


    private ConsumableController() {
        food = new Food(0);
        treasury = new Treasury(0);
    }

    public static ConsumableController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new ConsumableController();
        }

        return instance;
    }

    public static void deleteInstance() {
        if(instance != null){
            instance = null;
        }
    }

    public static ConsumableController resetInstance() {
        deleteInstance();
        return getInstance();
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
