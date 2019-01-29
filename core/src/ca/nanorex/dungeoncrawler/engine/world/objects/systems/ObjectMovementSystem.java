package ca.nanorex.dungeoncrawler.engine.world.objects.systems;

import ca.nanorex.dungeoncrawler.engine.world.system.GameSystem;
import ca.nanorex.dungeoncrawler.engine.screens.GameScreen;
import ca.nanorex.dungeoncrawler.engine.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.MovementComponent;

public class ObjectMovementSystem extends GameSystem {

    private GameScreen screen;

    public ObjectMovementSystem(GameScreen screen) {
        super();

        this.screen = screen;
    }

    @Override
    public void update() {

        /*
        for (GameObject object: screen.getWorld().getObjects()) {

            if (object.hasComponent(MovementComponent.class));
        }*/

        /*
        for object in screen.getWorld().getObjects();
            if object.hasComponent(MovementComponent.class)
                vel += accel
                pos += vel
        */

    }

    @Override
    public void dispose() {

    }
}
