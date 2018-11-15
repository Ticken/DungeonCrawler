package ca.nanorex.dungeoncrawler.game.world.objects.systems;

import java.util.ArrayList;
import java.util.List;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.components.ControllerComponent;

public abstract class ObjectSystem {
    public static final List<Class<? extends ObjectSystem>> order;

    static {
        order = new ArrayList<Class<? extends ObjectSystem>>();
        order.add(ControllerSystem.class);
    };

    public abstract void update(ObjectList objectList);
}
