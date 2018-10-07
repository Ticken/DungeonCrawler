package ca.nanorex.dungeoncrawler.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import java.security.Key;

import ca.nanorex.dungeoncrawler.game.utils.RectCollision;
import ca.nanorex.dungeoncrawler.game.utils.TestRectangle;
import ca.nanorex.dungeoncrawler.game.world.GameWorld;

/**
 * The main game screen class. Active when the gameplay is live
 */
public class GameScreen implements Screen {

    Game game;

    // Collision Test stuff
    TestRectangle test1 = new TestRectangle("squareGreen.png"), test2 = new TestRectangle("squareBlue.png");

    // Game Things
    GameCamera gameCamera;
    GameWorld gameWorld;
    RectCollision rectCollision;

    private static final int TICK_RATE = 30;
    private static final float TICK_PERIOD = 1 / TICK_RATE;
    private static final int MAX_FPS = 120;
    private static final float MAX_FPS_PERIOD = 1 / MAX_FPS;

    private float tickAccumulator, frameAccumulator;
    private float tickRate, frameRate;

    public GameScreen(Game game) {
        this.game = game;

        // Create Game Things
        gameCamera = new GameCamera(this, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameWorld = new GameWorld(this);
        rectCollision = new RectCollision();

        test1.setPosition(0, 0);
        test1.setSize(100, 100);
        test1.setVelocity(new Vector2(0, 0));

        test2.setPosition(300, 0);
        test2.setSize(100, 100);
        test2.setVelocity(new Vector2(0, 0));
    }

    private void testRender(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            test1.setVelocityY(200);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            test1.setVelocityY(-200);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            test1.setVelocityX(-200);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            test1.setVelocityX(200);
        }

        /*
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            test2.setVelocityY(1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            test2.setVelocityY(-1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            test2.setVelocityX(-1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            test2.setVelocityX(1);
        }
        */

        test1.render();
        test2.render();

        if(rectCollision.hasCollided(test1.getRect(), test2.getRect(), test1.getVel())) {
            test1.setIsHit(true);
            test2.setIsHit(true);
            System.out.println("-------- Colliding!! ----------");
        } else {
            test1.setIsHit(false);
            test2.setIsHit(false);
        }
    }

    @Override
    public void show() {

    }

    /**
     * The loop function of the game screen. Divides the game loop into a tick call and a render call, where the tick
     * is called at the set tick rate, or as fast as possible if that rate cannot be reached, and where the render is
     * called as fast as possible up to the set maximum frame rate. Also updates the tick and frame rate member
     * variables.
     *
     * @author David Neuman
     * @param delta The time in seconds since the last call to render()
     */
    @Override
    public void render(float delta) {

        tickAccumulator += delta;
        frameAccumulator += delta;

        //Check if a tick's worth of time has passed since last tick
        if (tickAccumulator > TICK_PERIOD) {

            doTick();

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

            doRender(tickAccumulator * TICK_RATE);

            frameRate = 1 / frameAccumulator;

            frameAccumulator %= MAX_FPS_PERIOD;
        }

        //Temporary code
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        testRender();
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

    private void doTick() {

    }

    /**
     * Renders a frame of the game screen. Because the frame rate is higher than the tick rate, the rendering
     * interpolates between the current tick and the previous tick, meaning there is increased smoothness at the cost
     * of a very slight increase in latency
     *
     * @author
     * @param lerp Value between 0.0f and 1.0f indicating the
     */
    private void doRender(float lerp) {

    }
}
