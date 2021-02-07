package fr.elpresidente.game.status;

import fr.elpresidente.game.events.Event;
import fr.elpresidente.game.events.EventController;

import java.util.Scanner;

public class GameEventDisplayManager {

    private Event event;

    private int choicesSize;

    public void showEvent() {
        setEventInfo();
        printHeadline();
        printEventChoices();
        readUserChoice();
    }

    private void printHeadline() {
        System.out.println("===== El-Présigaro =====");
        System.out.println("📰 " + event.getDescription());
    }

    private void printEventChoices() {
        for (int i = 0; i < choicesSize; i++) {
            System.out.println("===");
            System.out.println((i + 1) + " → " +event.getChoicesName().get(i));
        }
    }

    private void readUserChoice() {
        int userChoice;
        do {
            System.out.println("Quel est votre choix ? (Entrez un numéro valide de décision)");
            Scanner scanner = new Scanner(System.in);
            userChoice = scanner.nextInt() - 1;
        } while (!isUserChoiceNotInRangeOfEventChoices(userChoice));
        event.processEffectForChoice(userChoice);
    }

    private void setEventInfo() {
        this.event = EventController.getInstance().getCurrentEvent();
        this.choicesSize = this.event.getChoicesName().size();
    }

    private boolean isUserChoiceNotInRangeOfEventChoices(int userChoice) {
        return userChoice >= 0 && userChoice < choicesSize;
    }
}
