package fr.elpresidente.game.turn;

import fr.elpresidente.game.factions.FactionController;

public class Defeat {

    private FactionController factionController;

    public Defeat() {
        this.factionController = FactionController.getInstance();
    }

    public boolean completeConditionsToContinue(double threshold_percentage) {
        return this.isSatisfactionSuperiorThanThreshold(threshold_percentage);
    }

    public boolean isSatisfactionSuperiorThanThreshold(double threshold_percentage) {
        return this.factionController.determineGlobalSatisfaction() > threshold_percentage;
    }
}
