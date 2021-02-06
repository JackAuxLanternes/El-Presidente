package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ResourcesController;

public class AgricultureSurplus implements BalanceSheetEvent{

    private FactionController factionController;
    private ResourcesController resourcesController;

    public AgricultureSurplus() {
        this.factionController = FactionController.getInstance();
        this.resourcesController = ResourcesController.getInstance();
    }

    public void callEvent() {
        if(isAgricultureAloneIsSurplus()) {
            this.addNewSupportersThanksToAgricultureSurplus();
        }
    }

    public void addNewSupportersThanksToAgricultureSurplus() {
        this.factionController.addSupportersRandomly(this.determineNumberSupportersRandomly());
    }

    private boolean isAgricultureAloneIsSurplus() {
        return this.resourcesController.getAgriculture().getSize() > this.resourcesController.getIndustry().getSize();
    }

    public int determineNumberSupportersRandomly() {
        int limit_percentage = 10;
        return this.factionController. determineNumberBetweenThreshold(1, limit_percentage) * this.factionController.determineTotalSupporters() / 100;
    }
}
