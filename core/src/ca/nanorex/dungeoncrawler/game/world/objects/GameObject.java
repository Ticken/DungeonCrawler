package ca.nanorex.dungeoncrawler.game.world.objects;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.nanorex.dungeoncrawler.game.world.GameWorld;

import ca.nanorex.dungeoncrawler.game.world.objects.components.ObjectComponent;
import ca.nanorex.dungeoncrawler.game.world.objects.systems.ObjectSystem;

public class GameObject implements Serializable {
    private GameWorld world;

    protected Map<Class<? extends ObjectComponent>, ObjectComponent> components;

    private String type;
    private Set<String> tags;

    public GameObject() {
        type = new String();
        tags = new HashSet<String>();
        components = new HashMap();
    }

    public GameObject(GameObject template) {
        type = new String(template.type);
        tags = new HashSet<String>(template.tags);
        components = new HashMap();
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

    public void addTag(String tag) { // TODO @Jordan check that tag doesn't already exist
        tags.add(tag);
    }

    public <T extends ObjectComponent> T getComponent(Class<T> component) {
        return (T) components.get(component);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    /*public Vector2 getPos() {
        return pos;
    }*/
}
