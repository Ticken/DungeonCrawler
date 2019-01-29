package ca.nanorex.dungeoncrawler.engine.world.objects.components;

import com.badlogic.gdx.math.Vector2;

public class MovementComponent extends ObjectComponent {

    private Vector2 vel, accel;

    public MovementComponent() {

        vel = new Vector2();
        accel = new Vector2();
    }

    public Vector2 getVel() {
        return vel;
    }

    public Vector2 getAccel() {
        return accel;
    }
}
