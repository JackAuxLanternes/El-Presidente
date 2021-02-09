package fr.elpresidente.game.resources;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ConsumableController {

    private static ConsumableController instance;

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
        if (instance != null) {
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

    public Consumable getConsumableFromConsumableName(String consumableName) throws Exception {
        return toArrayList()
                .stream().filter(resource -> resource.getName().equals(consumableName)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("the resource " + consumableName + " doesn\'t exist"));
    }

    public JSONArray toJSONArray() {

        JSONArray consumablesArray = new JSONArray();
        consumablesArray.add(this.food.toJSONObject());
        consumablesArray.add(this.treasury.toJSONObject());

        return consumablesArray;
    }

    public ArrayList<Consumable> toArrayList() {
        ArrayList<Consumable> consumables = new ArrayList<>();
        consumables.add(this.food);
        consumables.add(this.treasury);

        return consumables;
    }

    public Consumable getFood() {
        return this.food;
    }

    public Consumable getTreasury() {
        return this.treasury;
    }
}
