package ca.nanorex.dungeoncrawler.game;
import java.io.Serializable;

public abstract class ObjectComponent implements Serializable {

    public ObjectComponent(GameObject object) {
        this.object = object;
    }

    public static final Class[] order;
    static {
        order = new Class[] {
            //ControllerComponent.class,
            //MovementComponent.class,
            //AttackComponent.class,
            //InventoryComponent.class,
            //CollisionComponent.class,
            //HealthComponent.class,
            RendererComponent.class,
            //SoundComponent.class
        };
    }

    private GameObject object;

    public abstract void update();
}
