package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.events.EventListener;
import ca.nanorex.dungeoncrawler.game.world.objects.events.EventPublisher;
import ca.nanorex.dungeoncrawler.game.world.objects.events.VelocityEvent;

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
