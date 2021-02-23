package fr.elpresidente.game.save;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SaveLoader {

    private Save save;

    private TurnController turnController;

    public SaveLoader(Save save, TurnController turnController) {
        this.setSave(save);
        this.setTurnController(turnController);
    }

    public void tryToLoadSave() {
        try {
            this.loadDateSaved();
            this.loadResourceSaved();
            this.loadConsumableSaved();
            this.loadFactionsSaved();
            this.loadGameMode();
            this.loadDifficulty();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Couldn't load save completely, check file is in the right folder and not corrupted.\n" + nullPointerException);
        }
    }

    public void loadDateSaved() {
        turnController.setStartDate(JSONTools.extractIntFromJSONObject(save.getDate(), "year"), JSONTools.extractSeasonFromJSONObject(save.getDate()));
    }

    public void loadResourceSaved() {
        JSONObject agricultureSaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getResources(), "name", "agriculture");
        JSONObject industrySaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getResources(), "name", "industry");

        ResourcesController.getInstance().getAgriculture().setSize(JSONTools.extractIntFromJSONObject(agricultureSaved, "value"));
        ResourcesController.getInstance().getIndustry().setSize(JSONTools.extractIntFromJSONObject(industrySaved, "value"));
    }

    public void loadConsumableSaved() {
        JSONObject foodSaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getConsumable(), "name", "food");
        JSONObject treasurySaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getConsumable(), "name", "treasury");

        ConsumableController.getInstance().getFood().setAmount(JSONTools.extractIntFromJSONObject(foodSaved, "value"));
        ConsumableController.getInstance().getTreasury().setAmount(JSONTools.extractIntFromJSONObject(treasurySaved, "value"));
    }

    public void loadFactionsSaved() {
        JSONArray factionsSaved = save.getFactions();

        for (Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject scenarioFaction = JSONTools.findJSONObjectInJSONArrayWithKeyValue(factionsSaved, "name", faction.getName());
            faction.setSatisfaction(JSONTools.extractIntFromJSONObject(scenarioFaction, "popularity"));
            faction.setSupporters(JSONTools.extractIntFromJSONObject(scenarioFaction, "supporters"));
        }
    }

    public void loadGameMode()
    {
        String gameModeSaved = JSONTools.extractStringFromJSONObject(save.getGameMode(), "value");
        GameModeController.getInstance().setGameModeFromName(gameModeSaved);
    }
    public void loadDifficulty()
    {
        String difficultySaved = JSONTools.extractStringFromJSONObject(save.getDifficulty(), "value");
        DifficultyController.getInstance().setDifficultyFromName(difficultySaved);
    }

    private void setSave(Save save) {
        this.save = save;
    }

    private void setTurnController(TurnController turnController) {
        this.turnController = turnController;
    }
}
