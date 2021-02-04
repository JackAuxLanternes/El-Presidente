package fr.elpresidente.game.factions;

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

    public void initFactions(){
        this.factions.forEach(faction -> faction.setSatisfaction(50));
        this.factions.forEach(faction -> faction.setSupporters(4));
    }

    public void removeSupportersForFood(int number_supporter) {

        if(isTotalSupportSuperiorThanNumberSupporter(number_supporter)) {
            this.removeSupportersRandomly(number_supporter);
            this.substractSatisfactionAccordingToNumberSupporterRemove(number_supporter);
        }
        this.errorSupporterToRemoveSuperiorThanTotalSuporters(number_supporter);
    }

    private void errorSupporterToRemoveSuperiorThanTotalSuporters(int number_supporter) {
        throw new Error("you can't kill " + number_supporter + " supporters, you have only a total of" +
                this.determineTotalSupporters()  + " supporters ");
    }

    public void substractSatisfactionForOneFaction(String name_faction, double satisfaction_percentage) {

        Faction faction_research = this.factions
                .stream().filter(faction -> faction.getName().equals(name_faction)).findFirst().orElseThrow(NoSuchElementException::new);
        faction_research.addSatisfaction(faction_research.getSatisfaction() - satisfaction_percentage);
    }

    public void addSatisfactionForOneFaction(String name_faction, double satisfaction_percentage) {

        Faction faction_research = this.factions
                .stream().filter(faction -> faction.getName().equals(name_faction)).findFirst().orElseThrow(NoSuchElementException::new);
        faction_research.addSatisfaction(faction_research.getSatisfaction() + satisfaction_percentage);
    }

    public int getSupportersFromNameFaction(String name_faction) {

        Faction faction_research = this.factions
                .stream().filter(faction -> faction.getName().equals(name_faction)).findFirst().orElseThrow(NoSuchElementException::new);
        return faction_research.getSupporters();
    }

    public void addNewSupportersThanksToAgricultureSurplus() {

        this.addSupportersRandomly(this.determineNumberSupportersRandomly());
    }

    private boolean isTotalSupportSuperiorThanNumberSupporter(int number_supporter) {
        return this.determineTotalSupporters() > number_supporter;
    }

    public boolean isSatisfactionSuperiorThanThreshold(int thresholdDifficulty) {
        return this.determineGlobalSatisfaction() > thresholdDifficulty;
    }

    private double determineGlobalSatisfaction() {
        return this.determineSatisfactionMultipliedBySupporters() / this.determineTotalSupporters();
    }

    private double determineSatisfactionMultipliedBySupporters() {
        return this.factions
                .stream().mapToDouble(faction -> faction.getSatisfactionMultiplySupporter()).sum();
    }

    private int determineTotalSupporters() {
        return this.factions
                .stream().mapToInt(faction -> faction.getSupporters()).sum();
    }

    private void removeSupportersRandomly(int number_supporter) {
        for(int i = 0; i < number_supporter; i++) {
            this.removeSupporterRandomFaction();
        }
    }

    private void addSupportersRandomly(int number_supporter) {
        for(int i = 0; i < number_supporter; i++) {
            this.addSupporterRandomFaction();
        }
    }

    private void removeSupporterRandomFaction() {

        int random_index_faction = this.determineNumberBetweenThreshold(0, this.factions.size());
        this.factions.get(random_index_faction).setSupporters(this.factions.get(random_index_faction).getSupporters() - 1 );
    }

    private void addSupporterRandomFaction() {

        int random_index_faction = this.determineNumberBetweenThreshold(0, this.factions.size());
        this.factions.get(random_index_faction).setSupporters(this.factions.get(random_index_faction).getSupporters() + 1 );
    }

    private void substractSatisfactionAccordingToNumberSupporterRemove(int number_supporter) {
        int percentage_for_one_supporter = 2;

        this.factions
                .forEach(faction -> faction.setSatisfaction(faction.getSatisfaction() - (percentage_for_one_supporter * number_supporter) ));
    }

    private int determineNumberSupportersRandomly() {
        int limit_percentage = 10;
        return this.determineNumberBetweenThreshold(1, limit_percentage) * this.determineTotalSupporters();
    }

    private int determineNumberBetweenThreshold(int min, int max) {
        return (int) (Math.random()*((max-min)+1))+min;
    }
}
