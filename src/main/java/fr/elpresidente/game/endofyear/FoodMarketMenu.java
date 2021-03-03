package fr.elpresidente.game.endofyear;

import fr.elpresidente.game.status.GameDisplay;
import fr.elpresidente.game.tools.UserIOTools;

class FoodMarketMenu implements EndOfYearActionMenu {

    public int choiceNumberFoodToBuy() {
        return this.readActionValue();
    }

    @Override
    public Integer readActionValue() {
        int amount_of_food_wanted;
        boolean isAmountOfFoodValid;

        do {
            this.printChoiceMenu();
            amount_of_food_wanted = UserIOTools.readIntFromUserInput();
            if (amount_of_food_wanted < 0) {
                isAmountOfFoodValid = false;
                this.printInvalideChoice(amount_of_food_wanted);
            } else {
                isAmountOfFoodValid = true;
            }
        } while (!isAmountOfFoodValid);

        return amount_of_food_wanted;
    }

    @Override
    public void printChoiceMenu() {
        System.out.println("==============================");
        System.out.println("=== Combien voulez-vous d'unité de nourriture ?");
        System.out.println("=== Indiquez seulement le nombre");
    }

    @Override
    public void printInvalideChoice(Object food_amount) {
        System.out.println("==============================");
        System.out.println("=== Le montant de nourriture ne peut pas être inférieur à zéro, votre choix: "
                + (int) food_amount);
    }

    @Override
    public void printGameStatusAfterAction() {
        GameDisplay gameDisplay = new GameDisplay();
        System.out.println("Voici le status des Ressources après votre achat");
        gameDisplay.showResourcesStatus();

    }
}
