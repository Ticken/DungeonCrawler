package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.game.AnimationInfo;
import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public class RendererComponent extends ObjectComponent {
    Model model;

    public RendererComponent(GameObject object, String json_path) {
        super(object);
        model = new Model(json_path);
    }

    public TextureRegion getFrame(String layer_name, String name, float currentAnimationTime) {
        return model.getLayer(layer_name).getTextureRegion(name, currentAnimationTime, Animation
                .PlayMode.LOOP);
    }

    @Override
    public void update() {

    }
}

