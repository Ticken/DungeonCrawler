package ca.nanorex.dungeoncrawler.engine.world.objects;

import java.util.ArrayList;
import java.util.List;

import ca.nanorex.dungeoncrawler.engine.world.World;
import ca.nanorex.dungeoncrawler.engine.world.objects.systems.MobMovementSystem;

public class SystemManager {

    private List<BaseSystem> systems;

    public SystemManager(World world) {
        systems = new ArrayList<BaseSystem>();

        //SUPER TEMP
        systems.add(new MobMovementSystem(world));
    }

    public void tick() {

        for (BaseSystem system: systems)
            if (system.isEnabled())
                system.update();
    }
}
