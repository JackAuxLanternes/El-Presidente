package fr.elpresidente.game.factions;

public class Faction {

    private final String name;
    private int supporters;
    private double satisfaction;

    public Faction(String name) {
        this.name = name;

    }

    public double getSatisfactionMultiplySupporter() {
        return this.getSupporters() * this.getSatisfaction();
    }

    public String getName() {
        return name;
    }

    public int getSupporters() {
        return supporters;
    }

    public void setSupporters(int supporters) {
        this.supporters = supporters;
    }

    public double getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(double satisfaction) {
        this.satisfaction = satisfaction;
    }

    public void addSatisfaction(double satisfaction) {

        if(isSatisfactionSuperiorThan0())
            this.satisfaction += satisfaction;
    }

    private boolean isSatisfactionSuperiorThan0() {
        return this.satisfaction > 0;
    }

    public void substractSatisfaction(double satisfaction) {

        if(isSatisfactionSuperiorThan0())
            this.satisfaction -= satisfaction;
    }

    public void addSupporter(int supporters) {
        this.supporters += supporters;
    }

    public void substractSupporter(int supporters) {
        this.supporters -= supporters;
    }
}
