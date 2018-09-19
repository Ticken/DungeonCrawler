package ca.nanorex.dungeoncrawler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import ca.nanorex.dungeoncrawler.game.GameScreen;

public class DungeonCrawler extends Game {

    // Screens
    GameScreen screenGame;

    // Other Things
    AssetLibrary assetLibrary;
    SoundEngine soundEngine;
    Settings settings;

	@Override
	public void create () {
        // Create Screens
        screenGame = new GameScreen(this);

        // Create Other Things
        assetLibrary = new AssetLibrary(this);
        soundEngine = new SoundEngine(this);
        settings = new Settings(this);
	}

	@Override
	public void render () {
	    super.render();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void dispose () {
	}
}
