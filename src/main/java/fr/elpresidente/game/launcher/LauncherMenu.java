package fr.elpresidente.game.launcher;

import java.util.Scanner;

public class LauncherMenu {

    public LauncherGameType choseGameType() throws Exception {
        System.out.println("==============================");
        System.out.println("=== Please choose game type:");
        System.out.println("=== 1. New game");
        System.out.println("=== 2. Load previous game");

        int choice = readGameType();

        if (choice == 1) {
            return LauncherGameType.NEW_GAME;
        }
        return LauncherGameType.LOAD_GAME;
    }

    private int readGameType() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 2) {
            throw new Exception();
        }

        return choice;
    }
}
