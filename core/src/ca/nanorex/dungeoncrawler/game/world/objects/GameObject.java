package ca.nanorex.dungeoncrawler.game.world.objects;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.game.world.objects.components.ObjectComponent;

public class GameObject implements Serializable {

    Map<Class<?>, ObjectComponent> components;

    public GameObject() {
        components = new HashMap();
    }

    public <T> boolean hasComponent(Class<T> type) {
        return components.get(type) != null;
    }
    public <T> T getComponent(Class<T> type) {
        return (T)(components.get(type));
    }
}
