package fr.elpresidente.game.status;

import fr.elpresidente.game.events.Event;
import fr.elpresidente.game.events.EventController;

import java.util.Scanner;

public class GameEventDisplayManager {

    private Event event;

    private int choicesSize;

    public void showEvent() {
        if (EventController.getInstance().getCurrentEvent() != null) {
            setEventInfo();
            printHeadline();
            printEventChoices();
            readUserChoice();
        } else {
            System.out.println("No news good news");
        }
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
            userChoice = scanner.nextInt();
        } while (!isUserChoiceNotInRangeOfEventChoices(userChoice));
        System.out.println("El-Presidente à choisis: " + userChoice);
    }

    private void setEventInfo() {
        this.event = EventController.getInstance().getCurrentEvent();
        this.choicesSize = this.event.getChoicesName().size();
    }

    private boolean isUserChoiceNotInRangeOfEventChoices(int userChoice) {
        return userChoice >= 1 && userChoice <= choicesSize + 1;
    }
}
