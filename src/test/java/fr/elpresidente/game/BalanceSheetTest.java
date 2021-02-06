package fr.elpresidente.game;

import fr.elpresidente.game.endofyear.events.BalanceSheet;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import junit.framework.TestCase;

public class BalanceSheetTest extends TestCase {

    public void testIfWedontHaveToEliminateSupporters()
    {
        BalanceSheet balanceSheet = new BalanceSheet();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 50);
        ConsumableController consumableController = ConsumableController.getInstance();
        consumableController.getFood().setAmount(200);

        balanceSheet.foodBalancingForSupporters();

        assertEquals(72, consumableController.getFood().getAmount());
    }

    public void testIfWeHaveToEliminateSupporters()
    {
        BalanceSheet balanceSheet = new BalanceSheet();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 50);
        ConsumableController consumableController = ConsumableController.getInstance();
        consumableController.getFood().setAmount(50);
        assertEquals(50.0, factionController.determineGlobalSatisfaction());
        balanceSheet.foodBalancingForSupporters();

        factionController.getFactions().forEach(faction -> System.out.println(faction.getSatisfaction()));

        assertEquals(2, consumableController.getFood().getAmount());
        assertEquals(12, factionController.determineTotalSupporters());
        assertEquals(10.0, factionController.determineGlobalSatisfaction());
    }

}
