package fr.elpresidente.game.turn;

import fr.elpresidente.game.builders.TurnBuilder;
import fr.elpresidente.game.endOfYearEvents.FoodMarket;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;

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
                newYear();
                return Seasons.WINTER;
        }
        return null;
    }

    @Override
    public void buildTurn() {

    }

    @Override
    public void newYear() {
        incrementYear();

        FoodMarket.getInstance().buyFoodUnits();
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
