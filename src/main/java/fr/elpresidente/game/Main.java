package fr.elpresidente.game;

import fr.elpresidente.game.launcher.GameLauncher;
import fr.elpresidente.game.launcher.LauncherGameType;
import fr.elpresidente.game.launcher.LauncherMenu;
import fr.elpresidente.game.scenario.ScenarioParser;

public class Main {
    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        LauncherMenu launcherMenu = new LauncherMenu();

        try {
            ScenarioParser scenarioParser = new ScenarioParser("src/main/resources/scenario.json");
            LauncherGameType gameType = launcherMenu.choseGameType();
            if (gameType == LauncherGameType.NEW_GAME) {
                launcherMenu.choseGameMode();
                launcherMenu.choseDifficultyForTheGame();
                scenarioParser.openScenario();
                gameLauncher.createNewGame(scenarioParser.getScenario());
            } else if (gameType == LauncherGameType.LOAD_GAME) {
                scenarioParser.openScenario();
                gameLauncher.loadSavedGame(scenarioParser.getScenario());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
