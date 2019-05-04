package ca.nanorex.dungeoncrawler.engine.render.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface Renderer<T> {

    void render(T renderable, SpriteBatch batch, float lerp);

}
