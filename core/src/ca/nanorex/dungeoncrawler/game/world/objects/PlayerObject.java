package ca.nanorex.dungeoncrawler.game.world.objects;

import ca.nanorex.dungeoncrawler.game.world.objects.components.PlayerMovementComponent;
import ca.nanorex.dungeoncrawler.game.world.objects.components.controllers.PlayerControllerComponent;

public class PlayerObject extends GameObject {
    public PlayerObject(int num) {
        super();
        if(num < 1 || num > 4)
            System.out.println("player number is not correct");
        components.put(PlayerControllerComponent.class, new PlayerControllerComponent());
        components.put(PlayerMovementComponent.class, new PlayerMovementComponent());
    }
}
