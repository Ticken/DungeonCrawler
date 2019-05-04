package ca.nanorex.dungeoncrawler.engine.world.objects;

import com.badlogic.gdx.utils.Disposable;

import ca.nanorex.dungeoncrawler.engine.world.World;

public abstract class BaseSystem implements Disposable {

    private boolean enabled;

    protected World world;

    public BaseSystem(World world) {

        this.world = world;

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
