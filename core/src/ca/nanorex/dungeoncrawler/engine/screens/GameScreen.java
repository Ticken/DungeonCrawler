package ca.nanorex.dungeoncrawler.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.Screen;
import ca.nanorex.dungeoncrawler.engine.input.TmpKeyboardInput;
import ca.nanorex.dungeoncrawler.engine.render.renderers.WorldRenderer;
import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteModel;
import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteTexture;
import ca.nanorex.dungeoncrawler.engine.world.World;

public class GameScreen extends Screen {

    private World world;

    private SpriteBatch batch = new SpriteBatch();
    private WorldRenderer worldRenderer = new WorldRenderer();

    public GameScreen(Game game) {
        super(game);

        Game.getAssetLibrary().load("basic_humanoid", SpriteModel.class);
        Game.getAssetLibrary().load("objects/player", SpriteTexture.class);
        Game.getAssetLibrary().load("objects/basic_sword", SpriteTexture.class);

        Game.getAssetLibrary().load("tmp/brick", Texture.class);

        Game.getAssetLibrary().finishLoading();

        world = new World();

        inputProcessors.add(new TmpKeyboardInput(world.getPlayer()));
    }

    @Override
    public void frame(float lerp) {

        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        worldRenderer.render(world, batch, lerp);
        batch.end();
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
