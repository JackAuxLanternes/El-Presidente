package fr.elpresidente.game.factions;

import java.util.HashMap;

public class FactionController {

    private static FactionController instance;

    private final Faction capitalist;
    private final Faction communist;
    private final Faction ecologist;
    private final Faction liberal;
    private final Faction loyalist;
    private final Faction militarist;
    private final Faction nationalist;
    private final Faction religious;

    private FactionController() {
        capitalist = new Capitalist();
        communist = new Communist();
        ecologist = new Ecologist();
        liberal = new Liberal();
        loyalist = new Loyalist();
        militarist = new Militarist();
        nationalist = new Nationalist();
        religious = new Religious();
    }

    public static FactionController getInstance() {
        if (isIntanceNotInitialized())
        {
            instance = new FactionController();
        }

        return instance;
    }

    private static Boolean isIntanceNotInitialized() {
        if (instance == null) {
            return true;
        }

        return false;
    }

}
