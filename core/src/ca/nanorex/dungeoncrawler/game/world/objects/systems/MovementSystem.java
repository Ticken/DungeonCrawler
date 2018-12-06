package ca.nanorex.dungeoncrawler.game.world.objects.systems;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.components.MovementComponent;
import ca.nanorex.dungeoncrawler.game.world.objects.components.controllers.PlayerControllerComponent;

public class MovementSystem extends ObjectSystem{
    @Override
    public void update(ObjectList objectList) {
        for (GameObject object : objectList.getObjects()) {
            PlayerControllerComponent playerControllerComponent = object.getComponent(
                    PlayerControllerComponent.class);
            if(playerControllerComponent != null)
            {
                Vector2 direction  = new Vector2(playerControllerComponent.getDirection());
                MovementComponent movementComponent = object.getComponent(MovementComponent.class);
                if(movementComponent != null)
                {
                    Vector2 position = movementComponent.getPosition();
                    Vector2 velocity = movementComponent.getVelocity();
//                    if (direction.len() == 0)
//                        direction = new Vector2(velocity).scl(-1).nor();
//                    velocity.add(direction.scl(movementComponent.getTraction()));
//                    if (velocity.len() != 0) {
//                        velocity.sub(new Vector2(velocity).scl(movementComponent.getDamping() *
//                                (velocity.len() * 0.0f + 1.0f)));
//                    }
                    direction.add(new Vector2(velocity).scl(-1)).nor();

                    velocity.add(direction.scl(movementComponent.getTraction()));
                    if (velocity.len() > movementComponent.getMaxSpeed())
                        velocity.scl(movementComponent.getMaxSpeed() / velocity.len());
                    if (velocity.len() < 4E-4)
                        velocity.scl(0.0f);

                    position.add(velocity);
                    System.out.println(velocity);
                }
            }
        }
    }
}
