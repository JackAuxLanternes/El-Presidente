package fr.elpresidente.game.status;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.consumable.ConsumableController;
import fr.elpresidente.game.resources.resource.ResourcesController;
import fr.elpresidente.game.tools.UserIOTools;
import fr.elpresidente.game.turn.TurnController;

public class GameDisplay {

    private final TurnController turnController;

    private final GameEventDisplayManager gameEventDisplayManager;

    public GameDisplay(TurnController turnController) {
        this.turnController = turnController;
        this.gameEventDisplayManager = new GameEventDisplayManager();
    }

    public GameDisplay() {
        this.turnController = new TurnController();
        this.gameEventDisplayManager = new GameEventDisplayManager();
    }

    public void showGameStatusWithEvent() {
        System.out.println("==============================");
        showTurnStatus();
        showResourcesStatus();
        showFactionsStatus();
        UserIOTools.waitForUserToContinue("Le journal vient d'arriver! Appuyez sur [ENTER] pour le lire...");
        gameEventDisplayManager.showEvent();
        System.out.println("==============================");
        UserIOTools.waitForUserToContinue("Fin du tour, appuyez sur [ENTER] pour continuer...");
    }

    public void showGameStatus() {
        System.out.println("==============================");
        showTurnStatus();
        showResourcesStatus();
        showFactionsStatus();
        UserIOTools.waitForUserToContinue("Fin du tour, appuyez sur [ENTER] pour continuer...");
    }

    public void showTurnStatus() {
        System.out.println("Année #" + turnController.getYear() + ", " + turnController.getTranslatedSeason());
    }

    public void showResourcesStatus() {
        System.out.println("=== Consommables ===");
        System.out.println("Argent: "
                + ConsumableController.getInstance().getTreasury().getAmount() + " $"
                + ", Nourriture: "
                + ConsumableController.getInstance().getFood().getAmount() + " unités");
        System.out.println("=== Ressources ===");
        System.out.println("Taille de l'industrie: "
                + ResourcesController.getInstance().getIndustry().getSize() + " %"
                + ", Taille de l'agriculture: "
                + ResourcesController.getInstance().getAgriculture().getSize() + " %");
        System.out.println("Taille totale de l'industrie et de l'agriculture: "
                + (ResourcesController.getInstance().getAgriculture().getSize() + ResourcesController.getInstance().getIndustry().getSize()) + " %");
        displayGraphicChart((ResourcesController.getInstance().getAgriculture().getSize() + ResourcesController.getInstance().getIndustry().getSize()));
    }

    public void showFactionsStatus() {
        System.out.println("=== Factions ===");
        System.out.println("Satisfactions globale: " + String.format("%.2f", FactionController.getInstance().determineGlobalSatisfaction()));
        displayGraphicChart(FactionController.getInstance().determineGlobalSatisfaction());
        for (Faction faction : FactionController.getInstance().getFactions()) {
            displayShortChart(faction.getSatisfaction());
            System.out.println(" - "
                    + faction.getName()
                    + ": "
                    + faction.getSatisfaction()
                    + "%, cette faction compte "
                    + faction.getSupporters()
                    + " partisans");
        }
    }

    public void showGameStatusOnDefeat() {
        System.out.println("==============================");
        System.out.println("=== Vous avez perdu, c'est tellement triste :'( ");
        System.out.println("==============================");
        this.showTurnStatus();
        this.showResourcesStatus();
        this.showFactionsStatus();
        UserIOTools.waitForUserToContinue("Fin de la partie, appuyez sur [ENTER] pour quitter...");
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
        displayShortChart((int) Math.round(value));
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
