package fr.elpresidente.game.status;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;

public class Game {

    private final GameDisplay gameDisplay;

    private final TurnController turnController;

    public Game() {
        turnController = new TurnController();
        gameDisplay = new GameDisplay(this.turnController);
    }

    public void initGame() {
        ResourcesController.getInstance().getAgriculture().setSize(20);
        ResourcesController.getInstance().getIndustry().setSize(20);
        ConsumableController.getInstance().getTreasury().setAmount(1000);
        ConsumableController.getInstance().getFood().setAmount(1000);
        FactionController.getInstance().initFactions();
    }

    public void gameLoop() {
        turnController.setCurrentTurn(Seasons.WINTER);
        while (!isDefeated()) {
            turnController.setCurrentTurn(turnController.getNextTurn());
            gameDisplay.showGameStatus();
        }
    }

    public boolean isDefeated() {
        return turnController.getYear() > 0;
    }
}
