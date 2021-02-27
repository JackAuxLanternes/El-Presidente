package fr.elpresidente.game.mode;

import fr.elpresidente.game.difficulty.EasyDifficulty;

public class GameModeController {

    private static GameModeController instance;

    private GameMode gameMode;

    public static GameModeController getInstance() {
        if (instance == null) {
            instance = new GameModeController();
        }

        return instance;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setGameModeFromName(String name)
    {
        switch (name)
        {
            case "scenario":
                gameMode = new ScenarioMode();
                break;

            case "sandbox":
                gameMode = new SandboxMode();
        }
    }
}
