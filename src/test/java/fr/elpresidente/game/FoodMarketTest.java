package fr.elpresidente.game;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.difficulty.NormalDifficulty;
import fr.elpresidente.game.endofyear.events.FoodMarket;
import fr.elpresidente.game.resources.consumable.ConsumableController;
import junit.framework.TestCase;

public class FoodMarketTest extends TestCase {
    FoodMarket foodMarket = new FoodMarket();

    public void setup() {
        //FactionController.getInstance().initFactions(4, 50);
        DifficultyController.getInstance().setDifficulty(new NormalDifficulty());
        ConsumableController.resetInstance();
    }

    public void testWhenThePlayerHaveEnoughMoney() {
        setup();
        ConsumableController.getInstance().getTreasury().updateAmount(40);
        foodMarket.goToFoodMarket(5);

        assertEquals(0, ConsumableController.getInstance().getTreasury().getAmount());
        assertEquals(5, ConsumableController.getInstance().getFood().getAmount());
    }

    public void testWhenThePlayerHaveNotEnoughMoney() {
        setup();
        ConsumableController.getInstance().getTreasury().updateAmount(8);
        foodMarket.goToFoodMarket(5);

        assertEquals(8, ConsumableController.getInstance().getTreasury().getAmount());
        assertEquals(0, ConsumableController.getInstance().getFood().getAmount());
    }
}
