package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public class ControllerComponent extends ObjectComponent {
    private Vector2 direction;
    public enum CONTROL_TYPE {PLAYER, ENT}
    private CONTROL_TYPE controlType;
    //private MovementComponent movementComponent;

    public ControllerComponent(GameObject object, CONTROL_TYPE controlType) {
        super(object);
        //movementComponent = object.getComponent(MovementComponent.class);
        direction = new Vector2();
        this.controlType = controlType;
    }

    @Override
    public void update() {
        System.out.println("(" + direction.x + ", " + direction.y + ")");
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public boolean isPlayer() { return this.controlType == CONTROL_TYPE.PLAYER; }
}
