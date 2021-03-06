package fr.elpresidente.game.builders;

import fr.elpresidente.game.JSON.scenario.Scenario;

public interface GameBuilder {

    void createNewGame(Scenario scenario);

    void loadSavedGame(Scenario scenario);
}
