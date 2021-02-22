package fr.elpresidente.game.mode;

public class ScenarioMode implements GameMode {

    private final String NAME = "Sandbox";

    public ScenarioMode() {
    }

    @Override
    public String getName() {
        return this.NAME;
    }
}
