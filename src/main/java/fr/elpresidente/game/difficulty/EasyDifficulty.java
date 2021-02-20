package fr.elpresidente.game.difficulty;

public class EasyDifficulty implements Difficulty {

    private final double EVENT_MULTIPLIER = 0.5;
    private final int PERCENTAGE_LOOSE = 10;

    @Override
    public double getDifficultyEventMultiplier() {
        return EVENT_MULTIPLIER;
    }

    @Override
    public int getPercentageLoose() {
        return PERCENTAGE_LOOSE;
    }
}
