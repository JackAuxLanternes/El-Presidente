package fr.elpresidente.game.endOfYearEvents;

import fr.elpresidente.game.resources.ConsumableController;

import java.util.Scanner;

import static fr.elpresidente.game.resources.Treasury.PRICE_ONE_YIELD_AGRICULTURE;

public class FoodMarket {

    private static FoodMarket instance;

    ConsumableController controller = ConsumableController.getInstance();

    public static FoodMarket getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new FoodMarket();
        }

        return instance;
    }

    public void buyFoodUnits() {
        if(getFoodNeeded() > 0)
        {
            askUserIfHeWantToBuyFood();
        }
        else
        {
            System.out.println("You didn't need to go to the food market, you have enough food");
        }
    }

    private void askUserIfHeWantToBuyFood()
    {
        System.out.println("You have not enough food for your citizens, did you to go to the food market for buying food ?");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if(choice == 1)
        {
            goBuyFood();
        }
        else
        {
            System.out.println("You're evil.");
        }
    }

    private void goBuyFood()
    {
        if(buyMaximumFoodPossible(getFoodNeeded()) != getFoodNeeded())
        {
            System.out.println("You have not enough money, you have used all of your money to pay equivalent food");
        }
        else
        {
            System.out.println("Great, your citizen didn't die this year !");
        }
    }

    private int buyMaximumFoodPossible(int amount_food)
    {
        int amount_to_substract = this.determineAmountForAmountFood(amount_food);
        if(amount_to_substract < controller.getTreasury().getAmount())
        {
            controller.getTreasury().substractAmount(amount_to_substract);
            controller.getFood().addAmount(amount_food);
        }
        else
        {
            amount_to_substract = controller.getTreasury().getAmount();
            controller.getTreasury().substractAmount(amount_to_substract);
            controller.getFood().addAmount(amount_to_substract);
        }

        return amount_to_substract;
    }

    private int getFoodNeeded()
    {
        return 0; //W.I.P.
    }

    private static boolean isInstanceNotInitialized() {
        return instance == null;
    }

    private int determineAmountForAmountFood(int amount_food) {
        return amount_food * PRICE_ONE_YIELD_AGRICULTURE;
    }
}
