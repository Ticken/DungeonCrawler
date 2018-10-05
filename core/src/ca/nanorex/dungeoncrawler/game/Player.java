package ca.nanorex.dungeoncrawler.game;

import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject {

    public Player() {
        super();

        Map<String, int[]> animationMap = new HashMap();
        animationMap.put("main", new int[]{0,0,16});

        components.put(RendererComponent.class,
                new RendererComponent(this,
                        "png/square.png",
                        64,
                        64,
                        animationMap));
    }
}
