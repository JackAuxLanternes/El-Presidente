package fr.elpresidente.game;

import fr.elpresidente.game.endofyear.events.Bribe;
import junit.framework.TestCase;

import java.util.NoSuchElementException;

public class BribeTest extends TestCase{


    

    public void testSatisfactionOfAFactionAfterBribeThisFaction()
    {
        Bribe bribe = new Bribe();
        bribe.getFactionController().getFactionFromNameFaction("capitalist").setSatisfaction(50);
        assertEquals(50.0, bribe.getFactionController().getFactionFromNameFaction("capitalist").getSatisfaction());

        bribe.bribeFaction("capitalist");
        assertEquals(55.0, bribe.getFactionController().getFactionFromNameFaction("capitalist").getSatisfaction());

    }

    public void testSatisfactionOfAFactionThatDoesnotExist()
    {
        try {
            Bribe bribe = new Bribe();
            bribe.getFactionController().getFactionFromNameFaction("pomme").setSatisfaction(50);
            assertEquals(50.0, bribe.getFactionController().getFactionFromNameFaction("capitalist").getSatisfaction());

            bribe.bribeFaction("capitalist");
            assertEquals(55.0, bribe.getFactionController().getFactionFromNameFaction("capitalist").getSatisfaction());
        }catch(NoSuchElementException exception) {
            assertEquals("the faction pomme doesn\'t exist", exception.getMessage());
        }
        
    }

    public void testTreasuryAfterBribeAFaction()
    {
        Bribe bribe = new Bribe();
        bribe.getConsumableController().getTreasury().setAmount(250);
        bribe.getFactionController().getFactionFromNameFaction("capitalist").setSupporters(10);

        bribe.bribeFaction("capitalist");
        assertEquals(100, bribe.getConsumableController().getTreasury().getAmount());
    }
}
