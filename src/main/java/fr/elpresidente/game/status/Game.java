package fr.elpresidente.game.status;

import fr.elpresidente.game.endofyear.EndOfYearController;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.scenario.Scenario;
import fr.elpresidente.game.scenario.ScenarioLoader;
import fr.elpresidente.game.scenario.ScenarioParser;
import fr.elpresidente.game.scenario.ScenarioWriter;
import fr.elpresidente.game.tools.JSONTools;
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

    public void initGame() {
        ResourcesController.getInstance().getAgriculture().setSize(20);
        ResourcesController.getInstance().getIndustry().setSize(20);
        ConsumableController.getInstance().getTreasury().setAmount(1000);
        ConsumableController.getInstance().getFood().setAmount(1000);
        FactionController.getInstance().initFactions(4, 50);
    }

    public void initFromScenario(Scenario scenario) {
        ScenarioLoader scenarioLoader = new ScenarioLoader(scenario, turnController);
        scenarioLoader.tryToLoadScenario();
    }

    public void initTurnForLoadGame() {
        ScenarioParser scenarioParser = new ScenarioParser("scenario.json");
        scenarioParser.openScenario();
        TurnController turnController_old_json = new TurnController();
        turnController_old_json.setStartDate(JSONTools.extractIntFromJSONObject(scenarioParser.getScenario().getDate(), "year"), JSONTools.extractSeasonFromJSONObject(scenarioParser.getScenario().getDate()));

        this.initCountTurnFromIntialScenarioToLoadScenario(turnController_old_json);
    }

    private void initCountTurnFromIntialScenarioToLoadScenario(TurnController turnController_old_json) {
        this.turnController.setCountTurn(this.determinateCountTurnFromIntialScenarioToLoadScenario(turnController_old_json));
    }

    private int determinateCountTurnFromIntialScenarioToLoadScenario(TurnController turnController_old_json) {
        int count_turn = 0;
        while(turnController_old_json.getYear() != this.turnController.getYear() || turnController_old_json.getCurrentTurn() != this.turnController.getCurrentTurn()) {
            count_turn += 1;
            turnController_old_json.nextTurn();
        }
        return count_turn;
    }

    public void gameLoop(Scenario scenario) {
        turnController.buildTurn();

        while (!isDefeated()) {
            turnController.callEndOfTheYearEventIfItsTime(gameDisplay);
            gameDisplay.showGameStatusWithEvent();
            turnController.nextTurn();
            this.saveGame(scenario);
        }

        gameDisplay.showGameStatusWithEvent();
    }


    private void saveGame(Scenario scenario) {
        ScenarioWriter scenarioWriter = new ScenarioWriter(scenario, turnController);
        scenarioWriter.writeScenario("scenario_saved.json");
    }

    public boolean isDefeated() {
        return turnController.getYear() > 1955;
    }
}
