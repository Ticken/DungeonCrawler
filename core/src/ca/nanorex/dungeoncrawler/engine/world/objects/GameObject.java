package ca.nanorex.dungeoncrawler.engine.world.objects;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.compression.lzma.Base;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.nanorex.dungeoncrawler.engine.util.Rotation;

public class GameObject {

    private ObjectManager manager;

    private String type;
    private Set<String> tags;

    private Map<Class<? extends BaseComponent>, BaseComponent> components;
    private Map<BaseComponent.Category, Class<? extends BaseComponent>> categories;

    public GameObject() {
        tags = new HashSet<String>();

        components = new HashMap<Class<? extends BaseComponent>, BaseComponent>();
        categories = new HashMap<BaseComponent.Category, Class<? extends BaseComponent>>();
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

    public <T extends BaseComponent> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

    public void setComponent(Class<? extends BaseComponent> componentType,
                             BaseComponent component) {

        if (component != null) {
            //Add or replace the component

            //If the object already has a component of the same category, remove the old component
            if (categories.containsKey(component.getCategory()))
                setComponent(categories.get(component.getCategory()), null);

            //Add component to component and category maps
            components.put(componentType, component);
            categories.put(component.getCategory(), componentType);

            //Register component in manager
            if (manager != null)
                manager.registerComponent(this, componentType);

        } else if (components.get(componentType) != null) {
            //Remove the component

            //Remove component from component and category maps
            categories.remove(components.get(componentType).getCategory());
            components.remove(componentType);

            //Unregister component in manager
            if (manager != null)
                manager.unregisterComponent(this, componentType);
        }
    }

    void setManager(ObjectManager manager) {
        this.manager = manager;
    }

    Map<Class<? extends BaseComponent>, BaseComponent> getComponents() {
        return components;
    }
}
