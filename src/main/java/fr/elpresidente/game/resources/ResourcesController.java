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
        if (instance == null)
        {
            instance = new ResourcesController();
        }

        return instance;
    }

    public Resource getAgriculture() {
        return this.agriculture;
    }

    public Resource getIndustry() {
        return this.industry;
    }
}
