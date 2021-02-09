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
            try {
                applyEffect(effect);
            } catch (Exception e) {
                System.err.println("Couldn't apply effect from event, make sure the file is correct.\n" + e);
            }
        }

        if (canTriggerEvent(userChoice)) {
            setTriggerEvent(JSONTools.extractStringFromJSONObject(choices.get(userChoice), "trigger"));
        }
    }

    private void applyEffect(JSONObject jsonEffect) throws Exception {
        String type = JSONTools.extractStringFromJSONObject(jsonEffect, "type");

        if (type.equals("faction")) {
            changeFactionPopularity(jsonEffect);
        } else if (type.equals("resource")) {
            changeResourceAmount(jsonEffect);
        } else if (type.equals("consumable")) {
            changeConsumableAmount(jsonEffect);
        }
    }

    private void changeFactionPopularity(JSONObject jsonEffect) throws Exception {
        FactionController.getInstance()
                .getFactionFromNameFaction(JSONTools.extractStringFromJSONObject(jsonEffect, "key"))
                .addSatisfaction(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
    }

    private void changeResourceAmount(JSONObject jsonEffect) throws Exception {
        ResourcesController.getInstance()
                .getResourceFromResourceName(JSONTools.extractStringFromJSONObject(jsonEffect, "key"))
                .addSize(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
    }

    private void changeConsumableAmount(JSONObject jsonEffect) throws Exception {
        ConsumableController.getInstance()
                .getConsumableFromConsumableName(JSONTools.extractStringFromJSONObject(jsonEffect, "key"))
                .addAmount(JSONTools.extractIntFromJSONObject(jsonEffect, "change"));
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
