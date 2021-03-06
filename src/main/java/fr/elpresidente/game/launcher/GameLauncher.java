package fr.elpresidente.game.launcher;

import fr.elpresidente.game.JSON.scenario.Scenario;
import fr.elpresidente.game.builders.GameBuilder;
import fr.elpresidente.game.status.Game;

public class GameLauncher implements GameBuilder {

    @Override
    public void createNewGame(Scenario scenario) {
        Game game = new Game();
        game.initFromScenario(scenario);
        game.gameLoop(false);
    }

    @Override
    public void loadSavedGame(Scenario scenario) {
        Game game = new Game();
        game.initFromScenario(scenario);
        game.initTurnForLoadGame();
        game.gameLoop(true);
    }
}
