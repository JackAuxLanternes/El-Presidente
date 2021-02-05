package fr.elpresidente.game.status;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.scenario.Scenario;
import fr.elpresidente.game.scenario.ScenarioLoader;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Game {

    private final GameDisplay gameDisplay;

    private final TurnController turnController;

    public Game() {
        turnController = new TurnController();
        gameDisplay = new GameDisplay(this.turnController);

        turnController.setCurrentTurn(Seasons.WINTER);
    }

    public void initGame() {
        ResourcesController.getInstance().getAgriculture().setSize(20);
        ResourcesController.getInstance().getIndustry().setSize(20);
        ConsumableController.getInstance().getTreasury().setAmount(1000);
        ConsumableController.getInstance().getFood().setAmount(1000);
        FactionController.getInstance().initFactions();
    }

    public void initFromScenario(Scenario scenario) {
        ScenarioLoader scenarioLoader = new ScenarioLoader(scenario);
        scenarioLoader.tryToLoadScenario();
    }

    public void gameLoop() {
        while (!isDefeated()) {
            gameDisplay.showGameStatus();
            turnController.nextTurn();
        }

        gameDisplay.showGameStatus();
    }

    public boolean isDefeated() {
        return turnController.getYear() > 0;
    }
}
