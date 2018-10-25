package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class ModelLayer {
    private String name;
    private Map<String, Animation<TextureRegion>[]> animations;
    private String default_animation;
    private float default_animation_speed;

    public ModelLayer(JsonValue layer, TextureRegion[][] tiles, int directions, int index) {
        name = layer.get("name").asString();
        default_animation = layer.get("default_animation").asString();
        default_animation_speed = layer.get("default_animation_speed").asFloat();
        animations = new HashMap();
        JsonValue animations = layer.get("animations");

        for (int i = 0; i < animations.size; i++) {
            Animation<TextureRegion>[] current_animation = new Animation[directions];

            for (int j = 0; j < directions; j++) {
                int start = animations.get(i).get("start").asInt();
                int length = animations.get(i).get("length").asInt();
                Array<TextureRegion> key_frames = new Array<TextureRegion>(true,
                        tiles[index * directions + j], start, length);

                current_animation[j] = new Animation<TextureRegion>(
                        1.0f / animations.get(i).get("base_speed").asFloat(),
                        key_frames);
            }
            this.animations.put(animations.get(i).get("name").asString(), current_animation);
        }
    }

    public String getName() {
        return name;
    }

    public TextureRegion getTextureRegion(String animation, float animationTime, Animation.PlayMode
            playMode, int direction) {
        return animations.get(animation)[direction].getKeyFrame(animationTime);
    }
}
