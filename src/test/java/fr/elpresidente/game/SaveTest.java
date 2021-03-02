package fr.elpresidente.game;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.save.SaveLoader;
import fr.elpresidente.game.save.SaveParser;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;
import junit.framework.TestCase;

public class SaveTest extends TestCase {

    public void testIfSaveWorkCanBeOpened()
    {
        SaveParser saveParser = new SaveParser("src/test/resources/testSave.json");
        saveParser.openSave();
    }

    public void testIfWeCanGetAnAttributOfSave()
    {
        SaveParser saveParser = new SaveParser("src/test/resources/testSave.json");
        saveParser.openSave();
        JSONTools.extractStringFromJSONObject(saveParser.getSave().getGameMode(), "value");

        assertEquals("scenario", JSONTools.extractStringFromJSONObject(saveParser.getSave().getGameMode(), "value"));
    }

    public void testIfWeCanLoadDifficultyWork()
    {
        TurnController turnController = new TurnController();
        SaveParser saveParser = new SaveParser("src/test/resources/testSave.json");
        saveParser.openSave();
        SaveLoader saveLoader = new SaveLoader(saveParser.getSave(), turnController);
        saveLoader.loadDifficulty();

        assertEquals("easy", DifficultyController.getInstance().getDifficulty().toString());
    }
}
