package fr.elpresidente.game.factions;

import fr.elpresidente.game.resources.Treasury;

public class FactionController {

    private static FactionController instance;

    private final Factions factions;

    private FactionController() {
        factions = new Factions();
        factions.addFaction(new Faction("capitalist"));
        factions.addFaction(new Faction("communist"));
        factions.addFaction(new Faction("ecologist"));
        factions.addFaction(new Faction("liberal"));
        factions.addFaction(new Faction("loyalist"));
        factions.addFaction(new Faction("militarist"));
        factions.addFaction(new Faction("nationalist"));
        factions.addFaction(new Faction("religious"));
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
        return this.factions.getFactionList()
                .stream().mapToDouble(faction -> faction.getSupporters() * faction.getSatisfaction()).sum();

    }

    public int determineTotalSupporters() {
        return this.factions.getFactionList()
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

        int random_index_faction = (int) Math.random() * this.factions.getFactionList().size();
        this.factions.getFactionList().get(random_index_faction).setSupporters(this.factions.getFactionList().get(random_index_faction).getSupporters() -1);
    }

    private void addSupporterRandomFaction() {

        int random_index_faction = (int) Math.random() * this.factions.getFactionList().size();
        this.factions.getFactionList().get(random_index_faction).setSupporters(this.factions.getFactionList().get(random_index_faction).getSupporters() +1);
    }

    private void substractSatisfactionAccordingToNumberSupporter(int number_supporter) {
        int percentage_for_one_supporter = 2;

        this.factions.getFactionList()
                .forEach(faction -> faction.setSatisfaction(faction.getSatisfaction() - (percentage_for_one_supporter * number_supporter) ));
    }

    private int determineNumberSupportersRandomly() {
        return this.determinePercentageSupportersToAdd() * this.determineTotalSupporters();
    }

    private int determinePercentageSupportersToAdd() {
         return (int) Math.random() * 10 + 1;
    }
}
