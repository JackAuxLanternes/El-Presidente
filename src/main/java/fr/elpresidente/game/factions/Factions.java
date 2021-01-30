package fr.elpresidente.game.factions;

import java.util.ArrayList;
import java.util.List;

class Factions {

    private List<Faction> factionList = new ArrayList<Faction>();

    public List<Faction> getFactionList() {
        return factionList;
    }

    public void addFaction(Faction faction) {
        this.factionList.add(faction);
    }

}
