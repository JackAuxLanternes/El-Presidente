package fr.elpresidente.game.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Event {

    private JSONObject event;

    private String description;

    private ArrayList<JSONObject> choices;

    private String triggerEvent = null;

    public Event(JSONObject event) {
        this.event = event;
        this.initEvent();
    }

    private void initEvent() {
        this.description = JSONTools.extractStringFromJSONObject(this.event, "description");
        this.choices = JSONTools.collectJSONArrayChildrenAsArrayList((JSONArray) this.event.get("choices"));
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getChoicesName() {
        ArrayList<String> choicesName = new ArrayList<>();
        for (JSONObject choice : choices) {
            choicesName.add(JSONTools.extractStringFromJSONObject(choice, "name"));
        }
        return choicesName;
    }

    public void processEffectForChoice(int userChoice) {
        ArrayList<JSONObject> effects = JSONTools.collectJSONArrayChildrenAsArrayList((JSONArray) choices.get(userChoice).get("effects"));
        for (JSONObject effect : effects) {
            applyEffect(effect);
        }

        if (canTriggerEvent(userChoice)) {
            setTriggerEvent(JSONTools.extractStringFromJSONObject(choices.get(userChoice), "trigger"));
        }
    }

    private void applyEffect(JSONObject jsonEffect) {
        String type = JSONTools.extractStringFromJSONObject(jsonEffect, "type");

        if (type.equals("faction")) {
            changeFactionPopularity(jsonEffect);
        } else if (type.equals("resource")) {
            changeResourceAmount(jsonEffect);
        } else if (type.equals("consumable")) {
            changeConsumableAmount(jsonEffect);
        }
    }

    private void changeFactionPopularity(JSONObject jsonEffect) {
        FactionController.getInstance()
                .getFactionFromNameFaction(JSONTools.extractStringFromJSONObject(jsonEffect, "key"))
                .addSatisfaction(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
    }
    //ToDo change if structure with getResourceFromResourceName
    private void changeResourceAmount(JSONObject jsonEffect) {
        String resource = JSONTools.extractStringFromJSONObject(jsonEffect, "key");
        if (resource.equals("agriculture")) {
            ResourcesController.getInstance().getAgriculture().addSize(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
        } else if (resource.equals("industry")){
            ResourcesController.getInstance().getIndustry().addSize(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
        }
    }
    //ToDo change if structure with getConsumableFromConsumableName
    private void changeConsumableAmount(JSONObject jsonEffect) {
        String resource = JSONTools.extractStringFromJSONObject(jsonEffect, "key");
        if (resource.equals("food")) {
            ConsumableController.getInstance().getFood().addAmount(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
        } else if (resource.equals("treasury")){
            ConsumableController.getInstance().getTreasury().addAmount(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
        }
    }

    private boolean canTriggerEvent(int userChoice) {
        return choices.get(userChoice).containsKey("trigger");
    }

    public String getTriggerEvent() {
        return triggerEvent;
    }

    private void setTriggerEvent(String triggerEvent) {
        this.triggerEvent = triggerEvent;
    }
}
