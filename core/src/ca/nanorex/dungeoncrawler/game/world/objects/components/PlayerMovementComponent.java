package ca.nanorex.dungeoncrawler.game.world.objects.components;

        import com.badlogic.gdx.math.Vector2;

public class PlayerMovementComponent extends ObjectComponent {
    private Vector2 position, velocity;
    private float damping, traction, maxSpeed;

    public PlayerMovementComponent() {
        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
        damping = 0.01f;
        traction = 0.01f;
        maxSpeed = 0.04f;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getTraction() { return traction; }

    public float getDamping() { return damping; }

    public float getMaxSpeed() { return maxSpeed; }
}
