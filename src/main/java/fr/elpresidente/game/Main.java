package fr.elpresidente.game;

import fr.elpresidente.game.launcher.GameLauncher;
import fr.elpresidente.game.launcher.LauncherGameType;
import fr.elpresidente.game.launcher.LauncherMenu;
import fr.elpresidente.game.JSON.scenario.Scenario;
import fr.elpresidente.game.JSON.JSONParser;

public class Main {
    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        LauncherMenu launcherMenu = new LauncherMenu();

        try {
            JSONParser scenarioParser = new JSONParser("src/main/resources/scenario.json");
            LauncherGameType gameType = launcherMenu.choseGameType();
            if (gameType == LauncherGameType.NEW_GAME) {
                launcherMenu.choseGameMode();
                launcherMenu.choseDifficultyForTheGame();
                scenarioParser.openAsScenario();
                gameLauncher.createNewGame((Scenario) scenarioParser.getContent());
            } else if (gameType == LauncherGameType.LOAD_GAME) {
                scenarioParser.openAsScenario();
                gameLauncher.loadSavedGame((Scenario) scenarioParser.getContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
