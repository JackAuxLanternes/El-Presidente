package fr.elpresidente.game.JSON.scenario;

import fr.elpresidente.game.JSON.JSONLoader;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.factions.supporters.SupportersDistributionController;
import fr.elpresidente.game.tools.JSONKeys;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;

public class ScenarioLoader {

    private Scenario scenario;

    private JSONLoader jsonLoader;

    public ScenarioLoader(Scenario scenario, TurnController turnController) {
        this.setScenario(scenario);
        this.setJSONLoader(scenario, turnController);
    }

    public void tryToLoadScenario() {
        try {
            this.jsonLoader.tryToLoadMainComponents();
            this.loadEventsFromScenario();
            this.loadSupportersDistribution();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Impossible de charger complètement le JSON, vérifiez que le fichier est dans le bon dossier et n'est pas corrompu.\n" + nullPointerException);
        }
    }

    public void loadEventsFromScenario() {
        EventController.getInstance().setScriptedEvents(scenario.getScriptedEvents());
        EventController.getInstance().setConditionalEvents(scenario.getConditionalEvents());
        EventController.getInstance().setGenericEvents(scenario.getGenericEvents());
    }

    public void loadSupportersDistribution() {
        SupportersDistributionController.getInstance().setSupportersDistributionFromJSONName(JSONTools.extractStringFromJSONObject(scenario.getSupportersDistribution(), JSONKeys.FACTION_KEY_NAME));
    }

    private void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    private void setJSONLoader(Scenario scenario, TurnController turnController) {
        this.jsonLoader = new JSONLoader(scenario, turnController);
    }
}
