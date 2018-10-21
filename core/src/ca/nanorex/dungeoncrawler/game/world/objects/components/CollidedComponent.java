package ca.nanorex.dungeoncrawler.game.world.objects.components;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

//literally almost nobody needs this class
public abstract class CollidedComponent extends ObjectComponent {

    //protected Queue<Collision> colliders;

    public CollidedComponent(GameObject object) {
        super(object);
    }

    @Override
    public void update() {
    }
}
