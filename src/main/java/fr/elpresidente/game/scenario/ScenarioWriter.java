package fr.elpresidente.game.scenario;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScenarioWriter {

    private Scenario scenario;

    private File jsonFile;

    private TurnController turnController;

    public ScenarioWriter(Scenario scenario, TurnController turnController) {
        this.scenario = scenario;
        this.turnController = turnController;
    }

    public void writeScenario(String file_name) {
        //Write JSON file
        try (FileWriter file = new FileWriter(file_name)) {

            for(Faction faction : FactionController.getInstance().getFactions()) {

            }
            JSONObject jsonObject = new JSONObject();

            // partie date
            JSONObject date = createDate();
            jsonObject.put("date", date);

            // partie pas event qui bouge
            //Resources
            JSONArray resourcesArray = createRessources();
            jsonObject.put("resources", resourcesArray);

            //Consumables
            JSONArray consumablesArray = createConsumables();
            jsonObject.put("consumable", consumablesArray);
            //jsonObject.put("consumable", scenario.getConsumable());

            //Factions
            JSONArray factionArray = createFactions();
            jsonObject.put("factions", factionArray);
            //jsonObject.put("factions", scenario.getFactions());
            // partie events qui ne bouge pas
            jsonObject.put("events", scenario.getScriptedEvents());
            jsonObject.put("conditional_events", scenario.getConditionalEvents());
            jsonObject.put("generic_events", scenario.getGenericEvents());
            file.write(jsonObject.toJSONString());
            //file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject createJSONObject(String key, int value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject;
    }

    private JSONObject createJSONObject(String key, String value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject;
    }

    private JSONObject createDate() {
        JSONObject date = new JSONObject();
        date.put("year", turnController.getYear());
        date.put("season", turnController.getCurrentTurn().toString());

        return date;
    }

    private JSONArray createRessources() {

        JSONArray resourcesArray = new JSONArray();
        JSONObject resourcesObjectIndustry = createJSONObject("name", "industry");
        JSONObject resourcesObjectAgriculture = createJSONObject("name", "agriculture");
        resourcesObjectIndustry.put("value", ResourcesController.getInstance().getIndustry().getSize());
        resourcesObjectAgriculture.put("value", ResourcesController.getInstance().getAgriculture().getSize());
        resourcesArray.add(resourcesObjectIndustry);
        resourcesArray.add(resourcesObjectAgriculture);

        return resourcesArray;
    }

    private JSONArray createConsumables() {

        JSONArray consumablesArray = new JSONArray();
        JSONObject consumablesFood = createJSONObject("name", "food");
        JSONObject consumablesTreasury = createJSONObject("name", "treasury");
        consumablesFood.put("value", ConsumableController.getInstance().getFood().getAmount());
        consumablesTreasury.put("value", ConsumableController.getInstance().getTreasury().getAmount());
        consumablesArray.add(consumablesFood);
        consumablesArray.add(consumablesTreasury);

        return consumablesArray;
    }

    private JSONArray createFactions() {
        JSONArray factionArray = new JSONArray();
        for(Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject factionJSONObject = createJSONObject("name", faction.getName());
            factionJSONObject.put("popularity", (int) faction.getSatisfaction());
            factionJSONObject.put("supporters", faction.getSupporters());
            factionArray.add(factionJSONObject);
        }

        return factionArray;
    }
}
