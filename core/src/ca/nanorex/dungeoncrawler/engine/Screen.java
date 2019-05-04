package ca.nanorex.dungeoncrawler.engine;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen implements Disposable {

    private Game game;
    protected List<InputProcessor> inputProcessors;

    public Screen(Game game) {

        this.game = game;

        inputProcessors = new ArrayList<InputProcessor>();
    }

    public List<InputProcessor> getInputProcessors() {
        return inputProcessors;
    }

    /**
     * Renders a frame of the game screen. Because the frame rate is higher than the tick rate, the rendering
     * interpolates between the current tick and the previous tick, meaning there is increased smoothness at the cost
     * of a very slight increase in latency
     *
     * @author
     * @param lerp Value between 0.0f and 1.0f indicating the
     */
    public abstract void frame(float lerp);

    public abstract void tick();

    public abstract void show();

    public abstract void hide();

    public abstract void pause();

    public abstract void resume();
}
