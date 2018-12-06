package ca.nanorex.dungeoncrawler.game.world.objects.components.controllers;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.components.ObjectComponent;

public class PlayerControllerComponent extends ObjectComponent {
    Vector2 direction;
    public PlayerControllerComponent() {
        direction = new Vector2(0,0);
    }

    public void setDirection(Vector2 direction) {
        this.direction = new Vector2(direction);
    }

    public Vector2 getDirection() {
        return direction;
    }
}
