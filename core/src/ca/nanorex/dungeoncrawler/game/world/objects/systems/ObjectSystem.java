package ca.nanorex.dungeoncrawler.game.world.objects.systems;

import java.util.List;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public abstract class ObjectSystem {
    public abstract void update(List<GameObject> objects);
}
