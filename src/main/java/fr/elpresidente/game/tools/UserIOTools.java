package fr.elpresidente.game.tools;

import java.util.Scanner;

public class UserIOTools {

    public static int readUserChoice(int minimumChoiceValue, int maximumChoiceValue) {
        int userChoice;
        do {
            System.out.println("Quel est votre choix ? (Entrez un numéro valide)");
            userChoice = readIntFromUserInput();
        } while (!isUserChoiceNotInRangeOfChoices(userChoice, minimumChoiceValue, maximumChoiceValue));
        return userChoice;
    }

    public static String readStringUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static int readIntFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static boolean isUserChoiceNotInRangeOfChoices(int userChoice, int minimumChoiceValue, int maximumChoiceValue) {
        return userChoice >= minimumChoiceValue && userChoice <= maximumChoiceValue;
    }

    public static void waitForUserToContinue(String message) {
        System.out.println(message);
        try {
            System.in.read();
            System.in.skip(System.in.available());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
