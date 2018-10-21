package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.game.GameScreen;
import ca.nanorex.dungeoncrawler.game.render.ModelAnimation;
import ca.nanorex.dungeoncrawler.game.render.ObjectModel;
import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

/**
 * This component provides functionality to use textures and models to render a GameObject
 */
public abstract class RenderComponent extends ObjectComponent {

    private Vector2 pos;
    private Vector2 prevPos;

    private ObjectModel.Data modelData;
    private Map<String, Texture> textures;
    private Vector2 textureSize;

    /**
     * Constructor for RenderComponent
     *
     * @author David Neuman
     * @param object The GameObject that uses this component
     * @param model The model to use for rendering
     * @param textures The textures to for rendering
     * @param textureSize The dimensions of on sprite on the texture
     */
    public RenderComponent(GameObject object, ObjectModel model, Map<String, Texture> textures, Vector2 textureSize) {
        super(object);

        this.textures = new HashMap<String, Texture>(textures);
        this.textureSize = new Vector2(textureSize);

        pos = new Vector2();
        prevPos = new Vector2();

        //Initialize model data
        modelData = new ObjectModel.Data(model);
    }

    /**
     * Updates the RenderComponent
     */
    public void update() {

        //Update position
        prevPos.set(pos);
        pos.set(object.getPos()).add(modelData.getModel().getOffset());

        //Update model data
        modelData.update(GameScreen.TICK_PERIOD);
    }

    /**
     * Sets a texture that is used for rendering
     *
     * @param name The name of this texture (referenced by the model) to set
     * @param texture The texture to use
     */
    public void setTexture(String name, Texture texture) {
        if (textures.containsKey(name))
            textures.put(name, texture);
    }

    /**
     * Sets the layer visibility of the specified layer
     *
     * @author David Neuman
     * @param layerName The name of the layer whose visibility to set
     * @param visible Whether the layer is visible or not
     */
    public void setLayerVisibility(String layerName, boolean visible) {
        modelData.setLayerVisibility(layerName, visible);
    }

    /**
     * Sets the animation speed of all currently playing animations
     *
     * @author David Neuman
     * @param speed The speed multiplier to use for animations
     */
    public void setAnimationSpeed(float speed) {
        modelData.setAnimationSpeed(speed);
    }

    /**
     * Sets the animation speed of a specific animation if it is currently playing
     *
     * @author David Neuman
     * @param animationName The name of the animation whose speed will be set
     * @param speed The speed multiplier to use for the animation
     */
    public void setAnimationSpeed(String animationName, float speed) {
        modelData.setAnimationSpeed(animationName, speed);
    }

    /**
     * Starts playing a specified animation on all layers that contain the animation
     *
     * @author David Neuman
     * @param animationName The name of animation to play
     * @param speed The speed multiplier for the animation
     * @param playMode The play mode for the animation. If null, uses the default play mode specified in the model file
     * @param stopLowerPriority If true, will stop all lower priority animations that are playing when this animation
     * starts
     */
    public void playAnimation(String animationName, float speed, ModelAnimation.Mode playMode, boolean stopLowerPriority) {
        modelData.playAnimation(animationName, speed, playMode, stopLowerPriority);
    }

    /**
     * Stops an animation that is currently playing
     *
     * @author David Neuman
     * @param animationName The name of the animation to stop
     * @param stopLowerPriority If true, will also stop all lower priority animations that are playing
     */
    public void stopAnimation(String animationName, boolean stopLowerPriority) {
        modelData.stopAnimation(animationName, stopLowerPriority);
    }

    /**
     * Checks to see if an animation is currently playing
     *
     * @author David Neuman
     * @param animationName The name of the animation to query
     * @return true if the animation is currently playing, false if not
     */
    public boolean isAnimationPlaying(String animationName) {
        return modelData.isAnimationPlaying(animationName);
    }
}