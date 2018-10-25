package ca.nanorex.dungeoncrawler.game.world.objects.events;

import com.badlogic.gdx.math.Vector2;

public class VelocityEvent extends Event {
    public Vector2 velocity;

    public VelocityEvent(Vector2 velocity) {
        this.velocity = velocity;
    }
}
