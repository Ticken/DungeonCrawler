package ca.nanorex.dungeoncrawler.game.world.objects.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.PlayerObject;
import ca.nanorex.dungeoncrawler.game.world.objects.components.MovementComponent;

public class ObjectList {
    private List<GameObject> objects;
    private List<ObjectSystem> systems;
    private InputSystem inputSystem;

    public ObjectList() {
        // OBJECTS
        objects = new ArrayList();
        PlayerObject player = new PlayerObject(1);
        objects.add(player);
        // SYSTEMS
        systems = new ArrayList();
        inputSystem = new InputSystem();
        systems.add(inputSystem);
        PlayerControllerSystem playerControllerSystem = new PlayerControllerSystem();
        MovementSystem movementSystem = new MovementSystem();
        systems.add(playerControllerSystem);
        systems.add(movementSystem);
    }

    public void update(ShapeRenderer shapeRenderer) { // TODO @Jordan Slater remove this testing param
        for (ObjectSystem system: systems)
            system.update(this);

        for (GameObject object : objects)
        {
            MovementComponent movementComponent = object.getComponent(MovementComponent.class);
            if (movementComponent != null)
            {
                Vector2 position = movementComponent.getPosition();
                //shapeRenderer.circle(position.x,position.y,0.1f,24);
                shapeRenderer.rect(position.x,position.y,0.1f,0.1f);
            }
        }
    }

    public InputSystem getInputSystem() {
        return inputSystem;
    }

    public List<GameObject> getObjects() {
        return objects;
    }
}
