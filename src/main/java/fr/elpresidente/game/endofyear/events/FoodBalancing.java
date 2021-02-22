package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;

import java.util.NoSuchElementException;

public class FoodBalancing implements BalanceSheetEvent {

    private FactionController factionController;
    private ConsumableController consumableController;
    private final int FOOD_NEEDED_BY_SUPPORTER = 4;

    public FoodBalancing() {
        this.factionController = FactionController.getInstance();
        this.consumableController = ConsumableController.getInstance();
    }


    public void callEvent() {

        if (!this.IsEnoughFoodForSupporters()) {
            this.removeSupportersRandomlyToEquilibrateFood();
        }
        System.out.println("We have enough food for supporters, we are going to feed them");
        this.feedSupporters();
    }

    private boolean IsEnoughFoodForSupporters() {

        return this.factionController.determineTotalSupporters() <= (this.consumableController.getFood().getAmount() / this.FOOD_NEEDED_BY_SUPPORTER);
    }


    private void removeSupportersRandomlyToEquilibrateFood() {

        this.removeSupportersAndSatisfactionAccordingly(this.determineMaximumSupportersThatWeCanKeep());
    }

    private double determineMaximumSupportersThatWeCanKeep() {

        return Math.floor(this.consumableController.getFood().getAmount() / this.FOOD_NEEDED_BY_SUPPORTER);
    }

    private void removeSupportersAndSatisfactionAccordingly(double maximum_supporter) {

        if (maximum_supporter > 0) {
            int number_supporter_that_have_to_be_removed = this.determineNumberSupporterThatHaveToBeRemoved(maximum_supporter);
            this.factionController.removeSupportersRandomly(number_supporter_that_have_to_be_removed);
            this.factionController.substractSatisfactionAccordingToNumberSupporters(number_supporter_that_have_to_be_removed);
        } else {
            throw new NoSuchElementException("You can't delete all your Supporters, you don't have enough food");
        }
    }

    private int determineNumberSupporterThatHaveToBeRemoved(double maximum_supporter) {
        return this.factionController.determineTotalSupporters() - (int) maximum_supporter;
    }

    private void feedSupporters() {

        this.consumableController.getFood().subtractAmount(this.factionController.determineTotalSupporters() * this.FOOD_NEEDED_BY_SUPPORTER);
    }
}