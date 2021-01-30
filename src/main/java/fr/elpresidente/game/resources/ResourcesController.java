package fr.elpresidente.game.resources;

public class ResourcesController {

    private static ResourcesController instance;

    private ConsumableController consumableController;

    private final Agriculture agriculture;

    private final Industry industry;


    private ResourcesController() {
        agriculture = new Agriculture();
        industry = new Industry();
    }

    public static ResourcesController getInstance() {
        if (instance == null)
        {
            instance = new ResourcesController();
        }

        return instance;
    }


    public Agriculture getAgriculture() {
        return this.agriculture;
    }

    public Industry getIndustry() {
        return this.industry;
    }
}
