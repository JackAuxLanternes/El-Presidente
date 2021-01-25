package fr.elpresidente.game.status;

import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;

public class Game {

    private final TurnController turnController;

    public Game() {
        turnController = new TurnController();
    }

    public void gameLoop() {
        turnController.setCurrentTurn(Seasons.WINTER);
        while (!isDefeated()) {
            turnController.setCurrentTurn(turnController.getNextTurn());
            showGameStatus();
        }
    }

    public boolean isDefeated() {
        if (turnController.getYear() > 0) {
            return true;
        }
        return false;
    }

    public void showGameStatus() {
        System.out.println("==============================");
        System.out.println("=== Year #" + turnController.getYear() + ", " + turnController.getCurrentTurn());
        System.out.println("==============================");
    }
}
