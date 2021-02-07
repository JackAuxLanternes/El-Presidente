package fr.elpresidente.game.resources;

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
        if(instance != null){
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

    public Resource getResourceFromResourceName(String resourceName) throws Exception
    {
        switch (resourceName)
        {
            case "agriculture" : return agriculture;
            case "industry" : return industry;
            default:
                throw new Exception("This Resource doesn't exist");
        }
    }

    public void addIndustrySize(int value)
    {
        industry.addSize(getMaximumPossibleValueToAddToSize(value));
    }

    public void addAgricultureSize(int value)
    {
        agriculture.addSize(getMaximumPossibleValueToAddToSize(value));
    }

    public int getCumulativeResources()
    {
        return agriculture.getSize() + industry.getSize();
    }

    private int getMaximumPossibleValueToAddToSize(int value)
    {
        if (getCumulative(value) > 100)
        {
            return value - (getCumulative(value) - 100);
        }

        return value;
    }

    private int getCumulative(int value)
    {
        return value + getCumulativeResources();
    }
}
