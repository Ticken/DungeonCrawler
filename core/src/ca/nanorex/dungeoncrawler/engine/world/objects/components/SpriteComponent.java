package ca.nanorex.dungeoncrawler.engine.world.objects.components;

import java.util.Map;

import ca.nanorex.dungeoncrawler.engine.render.sprite.Sprite;
import ca.nanorex.dungeoncrawler.engine.world.objects.BaseComponent;

public class SpriteComponent extends BaseComponent {

    private Sprite sprite;

    @Override
    public Category getCategory() {
        return Category.RENDER;
    }

    public SpriteComponent(String model, Map<String, String> textures) {
        sprite = new Sprite(model, textures);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
