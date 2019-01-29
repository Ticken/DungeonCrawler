package ca.nanorex.dungeoncrawler.engine.render.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Renderer<T> {

    public abstract void render(T renderable, SpriteBatch batch, float lerp);

    public static Vector2 getPos(Vector2 pos, Vector2 prevPos, float lerp) {

        //Return prevPos + (pos - prevPos) * lerp
        return new Vector2(prevPos).add(new Vector2(pos).sub(prevPos).scl(lerp));
    }
}
