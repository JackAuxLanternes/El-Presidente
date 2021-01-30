package fr.elpresidente.game.factions;

public interface Faction {

    public int getSupporters();

    public void setSupporters(int supporters);

    public double getSatisfaction();

    public void setSatisfaction(double satisfaction);

    public void addSatisfaction(double satisfaction);
}
