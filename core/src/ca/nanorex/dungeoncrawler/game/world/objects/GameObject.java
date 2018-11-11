package ca.nanorex.dungeoncrawler.game.world.objects;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.nanorex.dungeoncrawler.game.world.GameWorld;

import ca.nanorex.dungeoncrawler.game.world.objects.components.ObjectComponent;

public class GameObject implements Serializable {
    private GameWorld world;

    private String type;
    private Set<String> tags;
    private Vector2 pos;
    //needs rotation??

    public GameObject() {
        type = new String();
        tags = new HashSet<String>();
        pos = new Vector2();
    }

    public GameObject(GameObject template) {
        type = new String(template.type);
        tags = new HashSet<String>(template.tags);
        pos = new Vector2(template.pos);
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

    public Vector2 getPos() {
        return pos;
    }
}
