package fr.elpresidente.game.JSON.save;

import fr.elpresidente.game.JSON.JSONLoader;
import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.events.Event;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.mode.GameModeController;
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

    private void loadLastEvent() {
        EventController controller = EventController.getInstance();
        String eventType = JSONTools.extractStringFromJSONObject(save.getLastEvent(), "type");
        String eventDescription = JSONTools.extractStringFromJSONObject(save.getLastEvent(), "description");

        controller.setCurrentEvent(new Event(JSONTools.findJSONObjectInJSONArrayWithKeyValue(controller.getEventsByName(eventType), "description", eventDescription), eventType));
    }

    private void setSave(Save save) {
        this.save = save;
    }

    private void setJSONLoader(Save save, TurnController turnController)
    {
        this.jsonLoader = new JSONLoader(save, turnController);
    }
}
