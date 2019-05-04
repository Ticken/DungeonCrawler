package ca.nanorex.dungeoncrawler.engine.util;

public class Rotation {

    //Value in radians
    private float value;

    public Rotation() {}

    public Rotation(float radians) {
        setRadians(radians);
    }

    public Rotation(Rotation template) {
        set(template);
    }

    public Rotation set(Rotation other) {
        value = other.value;
        return this;
    }

    public float getRadians() {
        return value;
    }

    public Rotation setRadians(float radians) {
        value = radians;
        update();
        return this;
    }

    public float getDegrees() {
        return value * 180f / (float)Math.PI;
    }

    public Rotation setDegrees(float degrees) {
        value = degrees * (float)Math.PI / 180f;
        update();
        return this;
    }

    public int getFraction(int denom) {
        return (int)(Math.round(value / (Math.PI * 2) * denom)) % denom;
    }

    private void update() {
        value += 2 * Math.PI;
        value %= 2 * Math.PI;
    }
}
