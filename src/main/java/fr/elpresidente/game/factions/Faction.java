package fr.elpresidente.game.factions;

import fr.elpresidente.game.difficulty.DifficultyController;
import fr.elpresidente.game.tools.JSONKeys;
import org.json.simple.JSONObject;

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

    public void addSatisfaction(double satisfaction) {
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

        this.satisfaction -= this.updateValueToSubtractWithDifficulty(satisfaction);
        this.normalizedSatisfactionMinValue();
    }

    private int updateValueToSubtractWithDifficulty(double value) {
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
        factionJSONObject.put(JSONKeys.FACTION_KEY_NAME, this.getName());
        factionJSONObject.put(JSONKeys.FACTION_POPULARITY_KEY, (int) this.getSatisfaction());
        factionJSONObject.put(JSONKeys.FACTION_SUPPORTERS_KEY, this.getSupporters());

        return factionJSONObject;
    }
}
