package fr.elpresidente.game.difficulty;

public class NormalDifficulty implements Difficulty {

    private final double EVENT_MULTIPLIER = 1;
    private final int PERCENTAGE_LOOSE = 1;

    @Override
    public double getDifficultyEventMultiplier() {
        return EVENT_MULTIPLIER;
    }

    @Override
    public int getPercentageLoose() {
        return PERCENTAGE_LOOSE;
    }
}
