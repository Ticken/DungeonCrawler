package ca.nanorex.dungeoncrawler.game.world.objects.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.Stat;
import ca.nanorex.dungeoncrawler.game.world.objects.TickTimer;

public abstract class ObjectComponent implements Serializable {

    public static final List<Class<? extends ObjectComponent>> order;

    static {
        order = new ArrayList<Class<? extends ObjectComponent>>();
        order.add(ControllerComponent.class);
        order.add(MovementComponent.class);
        order.add(ColliderComponent.class);
        order.add(CollidedComponent.class);
        order.add(AttackComponent.class);
        order.add(VulnerabilityComponent.class);
        order.add(HealthComponent.class);
        order.add(RenderComponent.class);
        //order.add(SoundComponent.class);
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
