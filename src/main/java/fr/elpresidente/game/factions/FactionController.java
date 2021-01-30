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

        return this.capitalist.getSatisfaction() * this.capitalist.getSupporters() +
                this.communist.getSatisfaction() * this.communist.getSupporters() +
                this.ecologist.getSatisfaction() * this.ecologist.getSupporters() +
                this.liberal.getSatisfaction() * this.liberal.getSupporters() +
                this.loyalist.getSatisfaction() * this.loyalist.getSupporters() +
                this.militarist.getSatisfaction() * this.militarist.getSupporters() +
                this.nationalist.getSatisfaction() * this.nationalist.getSupporters() +
                this.religious.getSatisfaction() * this.religious.getSupporters();
    }

    public double determineTotalSupporters() {

        return this.capitalist.getSupporters() +
                this.communist.getSupporters() +
                this.ecologist.getSupporters() +
                this.liberal.getSupporters() +
                this.loyalist.getSupporters() +
                this.militarist.getSupporters() +
                this.nationalist.getSupporters() +
                this.religious.getSupporters();
    }



}
