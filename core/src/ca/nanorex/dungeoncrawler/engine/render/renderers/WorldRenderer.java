package ca.nanorex.dungeoncrawler.engine.render.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.render.sprite.Sprite;
import ca.nanorex.dungeoncrawler.engine.world.World;
import ca.nanorex.dungeoncrawler.engine.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.SpriteComponent;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.TransformComponent;

public class WorldRenderer implements Renderer<World> {

    private SpriteRenderer spriteRenderer = new SpriteRenderer();

    @Override
    public void render(World world, SpriteBatch batch, float lerp) {

        //SUPER TEMPORARY

        for (int x = 0; x < Gdx.graphics.getWidth(); x += 64) {
            for (int y = 0; y < Gdx.graphics.getHeight(); y += 64) {
                batch.draw(Game.getAssetLibrary().get("tmp/brick", Texture.class), x, y, 64, 64);
            }
        }

        for (GameObject object: world.getObjects().getWithComponent(SpriteComponent.class)) {

            Sprite sprite = object.getComponent(SpriteComponent.class).getSprite();

            TransformComponent transform;

            if ((transform = object.getComponent(TransformComponent.class)) != null) {

                sprite.setPos(transform.getPos2());
                sprite.getRotation().set(transform.getRotation());

                spriteRenderer.render(sprite, batch, lerp);
            }
        }
    }
}
