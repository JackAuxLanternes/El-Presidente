package fr.elpresidente.game;

import fr.elpresidente.game.difficulty.Difficulty;
import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.turn.Defeat;
import junit.framework.TestCase;

public class DefeatTest extends TestCase {

    public void testWhenTheSatisfactionIsUnderTheThreshold() throws Exception {
        Defeat defeat = new Defeat();
        DifficultyController.getInstance().setDifficulty(Difficulty.HARD);
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 51);
        assertTrue(defeat.completeConditionsToContinue());

    }

    public void testWhenTheSatisfactionIsSuperiorThanTheThreshold() throws Exception {
        Defeat defeat = new Defeat();
        DifficultyController.getInstance().setDifficulty(Difficulty.HARD);
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 50);
        assertFalse(defeat.completeConditionsToContinue());
    }

    public void testWhenTheSatisfactionIsUnderTheThresholdWithDifferentSatisfactionForSupporters() throws Exception {
        Defeat defeat = new Defeat();
        DifficultyController.getInstance().setDifficulty(Difficulty.HARD);
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(4, 51);
        factionController.getFactionFromNameFaction("capitalist").substractSatisfaction(49);
        factionController.getFactionFromNameFaction("loyalist").substractSatisfaction(51);
        assertFalse(defeat.completeConditionsToContinue());
    }
}
