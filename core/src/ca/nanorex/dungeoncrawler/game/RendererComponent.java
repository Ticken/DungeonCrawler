package ca.nanorex.dungeoncrawler.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class RendererComponent extends ObjectComponent {

    private TextureRegion sprite_sheet;
    private Map<String, Animation<TextureRegion>> animations;
    private TextureRegion[][] tiles;

    public RendererComponent(GameObject object,
                             String sprite_sheet_path,
                             int tile_width,
                             int tile_height,
                             Map<String, int[]> animationMap) {
        super(object);
        Texture tex = new Texture(Gdx.files.internal(sprite_sheet_path));
        sprite_sheet = new TextureRegion(tex);
        tiles = sprite_sheet.split(tile_width, tile_height);
        animations = new HashMap();

        for (String name: animationMap.keySet()) {
            int[] coords = animationMap.get(name);
            final int COORDS_LENGTH = 3;
            if(coords.length != COORDS_LENGTH)
                System.out.println("WARNING: there are not " + COORDS_LENGTH + " co-ordinates specified for " +
                        "animation: " + name);

            Array<TextureRegion> temp = new Array(true, tiles[coords[0]], coords[1], coords[2]);

            Animation<TextureRegion> temp2 = new Animation<TextureRegion>(1, temp);

            animations.put(name, temp2);
        }
    }



    TextureRegion getFrame(String name, int currentAnimationTick) {
        return animations.get(name).getKeyFrame(currentAnimationTick);
    }

    @Override
    public void update() {

    }
}

