package fr.elpresidente.game.scenario;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class ScenarioWriter {

    private Scenario scenario;

    private TurnController turnController;

    public ScenarioWriter(Scenario scenario, TurnController turnController) {
        this.scenario = scenario;
        this.turnController = turnController;
    }

    public void writeScenario(String file_name) {
        //Write JSON file
        try (FileWriter file = new FileWriter(file_name)) {

            file.write(createScenario().toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject createScenario() {

        JSONObject jsonObject = new JSONObject();

        JSONObject date = turnController.toJSONObject();
        jsonObject.put("date", date);

        JSONArray resourcesArray = ResourcesController.getInstance().toJSONArray();
        jsonObject.put("resources", resourcesArray);

        JSONArray consumablesArray = ConsumableController.getInstance().toJSONArray();
        jsonObject.put("consumable", consumablesArray);

        JSONArray factionArray = FactionController.getInstance().toJSONArray();
        jsonObject.put("factions", factionArray);

        addEventPartToScenario(jsonObject);

        return jsonObject;
    }

    private void addEventPartToScenario(JSONObject jsonObject) {

        jsonObject.put("events", scenario.getScriptedEvents());
        jsonObject.put("conditional_events", scenario.getConditionalEvents());
        jsonObject.put("generic_events", scenario.getGenericEvents());
    }
}
