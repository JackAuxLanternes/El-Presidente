package fr.elpresidente.game;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.save.SaveLoader;
import fr.elpresidente.game.save.SaveParser;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;
import junit.framework.TestCase;

public class SaveTest extends TestCase {

    public void testIfSaveWorkCanBeOpened()
    {
        TurnController turnController = new TurnController();
        SaveParser saveParser = new SaveParser("src/main/resources/save.json");
        saveParser.openSave();
        saveParser.openSave();
    }

    public void testIfWeCanGetAnAttributOfSave()
    {
        TurnController turnController = new TurnController();
        SaveParser saveParser = new SaveParser("src/main/resources/save.json");
        saveParser.openSave();
        JSONTools.extractStringFromJSONObject(saveParser.getSave().getGameMode(), "value");

        assertEquals("scenario", JSONTools.extractStringFromJSONObject(saveParser.getSave().getGameMode(), "value"));
    }

    public void testIfWeCanLoadDifficultyWork()
    {
        TurnController turnController = new TurnController();
        SaveParser saveParser = new SaveParser("src/main/resources/save.json");
        saveParser.openSave();
        SaveLoader saveLoader = new SaveLoader(saveParser.getSave(), turnController);
        saveLoader.loadDifficulty();

        assertEquals("normal", DifficultyController.getInstance().getDifficulty().toString());
    }
}
