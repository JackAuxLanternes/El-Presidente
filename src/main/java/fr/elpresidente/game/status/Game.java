package fr.elpresidente.game.status;

import fr.elpresidente.game.JSON.JSONParser;
import fr.elpresidente.game.JSON.save.Save;
import fr.elpresidente.game.JSON.save.SaveLoader;
import fr.elpresidente.game.JSON.save.SaveWriter;
import fr.elpresidente.game.JSON.scenario.Scenario;
import fr.elpresidente.game.JSON.scenario.ScenarioLoader;
import fr.elpresidente.game.turn.Defeat;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;

public class Game {

    private final GameDisplay gameDisplay;

    private final TurnController turnController;

    public Game() {
        turnController = new TurnController();
        gameDisplay = new GameDisplay(this.turnController);

        turnController.setCurrentTurn(Seasons.WINTER);
    }

    public void initFromScenario(Scenario scenario) {
        ScenarioLoader scenarioLoader = new ScenarioLoader(scenario, turnController);
        scenarioLoader.tryToLoadScenario();
    }

    public void initTurnForLoadGame() {
        JSONParser saveParser = new JSONParser("./save.json");
        saveParser.openAsSave();
        SaveLoader saveLoader = new SaveLoader((Save) saveParser.getContent(), turnController);

        saveLoader.tryToLoadSave();
    }

    public void gameLoop(boolean isGameLoaded) {
        if (!isGameLoaded) {
            turnController.buildTurn();
        }

        while (!isDefeated()) {
            turnController.callEndOfTheYearEventIfItsTime(gameDisplay);
            gameDisplay.showGameStatusWithEvent();
            turnController.nextTurn();
            this.saveGame();
        }
        gameDisplay.showGameStatusOnDefeat();
    }


    private void saveGame() {
        SaveWriter saveWriter = new SaveWriter(turnController);
        saveWriter.writeSave("./save.json");
    }

    public boolean isDefeated() {
        Defeat defeat = new Defeat();
        return !defeat.completeConditionsToContinue();
    }
}
