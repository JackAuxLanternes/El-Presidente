package fr.elpresidente.game.difficulty;

public class NormalDifficulty implements Difficulty {

    private final double LOOSE_MULTIPLIER = 1;
    private final int PERCENTAGE = 1;

    @Override
    public double getDifficultyLooseMultiplier() {
        return LOOSE_MULTIPLIER;
    }

    @Override
    public int getPercentage() {
        return PERCENTAGE;
    }
}
