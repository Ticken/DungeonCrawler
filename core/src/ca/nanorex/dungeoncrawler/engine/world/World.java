package ca.nanorex.dungeoncrawler.engine.world;

import ca.nanorex.dungeoncrawler.engine.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.engine.world.objects.ObjectManager;
import ca.nanorex.dungeoncrawler.engine.world.objects.SystemManager;
import ca.nanorex.dungeoncrawler.engine.world.objects.factories.MobFactory;

public class World {

    private ObjectManager objects;
    private SystemManager systemManager;

    private GameObject player;

    public World() {
        objects = new ObjectManager();
        systemManager = new SystemManager(this);

        player = MobFactory.create(MobFactory.Type.PLAYER);
        objects.add(player);
    }

    public void tick() {
        systemManager.tick();
    }

    public ObjectManager getObjects() {
        return objects;
    }

    public GameObject getPlayer() {
        return player;
    }
}
