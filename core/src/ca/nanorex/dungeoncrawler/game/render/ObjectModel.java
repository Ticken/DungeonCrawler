package ca.nanorex.dungeoncrawler.game.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.nanorex.dungeoncrawler.game.world.objects.Rotation;

/**
 * Represents a model that contains data concerning how to render an object
 */
public class ObjectModel {

    /**
     * Contains the data necessary to represent the state of an instance of a model
     */
    public static class Data {

        private ObjectModel model;

        private Map<String, ModelLayer.Data> layerData;
        private Map<String, ModelAnimation.Data> animationData;
        private List<String> animationStack;

        /**
         * Constructor for ObjectModel.Data
         *
         * @author David Neuman
         * @param model The model that this data is associated with
         */
        public Data(ObjectModel model) {

            this.model = model;

            //Ordered map of layers
            layerData = new LinkedHashMap<String, ModelLayer.Data>();

            //Map of all currently playing animations
            animationData = new HashMap<String, ModelAnimation.Data>();

            //Could be list of String
            animationStack = new ArrayList<String>();

            //Insert a new ModelLayer.Data for each layer in model
            for (String layerName: model.layers.keySet()) {
                layerData.put(layerName, new ModelLayer.Data());
            }

            //Insert an animation entry for each animation priority
            int maxPriority = Integer.MIN_VALUE;

            for (ModelAnimation animation: model.animations.values())
                maxPriority = Math.max(maxPriority, animation.priority);

            for (int i = 0; i < maxPriority; i++)
                animationStack.add(null);
        }

        /**
         * Updates the animations
         *
         * @author David Neuman
         * @param timeDelta The time in seconds since the last call to update()
         */
        public void update(float timeDelta) {
            for (String animationName: animationData.keySet()) {

                ModelAnimation.Data animation = animationData.get(animationName);

                //Update animation time
                animation.time += animation.speed * timeDelta;

                //Remove animation if it is done playing
                if (!isAnimationPlaying(animation.name))
                    stopAnimation(animation.name, false);
            }
        }

        public ObjectModel getModel() {
            return model;
        }

        /**
         * Sets the visibility of the specified layer
         *
         * @author David Neuman
         * @param layerName The name of the model layer whose visibilty to set
         * @param visible Whether the layer should be visible or not
         */
        public void setLayerVisibility(String layerName, boolean visible) {
            layerData.get(layerName).visible = visible;
        }

        /**
         * Sets the animation speed of all currently playing animations
         *
         * @author David Neuman
         * @param speed The speed multiplier to use for animations
         */
        public void setAnimationSpeed(float speed) {
            for (ModelAnimation.Data animation: animationData.values())
                animation.speed = speed;
        }

        /**
         * Sets the animation speed of a specific animation if it is currently playing
         *
         * @author David Neuman
         * @param animationName The name of the animation whose speed will be set
         * @param speed
         */
        public void setAnimationSpeed(String animationName, float speed) {

            if (animationData.containsKey(animationName))
                animationData.get(animationName).speed = speed;
        }

        /**
         * Starts playing a specified animation on all layers that use the animation
         *
         * @author David Neuman
         * @param animationName The name of animation to play
         * @param speed The speed multiplier for the animation
         * @param stopLowerPriority If true, will stop all lower priority animations that are playing when this
         *                          animation starts
         */
        public void playAnimation(String animationName, float speed, boolean stopLowerPriority) {
            playAnimation(animationName, speed, null, stopLowerPriority);
        }

        /**
         * Starts playing a specified animation on all layers that use the animation
         *
         * @author David Neuman
         * @param animationName The name of animation to play
         * @param speed The speed multiplier for the animation
         * @param playMode The play mode for the animation. If null, uses the default play mode specified in the
         *                 model file
         * @param stopLowerPriority If true, will stop all lower priority animations that are playing when this
         *                          animation starts
         */
        public void playAnimation(String animationName, float speed, ModelAnimation.Mode playMode,
                                  boolean stopLowerPriority) {

            ModelAnimation animation = model.animations.get(animationName);

            if (animation != null) {

                stopAnimation(animationName, stopLowerPriority);

                if (playMode == null)
                    playMode = animation.defaultMode;

                //Add to animation stack and map
                animationStack.set(animation.priority, animationName);
                animationData.put(animationName, new ModelAnimation.Data(speed, playMode));
            }
        }

        /**
         * Stops an animation that is currently playing
         *
         * @author David Neuman
         * @param animationName The name of the animation to stop
         * @param stopLowerPriority If true, will also stop all lower priority animations that are playing
         */
        public void stopAnimation(String animationName, boolean stopLowerPriority) {

            ModelAnimation animation = model.animations.get(animationName);

            if (animation != null) {
                for (int priority = animation.priority; priority < animationStack.size(); priority++) {
                    animationStack.set(priority, null);
                    animationData.remove(animationName);

                    //Janky break statement to stop after one iteration
                    if (stopLowerPriority) break;
                }
            }
        }

        /**
         * Checks to see if an animation is currently playing
         *
         * @author David Neuman
         * @param animationName The name of the animation to query
         * @return true if the animation is currently playing, false if not
         */
        public boolean isAnimationPlaying(String animationName) {

            ModelAnimation.Data animation = animationData.get(animationName);

            if (animation != null)
                return animation.isPlaying();

            return false;
        }
    }

    protected String name;
    protected Vector2 size;
    protected Vector2 offset; //equal to model offset (object center to model center) + texture offset (texture center to texture corner)
    protected Rotation.Count rotCount;
    protected String defaultAnimation;

    protected Map<String, ModelLayer> layers;
    protected Map<String, ModelAnimation> animations;

    /**
     *
     */
    public ObjectModel(String file) {

        layers = new LinkedHashMap<String, ModelLayer>();
        animations = new HashMap<String, ModelAnimation>();

        JsonValue root = new JsonReader().parse(Gdx.files.internal(file));

        name = root.getString("name");

        offset = new Vector2(0, root.getInt("y_offset"));

        rotCount = Rotation.Count.fromInt(root.getInt("rotation"));
        //if rotCount == null throw error

        defaultAnimation = root.getString("default_animation");

        JsonValue layers = root.get("layers");

        for (int i = 0; i < layers.size; i++) {
            JsonValue layer = layers.get(i);
            this.layers.put(layer.getString("name"),
                    new ModelLayer(layer.getString("texture"), layer.getInt("texture_index")));
        }

        JsonValue animations = root.get("animations");

        for (int i = 0; i < animations.size; i++) {
            JsonValue animation = animations.get(i);
            this.animations.put(animation.getString("name"),
                    new ModelAnimation(
                            animation.getInt("texture_index"),
                            animation.getInt("length"),
                            animation.getFloat("fps"),
                            animation.getInt("priority"),
                            ModelAnimation.Mode.valueOf(animation.getString("mode").toUpperCase()),
                            animation.get("layers").asStringArray()
                    ));
        }
    }

    public TextureRegion getTextureRegion(Data data, String layer, Rotation rotation) {

        TextureRegion region = new TextureRegion();

        //Find the active animation
        ModelAnimation.Data animationData;
        for (int i = data.animationStack.size(); (animationData = data.animationData.get(data.animationStack.get(i)))
                != null && animationData.animation.layers.contains(layer); i--);

        ModelAnimation animation = animations.get(animationData.name);

        int layerIndex = layers.get(layer).textureIndex;
        int frameCount = animation.length;
        int animationIndex = animation.index;

        int frameIndex = animation.getFrame(animationData);
        int rotationCount = rotCount.value;
        int rotationIndex = new Rotation(rotCount).setValue(rotation).getValue();

        int x = layerIndex * frameCount + animationIndex;
        int y = frameIndex * rotationCount + rotationIndex;

        int w = (int)size.x;
        int h = (int)size.y;

        region.setRegion(x, y, w, h);

        return region;
    }

    /**
     * Getter for name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for size
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     * Getter for offset. This includes the model offset (object center to model center) as well as the texture offset
     * (texture center to texture corner)
     */
    public Vector2 getOffset() {
        return offset;
    }

    /**
     * Getter for rotation count
     */
    public Rotation.Count getRotCount() {
        return rotCount;
    }

    /**
     * Getter for layers
     */
    public Set<String> getLayers() {
        return layers.keySet();
    }
}
