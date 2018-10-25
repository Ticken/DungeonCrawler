package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.events.EventListener;
import ca.nanorex.dungeoncrawler.game.world.objects.events.EventPublisher;
import ca.nanorex.dungeoncrawler.game.world.objects.events.VelocityEvent;

public class ControllerComponent extends ObjectComponent {
    private Vector2 direction;
    public enum CONTROL_TYPE {PLAYER, ENT}
    private CONTROL_TYPE controlType;
    private EventPublisher<VelocityEvent> velocityEventPublisher;

    public ControllerComponent(GameObject object, CONTROL_TYPE controlType) {
        super(object);
        direction = new Vector2();
        this.controlType = controlType;
        velocityEventPublisher = new EventPublisher(getVelocityListeners(object));
    }

    @Override
    public void update() {
        //System.out.println("(" + direction.x + ", " + direction.y + ")");
        velocityEventPublisher.publish(new VelocityEvent(direction));
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    //public boolean isPlayer() { return this.controlType == CONTROL_TYPE.PLAYER; }

    public List<EventListener<VelocityEvent>> getVelocityListeners(GameObject object) {
        List<EventListener<VelocityEvent>> listeners = new ArrayList();
        // TODO: this might be able to be generalized
        if (object.hasComponent(MovementComponent.class)) {
            listeners.add(object.getComponent(MovementComponent.class).getVelocityEventListener());
        }
        return listeners;
    }
}
