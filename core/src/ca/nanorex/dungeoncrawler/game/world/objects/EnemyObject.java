package ca.nanorex.dungeoncrawler.game.world.objects;

import ca.nanorex.dungeoncrawler.game.world.objects.components.controllers.EnemyControllerComponent;

public class EnemyObject extends GameObject{
    public EnemyObject() {
        super();
        components.put(EnemyControllerComponent.class, new EnemyControllerComponent());
    }
}
