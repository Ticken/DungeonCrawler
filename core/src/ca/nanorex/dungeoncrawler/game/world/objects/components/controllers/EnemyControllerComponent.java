package ca.nanorex.dungeoncrawler.game.world.objects.components.controllers;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.components.ObjectComponent;

public class EnemyControllerComponent extends ObjectComponent{
    private Vector2 direction;
    private Vector2 target;

    public EnemyControllerComponent() {
        direction = new Vector2(0, 0);
        target = new Vector2(0, 0);
    }

    public void setTarget(Vector2 target) {
        this.target = target;
    }
}
