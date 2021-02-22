package fr.elpresidente.game.endofyear;

import fr.elpresidente.game.endofyear.events.Bribe;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.status.GameDisplay;

import java.util.Arrays;
import java.util.Scanner;

class BribeMenu {

    public String choiceFactionForBribe() {
        return this.getFactionChoiceFromCommandLine();
    }
    private Bribe bribe;

    public BribeMenu() {
        this.bribe = new Bribe();
    }

    private String getFactionChoiceFromCommandLine() {

        String faction_name = "";
        boolean error;
        do {
            try {
                error = false;
                this.printChoiceMenu();
                faction_name = this.readFactionName();
            } catch (Exception e) {
                error = true;
            }
        } while (error);

        return faction_name;
    }

    private void printChoiceMenu() {
        System.out.println("==============================");
        System.out.println("=== A quelle faction voulez-vous verser un pot de vin ?");
        this.printFactionPossibility();
        System.out.println("=== Rentrez seulement le nom de la faction");
    }

    private void printFactionPossibility() {
        FactionController.getInstance().getFactions().forEach((faction) -> {
            boolean faction_doesnt_like_bribe = false;
            for(String faction_name: this.bribe.getFACTION_THAT_DOESNT_LIKE_BRIBE()) {
                if(faction_name == faction.getName()) {
                    faction_doesnt_like_bribe = true;
                }
            }
            if(!faction_doesnt_like_bribe)
                System.out.println(faction.getName());
        });
    }

    private String readFactionName() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String faction_name = scanner.next();
        FactionController.getInstance().getFactionFromNameFaction(faction_name);
        return faction_name;
    }

    public void printGameStatusAfterBribe() {
        GameDisplay gameDisplay = new GameDisplay();
        gameDisplay.showResourcesStatus();
        System.out.println("Voici le status des Factions avec la faction sur laquelle vous venez d'agir");
        gameDisplay.showFactionsStatus();
    }

}
