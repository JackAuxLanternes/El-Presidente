package fr.elpresidente.game;

import fr.elpresidente.game.JSON.JSONParser;
import fr.elpresidente.game.JSON.save.Save;
import fr.elpresidente.game.JSON.save.SaveLoader;
import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;
import junit.framework.TestCase;

public class SaveTest extends TestCase {

    public void testIfSaveWorkCanBeOpened() {
        JSONParser saveParser = new JSONParser("src/test/resources/testSave.json");
        saveParser.openAsSave();
    }

    public void testIfWeCanGetAnAttributOfSave() {
        JSONParser saveParser = new JSONParser("src/test/resources/testSave.json");
        saveParser.openAsSave();
        Save save = (Save) saveParser.getContent();
        JSONTools.extractStringFromJSONObject(save.getGameMode(), "value");

        assertEquals("scenario", JSONTools.extractStringFromJSONObject(save.getGameMode(), "value"));
    }

    public void testIfWeCanLoadDifficultyWork() {
        TurnController turnController = new TurnController();
        JSONParser saveParser = new JSONParser("src/test/resources/testSave.json");
        saveParser.openAsSave();
        SaveLoader saveLoader = new SaveLoader((Save) saveParser.getContent(), turnController);
        saveLoader.loadDifficulty();

        assertEquals("normal", DifficultyController.getInstance().getDifficulty().toString());
    }
}
