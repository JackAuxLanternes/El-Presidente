package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;

import java.util.Scanner;

import static fr.elpresidente.game.resources.Treasury.PRICE_ONE_YIELD_AGRICULTURE;

public class FoodMarket {

    ConsumableController controller = ConsumableController.getInstance();

    public void goToFoodMarket() {
        askUserIfHeWantToBuyFood();
    }

    private void askUserIfHeWantToBuyFood() {
        System.out.println("Do you want to buy some food for your Supporters ?");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            goBuyFood();
        } else {
            System.out.println("You're evil.");
        }
    }

    private void goBuyFood() {
        if (buyMaximumFoodPossible(getFoodNeeded()) != getFoodNeeded()) {
            System.out.println("You have not enough money, you have used all of your money to pay equivalent food");
        } else {
            System.out.println("Great, your citizen didn't die this year !");
        }
    }

    private int buyMaximumFoodPossible(int amount_food) {
        int amount_to_substract = this.determineAmountForAmountFood(amount_food);
        if (amount_to_substract < controller.getTreasury().getAmount()) {
            controller.getTreasury().subtractAmount(amount_to_substract);
            controller.getFood().addAmount(amount_food);
        } else {
            amount_to_substract = controller.getTreasury().getAmount();
            controller.getTreasury().subtractAmount(amount_to_substract);
            controller.getFood().addAmount(amount_to_substract);
        }

        return amount_to_substract;
    }

    public int getFoodNeeded() {
        int food_needed = ConsumableController.getInstance().getFood().getAmount() - (FactionController.getInstance().determineTotalSupporters() * 4);
        if (food_needed < 0) {
            return food_needed * (-1);
        } else {
            return 0;
        }
    }

    private int determineAmountForAmountFood(int amount_food) {
        return amount_food * PRICE_ONE_YIELD_AGRICULTURE;
    }
}
