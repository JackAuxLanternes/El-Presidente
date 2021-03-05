package fr.elpresidente.game.scenario;

import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.factions.supporters.SupportersDistributionController;
import fr.elpresidente.game.tools.JSONLoader;
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
            throw new NullPointerException("Couldn't load scenario completely, check file is in the right folder and not corrupted.\n" + nullPointerException);
        }
    }

    public void loadEventsFromScenario() {
        EventController.getInstance().setScriptedEvents(scenario.getScriptedEvents());
        EventController.getInstance().setConditionalEvents(scenario.getConditionalEvents());
        EventController.getInstance().setGenericEvents(scenario.getGenericEvents());
    }

    public void loadSupportersDistribution() {
        SupportersDistributionController.getInstance().setSupportersDistributionFromJSONName(JSONTools.extractStringFromJSONObject(scenario.getSupportersDistribution(), "name"));
    }

    private void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    private void setJSONLoader(Scenario scenario, TurnController turnController)
    {
        this.jsonLoader = new JSONLoader(scenario, turnController);
    }
}
