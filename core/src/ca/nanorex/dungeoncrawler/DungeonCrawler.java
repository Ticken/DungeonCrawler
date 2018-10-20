package ca.nanorex.dungeoncrawler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ca.nanorex.dungeoncrawler.game.GameScreen;
import ca.nanorex.dungeoncrawler.input.InputManager;

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

        // Sets the starting screen
        changeScreen(0);
    }

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
	}

	public void changeScreen (int screenNumber){
	    switch (screenNumber){
            case 0:
                // GameScreen
                setScreen(screenGame);

                break;
            // Add more cases as screens get added
            default:
                System.out.println("ERROR: Attempt to switch screens failed: " +
                        "Unknown screenNumber "+screenNumber);
                break;
        }
    }
}
