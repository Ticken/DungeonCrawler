package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Vector2;

public class MovementComponent extends ObjectComponent {
    private Vector2 direction, position, velocity;

    public MovementComponent() {
        direction = new Vector2();
        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return velocity;
    }
}
