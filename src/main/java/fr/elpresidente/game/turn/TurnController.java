package fr.elpresidente.game.turn;

import fr.elpresidente.game.builders.TurnBuilder;

public class TurnController implements TurnBuilder {

    private Seasons currentTurn;

    private int year;

    @Override
    public Seasons getNextTurn() {
        switch (currentTurn) {
            case WINTER:
                return Seasons.SPRING;

            case SPRING:
                return Seasons.SUMMER;

            case SUMMER:
                return Seasons.AUTUMN;

            case AUTUMN:
                incrementYear();
                return Seasons.WINTER;
        }
        return null;
    }

    @Override
    public void buildTurn() {

    }

    public Seasons getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Seasons currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getYear() {
        return year;
    }

    public void incrementYear() {
        this.year += 1;
    }
}
