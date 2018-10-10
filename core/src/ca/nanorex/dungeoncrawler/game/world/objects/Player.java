package ca.nanorex.dungeoncrawler.game.world.objects;

import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.game.world.objects.components.RendererComponent;

public class Player extends GameObject {
    private final String JSON_PATH = "json/skelly.json"; // in $PROJECT_PATH/android/assets

    public Player() {
        super();
        components.put(RendererComponent.class,
                new RendererComponent(this, JSON_PATH));
    }
}
