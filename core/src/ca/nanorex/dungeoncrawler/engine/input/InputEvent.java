package ca.nanorex.dungeoncrawler.engine.input;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.engine.event.Event;

public class InputEvent extends Event {

    public enum Type {
        MOVE, //vector
        CHANGE_DIRECTION, //angle
        ATTACK_MELEE, //angle
        ATTACK_RANGED_CHARGE,
        ATTACK_RANGED_FIRE, //angle
        ATTACK_RANGED_CANCEL,
        USE_ITEM, //angle
        INTERACT
    }

    public InputEvent(Type type, Vector2 vector, int angle) {

        this.type = type;
        this.vector = new Vector2(vector);
        this.angle = angle;
    }

    public Type type;
    public Vector2 vector;
    public int angle;
}
