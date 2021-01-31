package fr.elpresidente.game.status;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
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
        FactionController.getInstance().initFactions();
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

    private void showGameStatus() {
        System.out.println("==============================");
        showTurnStatus();
        showResourcesStatus();
        showFactionsStatus();
        System.out.println("==============================");
    }

    private void showTurnStatus() {
        System.out.println("Year #" + turnController.getYear() + ", " + turnController.getCurrentTurn());
    }

    private void showResourcesStatus() {
        System.out.println("=== Consumable ===");
        System.out.println("Money: "
                + ConsumableController.getInstance().getTreasury().getAmount()
                + ", Food: "
                + ConsumableController.getInstance().getFood().getAmount());
        System.out.println("=== Resources ===");
        System.out.println("Industry size: "
                + ResourcesController.getInstance().getIndustry().getSize()
                + ", Agriculture size: "
                + ResourcesController.getInstance().getAgriculture().getSize());
        System.out.println("Total size of the agriculture and industry: "
            + (ResourcesController.getInstance().getAgriculture().getSize() + ResourcesController.getInstance().getIndustry().getSize()));
        displayGraphicStatus((ResourcesController.getInstance().getAgriculture().getSize() + ResourcesController.getInstance().getIndustry().getSize()));
    }

    private void showFactionsStatus() {
        System.out.println("=== Factions ===");
        System.out.println("Satisfactions globale: " + FactionController.getInstance().determineGlobalSatisfaction());
        displayGraphicStatus((int) FactionController.getInstance().determineGlobalSatisfaction());
        for (Faction faction : FactionController.getInstance().getFactions()) {
            System.out.println(faction.getName()
                    + ": "
                    + faction.getSatisfaction()
                    + "%, ce qui nous rapporte "
                    + faction.getSupporters()
                    + " partisants");
        }
    }

    private void displayGraphicStatus(int value) {
        System.out.print('[');
        for (int i = 0; i < 100; i++) {
            if (i < value) {
                System.out.print('■');
            } else {
                System.out.print('.');
            }
        }
        System.out.println(']');
    }
}
