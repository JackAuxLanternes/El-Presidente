package fr.elpresidente.game.endofyear;

import fr.elpresidente.game.difficulty.HardDifficulty;
import fr.elpresidente.game.difficulty.NormalDifficulty;
import fr.elpresidente.game.factions.FactionController;

import java.util.Scanner;

public class BribeMenu {

    public String choiceFactionForBribe() {
        return this.getFactionChoiceFromCommandLine();
    }

    private String getFactionChoiceFromCommandLine() {

        String faction_name = "";
        boolean error;
        do {
            try{
                error = false;
                this.printChoiceMenu();
                faction_name = this.readFactionName();
            }catch(Exception e) {
                error = true;
            }
        }while(error);

        return faction_name;
    }

    private void printChoiceMenu() {
        System.out.println("==============================");
        System.out.println("=== A quelle faction voulez-vous verser un pot de vin ?");
        this.printFactionPossibility();
        System.out.println("=== Rentrez seulement le nom de la faction");
    }

    private void printFactionPossibility() {
        FactionController.getInstance().getFactions().forEach((faction) -> System.out.println(faction));
    }

    private String readFactionName() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String faction_name = scanner.next();

        FactionController.getInstance().getFactionFromNameFaction(faction_name);

        return faction_name;
    }

}
