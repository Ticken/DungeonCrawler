package ca.nanorex.dungeoncrawler.game.world.objects.components;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.Stat;
import ca.nanorex.dungeoncrawler.game.world.objects.TickTimer;

public abstract class ObjectComponent implements Serializable {

    public static final Class<? extends ObjectComponent>[] order;

    static {
        order = (Class<? extends ObjectComponent>[]) new Class<?>[]{
            ControllerComponent.class,
            MovementComponent.class,
            //ColliderComponent.class,
            //CollidedComponent.class,
            //AttackComponent.class,
            //VulnerabilityComponent.class,
            //HealthComponent.class,
            RenderComponent.class,
            //SoundComponent.class
        };
    }

    protected GameObject object;
    protected Map<String, Stat> stats;
    protected TreeMap<String, TickTimer> timers;
    //protected Queue<Event> events;

    public ObjectComponent(GameObject object) {
        this.object = object;
        stats = new HashMap<String, Stat>();
        timers = new TreeMap<String, TickTimer>();
        //events = new LinkedList<Event>();
    }

    public abstract void update();

    public Stat getStat(String stat) {
        return stats.get(stat);
    }

    public TickTimer getTimer(String timer) {
        return timers.get(timer);
    }
}
