package fr.elpresidente.game;

import fr.elpresidente.game.launcher.GameLauncher;
import fr.elpresidente.game.launcher.LauncherGameType;
import fr.elpresidente.game.launcher.LauncherMenu;
import fr.elpresidente.game.scenario.ScenarioParser;
import fr.elpresidente.game.scenario.ScenarioWriter;

public class Main {
    public static void main(String[] args) {
        //Launch program (I have no idea what I'm doing for this class and trust me "no idea" is an understatement)
        GameLauncher gameLauncher = new GameLauncher();
        LauncherMenu launcherMenu = new LauncherMenu();

        try {
            LauncherGameType gameType = launcherMenu.choseGameType();

            if (gameType == LauncherGameType.NEW_GAME) {
                ScenarioParser scenarioParser = new ScenarioParser("example.json");
                scenarioParser.openScenario();
                gameLauncher.createNewGame(scenarioParser.getScenario());
                gameLauncher.init();
            } else if (gameType == LauncherGameType.LOAD_GAME) {

                ScenarioParser scenarioParser = new ScenarioParser("scenario_saved.json");
                scenarioParser.openScenario();
                gameLauncher.loadSavedGame(scenarioParser.getScenario());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
