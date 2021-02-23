package fr.elpresidente.game.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Event {

    private final JSONObject event;

    private String description;

    private ArrayList<JSONObject> choices;

    private String triggerEvent = null;

    public Event(JSONObject event) {
        this.event = event;
        this.initEvent();
    }

    private void initEvent() {
        this.description = JSONTools.extractStringFromJSONObject(this.event, JSONKeys.EVENT_DESCRIPTION_KEY);
        this.choices = JSONTools.collectJSONArrayChildrenAsArrayList((JSONArray) this.event.get(JSONKeys.EVENT_CHOICES_KEY));
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getChoicesName() {
        ArrayList<String> choicesName = new ArrayList<>();
        for (JSONObject choice : choices) {
            choicesName.add(JSONTools.extractStringFromJSONObject(choice, JSONKeys.EVENT_CHOICE_NAME_KEY));
        }
        return choicesName;
    }

    public void processEffectForChoice(int userChoice) {
        ArrayList<JSONObject> effects = JSONTools.collectJSONArrayChildrenAsArrayList((JSONArray) choices.get(userChoice).get(JSONKeys.EVENT_CHOICE_EFFECT_KEY));
        for (JSONObject effect : effects) {
            try {
                applyEffect(effect);
            } catch (Exception e) {
                System.err.println("Couldn't apply effect from event, make sure the file is correct.\n" + e);
            }
        }

        if (canTriggerEvent(userChoice)) {
            setTriggerEvent(JSONTools.extractStringFromJSONObject(choices.get(userChoice), JSONKeys.EVENT_TRIGGER_KEY));
        }
    }

    private void applyEffect(JSONObject jsonEffect) {
        String type = JSONTools.extractStringFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_TYPE_KEY);

        if (type.equals(JSONKeys.EVENT_EFFECT_FACTION_NAME_KEY)) {
            changeFactionPopularity(jsonEffect);
        } else if (type.equals(JSONKeys.EVENT_EFFECT_SUPPORTERS_KEY)) {
            changeFactionSupporters(jsonEffect);
        } else if (type.equals(JSONKeys.EVENT_EFFECT_RESOURCE_KEY)) {
            changeResourceAmount(jsonEffect);
        } else if (type.equals(JSONKeys.EVENT_EFFECT_CONSUMABLE_KEY)) {
            changeConsumableAmount(jsonEffect);
        }
    }

    private void changeFactionPopularity(JSONObject jsonEffect) {
        FactionController.getInstance()
                .getFactionFromNameFaction(JSONTools.extractStringFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_KEY))
                .updateSatisfaction(JSONTools.extractIntFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_CHANGE_KEY));
    }


    private void changeFactionSupporters(JSONObject jsonEffect) {
        String faction_name = JSONTools.extractStringFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_KEY);
        int change = JSONTools.extractIntFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_CHANGE_KEY);

        if (faction_name.equals(JSONKeys.EVENT_EFFECT_RANDOM_KEY)) {
            changeFactionSupportersRandomly(change);
        } else {
            changeFactionSupportersFromFactionName(faction_name, change);
        }
    }

    private void changeFactionSupportersRandomly(int change) {
        if (change > 0) {
            FactionController.getInstance().addSupportersRandomly(change);
        } else {
            FactionController.getInstance().removeSupportersRandomly(Math.abs(change));
        }
    }

    private void changeFactionSupportersFromFactionName(String faction_name, int change) {
        if (change > 0) {
            FactionController.getInstance().getFactionFromNameFaction(faction_name).addSupporter(change);
        } else {
            FactionController.getInstance().getFactionFromNameFaction(faction_name).removeSupporter(Math.abs(change));
        }
    }

    private void changeResourceAmount(JSONObject jsonEffect) {
        ResourcesController.getInstance()
                .getResourceFromResourceName(JSONTools.extractStringFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_KEY))
                .updateSize(JSONTools.extractIntFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_CHANGE_KEY));
    }

    private void changeConsumableAmount(JSONObject jsonEffect) {
        ConsumableController.getInstance()
                .getConsumableFromConsumableName(JSONTools.extractStringFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_KEY))
                .updateAmount(JSONTools.extractIntFromJSONObject(jsonEffect, JSONKeys.EVENT_EFFECT_CHANGE_KEY));
    }

    private boolean canTriggerEvent(int userChoice) {
        return choices.get(userChoice).containsKey(JSONKeys.EVENT_TRIGGER_KEY);
    }

    public String getTriggerEvent() {
        return triggerEvent;
    }

    private void setTriggerEvent(String triggerEvent) {
        this.triggerEvent = triggerEvent;
    }
}
