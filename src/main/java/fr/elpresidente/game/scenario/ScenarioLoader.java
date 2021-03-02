package fr.elpresidente.game.scenario;

import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.factions.supporters.SupportersDistributionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ScenarioLoader {

    private Scenario scenario;

    private TurnController turnController;

    public ScenarioLoader(Scenario scenario, TurnController turnController) {
        this.setScenario(scenario);
        this.setTurnController(turnController);
    }

    public void tryToLoadScenario() {
        try {
            this.loadDateScenario();
            this.loadResourceScenario();
            this.loadConsumableScenario();
            this.loadFactionsScenario();
            this.loadEventsFromScenario();
            this.loadSupportersDistribution();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Couldn't load scenario completely, check file is in the right folder and not corrupted.\n" + nullPointerException);
        }
    }

    public void loadDateScenario() {
        turnController.loadFromJSON(scenario.getDate());
    }

    public void loadResourceScenario() {
        JSONObject agricultureScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getAgriculture().getName());
        JSONObject industryScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getIndustry().getName());

        ResourcesController.getInstance().getAgriculture().loadFromJSON(agricultureScenario);
        ResourcesController.getInstance().getIndustry().loadFromJSON(industryScenario);
    }

    public void loadConsumableScenario() {
        JSONObject foodScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getFood().getName());
        JSONObject treasuryScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getTreasury().getName());

        ConsumableController.getInstance().getFood().loadFromJSON(foodScenario);
        ConsumableController.getInstance().getTreasury().loadFromJSON(treasuryScenario);
    }

    public void loadFactionsScenario() {
        JSONArray factionsScenario = scenario.getFactions();

        for (Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject scenarioFaction = JSONTools.findJSONObjectInJSONArrayWithKeyValue(factionsScenario, JSONKeys.FACTION_KEY_NAME, faction.getName());
            faction.loadFromJSON(scenarioFaction);
        }
    }

    public void loadEventsFromScenario() {
        EventController.getInstance().setScriptedEvents(scenario.getScriptedEvents());
        EventController.getInstance().setConditionalEvents(scenario.getConditionalEvents());
        EventController.getInstance().setGenericEvents(scenario.getGenericEvents());
    }

    public void loadSupportersDistribution() {

        SupportersDistributionController.getInstance().setSupportersDistributionFromJSONName(JSONTools.extractStringFromJSONObject(scenario.getSupportersDistribution(), "name"));
    }

    private void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    private void setTurnController(TurnController turnController) {
        this.turnController = turnController;
    }
}
