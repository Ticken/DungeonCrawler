package ca.nanorex.dungeoncrawler.engine.world.objects.components;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.engine.world.objects.BaseComponent;

public class MobMovementComponent extends BaseComponent {

    //Actual velocity
    private Vector2 vel;

    //Desired velocity
    private Vector2 desiredVel;

    //Maximum speed in pixels per tick
    private float maxSpeed;

    //Acceleration in pixels per tick ^ 2
    private float traction;

    @Override
    public Category getCategory() {
        return Category.MOVEMENT;
    }

    public MobMovementComponent(float maxSpeed) {

        //Construct with standard traction ratio
        this(maxSpeed, maxSpeed * 0.2f);
    }

    public MobMovementComponent(float maxSpeed, float traction) {

        this.maxSpeed = maxSpeed;
        this.traction = traction;

        vel = new Vector2();
        desiredVel = new Vector2();
    }

    public Vector2 getVel() {
        return new Vector2(vel);
    }

    public void setVel(Vector2 vel) {
        this.vel.set(vel);
    }

    public Vector2 getDesiredVel() {
        return new Vector2(desiredVel);
    }

    public void setDesiredVel(Vector2 desiredVel) {
        this.desiredVel.set(desiredVel);
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float value) {
        maxSpeed = value;
    }

    public float getTraction() {
        return traction;
    }

    public void setTraction(float value) {
        traction = value;
    }

    public void setSpeed(float value) {

        //Set maxSpeed to value and scale traction by the same ratio
        traction = value / maxSpeed * traction;
        maxSpeed = value;
    }
}
