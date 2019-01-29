package ca.nanorex.dungeoncrawler.engine.world.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.nanorex.dungeoncrawler.engine.world.objects.components.ObjectComponent;

public class GameObject {

    private ObjectManager manager;

    private String type;
    private Set<String> tags;
    private Vector3 pos;
    //needs rotation??

    private Map<Class<? extends ObjectComponent>, ObjectComponent> components;

    public GameObject() {
        tags = new HashSet<String>();
        pos = new Vector3();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public Vector3 getPos() {
        return pos;
    }

    public <T extends ObjectComponent> boolean hasComponent(Class<T> type) {
        return components.get(type) != null;
    }

    public <T extends ObjectComponent> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

    public void setComponent(Class<? extends ObjectComponent> componentType,
                             ObjectComponent component) {

        if (component != null) {
            components.put(componentType, component);
            if (manager != null)
                manager.registerComponent(this, componentType);
        } else {
            components.remove(componentType);
            if (manager != null)
                manager.unregisterComponent(this, componentType);
        }
    }

    void setManager(ObjectManager manager) {
        this.manager = manager;
    }

    Map<Class<? extends ObjectComponent>, ObjectComponent> getComponents() {
        return components;
    }
}
