package fr.elpresidente.game.status;

import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.turn.Seasons;
import fr.elpresidente.game.turn.TurnController;

public class Game {

    private final TurnController turnController;

    public Game() {
        turnController = new TurnController();
    }

    public void initGame() {
        ResourcesController.getInstance().getAgriculture().setSize(20);
        ResourcesController.getInstance().getIndustry().setSize(20);
        ConsumableController.getInstance().getTreasury().setAmount(1000);
        ConsumableController.getInstance().getFood().setAmount(1000);
    }

    public void gameLoop() {
        turnController.setCurrentTurn(Seasons.WINTER);
        while (!isDefeated()) {
            turnController.setCurrentTurn(turnController.getNextTurn());
            showGameStatus();
        }
    }

    public boolean isDefeated() {
        return turnController.getYear() > 0;
    }

    public void showGameStatus() {
        System.out.println("==============================");
        System.out.println("=== Year #" + turnController.getYear() + ", " + turnController.getCurrentTurn());
        System.out.println("====== Report");
        System.out.println("=== Industry: "
                + ResourcesController.getInstance().getIndustry().getSize()
                + ", Agriculture: "
                + ResourcesController.getInstance().getAgriculture().getSize());
        System.out.println("=== Money: "
                + ConsumableController.getInstance().getTreasury().getAmount()
                + ", Food: "
                + ConsumableController.getInstance().getFood().getAmount());
        System.out.println("==============================");
    }
}
