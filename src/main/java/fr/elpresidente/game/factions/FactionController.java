package fr.elpresidente.game.factions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FactionController {

    private static FactionController instance;

    private final List<Faction> factions;

    private FactionController() {
        factions = new ArrayList<Faction>();
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
        if (isInstanceNotInitialized())
        {
            instance = new FactionController();
        }

        return instance;
    }

    private static Boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public static void resetInstance() {
        if(instance != null){
            instance = null;
        }
    }

    public void initFactions(int supporters, double satisfaction){
        this.factions.forEach(faction -> faction.setSatisfaction(satisfaction));
        this.factions.forEach(faction -> faction.setSupporters(supporters));
    }


    public void addSatisfactionPercentageForOneFaction(String name_faction, double satisfaction_percentage) {

        Faction faction_research = this.getFactionFromNameFaction(name_faction);
        faction_research.addSatisfaction(faction_research.getSatisfaction() * satisfaction_percentage / 100);
    }

    public Faction getFactionFromNameFaction(String name_faction) {
        return this.factions
                .stream().filter(faction -> faction.getName().equals(name_faction)).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("the faction "+ name_faction +" doesn\'t exist"));
    }

    private boolean isTotalSupportSuperiorThanNumberSupporter(int number_supporter) {
        return this.determineTotalSupporters() > number_supporter;
    }

    public double determineGlobalSatisfaction() {
        return this.determineSatisfactionMultipliedBySupporters() / this.determineTotalSupporters();
    }

    private double determineSatisfactionMultipliedBySupporters() {
        return this.factions
                .stream().mapToDouble(faction -> faction.getSatisfactionMultiplySupporter()).sum();
    }

    public int determineTotalSupporters() {
        return this.factions
                .stream().mapToInt(faction -> faction.getSupporters()).sum();
    }

    public void removeSupportersRandomly(int number_supporter) {
        for(int i = 0; i < number_supporter; i++) {
            this.removeSupporterRandomFaction();
        }
    }

    public void addSupportersRandomly(int number_supporter) {
        for(int i = 0; i < number_supporter; i++) {
            this.addSupporterRandomFaction();
        }
    }

    private void removeSupporterRandomFaction() {

        int random_index_faction;
        do {
            random_index_faction = this.determineNumberBetweenThreshold(0, this.factions.size() - 1);
        }while(this.factions.get(random_index_faction).getSupporters() <= 0);
        this.factions.get(random_index_faction).substractSupporter(1);
    }

    private void addSupporterRandomFaction() {

        /*TODO vérifier que j'ai au moins une faction avec Supporters > 0*/
        int random_index_faction;
        do {
            random_index_faction = this.determineNumberBetweenThreshold(0, this.factions.size() - 1);
        }while(this.factions.get(random_index_faction).getSupporters() <= 0);
        this.factions.get(random_index_faction).addSupporter(1);
    }

    public void substractSatisfactionAccordingToNumberSupporters(int number_supporter) {
        int percentage_for_one_supporter = 2;

        this.factions
                .forEach(faction -> faction.substractSatisfaction( percentage_for_one_supporter * number_supporter ));
    }

    public int determineNumberBetweenThreshold(int min, int max) {
        return (int) (Math.random()*((max-min)+1))+min;
    }

    public JSONArray toJSONArray() {

        JSONArray factionArray = new JSONArray();
        for(Faction faction : FactionController.getInstance().getFactions()) {
            JSONObject factionJSONObject = new JSONObject();
            factionJSONObject.put("name", faction.getName());
            factionJSONObject.put("popularity", (int) faction.getSatisfaction());
            factionJSONObject.put("supporters", faction.getSupporters());
            factionArray.add(factionJSONObject);
        }

        return factionArray;
    }

    public List<Faction> getFactions() {
        return factions;
    }
}
