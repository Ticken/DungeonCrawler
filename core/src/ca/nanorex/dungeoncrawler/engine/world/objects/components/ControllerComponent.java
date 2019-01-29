package ca.nanorex.dungeoncrawler.engine.world.objects.components;

import com.badlogic.gdx.math.Vector2;

public class ControllerComponent extends ObjectComponent {
    private Vector2 direction;
    public enum CONTROL_TYPE {PLAYER, ENT}
    private CONTROL_TYPE controlType;

    public ControllerComponent(CONTROL_TYPE controlType) {
        direction = new Vector2();
        this.controlType = controlType;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }
}
