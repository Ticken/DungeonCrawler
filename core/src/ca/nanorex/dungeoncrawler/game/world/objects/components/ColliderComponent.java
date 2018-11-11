package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Rectangle;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public abstract class ColliderComponent extends ObjectComponent {

    protected Rectangle rect;
    protected boolean solid;

    public ColliderComponent(GameObject object) {

    }

    public Rectangle getRect() {
        return rect; //adjust for object position lol
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
