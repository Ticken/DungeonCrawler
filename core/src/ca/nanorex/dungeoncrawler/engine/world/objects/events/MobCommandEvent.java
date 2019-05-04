package ca.nanorex.dungeoncrawler.engine.world.objects.events;

import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.engine.event.Event;
import ca.nanorex.dungeoncrawler.engine.world.objects.GameObject;

public class MobCommandEvent extends Event {

    public enum Type {
        MOVE, //vector
        CHANGE_ROTATION, //angle
        ATTACK_MELEE, //angle
        ATTACK_RANGED_CHARGE,
        ATTACK_RANGED_FIRE, //angle
        ATTACK_RANGED_CANCEL,
        USE_ITEM, //angle
        INTERACT
    }

    public MobCommandEvent(GameObject mob, Type type, Vector2 vector, int angle) {

        this.mob = mob;
        this.type = type;
        this.vector = new Vector2(vector);
        this.angle = angle;
    }

    public GameObject mob;
    public Type type;
    public Vector2 vector;
    public int angle;
}
