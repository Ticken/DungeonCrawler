package ca.nanorex.dungeoncrawler.game;

import com.badlogic.gdx.Screen;

import ca.nanorex.dungeoncrawler.DungeonCrawler;
import ca.nanorex.dungeoncrawler.game.world.GameWorld;

public class GameScreen implements Screen {

    DungeonCrawler dungeonCrawler;

    // Game Things
    GameCamera gameCamera;
    GameWorld gameWorld;

    public GameScreen(DungeonCrawler _dungeonCrawler){
        dungeonCrawler = _dungeonCrawler;

        // Create Game Things
        gameCamera = new GameCamera();
        gameWorld = new GameWorld(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
