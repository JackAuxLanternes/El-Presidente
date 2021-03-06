package fr.elpresidente.game.JSON.save;

import fr.elpresidente.game.JSON.JSONLoader;
import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.events.Event;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.mode.GameModeController;
import fr.elpresidente.game.tools.JSONKeys;
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
            this.loadLastEvent();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Impossible de charger complètement la sauvegarde, vérifiez que le fichier est dans le bon dossier et n'est pas corrompu.\n" + nullPointerException);
        }
    }

    public void loadGameMode() {
        String gameModeSaved = JSONTools.extractStringFromJSONObject(save.getGameMode(), JSONKeys.GAMEMODE_KEY_VALUE);
        GameModeController.getInstance().setGameModeFromName(gameModeSaved);
    }

    public void loadDifficulty() {
        String difficultySaved = JSONTools.extractStringFromJSONObject(save.getDifficulty(), JSONKeys.DIFFICULTY_KEY_VALUE);
        DifficultyController.getInstance().setDifficultyFromName(difficultySaved);
    }

    private void loadLastEvent() {
        EventController controller = EventController.getInstance();
        String eventType = JSONTools.extractStringFromJSONObject(save.getLastEvent(), JSONKeys.EVENT_EFFECT_TYPE_KEY);
        String eventDescription = JSONTools.extractStringFromJSONObject(save.getLastEvent(), JSONKeys.EVENT_DESCRIPTION_KEY);

        controller.setCurrentEvent(new Event(JSONTools.findJSONObjectInJSONArrayWithKeyValue(controller.getEventsByName(eventType), JSONKeys.EVENT_DESCRIPTION_KEY, eventDescription), eventType));
    }

    private void setSave(Save save) {
        this.save = save;
    }

    private void setJSONLoader(Save save, TurnController turnController)
    {
        this.jsonLoader = new JSONLoader(save, turnController);
    }
}
