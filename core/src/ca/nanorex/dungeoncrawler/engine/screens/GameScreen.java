package ca.nanorex.dungeoncrawler.engine.screens;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.Screen;
import ca.nanorex.dungeoncrawler.engine.world.World;
import ca.nanorex.dungeoncrawler.engine.world.objects.systems.ObjectMovementSystem;

public class GameScreen extends Screen {

    private World world;

    public GameScreen(Game game) {
        super(game);

        world = new World();
    }

    @Override
    public void frame(float lerp) {
        world.render(lerp);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public World getWorld() {
        return world;
    }
}
