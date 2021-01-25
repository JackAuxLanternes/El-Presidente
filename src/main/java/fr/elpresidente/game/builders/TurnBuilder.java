package fr.elpresidente.game.builders;

import fr.elpresidente.game.turn.Seasons;

public interface TurnBuilder {

    Seasons getNextTurn();

    void buildTurn();

}
