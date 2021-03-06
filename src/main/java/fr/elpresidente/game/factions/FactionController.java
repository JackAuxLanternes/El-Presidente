package fr.elpresidente.game.factions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FactionController {

    private static FactionController instance;

    private final List<Faction> factions;

    private FactionController() {
        factions = new ArrayList<>();
        factions.add(new Faction("capitalist"));
        factions.add(new Faction("communist"));
        factions.add(new Faction("ecologist"));
        factions.add(new Faction("liberal"));
        factions.add(new Faction("loyalist"));
        factions.add(new Faction("militarist"));
        factions.add(new Faction("nationalist"));
        factions.add(new Faction("religious"));
    }

    public static FactionController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new FactionController();
        }

        return instance;
    }

    private static Boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public static void resetInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public void initFactions(int supporters, double satisfaction) {
        this.factions.forEach(faction -> faction.setSatisfaction(satisfaction));
        this.factions.forEach(faction -> faction.setSupporters(supporters));
    }


    public Faction getFactionFromNameFaction(String name_faction) {
        return this.factions
                .stream().filter(faction -> faction.getName().equals(name_faction)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("the faction " + name_faction + " doesn't exist"));
    }

    public double determineGlobalSatisfaction() {
        return this.determineSatisfactionMultipliedBySupporters() / this.determineTotalSupporters();
    }

    private double determineSatisfactionMultipliedBySupporters() {
        return this.factions
                .stream().mapToDouble(Faction::getSatisfactionMultiplySupporter).sum();
    }

    public int determineTotalSupporters() {
        return this.factions
                .stream().mapToInt(Faction::getSupporters).sum();
    }

    public void removeSupportersRandomly(int number_supporter) {
        for (int i = 0; i < number_supporter; i++) {
            this.removeSupporterRandomFaction();
        }
    }


    private void removeSupporterRandomFaction() {

        int random_index_faction;
        do {
            random_index_faction = this.determineNumberBetweenThreshold(0, this.factions.size() - 1);
        } while (this.factions.get(random_index_faction).getSupporters() <= 0);
        this.factions.get(random_index_faction).removeSupporter(1);
    }

    public void subtractSatisfactionAccordingToNumberSupporters(int number_supporter) {
        int percentage_for_one_supporter = 2;

        this.factions
                .forEach(faction -> faction.removeSatisfaction(percentage_for_one_supporter * number_supporter));
    }

    public int getRandomFactionIndex() {
        return this.determineNumberBetweenThreshold(0, this.factions.size() - 1);
    }

    public int determineNumberBetweenThreshold(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public JSONArray toJSONArray() {

        JSONArray factionArray = new JSONArray();
        for (Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject factionJSONObject = faction.toJSONObject();
            factionArray.add(factionJSONObject);
        }

        return factionArray;
    }

    public List<Faction> getFactions() {
        return factions;
    }
}
