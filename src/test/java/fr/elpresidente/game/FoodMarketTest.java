package fr.elpresidente.game;

import fr.elpresidente.game.endofyear.events.FoodMarket;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import junit.framework.TestCase;

public class FoodMarketTest extends TestCase {
    FoodMarket foodMarket = new FoodMarket();

    public void setup()
    {
        FactionController.getInstance().initFactions();
        ConsumableController.resetInstance();
    }

    public void testIfThePlayerHaveEnoughFoodHeShouldntBeAskedIfHeWantToGoToTheFoodMarket()
    {
        setup();
        ConsumableController.getInstance().getFood().addAmount(128);

        assertEquals(128, ConsumableController.getInstance().getFood().getAmount());
        assertEquals(128, FactionController.getInstance().determineTotalSupporters() * 4);
        assertEquals(0, foodMarket.getFoodNeeded());
    }

    public void testIfThePlayerHaveNotEnoughFoodHeShouldBeAskedIfHeWantToGoToTheFoodMarket()
    {
        setup();
        ConsumableController.getInstance().getFood().addAmount(32);

        assertEquals(32, ConsumableController.getInstance().getFood().getAmount());
        assertEquals(128, FactionController.getInstance().determineTotalSupporters() * 4);
        assertEquals(96, foodMarket.getFoodNeeded());
    }
}
