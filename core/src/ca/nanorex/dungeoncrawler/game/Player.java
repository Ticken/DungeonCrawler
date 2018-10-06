package ca.nanorex.dungeoncrawler.game;

import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject {
    private final String SPRITE_SHEET_PATH = "png/skelly.png"; // in $PROJECT_PATH/
    private final int TILE_WIDTH = 64; // in pixels
    private final int TILE_HEIGHT = 64; // in pixels

    public Player() {
        super();
        components.put(RendererComponent.class,
                new RendererComponent(this,
                        SPRITE_SHEET_PATH,
                        TILE_WIDTH,
                        TILE_HEIGHT,
                        initAnimationMap()));
    }

    private Map<String, AnimationInfo> initAnimationMap() {
        Map<String, AnimationInfo> animationMap = new HashMap();
        animationMap.put("up", new AnimationInfo(0,1,8));
        animationMap.put("left", new AnimationInfo(1,1,8));
        animationMap.put("down", new AnimationInfo(2,1,8));
        animationMap.put("right", new AnimationInfo(3,1,8));
        animationMap.put("still_up", new AnimationInfo(0,0,1));
        animationMap.put("still_left", new AnimationInfo(1,0,1));
        animationMap.put("still_down", new AnimationInfo(2,0,1));
        animationMap.put("still_right", new AnimationInfo(3,0,1));
        return animationMap;
    }
}
