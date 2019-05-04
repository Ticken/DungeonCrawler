package ca.nanorex.dungeoncrawler.engine.world.objects.systems;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.event.EventListener;
import ca.nanorex.dungeoncrawler.engine.util.Rotation;
import ca.nanorex.dungeoncrawler.engine.world.objects.BaseSystem;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.TransformComponent;
import ca.nanorex.dungeoncrawler.engine.world.objects.events.MobCommandEvent;
import ca.nanorex.dungeoncrawler.engine.world.World;
import ca.nanorex.dungeoncrawler.engine.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.engine.world.objects.components.MobMovementComponent;

public class MobMovementSystem extends BaseSystem {

    private EventListener<MobCommandEvent> commandEventListener;

    public MobMovementSystem(World world) {

        super(world);

        commandEventListener = new EventListener<MobCommandEvent>();
        Game.getEventManager().registerListener(commandEventListener, MobCommandEvent.class);
    }

    @Override
    public void update() {

        MobCommandEvent commandEvent;

        MobMovementComponent movement;
        TransformComponent transform;

        //Process mob command events
        while ((commandEvent = commandEventListener.getEvent()) != null) {

            if (commandEvent.type == MobCommandEvent.Type.MOVE &&
                    commandEvent.mob != null &&
                    (movement = commandEvent.mob.getComponent(MobMovementComponent.class))
                            != null) {

                //Desired velocity is that of input * maxSpeed
                movement.setDesiredVel(commandEvent.vector.scl(movement.getMaxSpeed()));
            }
        }

        //Update all mobs
        for (GameObject object: world.getObjects().getWithComponent(MobMovementComponent.class)) {

            movement = object.getComponent(MobMovementComponent.class);
            transform = object.getComponent(TransformComponent.class);

            //Skip objects that don't have a transform component for some reason
            if (transform == null) continue;

            Vector2 vel = movement.getVel();
            Vector2 desiredVel = movement.getDesiredVel();

            if (!vel.epsilonEquals(desiredVel)) {

                float maxSpeed = movement.getMaxSpeed();
                float traction = movement.getTraction();

                //Determine difference between actual velocity and desired velocity
                Vector2 deltaVel = new Vector2(desiredVel).sub(vel);

                //Acceleration is in same direction as difference, magnitude of traction
                Vector2 accel = new Vector2(deltaVel).setLength(traction);

                //If acceleration will cause overshoot of desired velocity, clamp magnitude
                float deltaLen2 = deltaVel.len2();

                if (accel.len2() > deltaLen2)
                    accel.setLength2(deltaLen2);

                //Add acceleration to velocity
                vel.add(accel);

                //Clamp velocity to max speed
                float maxSpeed2 = maxSpeed * maxSpeed;

                if (vel.len2() > maxSpeed2)
                    vel.setLength2(maxSpeed2);

                //Set velocity to zero if magnitude is below epsilon value
                if (vel.isZero(4E-4f))
                    vel.scl(0f);
            }

            movement.setVel(vel);

            //Add velocity to position
            transform.movePos2(vel);

            //Temporary! Set rotation to desired velocity direction
            if (!desiredVel.isZero())
                transform.setRotation(new Rotation((float)Math.atan2(desiredVel.y, desiredVel.x)));
        }
    }

    @Override
    public void dispose() {
        commandEventListener.dispose();
    }
}

