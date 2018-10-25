package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.events.EventListener;
import ca.nanorex.dungeoncrawler.game.world.objects.events.VelocityEvent;


public class MovementComponent extends ObjectComponent {
    private Vector2 direction, position, velocity;
    private EventListener<VelocityEvent> VelocityEventListener;

    public MovementComponent(GameObject object) {
        super(object);
        direction = new Vector2();
        VelocityEventListener = new EventListener<VelocityEvent>();
        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
    }

    @Override
    public void update() {
        VelocityEvent velocityEvent;
        if ((velocityEvent = VelocityEventListener.getEvents().poll()) != null) {
            velocity = velocityEvent.velocity;
        }
        Vector2 velocityTemp = new Vector2(velocity);
        velocityTemp.scl(1.2f);
        position.add(velocityTemp);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return velocity;
    }

    public EventListener<VelocityEvent> getVelocityEventListener() {
        return VelocityEventListener;
    }
}
