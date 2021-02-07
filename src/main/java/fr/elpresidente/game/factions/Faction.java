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
        this.normalizedSatisfactioMaxValue();
    }

    private boolean isSatisfactionSuperiorThan0() {
        return this.satisfaction > 0;
    }

    private boolean isSupportersSuperiorThan0() {
        return this.supporters > 0;
    }

    private boolean isSatisfactionGreaterThan100() {
        return this.satisfaction > 100;
    }

    public void substractSatisfaction(double satisfaction) {

        this.satisfaction -= satisfaction;
        this.normalizedSatisfactioMinValue();
    }

    public void addSupporter(int supporters) {
        if(isSupportersSuperiorThan0())
            this.supporters += supporters;
    }

    public void substractSupporter(int supporters) {

        this.supporters -= supporters;
        this.normalizedSupportersMinValue();
    }

    private void normalizedSupportersMinValue() {
        if(!isSupportersSuperiorThan0())
            this.supporters = 0;
    }

    private void normalizedSatisfactioMaxValue() {
        if(!isSatisfactionGreaterThan100())
            this.satisfaction = 100;
    }

    private void normalizedSatisfactioMinValue() {
        if(!isSatisfactionSuperiorThan0())
            this.satisfaction = 0;
    }
}
