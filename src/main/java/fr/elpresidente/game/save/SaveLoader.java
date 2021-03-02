package fr.elpresidente.game.save;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.tools.JSONKeys;
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
        turnController.loadFromJSON(save.getDate());
    }

    public void loadResourceSaved() {
        JSONObject agricultureSaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getAgriculture().getName());
        JSONObject industrySaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getIndustry().getName());

        ResourcesController.getInstance().getAgriculture().loadFromJSON(agricultureSaved);
        ResourcesController.getInstance().getIndustry().loadFromJSON(industrySaved);
    }

    public void loadConsumableSaved() {
        JSONObject foodSaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getFood().getName());
        JSONObject treasurySaved = JSONTools.findJSONObjectInJSONArrayWithKeyValue(save.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getTreasury().getName());

        ConsumableController.getInstance().getFood().loadFromJSON(foodSaved);
        ConsumableController.getInstance().getTreasury().loadFromJSON(treasurySaved);
    }

    public void loadFactionsSaved() {
        JSONArray factionsSaved = save.getFactions();

        for (Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject scenarioFaction = JSONTools.findJSONObjectInJSONArrayWithKeyValue(factionsSaved, JSONKeys.FACTION_KEY_NAME, faction.getName());
            faction.loadFromJSON(scenarioFaction);
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
