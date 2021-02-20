package fr.elpresidente.game.launcher;

import fr.elpresidente.game.difficulty.*;

import java.util.Scanner;

public class LauncherMenu {

    public LauncherGameType choseGameType() {

        int choice = getGameTypeFromCommandLine();

        if (choice == 1) {
            return LauncherGameType.NEW_GAME;
        }
        return LauncherGameType.LOAD_GAME;
    }

    private void printChoiceGameType() {
        System.out.println("==============================");
        System.out.println("=== Please choose game type:");
        System.out.println("=== 1. New game");
        System.out.println("=== 2. Load previous game");
    }

    private int getGameTypeFromCommandLine() {

        int choice = 0;
        boolean error;
        do {
            try{
                error = false;
                this.printChoiceGameType();
                choice = this.readGameType();
            }catch(Exception e) {
                error = true;
            }
        }while(error);

        return choice;
    }

    private int readGameType() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 2) {
            throw new Exception();
        }

        return choice;
    }

    public void choseDifficultyForTheGame(){

        int choice = this.getDifficultyChoiceFromCommandLine();
        switch(choice) {
            case 1:
                DifficultyController.getInstance().setDifficulty(new EasyDifficulty());
                break;
            case 2:
                DifficultyController.getInstance().setDifficulty(new NormalDifficulty());
                break;
            case 3:
                DifficultyController.getInstance().setDifficulty(new HardDifficulty());
                break;
        }
    }

    private int getDifficultyChoiceFromCommandLine() {

        int choice = 0;
        boolean error;
        do {
            try{
                error = false;
                this.printChoiceDiffciulty();
                choice = this.readDifficulty();
            }catch(Exception e) {
                error = true;
            }
        }while(error);

        return choice;
    }

    private void printChoiceDiffciulty() {
        System.out.println("==============================");
        System.out.println("=== Choisissez une difficulté pour le jeu:");
        System.out.println("=== 1. Facile");
        System.out.println("=== 2. Moyen");
        System.out.println("=== 3. Difficile");
    }

    private int readDifficulty() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 3) {
            throw new Exception();
        }

        return choice;
    }
}
