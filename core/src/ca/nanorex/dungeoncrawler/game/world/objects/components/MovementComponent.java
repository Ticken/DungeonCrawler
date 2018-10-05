package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public abstract class MovementComponent extends ObjectComponent {

    protected Vector2 vel;
    //speed stat

    public MovementComponent(GameObject object) {
        super(object);

        vel = new Vector2();
    }

    @Override
    public void update() {
        object.getPos().add(vel);
    }

    public Vector2 getVel() {
        return vel;
    }
}
