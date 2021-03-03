package fr.elpresidente.game.save;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
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
        jsonObject.put("gamemode", gamemode);

        JSONObject difficulty = DifficultyController.getInstance().getDifficulty().toJSONObject();
        jsonObject.put("difficulty", difficulty);

        JSONObject date = turnController.toJSONObject();
        jsonObject.put("date", date);

        JSONArray resourcesArray = ResourcesController.getInstance().toJSONArray();
        jsonObject.put("resources", resourcesArray);

        JSONArray consumablesArray = ConsumableController.getInstance().toJSONArray();
        jsonObject.put("consumable", consumablesArray);

        JSONArray factionArray = FactionController.getInstance().toJSONArray();
        jsonObject.put("factions", factionArray);

        return jsonObject;
    }
}
