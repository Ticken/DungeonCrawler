package ca.nanorex.dungeoncrawler.game.world.objects.systems;

import java.util.ArrayList;
import java.util.List;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.PlayerObject;

public class ObjectList {
    private List<GameObject> objects;
    private List<ObjectSystem> systems;

    public ObjectList() {
        // OBJECTS
        objects = new ArrayList();
        PlayerObject player = new PlayerObject(1);
        objects.add(player);
        // SYSTEMS
        systems = new ArrayList();
        PlayerControllerSystem playerControllerSystem = new PlayerControllerSystem();
        systems.add(playerControllerSystem);
    }

    public void update() {
        for (ObjectSystem system: systems)
            system.update(this);
    }
}
