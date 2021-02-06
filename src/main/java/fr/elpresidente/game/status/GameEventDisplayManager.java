package fr.elpresidente.game.status;

import fr.elpresidente.game.events.EventController;

import java.util.Scanner;

public class GameEventDisplayManager {

    public void showEvent() {
        if (EventController.getInstance().getCurrentEvent() != null) {
            printHeadline();
            printEventChoices();
            readUserChoice();
        } else {
            System.out.println("No news good news");
        }
    }

    private void printHeadline() {
        System.out.println("===== El-Présigaro =====");
        System.out.println("📰 " + EventController.getInstance().getCurrentEvent().getDescription());
    }

    private void printEventChoices() {
        for (int i = 0; i < EventController.getInstance().getCurrentEvent().getChoicesName().size(); i++) {
            System.out.println("===");
            System.out.println((i + 1) + " → " + EventController.getInstance().getCurrentEvent().getChoicesName().get(i));
        }
    }

    private void readUserChoice() {
        System.out.println("Quel est votre choix ? (Entrez le numéro de la décision)");
        Scanner scanner = new Scanner(System.in);
        System.out.println("El-Presidente à choisis: " + scanner.nextInt());
    }
}
