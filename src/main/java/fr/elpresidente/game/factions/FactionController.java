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
        if (instance == null) {
            return true;
        }

        return false;
    }

    public void applyEventToFactions() {

    }

    public boolean isSatisfactionSuperiorThanThreshold(int thresholdDifficulty) {
        if (this.determineGlobalSatisfaction() > thresholdDifficulty) {
            return true;
        }
        return false;
    }

    private double determineGlobalSatisfaction() {

        return this.determineSatisfactionMultipliedBySupporters() / this.determineTotalPartisans();
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

    private double determineTotalPartisans() {

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
