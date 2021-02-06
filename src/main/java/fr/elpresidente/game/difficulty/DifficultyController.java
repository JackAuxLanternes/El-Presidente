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
}
