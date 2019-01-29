package ca.nanorex.dungeoncrawler.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.engine.assets.AssetLibrary;

public abstract class Game implements ApplicationListener {

    public static final int TICK_RATE = 30;
    public static final float TICK_PERIOD = 1f / TICK_RATE;
    public static final int MAX_FPS = 120; //Maybe change this to max framerate of device?
    public static final float MAX_FPS_PERIOD = 1f / MAX_FPS;

    private float tickAccumulator, frameAccumulator;
    private float tickRate, frameRate;

    protected Map<String, Screen> screens;
    private Screen currentScreen;

    private AssetLibrary assetLibrary;

    public Game() {
        screens = new HashMap<String, Screen>();
        assetLibrary =  new AssetLibrary();
    }

    @Override
    public void create() {
        //perhaps this should not be defined here?
    }

    @Override
    public void resize(int width, int height) {

    }

    /**
     * The loop function of the game. Divides the game loop into a tick call and a render call,
     * where the tick is called at the set tick rate, or as fast as possible if that rate cannot
     * be reached, and where the render is called as fast as possible up to the set maximum frame
     * rate. Also updates the tick and frame rate member variables.
     *
     * @author David Neuman
     */
    @Override
    public void render() {

        float delta = Gdx.graphics.getDeltaTime();

        tickAccumulator += delta;
        frameAccumulator += delta;

        //Check if a tick's worth of time has passed since last tick
        if (tickAccumulator > TICK_PERIOD) {

            //Execute tick
            if (currentScreen != null)
                currentScreen.tick();

            //Get remainder of time
            tickAccumulator -= TICK_PERIOD;

            //If two ticks worth of time or more has passed since last tick,
            //prevent accumulator from winding up
            if (tickAccumulator > TICK_PERIOD) {
                tickRate = 1 / tickAccumulator;
                tickAccumulator = TICK_PERIOD;
            } else {
                tickRate = TICK_RATE;
            }
        }

        //Check if one frame (at max framerate) worth of time has passed
        if (frameAccumulator > MAX_FPS_PERIOD) {

            //Execute frame
            if (currentScreen != null)
                currentScreen.frame(tickAccumulator * TICK_RATE);

            frameRate = 1 / frameAccumulator;

            frameAccumulator %= MAX_FPS_PERIOD;
        }
    }

    @Override
    public void pause() {
        if (currentScreen != null)
            currentScreen.pause();
    }

    @Override
    public void resume() {

        assetLibrary.finishLoading();

        if (currentScreen != null)
            currentScreen.resume();
    }

    @Override
    public void dispose () {
        if (currentScreen != null)
            currentScreen.hide();
    }

    /**
     * Retrieves the current tick rate of the game
     *
     * @author David Neuman
     * @return The current tick rate of the game
     */
    public float getTickRate() {
        return tickRate;
    }

    /**
     * Retrieves the current frame rate of the game
     *
     * @author David Neuman
     * @return The current frame rate of the game
     */
    public float getFrameRate() {
        return frameRate;
    }

    public void setScreen(String screenName) {

        if (currentScreen != null)
            currentScreen.hide();

        currentScreen = screens.get(screenName);

        if (currentScreen != null)
            currentScreen.show();
    }


    public static AssetLibrary getAssetLibrary() {
        return ((Game)Gdx.app.getApplicationListener()).assetLibrary;
    }
}
