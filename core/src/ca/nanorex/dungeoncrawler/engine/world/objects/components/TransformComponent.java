package ca.nanorex.dungeoncrawler.engine.world.objects.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ca.nanorex.dungeoncrawler.engine.util.Rotation;
import ca.nanorex.dungeoncrawler.engine.world.objects.BaseComponent;

public class TransformComponent extends BaseComponent {

    private Vector3 pos;
    private Rotation rot;
    private float scale;

    private boolean teleported;

    @Override
    public Category getCategory() {
        return Category.TRANSFORM;
    }

    public TransformComponent() {
        pos = new Vector3();
        rot = new Rotation();
    }

    public Vector2 getPos2() {
        return new Vector2(pos.x, pos.y);
    }

    public Vector3 getPos3() {
        return new Vector3(pos);
    }

    public Rotation getRotation() {
        return new Rotation(rot);
    }

    public float getScale() {
        return scale;
    }

    public void setPos2(Vector2 pos) {
        this.pos.set(pos.x, pos.y, this.pos.z);
        teleported = true;
    }

    public void setPos3(Vector3 pos) {
        this.pos.set(pos);
        teleported = true;
    }

    public void movePos2(Vector2 vel) {
        pos.add(vel.x, vel.y, 0);
        teleported = false;
    }

    public void movePos3(Vector3 vel) {
        pos.add(vel);
        teleported = false;
    }

    public void setRotation(Rotation rot) {
        this.rot.set(rot);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean wasTeleported() {
        return teleported;
    }
}
