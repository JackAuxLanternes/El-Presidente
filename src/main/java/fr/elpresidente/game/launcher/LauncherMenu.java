package fr.elpresidente.game.launcher;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.difficulty.EasyDifficulty;
import fr.elpresidente.game.difficulty.HardDifficulty;
import fr.elpresidente.game.difficulty.NormalDifficulty;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.mode.SandboxMode;
import fr.elpresidente.game.mode.ScenarioMode;
import fr.elpresidente.game.tools.UserInputReader;

public class LauncherMenu {

    public LauncherGameType choseGameType() {
        this.printChoiceGameType();
        int choice = UserInputReader.readUserChoice(1, 2);

        if (choice == 1) {
            return LauncherGameType.NEW_GAME;
        }
        return LauncherGameType.LOAD_GAME;
    }

    private void printChoiceGameType() {
        System.out.println("==============================");
        System.out.println("=== Choisissez ce que vous voulez faire:");
        System.out.println("=== 1. Nouvelle partie");
        System.out.println("=== 2. Charger la partie précédente");
    }

    public void choseDifficultyForTheGame() {
        this.printChoiceDifficulty();
        int choice = UserInputReader.readUserChoice(1, 3);
        switch (choice) {
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

    private void printChoiceDifficulty() {
        System.out.println("==============================");
        System.out.println("=== Choisissez une difficulté pour le jeu:");
        System.out.println("=== 1. Facile");
        System.out.println("=== 2. Moyen");
        System.out.println("=== 3. Difficile");
    }

    public void choseGameMode() {
        this.printChoiceGameMode();
        int choice = UserInputReader.readUserChoice(1, 2);

        if (choice == 1) {
            GameModeController.getInstance().setGameMode(new ScenarioMode());
        } else if (choice == 2) {
            GameModeController.getInstance().setGameMode(new SandboxMode());
        }
    }

    private void printChoiceGameMode() {
        System.out.println("==============================");
        System.out.println("=== Choisissez un mode de jeu:");
        System.out.println("=== 1. Scénario");
        System.out.println("=== 2. Bac à sable");
    }
}
