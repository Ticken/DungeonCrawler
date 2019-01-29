package ca.nanorex.dungeoncrawler.engine.world.system;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {

    private List<GameSystem> systems;

    public SystemManager() {
        systems = new ArrayList<GameSystem>();
    }

    public void tick() {

        for (GameSystem system: systems)
            if (system.isEnabled())
                system.update();
    }

}
