package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;

import java.util.Scanner;

import static fr.elpresidente.game.resources.Treasury.PRICE_ONE_YIELD_AGRICULTURE;

public class FoodMarket {

    ConsumableController controller = ConsumableController.getInstance();
    private final int PRICE_FOR_ONE_FOOD_UNITY = 8;

    public void goToFoodMarket(int amount_food) {
        if(haveEnoughMoneyToBuyAmountFood(amount_food)) {
            this.buyAmountOfFood(amount_food);
        }else {
            System.out.println("You have not enough money, you have used all of your money to pay equivalent food");
        }
    }

    public void buyAmountOfFood(int amount_food) {
        controller.getTreasury().subtractAmount(amount_food * PRICE_FOR_ONE_FOOD_UNITY);
        controller.getFood().addAmount(amount_food);
    }

    private boolean haveEnoughMoneyToBuyAmountFood(int amount_food) {
        return this.controller.getTreasury().getAmount() >= amount_food * PRICE_FOR_ONE_FOOD_UNITY;
    }
}
