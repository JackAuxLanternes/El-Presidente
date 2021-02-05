package fr.elpresidente.game;

import fr.elpresidente.game.endofyear.events.Bribe;
import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.turn.Defeat;
import junit.framework.TestCase;

public class DefeatTest extends TestCase {

    public void testWhenTheSatisfactionIsUnderTheThreshold()
    {
        Defeat defeat = new Defeat();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 51);
        assertEquals(true, defeat.completeConditionsToContinue(50));

    }

    public void testWhenTheSatisfactionIsSuperiorThanTheThreshold()
    {
        Defeat defeat = new Defeat();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 50);
        assertEquals(false, defeat.completeConditionsToContinue(50));
    }

    public void testWhenTheSatisfactionIsUnderTheThresholdWithDifferentSatisfactionForSupporters()
    {
        Defeat defeat = new Defeat();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 51);
        factionController.getFactionFromNameFaction("capitalist").substractSatisfaction(49);
        factionController.getFactionFromNameFaction("loyalist").substractSatisfaction(51);
        assertEquals(false, defeat.completeConditionsToContinue(50));
    }
}
