package fr.elpresidente.game.difficulty;

public class HardDifficulty implements Difficulty {
    @Override
    public double getDifficultyLooseMultiplier() {
        return 2;
    }

    @Override
    public int getPercentage() {
        return 50;
    }
}
