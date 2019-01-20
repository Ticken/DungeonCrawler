package ca.nanorex.dungeoncrawler.game.world;

import com.badlogic.gdx.Screen;

import ca.nanorex.dungeoncrawler.game.world.map.Map;

public class GameWorld {

    Screen screen;
    Map map;

    /**
     * Default constructor for GameScreen
     *
     * @param screen The Screen object that uses this GameWorld
     */
    public GameWorld(Screen screen) {
        this.screen = screen;

        //JoelTest
        map = new Map();
    }
}
