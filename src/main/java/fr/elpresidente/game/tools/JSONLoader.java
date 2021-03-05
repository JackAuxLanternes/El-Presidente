package fr.elpresidente.game.tools;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONLoader
{
    private JSONContent scenario;

    private TurnController turnController;

    public JSONLoader(JSONContent scenario, TurnController turnController) {
        this.setScenario(scenario);
        this.setTurnController(turnController);
    }

    public void tryToLoadMainComponents() {
        try {
            this.loadDate();
            this.loadResource();
            this.loadConsumable();
            this.loadFactions();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Couldn't load JSON completely, check file is in the right folder and not corrupted.\n" + nullPointerException);
        }
    }

    public void loadDate() {
        turnController.loadFromJSON(scenario.getDate());
    }

    public void loadResource() {
        JSONObject agriculture = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getAgriculture().getName());
        JSONObject industry = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getIndustry().getName());

        ResourcesController.getInstance().getAgriculture().loadFromJSON(agriculture);
        ResourcesController.getInstance().getIndustry().loadFromJSON(industry);
    }

    public void loadConsumable() {
        JSONObject food = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getFood().getName());
        JSONObject treasury = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getTreasury().getName());

        ConsumableController.getInstance().getFood().loadFromJSON(food);
        ConsumableController.getInstance().getTreasury().loadFromJSON(treasury);
    }

    public void loadFactions() {
        JSONArray factions = scenario.getFactions();

        for (Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject scenarioFaction = JSONTools.findJSONObjectInJSONArrayWithKeyValue(factions, JSONKeys.FACTION_KEY_NAME, faction.getName());
            faction.loadFromJSON(scenarioFaction);
        }
    }

    public void setScenario(JSONContent scenario) {
        this.scenario = scenario;
    }

    public void setTurnController(TurnController turnController) {
        this.turnController = turnController;
    }
}
