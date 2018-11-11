package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RendererComponent extends ObjectComponent {
    Model model;

    public RendererComponent(String json_path) {
        model = new Model(json_path);
    }

    public TextureRegion getFrame(String layer_name, String name, float currentAnimationTime, int
            direction) {
        return model.getLayer(layer_name).getTextureRegion(name, currentAnimationTime, Animation
                .PlayMode.LOOP, direction);
    }
}
