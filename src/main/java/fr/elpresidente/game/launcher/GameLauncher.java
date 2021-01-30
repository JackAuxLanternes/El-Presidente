package fr.elpresidente.game.launcher;

import fr.elpresidente.game.builders.GameBuilder;
import fr.elpresidente.game.status.Game;

public class GameLauncher implements GameBuilder {

    @Override
    public void init() {
        //ToDo replace blabla with actual code
        System.out.println("Initializing el-presidente");
    }

    @Override
    public void createNewGame() {
        Game game = new Game();
        game.initGame();
        game.gameLoop();
    }

    @Override
    public void loadSavedGame() {

    }
}
