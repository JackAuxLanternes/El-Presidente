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

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public Resource getAgriculture() {
        return this.agriculture;
    }

    public Resource getIndustry() {
        return this.industry;
    }
}
