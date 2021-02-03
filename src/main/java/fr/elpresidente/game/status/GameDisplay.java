package fr.elpresidente.game.status;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import fr.elpresidente.game.turn.TurnController;

public class GameDisplay {

    private final TurnController turnController;

    public GameDisplay(TurnController turnController) {
        this.turnController = turnController;
    }

    public void showGameStatus() {
        System.out.println("==============================");
        showTurnStatus();
        showResourcesStatus();
        showFactionsStatus();
        System.out.println("==============================");
        waitForUserInput();
    }

    public void waitForUserInput() {
        System.out.println("Press [ENTER] to continue...");
        try {
            System.in.read();
            System.in.skip(System.in.available());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTurnStatus() {
        System.out.println("Year #" + turnController.getYear() + ", " + turnController.getCurrentTurn());
    }

    public void showResourcesStatus() {
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
        displayGraphicChart((ResourcesController.getInstance().getAgriculture().getSize() + ResourcesController.getInstance().getIndustry().getSize()));
    }

    public void showFactionsStatus() {
        System.out.println("=== Factions ===");
        System.out.println("Satisfactions globale: " + FactionController.getInstance().determineGlobalSatisfaction());
        displayGraphicChart(FactionController.getInstance().determineGlobalSatisfaction());
        for (Faction faction : FactionController.getInstance().getFactions()) {
            displayShortChart(faction.getSatisfaction());
            System.out.println(" - "
                    + faction.getName()
                    + ": "
                    + faction.getSatisfaction()
                    + "%, ce qui nous rapporte "
                    + faction.getSupporters()
                    + " partisants");
        }
    }

    public void displayGraphicChart(double value) {
        displayGraphicChart((int) Math.round(value));
    }

    public void displayGraphicChart(int value) {
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

    public void displayShortChart(double value) {
        displayShortChart( (int) Math.round(value) );
    }

    public void displayShortChart(int value) {
        System.out.print('[');
        for (int i = 0; i < 10; i++) {
            if (i < value / 10) {
                System.out.print('■');
            } else {
                System.out.print('.');
            }
        }
        System.out.print(']');
    }
}
