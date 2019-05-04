package ca.nanorex.dungeoncrawler;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.screens.GameScreen;

public class DungeonCrawler extends Game {

    public DungeonCrawler() {
        super();
    }

    @Override
    public void create() {
        super.create();

        screens.put("game", new GameScreen(this));
        setScreen("game");
    }
}
