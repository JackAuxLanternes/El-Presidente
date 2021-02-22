package fr.elpresidente.game.mode;

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
}
