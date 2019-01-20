package ca.nanorex.dungeoncrawler.game.world.objects.systems;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.components.PlayerMovementComponent;
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
                PlayerMovementComponent playerMovementComponent = object.getComponent(
                        PlayerMovementComponent.class);
                if(playerMovementComponent != null)
                {
                    Vector2 position = playerMovementComponent.getPosition();
                    Vector2 velocity = playerMovementComponent.getVelocity();
//                    if (direction.len() == 0)
//                        direction = new Vector2(velocity).scl(-1).nor();
//                    velocity.add(direction.scl(playerMovementComponent.getTraction()));
//                    if (velocity.len() != 0) {
//                        velocity.sub(new Vector2(velocity).scl(playerMovementComponent.getDamping() *
//                                (velocity.len() * 0.0f + 1.0f)));
//                    }
                    float relativeSpeedFeedback = 0.01f;
                    direction.add(new Vector2(velocity).scl(-1 * relativeSpeedFeedback
                            / playerMovementComponent.getMaxSpeed())).nor();

                    velocity.add(direction.scl(playerMovementComponent.getTraction()));
                    if (velocity.len() > playerMovementComponent.getMaxSpeed())
                        velocity.scl(playerMovementComponent.getMaxSpeed() / velocity.len());
                    if (velocity.len() < 4E-4)
                        velocity.scl(0.0f);

                    position.add(velocity);
                    //System.out.println(velocity);
                }
            }
        }
    }
}
