package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;

import java.util.NoSuchElementException;

public class BalanceSheet {
    private FactionController factionController;

    private ConsumableController consumableController;

    public BalanceSheet() {
        this.factionController = FactionController.getInstance();
        this.consumableController = ConsumableController.getInstance();
    }

    public void foodBalancingForSupporters() {
        if(this.isEnoughFoodToFeedSupporters()) {
            this.removeSupportersRandomlyToEquilibrateFood();
        }
    }

    private boolean isEnoughFoodToFeedSupporters() {
        int food_needed_by_supporter = 4;
        return this.factionController.determineTotalSupporters() >= ( this.consumableController.getFood().getAmount() / food_needed_by_supporter );
    }

    private void removeSupportersRandomlyToEquilibrateFood() {

        this.removeSupportersForFoodAndSatisfactionAccordingly(this.determineMaximumSupportersThatWeCanKeep());
    }

    private double determineMaximumSupportersThatWeCanKeep() {
        int food_needed_by_supporter = 4;

        return Math.floor(this.consumableController.getFood().getAmount() / food_needed_by_supporter);
    }

    private void removeSupportersForFoodAndSatisfactionAccordingly(double maximum_supporter) {

        if(maximum_supporter > 0) {
            this.factionController.removeSupportersRandomly(this.factionController.determineTotalSupporters() - (int) maximum_supporter);
            this.factionController.substractSatisfactionAccordingToNumberSupporters(this.factionController.determineTotalSupporters() - (int) maximum_supporter);
        }else {
            throw new NoSuchElementException("You can't delete all your Supporters, you don't have enough food");
        }
    }
}
