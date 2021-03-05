package fr.elpresidente.game.save;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.tools.JSONLoader;
import fr.elpresidente.game.tools.JSONTools;
import fr.elpresidente.game.turn.TurnController;

public class SaveLoader {

    private Save save;

    private JSONLoader jsonLoader;

    public SaveLoader(Save save, TurnController turnController) {
        this.setSave(save);
        this.setJSONLoader(save, turnController);
    }

    public void tryToLoadSave() {
        try {
            this.jsonLoader.tryToLoadMainComponents();
            this.loadGameMode();
            this.loadDifficulty();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Couldn't load save completely, check file is in the right folder and not corrupted.\n" + nullPointerException);
        }
    }

    public void loadGameMode() {
        String gameModeSaved = JSONTools.extractStringFromJSONObject(save.getGameMode(), "value");
        GameModeController.getInstance().setGameModeFromName(gameModeSaved);
    }

    public void loadDifficulty() {
        String difficultySaved = JSONTools.extractStringFromJSONObject(save.getDifficulty(), "value");
        DifficultyController.getInstance().setDifficultyFromName(difficultySaved);
    }

    private void setSave(Save save) {
        this.save = save;
    }

    private void setJSONLoader(Save save, TurnController turnController)
    {
        this.jsonLoader = new JSONLoader(save, turnController);
    }
}
