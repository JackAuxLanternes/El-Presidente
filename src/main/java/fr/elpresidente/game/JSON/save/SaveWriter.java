package fr.elpresidente.game.JSON.save;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.resources.consumable.ConsumableController;
import fr.elpresidente.game.resources.resource.ResourcesController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SaveWriter {

    private final TurnController turnController;

    public SaveWriter(TurnController turnController) {
        this.turnController = turnController;
    }

    public void writeSave(String file_name) {
        //Write JSON file
        try (FileWriter file = new FileWriter(file_name)) {
            file.write(createSave().toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject createSave() {

        JSONObject jsonObject = new JSONObject();

        JSONObject gamemode = GameModeController.getInstance().getGameMode().toJSONObject();
        jsonObject.put(JSONKeys.SAVE_GAMEMODE_KEY, gamemode);

        JSONObject difficulty = DifficultyController.getInstance().getDifficulty().toJSONObject();
        jsonObject.put(JSONKeys.SAVE_DIFFICULTY_KEY, difficulty);

        JSONObject date = turnController.toJSONObject();
        jsonObject.put(JSONKeys.SAVE_DATE_KEY, date);

        JSONArray resourcesArray = ResourcesController.getInstance().toJSONArray();
        jsonObject.put(JSONKeys.SAVE_RESOURCES_KEY, resourcesArray);

        JSONArray consumablesArray = ConsumableController.getInstance().toJSONArray();
        jsonObject.put(JSONKeys.SAVE_CONSUMABLE_KEY, consumablesArray);

        JSONArray factionArray = FactionController.getInstance().toJSONArray();
        jsonObject.put(JSONKeys.SAVE_FACTION_KEY, factionArray);

        JSONObject lastEvent = EventController.getInstance().getCurrentEvent().toJSONObject();
        jsonObject.put(JSONKeys.SAVE_LAST_EVENT_KEY, lastEvent);

        return jsonObject;
    }
}
