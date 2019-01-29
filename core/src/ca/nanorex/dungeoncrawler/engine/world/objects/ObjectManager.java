package ca.nanorex.dungeoncrawler.engine.world.objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.nanorex.dungeoncrawler.engine.world.objects.components.ObjectComponent;

public class ObjectManager {

    private Set<GameObject> objects;
    private Map<Class<? extends ObjectComponent>, Set<GameObject>> objectsByComponent;

    public ObjectManager() {
        objects = new HashSet<GameObject>();
        objectsByComponent = new HashMap<Class<? extends ObjectComponent>, Set<GameObject>>();
    }

    public Set<GameObject> getObjects() {
        return objects;
    }

    public Set<GameObject> getObjectsWithComponent(Class<? extends ObjectComponent> componentType) {
        return objectsByComponent.get(componentType);
    }

    public void add(GameObject object) {

        objects.add(object);

        for (Class<? extends ObjectComponent> componentType: object.getComponents().keySet()) {

            if (objectsByComponent.get(componentType) == null)
                objectsByComponent.put(componentType, new HashSet<GameObject>());

            objectsByComponent.get(componentType).add(object);
        }

        object.setManager(this);
    }

    public void remove(GameObject object) {

        objects.remove(object);

        for (Class<? extends ObjectComponent> componentType: object.getComponents().keySet()) {

            if (objectsByComponent.get(componentType) == null)
                objectsByComponent.put(componentType, new HashSet<GameObject>());

            objectsByComponent.get(componentType).remove(object);
        }

        object.setManager(null);
    }

    void registerComponent(GameObject object, Class<? extends ObjectComponent> componentType) {

        //Add new set if doesn't exist
        if (objectsByComponent.get(componentType) == null)
            objectsByComponent.put(componentType, new HashSet<GameObject>());

        //Add object
        objectsByComponent.get(componentType).add(object);
    }

    void unregisterComponent(GameObject object, Class<? extends ObjectComponent> componentType) {

        //Remove from object set if it exists
        if (objectsByComponent.get(componentType) != null)
            objectsByComponent.get(componentType).remove(object);
    }
}
