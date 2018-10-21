package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;


public class MovementComponent extends ObjectComponent {
    private Vector2 direction;

    public MovementComponent(GameObject object) {
        super(object);
        direction = new Vector2();
    }

    @Override
    public void update() {

    }
}
