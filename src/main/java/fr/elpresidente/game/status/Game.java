package fr.elpresidente.game.status;

import fr.elpresidente.game.ressources.ResourcesController;
import fr.elpresidente.game.scenario.ScenarioLoader;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;

public class Game {

    private final TurnController turnController;

    public Game() {
        turnController = new TurnController();
    }

    public void initGame() {
        ResourcesController.getInstance().setAgricultureSize(20);
        ResourcesController.getInstance().setIndustrySize(20);
        ResourcesController.getInstance().setTreasuryAmount(1000);
        ResourcesController.getInstance().setFoodAmount(1000);
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
                + ResourcesController.getInstance().getIndustrySize()
                + ", Agriculture: "
                + ResourcesController.getInstance().getAgricultureSize());
        System.out.println("=== Money: "
                + ResourcesController.getInstance().getTreasuryAmount()
                + ", Food: "
                + ResourcesController.getInstance().getFoodAmount());
        System.out.println("==============================");
    }
}
