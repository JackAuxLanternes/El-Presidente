package fr.elpresidente.game.difficulty;

public class HardDifficulty implements Difficulty {

    private final double EVENT_MULTIPLIER = 2;
    private final int PERCENTAGE_LOOSE = 50;

    @Override
    public double getDifficultyEventMultiplier() {
        return EVENT_MULTIPLIER;
    }

    @Override
    public int getPercentageLoose() {
        return PERCENTAGE_LOOSE;
    }
}
