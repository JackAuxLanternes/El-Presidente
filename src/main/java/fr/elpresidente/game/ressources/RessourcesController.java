package fr.elpresidente.game.ressources;

public class RessourcesController {

    private static RessourcesController instance;

    private final Agriculture agriculture;

    private final Industry industry;

    private Treasury treasury;

    private RessourcesController() {
        agriculture = new Agriculture();
        industry = new Industry();
        treasury = new Treasury();
    }

    public static RessourcesController getInstance() {
        if (instance == null)
        {
            instance = new RessourcesController();
        }

        return instance;
    }

    public void buyAgriculutreYields(int number_yields) {

        this.treasury.substractAmountAccordingToYields(number_yields);
        this.agriculture.addYields(number_yields);
    }

    public int getAgricultureSize() {
        return agriculture.getSize();
    }

    public int getIndustrySize() {
        return industry.getSize();
    }

    public int getRessourcesSize() {
        return agriculture.getSize() + industry.getSize();
    }

    public int getAgricultureYields() {
        return agriculture.getYields();
    }

    public int getIndustryAnnualYields() {
        return industry.getAnnualYields();
    }

    public int getTreasuryAmount() {
        return treasury.getAmount();
    }


}
