package fr.elpresidente.game.factions;

import fr.elpresidente.game.difficulty.DifficultyController;
import org.json.simple.JSONObject;

public class Faction {

    private final String name;
    private int supporters;
    private double satisfaction;
    private static final String JSON_NAME_KEY = "name";
    private final String JSON_SUPPORTERS_KEY = "supporters";
    private final String JSON_POPULARITY_KEY = "popularity";

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

    public void updateSupporters(double supporters) {
        if (isSupportersSuperiorThan0())
            this.supporters += supporters;
        this.normalizedSupportersMinValue();
    }

    public void updateSatisfaction(double satisfaction) {

        if (isSatisfactionSuperiorThan0())
            this.satisfaction += satisfaction;
        this.normalizedSatisfactionMaxValue();
        this.normalizedSatisfactionMinValue();
    }

    public void addSatisaction(double satisfaction) {
        if (isSatisfactionSuperiorThan0())
            this.satisfaction += satisfaction;
        System.out.println("la satisfaction est de 2 -> " + this.satisfaction);
        this.normalizedSatisfactionMaxValue();
    }

    private boolean isSatisfactionSuperiorThan0() {
        return this.satisfaction > 0;
    }

    private boolean isSupportersSuperiorThan0() {
        return this.supporters > 0;
    }

    private boolean isSatisfactionSuperiorThan100() {
        return this.satisfaction > 100;
    }

    public void removeSatisfaction(double satisfaction) {

        this.satisfaction -= this.updateValueToSubstractWithDiffciulty(satisfaction);
        this.normalizedSatisfactionMinValue();
    }

    private int updateValueToSubstractWithDiffciulty(double value) {
        return (int) (value * DifficultyController.getInstance().getDifficulty().getDifficultyEventMultiplier());
    }

    public void addSupporter(int supporters) {
        if (isSupportersSuperiorThan0())
            this.supporters += supporters;
    }

    public void removeSupporter(int supporters) {

        this.supporters -= supporters;
        this.normalizedSupportersMinValue();
    }

    private void normalizedSupportersMinValue() {
        if (!isSupportersSuperiorThan0())
            this.supporters = 0;
    }

    private void normalizedSatisfactionMaxValue() {
        if (isSatisfactionSuperiorThan100())
            this.satisfaction = 100;
    }

    private void normalizedSatisfactionMinValue() {
        if (!isSatisfactionSuperiorThan0())
            this.satisfaction = 0;
    }

    public JSONObject toJSONObject() {
        JSONObject factionJSONObject = new JSONObject();
        factionJSONObject.put(JSON_NAME_KEY, this.getName());
        factionJSONObject.put(JSON_POPULARITY_KEY, (int) this.getSatisfaction());
        factionJSONObject.put(JSON_SUPPORTERS_KEY, this.getSupporters());

        return factionJSONObject;
    }
}
