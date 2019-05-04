package ca.nanorex.dungeoncrawler.engine.render.sprite;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.engine.util.Rotation;

public class SpriteModel {

    String name;
    Vector2 offset;
    int rotCount;
    String defaultAnimation;

    Map<String, SpriteModelLayer> layers;
    Map<String, SpriteModelAnimation> animations;

    public SpriteModel(FileHandle file) {

        layers = new LinkedHashMap<String, SpriteModelLayer>();
        animations = new HashMap<String, SpriteModelAnimation>();

        //Read properties from JSON file
        JsonValue jsonRoot = new JsonReader().parse(file);

        //Root level properties
        name = jsonRoot.getString("name");
        offset = new Vector2(0, jsonRoot.getInt("y_offset"));
        rotCount = jsonRoot.getInt("directions");
        defaultAnimation = jsonRoot.getString("default_animation");

        //Layer properties
        JsonValue jsonLayers = jsonRoot.get("layers");

        for (int i = 0; i < jsonLayers.size; i++) {

            JsonValue layer = jsonLayers.get(i);
            this.layers.put(layer.getString("name"),
                    new SpriteModelLayer(
                            layer.getString("name"),
                            layer.getString("texture"),
                            layer.getInt("texture_index")));
        }

        //Animation properties
        JsonValue jsonAnimations = jsonRoot.get("animations");

        for (int i = 0; i < jsonAnimations.size; i++) {

            JsonValue animation = jsonAnimations.get(i);
            this.animations.put(animation.getString("name"),
                    new SpriteModelAnimation(
                            animation.getInt("texture_index"),
                            animation.getInt("length"),
                            animation.getInt("priority"),
                            animation.getFloat("fps"),
                            SpriteModelAnimation.Mode.valueOf(
                                    animation.getString("mode").toUpperCase()),
                            animation.get("layers").asStringArray()
                    ));
        }
    }
}
