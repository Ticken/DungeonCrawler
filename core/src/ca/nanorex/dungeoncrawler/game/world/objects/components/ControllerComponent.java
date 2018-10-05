package ca.nanorex.dungeoncrawler.game.world.objects.components;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public abstract class ControllerComponent extends ObjectComponent {

    public ControllerComponent(GameObject object) {
        super(object);
    }

    @Override
    public void update() {
    }
}
