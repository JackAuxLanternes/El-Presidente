package fr.elpresidente.game.factions;

import fr.elpresidente.game.ressources.ResourcesController;

import java.util.HashMap;

public class FactionController {

    private static FactionController instance;

    private HashMap<FactionEnumeration, Faction> factionHashMap;

    private FactionController() {
        initFactionHashMap();
    }

    public static FactionController getInstance() {
        if (instance == null)
        {
            instance = new FactionController();
        }

        return instance;
    }

    private void initFactionHashMap() {
        factionHashMap = new HashMap<>();
        factionHashMap.put(FactionEnumeration.CAPITALISTS, new Faction("Capitalists"));
        factionHashMap.put(FactionEnumeration.COMMUNISTS, new Faction("Communists"));
        factionHashMap.put(FactionEnumeration.ECOLOGISTS, new Faction("Ecologists"));
        factionHashMap.put(FactionEnumeration.LIBERALS, new Faction("Liberals"));
        factionHashMap.put(FactionEnumeration.LOYALISTS, new Faction("Loyalists"));
        factionHashMap.put(FactionEnumeration.MILITARISTS, new Faction("Militarists"));
        factionHashMap.put(FactionEnumeration.NATIONALISTS, new Faction("Nationalists"));
        factionHashMap.put(FactionEnumeration.RELIGIOUS, new Faction("Religious"));
    }

    public HashMap<FactionEnumeration, Faction> getFactionHashMap() {
        return factionHashMap;
    }
}
