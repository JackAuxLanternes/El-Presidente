package fr.elpresidente.game.turn;

import fr.elpresidente.game.builders.TurnBuilder;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import org.json.simple.JSONObject;

public class TurnController implements TurnBuilder {

    private Seasons currentTurn;

    private int year;

    @Override
    public void setStartDate(int year, Seasons season) {
        this.year = year;
        this.currentTurn = season;
    }

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
        EventController.getInstance().findEvent(year, currentTurn);
    }

    @Override
    public void nextTurn() {
        setCurrentTurn(getNextTurn());
        buildTurn();
    }

    @Override
    public void newYear() {
        incrementYear();
        ConsumableController.getInstance().getFood().addAmount(ResourcesController.getInstance().getAgriculture().getAnnualYields());
        ConsumableController.getInstance().getTreasury().addAmount(ResourcesController.getInstance().getIndustry().getAnnualYields());
        //TODO replace with constante or getter to avoid using * 4
        ConsumableController.getInstance().getFood().substractAmount(FactionController.getInstance().determineTotalSupporters() * 4);
    }

    public JSONObject toJSONObject() {
        JSONObject date = new JSONObject();
        date.put("year", this.getYear());
        date.put("season", this.getCurrentTurn().toString());

        return date;
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
