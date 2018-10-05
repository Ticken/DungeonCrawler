package ca.nanorex.dungeoncrawler.game.world.objects.components;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.Stat;
import ca.nanorex.dungeoncrawler.game.world.objects.TickTimer;

public abstract class ObjectComponent implements Serializable {

    public static final Class<?>[] order;

    static {
        order = new Class<?>[] {
            //ControllerComponent.class,
            //MovementComponent.class,
            //AttackComponent.class,
            //InventoryComponent.class,
            //CollisionComponent.class,
            //HealthComponent.class,
            //RenderComponent.class,
            //SoundComponent.class
        };
    }

    private GameObject object;
    private Map<String, Stat> stats;
    private TreeMap<String, TickTimer> timers;
    //private Queue<Event> events;

    public ObjectComponent(GameObject object) {
        this.object = object;
        stats = new HashMap<String, Stat>();
        timers = new TreeMap<String, TickTimer>();
        //events = new LinkedList<Event>();
    }

    public abstract void update();

    public Stat getStat(String stat) { return stats.get(stat); }
    public TickTimer getTimer(String timer) { return timers.get(timer); }
}
