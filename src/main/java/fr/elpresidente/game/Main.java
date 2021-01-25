package fr.elpresidente.game;

import fr.elpresidente.game.launcher.GameLauncher;
import fr.elpresidente.game.launcher.LauncherGameType;
import fr.elpresidente.game.launcher.LauncherMenu;

public class Main {
    public static void main(String[] args) {
        //Launch program (I have no idea what I'm doing for this class and trust me "no idea" is an understatement)
        GameLauncher gameLauncher = new GameLauncher();
        LauncherMenu launcherMenu = new LauncherMenu();

        gameLauncher.init();

        try {
            LauncherGameType gameType = launcherMenu.choseGameType();

            if (gameType == LauncherGameType.NEW_GAME) {
                gameLauncher.createNewGame();
            } else if (gameType == LauncherGameType.LOAD_GAME) {
                gameLauncher.loadSavedGame();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
