package fr.elpresidente.game;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.difficulty.NormalDifficulty;
import fr.elpresidente.game.endofyear.events.Bribe;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import junit.framework.TestCase;

import java.util.NoSuchElementException;

public class BribeTest extends TestCase{

    ConsumableController consumableController  = ConsumableController.getInstance();

    public void testSatisfactionOfAFactionAfterBribeThisFaction()
    {
        Bribe bribe = new Bribe();
        ConsumableController.getInstance().getTreasury().setAmount(2000);
        FactionController.getInstance().getFactionFromNameFaction("capitalist").setSatisfaction(50);
        FactionController.getInstance().getFactionFromNameFaction("capitalist").setSupporters(10);
        DifficultyController.getInstance().setDifficulty(new NormalDifficulty());
        assertEquals(50.0, FactionController.getInstance().getFactionFromNameFaction("capitalist").getSatisfaction());

        bribe.bribeFaction("capitalist");
        assertEquals(60.0, FactionController.getInstance().getFactionFromNameFaction("capitalist").getSatisfaction());

    }

    public void testSatisfactionOfAFactionThatDoesnotExist()
    {
        try {
            Bribe bribe = new Bribe();
            FactionController.getInstance().getFactionFromNameFaction("pomme").setSatisfaction(50);
            this.consumableController.getTreasury().setAmount(2000);
            DifficultyController.getInstance().setDifficulty(new NormalDifficulty());
            assertEquals(50.0, FactionController.getInstance().getFactionFromNameFaction("capitalist").getSatisfaction());

            bribe.bribeFaction("capitalist");
            assertEquals(55.0, FactionController.getInstance().getFactionFromNameFaction("capitalist").getSatisfaction());
        }catch(NoSuchElementException exception) {
            assertEquals("the faction pomme doesn\'t exist", exception.getMessage());
        }
        
    }

    public void testTreasuryAfterBribeAFaction()
    {
        Bribe bribe = new Bribe();
        FactionController.getInstance().getFactionFromNameFaction("capitalist").setSupporters(10);
        FactionController.getInstance().getFactionFromNameFaction("capitalist").setSatisfaction(80);
        ConsumableController.getInstance().getTreasury().setAmount(250);
        DifficultyController.getInstance().setDifficulty(new NormalDifficulty());

        bribe.bribeFaction("capitalist");
        assertEquals(100, ConsumableController.getInstance().getTreasury().getAmount());
    }
}
