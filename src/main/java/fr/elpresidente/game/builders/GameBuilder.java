package fr.elpresidente.game.builders;

import fr.elpresidente.game.scenario.Scenario;

public interface GameBuilder {

    void init();

    void createNewGame(Scenario scenario);

    void loadSavedGame();
}
