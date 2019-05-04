package ca.nanorex.dungeoncrawler.engine.world.objects.factories;

import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.engine.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.MobMovementComponent;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.SpriteComponent;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.TransformComponent;

public class MobFactory {

    public enum Type {
        PLAYER
    }

    public static GameObject create(Type type) {

        GameObject object = new GameObject();

        switch (type) {

            case PLAYER:

                object.setComponent(TransformComponent.class, new TransformComponent());
                object.setComponent(MobMovementComponent.class, new MobMovementComponent(10f, 2f));

                Map<String, String> textures = new HashMap<String, String>();
                textures.put("main", "objects/player");
                textures.put("weapon", "objects/basic_sword");
                object.setComponent(SpriteComponent.class, new SpriteComponent("basic_humanoid",
                        textures));

                return object;

            default:
                return null;
        }
    }
}
