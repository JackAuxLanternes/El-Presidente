package fr.elpresidente.game.difficulty;

public class DifficultyController {

    private static DifficultyController instance;

    private Difficulty difficulty;

    public static DifficultyController getInstance() {
        if (instance == null) {
            instance = new DifficultyController();
        }

        return instance;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public double getDifficultyLooseMultiplier() throws Exception {
        switch (difficulty)
        {
            case EASY:
                return 0.5;

            case NORMAL:
                return 1;

            case HARD:
                return 2;
        }

        throw new Exception("You can't trigger this method because you didn't initialize difficulty");
    }

    public int getPercentage() throws Exception
    {
        switch (difficulty)
        {
            case EASY:
                return 10;

            case NORMAL:
                return 25;

            case HARD:
                return 50;
        }

        throw new Exception("You can't trigger this method because you didn't initialize difficulty");
    }
}
