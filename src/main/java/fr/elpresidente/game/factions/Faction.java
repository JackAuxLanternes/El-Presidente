package fr.elpresidente.game.factions;

import fr.elpresidente.game.difficulty.DifficultyController;
import org.json.simple.JSONObject;

public class Faction {

    private final String JSON_NAME_KEY = "name";
    private final String name;
    private final String JSON_SUPPORTERS_KEY = "supporters";
    private final String JSON_POPULARITY_KEY = "popularity";
    private final double MAXIMUM_SATISFACTION = 100.0;
    private final double MINIMUM_SATISFACTION = 0.0;
    private final int MINIMUM_SUPPORTER = 0;

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

    public void updateSupporters(double supporters) {
        if (isSupportersSuperiorThanMinimum())
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
        this.normalizedSatisfactionMaxValue();
    }

    private boolean isSatisfactionSuperiorThan0() {
        return this.satisfaction > MINIMUM_SATISFACTION;
    }

    private boolean isSupportersSuperiorThanMinimum() {
        return this.supporters > MINIMUM_SUPPORTER;
    }

    private boolean isSatisfactionSuperiorThanMaximum() {
        return this.satisfaction > MAXIMUM_SATISFACTION;
    }

    public void removeSatisfaction(double satisfaction) {

        this.satisfaction -= this.updateValueToSubstractWithDiffciulty(satisfaction);
        this.normalizedSatisfactionMinValue();
    }

    private int updateValueToSubstractWithDiffciulty(double value) {
        return (int) (value * DifficultyController.getInstance().getDifficulty().getDifficultyEventMultiplier());
    }

    public void addSupporter(int supporters) {
        if (isSupportersSuperiorThanMinimum())
            this.supporters += supporters;
    }

    public void removeSupporter(int supporters) {

        this.supporters -= supporters;
        this.normalizedSupportersMinValue();
    }

    private void normalizedSupportersMinValue() {
        if (!isSupportersSuperiorThanMinimum())
            this.supporters = MINIMUM_SUPPORTER;
    }

    private void normalizedSatisfactionMaxValue() {
        if (isSatisfactionSuperiorThanMaximum())
            this.satisfaction = MAXIMUM_SATISFACTION;
    }

    private void normalizedSatisfactionMinValue() {
        if (!isSatisfactionSuperiorThan0())
            this.satisfaction = MINIMUM_SATISFACTION;
    }

    public JSONObject toJSONObject() {
        JSONObject factionJSONObject = new JSONObject();
        factionJSONObject.put(JSON_NAME_KEY, this.getName());
        factionJSONObject.put(JSON_POPULARITY_KEY, (int) this.getSatisfaction());
        factionJSONObject.put(JSON_SUPPORTERS_KEY, this.getSupporters());

        return factionJSONObject;
    }

    public double getMAXIMUM_SATISFACTION() {
        return MAXIMUM_SATISFACTION;
    }

    public double getMINIMUM_SATISFACTION() {
        return MINIMUM_SATISFACTION;
    }
}
