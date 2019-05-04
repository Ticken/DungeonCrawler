
package ca.nanorex.dungeoncrawler.engine.render.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.engine.render.sprite.Sprite;

public class SpriteRenderer implements Renderer<Sprite> {

    @Override
    public void render(Sprite sprite, SpriteBatch batch, float lerp) {

        for (TextureRegion region: sprite.getTextureRegions()) {

            int width = region.getRegionWidth();
            int height = region.getRegionHeight();

            //Find render position by lerping
            Vector2 pos = new Vector2(sprite.getRenderPrevPos()).lerp(sprite.getRenderPos(), lerp);

            //Adjust position to corner, rather than center
            pos.sub(new Vector2(width, height).scl(0.5f));

            //Draw
            batch.draw(region, pos.x, pos.y, width * 4, height * 4);
        }
    }
}
