package fr.elpresidente.game.difficulty;

public class HardDifficulty implements Difficulty {

    private final double LOOSE_MULTIPLIER = 2;
    private final int PERCENTAGE = 50;

    @Override
    public double getDifficultyLooseMultiplier() {
        return LOOSE_MULTIPLIER;
    }

    @Override
    public int getPercentage() {
        return PERCENTAGE;
    }
}
