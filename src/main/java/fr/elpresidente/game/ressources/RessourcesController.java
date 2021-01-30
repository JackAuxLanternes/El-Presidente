package fr.elpresidente.game.ressources;

public class RessourcesController {

    private static RessourcesController instance;

    private ConsumableController consumableController;

    private final Agriculture agriculture;

    private final Industry industry;


    private RessourcesController() {
        agriculture = new Agriculture();
        industry = new Industry();
    }

    public static RessourcesController getInstance() {
        if (instance == null)
        {
            instance = new RessourcesController();
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
