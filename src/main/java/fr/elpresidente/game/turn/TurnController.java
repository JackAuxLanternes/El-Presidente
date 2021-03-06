package fr.elpresidente.game.turn;

import fr.elpresidente.game.builders.LoadFromSaveBuilder;
import fr.elpresidente.game.builders.TurnBuilder;
import fr.elpresidente.game.endofyear.EndOfYearController;
import fr.elpresidente.game.endofyear.events.AgricultureSurplus;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.resources.consumable.ConsumableController;
import fr.elpresidente.game.resources.resource.ResourcesController;
import fr.elpresidente.game.status.GameDisplay;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import org.json.simple.JSONObject;

public class TurnController implements TurnBuilder, LoadFromSaveBuilder {

    private Seasons currentTurn;

    private int year;

    private int count_turn;

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
        GameModeController.getInstance().findEvent(year, currentTurn);
    }

    @Override
    public void nextTurn() {
        setCurrentTurn(getNextTurn());
        buildTurn();
        this.count_turn += 1;
    }

    public boolean callEndOfTheYearEventIfItsTime(GameDisplay gameDisplay) {
        if (this.isEndOfTheYear()) {
            gameDisplay.showGameStatus();
            EndOfYearController endOfYearController = new EndOfYearController();
            endOfYearController.callEvents();

            this.count_turn += 1;

            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isEndOfTheYear() {
        return this.count_turn % 5 == 4;
    }

    @Override
    public void newYear() {
        incrementYear();
        ConsumableController.getInstance().getFood().addAmount(ResourcesController.getInstance().getAgriculture().getAnnualYields());
        ConsumableController.getInstance().getTreasury().addAmount(ResourcesController.getInstance().getIndustry().getAnnualYields());
        ConsumableController.getInstance().getFood().subtractAmount(FactionController.getInstance().determineTotalSupporters() * AgricultureSurplus.FOOD_NEEDED_BY_SUPPORTER);
    }

    public JSONObject toJSONObject() {
        JSONObject date = new JSONObject();
        date.put("starting_turn", this.getCountTurn());
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

    public String getTranslatedSeason() {
        switch (currentTurn) {
            case WINTER:
                return "Hiver";

            case SPRING:
                return "Printemps";

            case SUMMER:
                return "Été";

            case AUTUMN:
                return "Automne";
        }
        return null;
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

    @Override
    public void loadFromJSON(JSONObject jsonObject) {
        this.year = JSONTools.extractIntFromJSONObject(jsonObject, JSONKeys.DATE_YEAR_KEY);
        this.currentTurn = JSONTools.extractSeasonFromJSONObject(jsonObject);
        this.count_turn = JSONTools.extractIntFromJSONObject(jsonObject, JSONKeys.DATE_COUNT_TURN);
    }
}
