package fr.elpresidente.game.status;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.scenario.Scenario;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
        System.out.println(
                scenario.getResources().stream().filter(resource -> ((JSONObject) JSONValue.parse(resource.toString())).get("name").equals("agriculture")).findFirst()
                .stream().mapToInt(resource -> JSONTools.extractIntFromJSONObject((JSONObject) resource, "value")).sum()
        );
        for (int i = 0; i < scenario.getResources().size(); i++) {
            JSONObject resource = (JSONObject) scenario.getResources().get(i);
            if (resource.containsKey("name") && resource.containsValue("agriculture")) {
                ResourcesController.getInstance().getAgriculture().setSize(Math.toIntExact((long) resource.get("value")));
            }
        }
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
