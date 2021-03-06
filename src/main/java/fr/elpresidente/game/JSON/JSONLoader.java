package fr.elpresidente.game.JSON;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.consumable.ConsumableController;
import fr.elpresidente.game.resources.resource.ResourcesController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONLoader
{
    private JSONContent jsonContent;

    private TurnController turnController;

    public JSONLoader(JSONContent jsonContent, TurnController turnController) {
        this.setJsonContent(jsonContent);
        this.setTurnController(turnController);
    }

    public void tryToLoadMainComponents() {
        try {
            this.loadDate();
            this.loadResource();
            this.loadConsumable();
            this.loadFactions();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Impossible de charger complètement le JSON, vérifiez que le fichier est dans le bon dossier et n'est pas corrompu.\n" + nullPointerException);
        }
    }

    public void loadDate() {
        turnController.loadFromJSON(jsonContent.getDate());
    }

    public void loadResource() {
        JSONObject agriculture = JSONTools.findJSONObjectInJSONArrayWithKeyValue(jsonContent.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getAgriculture().getName());
        JSONObject industry = JSONTools.findJSONObjectInJSONArrayWithKeyValue(jsonContent.getResources(), JSONKeys.RESOURCE_KEY_NAME, ResourcesController.getInstance().getIndustry().getName());

        ResourcesController.getInstance().getAgriculture().loadFromJSON(agriculture);
        ResourcesController.getInstance().getIndustry().loadFromJSON(industry);
    }

    public void loadConsumable() {
        JSONObject food = JSONTools.findJSONObjectInJSONArrayWithKeyValue(jsonContent.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getFood().getName());
        JSONObject treasury = JSONTools.findJSONObjectInJSONArrayWithKeyValue(jsonContent.getConsumable(), JSONKeys.CONSUMABLE_KEY_NAME, ConsumableController.getInstance().getTreasury().getName());

        ConsumableController.getInstance().getFood().loadFromJSON(food);
        ConsumableController.getInstance().getTreasury().loadFromJSON(treasury);
    }

    public void loadFactions() {
        JSONArray factions = jsonContent.getFactions();

        for (Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject jsonContentFaction = JSONTools.findJSONObjectInJSONArrayWithKeyValue(factions, JSONKeys.FACTION_KEY_NAME, faction.getName());
            faction.loadFromJSON(jsonContentFaction);
        }
    }

    public void setJsonContent(JSONContent jsonContent) {
        this.jsonContent = jsonContent;
    }

    public void setTurnController(TurnController turnController) {
        this.turnController = turnController;
    }
}
