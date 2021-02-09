package fr.elpresidente.game.difficulty;

public class EasyDifficulty implements Difficulty {
    @Override
    public double getDifficultyLooseMultiplier() {
        return 0.5;
    }

    @Override
    public int getPercentage() {
        return 10;
    }
}
