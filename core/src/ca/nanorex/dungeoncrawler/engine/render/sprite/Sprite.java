package ca.nanorex.dungeoncrawler.engine.render.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.util.Rotation;

public class Sprite {

    private SpriteModel model;
    private Map<String, SpriteTexture> textures;

    private Vector2 pos;
    private Vector2 prevPos;
    private Rotation rotation;

    private Map<String, SpriteModelLayer.InstanceData> layerData;
    private List<SpriteModelAnimation.InstanceData> animationData;

    public Sprite(String model, Map<String, String> textures) {

        layerData = new LinkedHashMap<String, SpriteModelLayer.InstanceData>();
        animationData = new ArrayList<SpriteModelAnimation.InstanceData>();

        setModel(model);

        this.textures = new HashMap<String, SpriteTexture>();

        for (String name: textures.keySet())
            this.textures.put(name, Game.getAssetLibrary().get(textures.get(name),
                    SpriteTexture.class));

        pos = new Vector2();
        prevPos = new Vector2();
        rotation = new Rotation();

        playAnimation(this.model.defaultAnimation, 1, false);
    }

    public void setModel(String model) {

        this.model = Game.getAssetLibrary().get(model, SpriteModel.class);

        layerData.clear();
        animationData.clear();

        //Initialize layer data
        for (SpriteModelLayer layer: this.model.layers.values())
            layerData.put(layer.name, new SpriteModelLayer.InstanceData(true));

        //Populate animation stack with null entries
        for (int i = 0; i < this.model.animations.size(); i++) //todo: EEP THIS SHOULD BE THE HIGHEST PRIORITY NOT THE COUNT
            animationData.add(null);
    }

    public void setTexture(String name, String texture) {
        if (textures.containsKey(name))
            textures.put(name, Game.getAssetLibrary().get(name, SpriteTexture.class));
    }

    public Vector2 getRenderPos() {
        return pos;
    }

    public Vector2 getRenderPrevPos() {
        return prevPos;
    }

    public void setPos(Vector2 pos) {
        setPos(pos, false);
    }

    public void setPos(Vector2 pos, boolean teleport) {

        prevPos.set(this.pos);
        this.pos.set(pos).add(model.offset);

        //If teleporting, set both positions equal to avoid smoothing the transition
        if (teleport)
            prevPos.set(this.pos);
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setLayerVisibility(String layerName, boolean visible) {
        layerData.get(layerName).visible = visible;
    }

    public void setAnimationSpeed(float speed) {
        for (SpriteModelAnimation.InstanceData animation: animationData)
            if (animation != null)
                animation.speed = speed;
    }

    public void setAnimationSpeed(String animationName, float speed) {
        for (SpriteModelAnimation.InstanceData animation: animationData)
            if (animation != null && animation.name.equals(animationName))
                animation.speed = speed;
    }

    public void playAnimation(String animationName, float speed, boolean stopLowerPriority) {
        playAnimation(animationName, speed, null, stopLowerPriority);
    }

    public void playAnimation(String animationName, float speed, SpriteModelAnimation.Mode playMode,
                              boolean stopLowerPriority) {

        SpriteModelAnimation animation = model.animations.get(animationName);

        if (animation != null) {

            stopAnimation(animationName, stopLowerPriority);

            if (playMode == null)
                playMode = animation.defaultMode;

            //Add to animation stack and map
            animationData.set(animation.priority,
                    new SpriteModelAnimation.InstanceData(animationName, speed, playMode));
        }
    }

    public void stopAnimation(String animationName, boolean stopLowerPriority) {

        SpriteModelAnimation animation = model.animations.get(animationName);

        if (animation != null) {
            for (int priority = animation.priority; priority < animationData.size(); priority++) {
                animationData.set(priority, null);

                //Janky break statement to stop after one iteration
                if (stopLowerPriority) break;
            }
        }

    }

    public void tick() {

        //todo: this needs complete rewriting

        boolean found = false;

        for (int i = 0; i < animationData.size(); i++) {
            SpriteModelAnimation.InstanceData a = animationData.get(i);
            if (a != null) {


                a.time += Game.TICK_PERIOD * a.speed;

                if (!model.animations.get(a.name).isPlaying(a)) {
                    animationData.set(i, null);
                }
                else
                    found = true;

            }
        }

        if (!found)
            playAnimation(model.defaultAnimation, 1, false);
    }

    public List<TextureRegion> getTextureRegions() {

        List<TextureRegion> regions = new ArrayList<TextureRegion>();

        for (String layerName: layerData.keySet()) {

            //todo: if layer is not visible continue
            if (!layerData.get(layerName).visible) continue;

            SpriteTexture texture = textures.get(model.layers.get(layerName).texture);

            if (texture == null) continue;

            TextureRegion region = new TextureRegion(texture.getTexture());


            int tileWidth = (int)texture.getTileSize().x;
            int tileHeight = (int)texture.getTileSize().y;

            //Find the active animation
            SpriteModelAnimation.InstanceData activeAnimationData = null;

            int i;
            i = animationData.size() - 1;


            while (i >= 0) {

                activeAnimationData = animationData.get(i);

                if (activeAnimationData != null) {


                    if (
                            model.animations.get(activeAnimationData.name).layers.contains(layerName) ||

                                    layerName.equals(model.defaultAnimation)
                    ) {


                        break;
                    }
                }

                i--;
            }

            //todo: make sure this can deal with when the default animation does not have all
            // layers listed
            /*
            for (int i = animationData.size() - 1; (activeAnimationData = animationData.get(i)) != null &&
                    model.animations.get(activeAnimationData.name).layers.contains(layerName); i--);//todo: look at this loop and make sure it makes sense
                    */

            SpriteModelAnimation activeAnimation = model.animations.get(activeAnimationData.name);

            int animationIndex = activeAnimation.textureIndex;
            int frameIndex = activeAnimation.getFrame(activeAnimationData);

            int layerIndex = model.layers.get(layerName).textureIndex;
            int rotationCount = model.rotCount;
            int rotationIndex = rotation.getFraction(model.rotCount);

            int x = (animationIndex + frameIndex) * tileWidth;
            int y = (layerIndex * rotationCount + rotationIndex) * tileHeight;

            region.setRegion(x, y, tileWidth, tileHeight);
            regions.add(region);
        }

        return regions;
    }
}
