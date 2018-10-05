package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Rectangle;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public abstract class AttackComponent extends ObjectComponent {
    private Rectangle[] hitboxes;

    public AttackComponent(GameObject object) {
        super(object);
    }

    @Override
    public void update() {
        //Check all objects to see if this hitbox is colliding with their hurtbox
    }
}
