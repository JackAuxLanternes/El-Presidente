package fr.elpresidente.game.status;

import fr.elpresidente.game.endofyear.EndOfYearController;
import fr.elpresidente.game.save.SaveLoader;
import fr.elpresidente.game.save.SaveParser;
import fr.elpresidente.game.save.SaveWriter;
import fr.elpresidente.game.scenario.Scenario;
import fr.elpresidente.game.scenario.ScenarioLoader;
import fr.elpresidente.game.turn.Defeat;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;

public class Game {

    private final GameDisplay gameDisplay;

    private final TurnController turnController;

    private final EndOfYearController endOfYearController;

    public Game() {
        turnController = new TurnController();
        gameDisplay = new GameDisplay(this.turnController);
        endOfYearController = new EndOfYearController();

        turnController.setCurrentTurn(Seasons.WINTER);
    }

    public void initFromScenario(Scenario scenario) {
        ScenarioLoader scenarioLoader = new ScenarioLoader(scenario, turnController);
        scenarioLoader.tryToLoadScenario();
    }

    public void initTurnForLoadGame() {
        SaveParser saveParser = new SaveParser("src/main/resources/save.json");
        saveParser.openSave();
        SaveLoader saveLoader = new SaveLoader(saveParser.getSave(), turnController);

        saveLoader.tryToLoadSave();
        this.initCountTurnFromInitialScenarioToLoadScenario(turnController);
    }

    private void initCountTurnFromInitialScenarioToLoadScenario(TurnController turnController) {
        this.turnController.setCountTurn(this.determinateCountTurnFromInitialScenarioToLoadScenario(turnController));
    }

    private int determinateCountTurnFromInitialScenarioToLoadScenario(TurnController turnController_old_json) {
        int count_turn = 0;
        while (turnController_old_json.getYear() != this.turnController.getYear() || turnController_old_json.getCurrentTurn() != this.turnController.getCurrentTurn()) {
            count_turn += 1;
            turnController_old_json.nextTurn();
        }
        return count_turn;
    }

    public void gameLoop() {
        turnController.buildTurn();

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
        saveWriter.writeSave("src/main/resources/save.json");
    }

    public boolean isDefeated() {
        Defeat defeat = new Defeat();
        return !defeat.completeConditionsToContinue();
    }
}
