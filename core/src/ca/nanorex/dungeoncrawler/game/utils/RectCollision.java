package ca.nanorex.dungeoncrawler.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectCollision {

    public RectCollision() {
    }

    public boolean isColliding(Rectangle rectA, Rectangle rectB) {
        return rectA.overlaps(rectB);
    }
}
