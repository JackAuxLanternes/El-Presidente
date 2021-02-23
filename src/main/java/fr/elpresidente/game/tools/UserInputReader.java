package fr.elpresidente.game.tools;

import java.util.Scanner;

public class UserInputReader {

    public static int readUserChoice(int minimumChoiceValue, int maximumChoiceValue) {
        int userChoice;
        do {
            System.out.println("Quel est votre choix ? (Entrez un numéro valide)");
            Scanner scanner = new Scanner(System.in);
            userChoice = scanner.nextInt();
        } while (!isUserChoiceNotInRangeOfChoices(userChoice, minimumChoiceValue, maximumChoiceValue));
        return userChoice;
    }

    private static boolean isUserChoiceNotInRangeOfChoices(int userChoice, int minimumChoiceValue, int maximumChoiceValue) {
        return userChoice >= minimumChoiceValue && userChoice <= maximumChoiceValue;
    }
}
