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
        this.satisfaction += satisfaction;
    }

    public void substractSatisfaction(double satisfaction) {
        this.satisfaction -= satisfaction;
    }

    public void addSupporter(int supporters) {
        this.supporters += supporters;
    }

    public void substractSupporter(int supporters) {
        this.supporters -= supporters;
    }
}
