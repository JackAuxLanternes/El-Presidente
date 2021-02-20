package fr.elpresidente.game.turn;

import fr.elpresidente.game.builders.TurnBuilder;
import fr.elpresidente.game.endofyear.EndOfYearController;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.status.GameDisplay;
import org.json.simple.JSONObject;

public class TurnController implements TurnBuilder {

    private Seasons currentTurn;

    private int year;

    private int count_turn = 0;

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
    public void nextTurn(){
        setCurrentTurn(getNextTurn());
        buildTurn();
        this.count_turn += 1;
    }

    public void callEndOfTheYearEventIfItsTime(GameDisplay gameDisplay){
        System.out.println("count_turn "+ this.count_turn);
        if (this.isEndOfTheYear()) {
            gameDisplay.showGameStatus();
            EndOfYearController endOfYearController = new EndOfYearController();
            endOfYearController.callEvents();
        }
    }

    private boolean isEndOfTheYear() {
        return this.count_turn > 0 ? this.count_turn % 4 == 0 : false;
    }

    @Override
    public void newYear() {
        incrementYear();
        ConsumableController.getInstance().getFood().addAmount(ResourcesController.getInstance().getAgriculture().getAnnualYields());
        ConsumableController.getInstance().getTreasury().addAmount(ResourcesController.getInstance().getIndustry().getAnnualYields());
        //TODO replace with constante or getter to avoid using * 4
        ConsumableController.getInstance().getFood().subtractAmount(FactionController.getInstance().determineTotalSupporters() * 4);
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

    public int getCountTurn() {
        return this.count_turn;
    }

    public void setCountTurn(int count_turn) {
        this.count_turn = count_turn;
    }
}
