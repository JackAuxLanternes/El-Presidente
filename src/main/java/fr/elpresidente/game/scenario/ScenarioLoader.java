package fr.elpresidente.game.scenario;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ScenarioLoader {

    private Scenario scenario;

    public ScenarioLoader(Scenario scenario) {
        this.setScenario(scenario);
    }

    public void tryToLoadScenario() {
        try {
            this.loadResourceScenario();
            this.loadConsumableScenario();
            this.loadFactionsScenario();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Couldn't load scenario completely, check file is in the right folder and not corrupted.\n" + nullPointerException);
        }
    }

    public void loadResourceScenario() {
        JSONObject agricultureScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getResources(), "name", "agriculture");
        JSONObject industryScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getResources(), "name", "industry");

        ResourcesController.getInstance().getAgriculture().setSize(JSONTools.extractIntFromJSONObject(agricultureScenario, "value"));
        ResourcesController.getInstance().getIndustry().setSize(JSONTools.extractIntFromJSONObject(industryScenario, "value"));
    }

    public void loadConsumableScenario() {
        JSONObject foodScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getConsumable(), "name", "food");
        JSONObject treasuryScenario = JSONTools.findJSONObjectInJSONArrayWithKeyValue(scenario.getConsumable(), "name", "treasury");

        ConsumableController.getInstance().getFood().setAmount(JSONTools.extractIntFromJSONObject(foodScenario, "value"));
        ConsumableController.getInstance().getTreasury().setAmount(JSONTools.extractIntFromJSONObject(treasuryScenario, "value"));
    }

    public void loadFactionsScenario() {
        JSONArray factionsScenario = scenario.getFactions();

        for (Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject scenarioFaction = JSONTools.findJSONObjectInJSONArrayWithKeyValue(factionsScenario, "name", faction.getName());
            faction.setSatisfaction(JSONTools.extractIntFromJSONObject(scenarioFaction, "popularity"));
            faction.setSupporters(JSONTools.extractIntFromJSONObject(scenarioFaction, "supporters"));
        }
    }

    private void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
