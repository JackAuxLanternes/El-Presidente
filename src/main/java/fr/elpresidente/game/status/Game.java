package fr.elpresidente.game.status;

import fr.elpresidente.game.ressources.RessourcesController;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;

public class Game {

    private final TurnController turnController;

    public Game() {
        turnController = new TurnController();
    }

    public void initGame() {
        RessourcesController.getInstance().setAgricultureSize(20);
        RessourcesController.getInstance().setIndustrySize(20);
        RessourcesController.getInstance().setTreasuryAmount(1000);
        RessourcesController.getInstance().setFoodAmount(1000);
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
        System.out.println("====== Report");
        System.out.println("=== Industry: "
                + RessourcesController.getInstance().getIndustrySize()
                + ", Agriculture: "
                + RessourcesController.getInstance().getAgricultureSize());
        System.out.println("=== Money: "
                + RessourcesController.getInstance().getTreasuryAmount()
                + ", Food: "
                + RessourcesController.getInstance().getFoodAmount());
        System.out.println("==============================");
    }
}
