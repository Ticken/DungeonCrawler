package ca.nanorex.dungeoncrawler.engine.world.objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectManager {

    private Set<GameObject> objects;
    private Map<Class<? extends BaseComponent>, Set<GameObject>> objectsByComponent;

    public ObjectManager() {
        objects = new HashSet<GameObject>();
        objectsByComponent = new HashMap<Class<? extends BaseComponent>, Set<GameObject>>();
    }

    public Set<GameObject> get() {
        return objects;
    }

    public Set<GameObject> getWithComponent(Class<? extends BaseComponent> componentType) {
        return objectsByComponent.get(componentType);
    }

    public void add(GameObject object) {

        objects.add(object);

        for (Class<? extends BaseComponent> componentType: object.getComponents().keySet()) {

            if (objectsByComponent.get(componentType) == null)
                objectsByComponent.put(componentType, new HashSet<GameObject>());

            objectsByComponent.get(componentType).add(object);
        }

        object.setManager(this);
    }

    public void remove(GameObject object) {

        objects.remove(object);

        for (Class<? extends BaseComponent> componentType: object.getComponents().keySet()) {

            if (objectsByComponent.get(componentType) == null)
                objectsByComponent.put(componentType, new HashSet<GameObject>());

            objectsByComponent.get(componentType).remove(object);
        }

        object.setManager(null);
    }

    void registerComponent(GameObject object, Class<? extends BaseComponent> componentType) {

        //Add new set if doesn't exist
        if (objectsByComponent.get(componentType) == null)
            objectsByComponent.put(componentType, new HashSet<GameObject>());

        //Add object
        objectsByComponent.get(componentType).add(object);
    }

    void unregisterComponent(GameObject object, Class<? extends BaseComponent> componentType) {

        //Remove from object set if it exists
        if (objectsByComponent.get(componentType) != null)
            objectsByComponent.get(componentType).remove(object);
    }
}
