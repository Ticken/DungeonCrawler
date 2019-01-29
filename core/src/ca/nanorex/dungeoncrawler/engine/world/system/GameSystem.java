package ca.nanorex.dungeoncrawler.engine.world.system;

import com.badlogic.gdx.utils.Disposable;

public abstract class GameSystem implements Disposable {

    private boolean enabled;

    public GameSystem() {

        enabled = true;
    }

    public abstract void update();

    public void enable(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
