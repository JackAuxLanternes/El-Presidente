package fr.elpresidente.game.difficulty;

public class NormalDifficulty implements Difficulty {
    @Override
    public double getDifficultyLooseMultiplier() {
        return 1;
    }

    @Override
    public int getPercentage() {
        return 25;
    }
}
