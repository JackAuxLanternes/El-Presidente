package fr.elpresidente.game.difficulty;

public class EasyDifficulty implements Difficulty {

    private final double LOOSE_MULTIPLIER = 0.5;
    private final int PERCENTAGE = 10;

    @Override
    public double getDifficultyLooseMultiplier() {
        return LOOSE_MULTIPLIER;
    }

    @Override
    public int getPercentage() {
        return PERCENTAGE;
    }
}
