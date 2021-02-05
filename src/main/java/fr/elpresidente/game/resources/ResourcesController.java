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

    public void addResourceSize(Resource resource, int size) {
        if (isResourceExpendable(size)) {
            resource.addSize(size);
        }
    }

    public boolean isResourceExpendable(int size) {
        return getResourceTotalSize() + size < 100;
    }

    public int getResourceTotalSize() {
        return this.agriculture.getSize() + this.industry.getSize();
    }
}
