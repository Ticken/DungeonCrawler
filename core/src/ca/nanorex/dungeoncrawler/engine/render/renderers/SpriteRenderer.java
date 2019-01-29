
package ca.nanorex.dungeoncrawler.engine.render.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.engine.render.sprite.Sprite;

public class SpriteRenderer extends Renderer<Sprite> {

    @Override
    public void render(Sprite sprite, SpriteBatch batch, float lerp) {

        for (TextureRegion region: sprite.getTextureRegions()) {

            int width = region.getRegionWidth();
            int height = region.getRegionHeight();

            Vector2 pos = getPos(sprite.getRenderPos(), sprite.getRenderPrevPos(), lerp);
            pos.sub(new Vector2(width, height).scl(0.5f));

            batch.draw(region, pos.x, pos.y, width * 4, height * 4);
        }
    }
}

