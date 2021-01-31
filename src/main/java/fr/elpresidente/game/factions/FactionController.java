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

    public void removeSupporters() {
        // déclenché quand on a moins de 4 amount de Food par personne
        // On supprime des partisans aléatoirement dans nimporte quelle faction
        // On retire les 2% de satisfaction pour toutes les factions pour chaque partisan remove
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
}
