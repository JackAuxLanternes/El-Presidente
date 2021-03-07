package fr.elpresidente.game;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.difficulty.NormalDifficulty;
import fr.elpresidente.game.endofyear.events.BalanceSheetEvent;
import fr.elpresidente.game.endofyear.events.FoodBalancing;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.consumable.ConsumableController;
import junit.framework.TestCase;

public class FoodBalancingTest extends TestCase {

    public void testIfWeDontHaveToEliminateSupporters() {
        BalanceSheetEvent foodbalancing = new FoodBalancing();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 50);
        ConsumableController consumableController = ConsumableController.getInstance();
        consumableController.getFood().setAmount(200);
        DifficultyController.getInstance().setDifficulty(new NormalDifficulty());

        foodbalancing.callEvent();

        assertEquals(72, consumableController.getFood().getAmount());
    }

    public void testIfWeHaveToEliminateSupporters() {
        BalanceSheetEvent foodbalancing = new FoodBalancing();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 50);
        ConsumableController consumableController = ConsumableController.getInstance();
        consumableController.getFood().setAmount(50);
        assertEquals(50.0, factionController.determineGlobalSatisfaction());
        foodbalancing.callEvent();
        DifficultyController.getInstance().setDifficulty(new NormalDifficulty());

        factionController.getFactions().forEach(faction -> System.out.println(faction.getSatisfaction()));

        assertEquals(2, consumableController.getFood().getAmount());
        assertEquals(12, factionController.determineTotalSupporters());
        assertEquals(10.0, factionController.determineGlobalSatisfaction());
    }

}
