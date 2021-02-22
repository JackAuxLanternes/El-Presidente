package fr.elpresidente.game.resources;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ResourcesController {

    private static ResourcesController instance;

    private final Resource agriculture;

    private final Resource industry;

    private ResourcesController() {
        agriculture = new Agriculture();
        industry = new Industry();
    }

    public static ResourcesController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new ResourcesController();
        }
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static ResourcesController resetInstance() {
        deleteInstance();
        return getInstance();
    }

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public Resource getAgriculture() {
        return this.agriculture;
    }

    public Resource getIndustry() {
        return this.industry;
    }

    public Resource getResourceFromResourceName(String resourceName) {
        return toArrayList()
                .stream().filter(resource -> resource.getName().equals(resourceName)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("the resource " + resourceName + " doesn't exist"));
    }

    public void addIndustrySize(int value) {
        industry.addSize(getMaximumPossibleValueToAddToSize(value));
    }

    public void addAgricultureSize(int value) {
        agriculture.addSize(getMaximumPossibleValueToAddToSize(value));
    }

    public int getCumulativeResources() {
        return agriculture.getSize() + industry.getSize();
    }

    private int getMaximumPossibleValueToAddToSize(int value) {
        if (getCumulative(value) > 100) {
            return value - (getCumulative(value) - 100);
        }

        return value;
    }

    public JSONArray toJSONArray() {

        JSONArray resourcesArray = new JSONArray();
        resourcesArray.add(this.agriculture.toJSONObject());
        resourcesArray.add(this.industry.toJSONObject());

        return resourcesArray;
    }

    public ArrayList<Resource> toArrayList() {
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(this.agriculture);
        resources.add(this.industry);

        return resources;
    }

    private int getCumulative(int value) {
        return value + getCumulativeResources();
    }
}
