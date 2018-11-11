package ca.nanorex.dungeoncrawler.game.world.objects;

import ca.nanorex.dungeoncrawler.game.world.objects.components.ControllerComponent;
import ca.nanorex.dungeoncrawler.game.world.objects.components.MovementComponent;
import ca.nanorex.dungeoncrawler.game.world.objects.components.RendererComponent;

public class Player extends GameObject {
    private final String JSON_PATH = "json/skelly.json"; // in $PROJECT_PATH/android/assets

    public Player() {
        super();
        /*
        setComponent(RendererComponent.class,
                new RendererComponent(this, JSON_PATH));
        setComponent(MovementComponent.class, new MovementComponent(this));
        setComponent(ControllerComponent.class, new ControllerComponent(this,
                ControllerComponent.CONTROL_TYPE.PLAYER));
        */
    }
}
