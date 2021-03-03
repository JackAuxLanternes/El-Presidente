package fr.elpresidente.game.endofyear;

import fr.elpresidente.game.endofyear.events.Bribe;
import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.status.GameDisplay;
import fr.elpresidente.game.tools.UserIOTools;

import java.util.Arrays;
import java.util.NoSuchElementException;

class BribeMenu implements EndOfYearActionMenu {

    private final Bribe bribe;

    public BribeMenu() {
        this.bribe = new Bribe();
    }

    public String choseFactionNameForBribe() {
        return this.readActionValue();
    }

    @Override
    public String readActionValue() {
        String faction_name;
        boolean isFactionNameValid;

        do {
            this.printChoiceMenu();
            faction_name = UserIOTools.readStringUserInput();

            try {
                FactionController.getInstance().getFactionFromNameFaction(faction_name);
                isFactionNameValid = true;
            } catch (NoSuchElementException noSuchElementException) {
                this.printInvalideChoice(faction_name);
                isFactionNameValid = false;
            } catch (Exception exception) {
                isFactionNameValid = false;
            }

        } while (!isFactionNameValid);

        return faction_name;
    }

    @Override
    public void printChoiceMenu() {
        System.out.println("==============================");
        System.out.println("=== A quelle faction voulez-vous verser un pot de vin ?");
        this.printFactionPossibility();
        System.out.println("=== Rentrez seulement le nom de la faction");
    }

    private void printFactionPossibility() {
        for (Faction faction :
                FactionController.getInstance().getFactions().stream()
                        .filter(faction ->
                                Arrays.stream(this.bribe.FACTIONS_THAT_DOES_NOT_LIKE_BRIBE)
                                        .anyMatch(anti_bribe_faction -> !anti_bribe_faction.equals(faction.getName())))
                        .toArray(Faction[]::new)) {
            System.out.println(faction.getName());
        }
    }

    @Override
    public void printInvalideChoice(Object faction_name) {
        System.out.println("==============================");
        System.out.println("=== Nom de faction inconnu: "
                + faction_name
                + ", merci de rentrer un nom valide");
    }

    @Override
    public void printGameStatusAfterAction() {
        GameDisplay gameDisplay = new GameDisplay();
        gameDisplay.showResourcesStatus();
        System.out.println("Voici le status des Factions avec la faction sur laquelle vous venez d'agir");
        gameDisplay.showFactionsStatus();
    }

}
