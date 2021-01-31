package fr.elpresidente.game.resources;

public class ResourcesController {

    private static ResourcesController instance;

    private final Resource agriculture;

    private final Resource industry;

    public ResourcesController() {
        agriculture = new Agriculture();
        industry = new Industry();
    }

    public static ResourcesController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new ResourcesController();
        }

        return instance;
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

    public void addIndustry(int value)
    {
        industry.addSize(getMaximumPossibleValueToAddWith(value));
    }

    public void addAgriculture(int value)
    {
        agriculture.addSize(getMaximumPossibleValueToAddWith(value));
    }

    private int getMaximumPossibleValueToAddWith(int value)
    {
        int cumulative = value + getCumulativeResources();

        if (cumulative > 100)
        {
            return value - (cumulative - 100);
        }

        return value;
    }

    public int getCumulativeResources()
    {
        return agriculture.getSize() + industry.getSize();
    }
}
