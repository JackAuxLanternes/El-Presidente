package fr.elpresidente.game.endofyear;

import fr.elpresidente.game.endofyear.events.Bribe;
import fr.elpresidente.game.endofyear.events.FoodMarket;

import java.util.Scanner;

class OptionalEventMenu {


    public void choseOptionalEvent() {
        int choice = 0;

        while (choice != 3) {
            this.printChoiceMenu();
            choice = getOptionalEventChoiceFromCommandLine();
            if (choice == 1) {

                BribeMenu bribeMenu = new BribeMenu();
                Bribe bribe = new Bribe();
                bribe.bribeFaction(bribeMenu.choiceFactionForBribe());
                bribeMenu.printGameStatusAfterBribe();
            } else if (choice == 2) {

                FoodMarketMenu foodMarketMenu = new FoodMarketMenu();
                FoodMarket foodMarket = new FoodMarket();
                foodMarket.goToFoodMarket(foodMarketMenu.choiceNumberFoodToBuy());
                foodMarketMenu.printGameStatusAfterFoodMarket();
            }
        }
    }

    private void printChoiceMenu() {
        System.out.println("==============================");
        System.out.println("=== Bienvenue aux actions de fin d'année");
        System.out.println("=== Ces actions ont pour but de sauver la république d'El Presidente");
        System.out.println("=== Choisissez l'une des option si vous le voulez:");
        System.out.println("=== 1. Pot de vin");
        System.out.println("=== \t Cela vous coutera 15$ par partisan d'une faction et vous permettra d'augmenter la satisfaction de cette faction de 10%");
        System.out.println("=== \t Cela aura en revanche un effet négatif sur vos loyalistes");
        System.out.println("=== 2. Le Marché alimentaire");
        System.out.println("=== \t Vous pouvez acheter des unités de nourriture pour Compléter l'année qui s'achève, 1 unité = 8$");
        System.out.println("=== 3. Aucun de ces évènements ne m'intéresse");
    }

    private int getOptionalEventChoiceFromCommandLine() {

        int choice = 0;
        boolean error;
        do {
            try {
                error = false;
                choice = this.readOptionalEvent();
            } catch (Exception e) {
                error = true;
                this.printErrorMenu();
            }
        } while (error);

        return choice;
    }

    private void printErrorMenu() {

        System.out.println("=== Les Choix ne peuvent-être que:");
        System.out.println("=== \t 1 pour le pot de vin");
        System.out.println("=== \t 2 pour le marché alimentaire");
        System.out.println("=== \t 3 pour aucune action");
    }

    private int readOptionalEvent() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 3) {
            throw new Exception();
        }

        return choice;
    }


}
