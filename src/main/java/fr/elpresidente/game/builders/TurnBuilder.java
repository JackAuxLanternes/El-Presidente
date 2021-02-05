package fr.elpresidente.game.builders;

import fr.elpresidente.game.turn.Seasons;

public interface TurnBuilder {

    void setStartDate(int year, Seasons season);

    Seasons getNextTurn();

    void buildTurn();

    void nextTurn();

    void newYear();
}
