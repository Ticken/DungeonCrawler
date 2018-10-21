package ca.nanorex.dungeoncrawler.game.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Renderer<T> {

    public abstract void render(T renderable, SpriteBatch batch, float lerp);

}
