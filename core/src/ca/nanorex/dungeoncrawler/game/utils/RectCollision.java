package ca.nanorex.dungeoncrawler.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectCollision {

    // Temp Items
    Rectangle rectATemp = new Rectangle();
    Vector2 velATemp = new Vector2();

    public RectCollision() { }

    public boolean isColliding (Rectangle rectA, Rectangle rectB) {
        return rectA.overlaps(rectB);
    }

    public boolean hasCollided (Rectangle rectA, Rectangle rectB, Vector2 velA) { // B is static
        // Normalizes velocity, then loops to check every position between it's last position and it's current position
        // Assume velocity was already applied to rectangle

        rectATemp.set(rectA);
        velATemp.set(velA);

        rectATemp.setPosition(rectATemp.x + velATemp.x,rectATemp.y + velATemp.y);
        velATemp.nor();

        int loopNum = (int)(velA.x);
        //System.out.println("loopNum: "+loopNum);

        for (int i = 0; i <= loopNum; i++) {
            rectATemp.setPosition(rectATemp.x + (velATemp.x * i), rectATemp.y + (velATemp.y * i));
            if (isColliding(rectATemp, rectB)) {
                return true;
            }
        }

        return false; // If it has not found a collision it will return false
    }
}
