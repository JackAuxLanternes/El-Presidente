package fr.elpresidente.game.ressources;

public class RessourcesController {

    private static RessourcesController instance;

    private final Agriculture agriculture;

    private final Industry industry;

    private final Treasury treasury;

    private final Food food;

    private RessourcesController() {
        agriculture = new Agriculture();
        industry = new Industry();
        treasury = new Treasury();
        food = new Food();
    }

    public static RessourcesController getInstance() {
        if (instance == null)
        {
            instance = new RessourcesController();
        }

        return instance;
    }

    public int getAgricultureSize() {
        return agriculture.getSize();
    }

    public void setAgricultureSize(int size) {
        agriculture.setSize(size);
    }

    public int getIndustrySize() {
        return industry.getSize();
    }

    public void setIndustrySize(int size) {
        industry.setSize(size);
    }

    public int getRessourcesSize() {
        return agriculture.getSize() + industry.getSize();
    }

    public int getAgricultureAnnualYields() {
        return agriculture.getAnnualYields();
    }

    public int getIndustryAnnualYields() {
        return industry.getAnnualYields();
    }

    public void processRessourcesAnnualYields() {
        treasury.addAmount(getIndustryAnnualYields());
        food.addAmount(getAgricultureAnnualYields());
    }

    public int getTreasuryAmount() {
        return treasury.getAmount();
    }

    public void setTreasuryAmount(int amount) {
        treasury.setAmount(amount);
    }

    public int getFoodAmount() {
        return food.getAmount();
    }

    public void setFoodAmount(int amount) {
        food.setAmount(amount);
    }
}
