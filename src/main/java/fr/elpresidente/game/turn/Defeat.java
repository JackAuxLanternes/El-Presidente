package fr.elpresidente.game.turn;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.factions.FactionController;

public class Defeat {

    private final FactionController factionController;

    public Defeat() {
        this.factionController = FactionController.getInstance();
    }

    public boolean completeConditionsToContinue(){
        return this.isSatisfactionSuperiorThanThreshold();
    }

    public boolean isSatisfactionSuperiorThanThreshold(){
        return this.factionController.determineGlobalSatisfaction() > DifficultyController.getInstance().getDifficulty().getPercentageLoose();
    }
}
