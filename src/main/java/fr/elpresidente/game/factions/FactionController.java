package fr.elpresidente.game.factions;

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

    public void applyEventToFactions() {

    }

    public void removeSupportersForFood(int number_supporter) {
        //On teste que le nombre de supporter est > à number_supporter
        // déclenché quand on a moins de 4 amount de Food par personne
        // On supprime des partisans aléatoirement dans nimporte quelle faction
        // On retire les 2% de satisfaction pour toutes les factions pour chaque partisan remove
        if(isTotalSupportSuperiorThanNumberSupporter(number_supporter)) {
            this.removeSupportersRandomly(number_supporter);
            this.substractSatisfactionAccordingToNumberSupporter(number_supporter);
        }
        //gestion d'erreur ici à ajouter
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

    public double determineTotalSupporters() {
        return this.factions.getFactionList()
                .stream().mapToInt(faction -> faction.getSupporters()).sum();
    }

    private void removeSupportersRandomly(int number_supporter) {
        for(int i = 0; i < number_supporter; i++) {
            this.removeSupporter();
        }
    }

    private void removeSupporter() {

        int random_index_faction = (int) Math.random() * this.factions.getFactionList().size();
        this.factions.getFactionList().get(random_index_faction).setSupporters(this.factions.getFactionList().get(random_index_faction).getSupporters() -1);
    }

    private void substractSatisfactionAccordingToNumberSupporter(int number_supporter) {
        int percentage_for_one_supporter = 2;

        this.factions.getFactionList()
                .forEach(faction -> faction.setSatisfaction(faction.getSatisfaction() - (percentage_for_one_supporter * number_supporter) ));
    }
}
