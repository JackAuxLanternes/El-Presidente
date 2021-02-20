package fr.elpresidente.game.endofyear;

import fr.elpresidente.game.status.GameDisplay;

import java.util.Scanner;

public class FoodMarketMenu {

    public int choiceNumberFoodToBuy() {
        return this.getNumberFoodFromCommandLine();
    }

    private int getNumberFoodFromCommandLine() {
        this.printChoiceMenu();
        int choice = 0;
        boolean error;
        do {
            try{
                error = false;
                this.printChoiceFoodError();
                choice = this.readChoiceNumberFood();
            }catch(Exception e) {
                error = true;
            }
        }while(error);

        return choice;
    }

    private void printChoiceMenu() {
        System.out.println("==============================");
        System.out.println("=== Combien voulez-vous d'unité de nourriture ?");
        System.out.println("=== Indiquez seulement le nombre");
    }

    private void printChoiceFoodError() {
        System.out.println("==============================");
        System.out.println("=== Le montant de nourriture ne peut pas être inferieur à zéro");
    }

    private int readChoiceNumberFood() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice < 0) {
            throw new Exception();
        }

        return choice;
    }

    public void printGameStatusAfterFoodMarket() {
        GameDisplay gameDisplay = new GameDisplay();
        System.out.println("Voici le status des Ressources après votre achat");
        gameDisplay.showResourcesStatus();

    }
}
