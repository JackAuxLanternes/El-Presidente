package fr.elpresidente.game.factions;

import fr.elpresidente.game.resources.Treasury;

import java.util.ArrayList;
import java.util.List;

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
        if (isIntanceNotInitialized())
        {
            instance = new FactionController();
        }

        return instance;
    }

    private static Boolean isIntanceNotInitialized() {
        return instance == null;
    }


    public void removeSupportersForFood(int number_supporter) {

        if(isTotalSupportSuperiorThanNumberSupporter(number_supporter)) {
            this.removeSupportersRandomly(number_supporter);
            this.substractSatisfactionAccordingToNumberSupporter(number_supporter);
        }
        this.errorSupporterToRemoveSuperiorThanTotalSuporters(number_supporter);
    }

    private void errorSupporterToRemoveSuperiorThanTotalSuporters(int number_supporter) {
        throw new Error("you can't kill " + number_supporter + " supporters, you have only a total of" +
                this.determineTotalSupporters()  + " supporters ");
    }

    public void addNewSupportersThanksToAgricultureSurplus() {

        this.addSupportersRandomly(this.determineNumberSupportersRandomly());
    }


    public boolean isTotalSupportSuperiorThanNumberSupporter(int number_supporter) {
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

    public int determineTotalSupporters() {
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
            this.removeSupporterRandomFaction();
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

    private void substractSatisfactionAccordingToNumberSupporter(int number_supporter) {
        int percentage_for_one_supporter = 2;

        this.factions
                .forEach(faction -> faction.setSatisfaction(faction.getSatisfaction() - (percentage_for_one_supporter * number_supporter) ));
    }

    private int determineNumberSupportersRandomly() {
        return this.determineNumberBetweenThreshold(1, 10) * this.determineTotalSupporters();
    }

    private int determineNumberBetweenThreshold(int min, int max) {
         return (int) (Math.random()*((max-min)+1))+min;
    }
}