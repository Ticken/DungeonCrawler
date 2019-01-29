package ca.nanorex.dungeoncrawler.engine.world.objects;

public class Rotation {
    public enum Count {

        COUNT_1 (1 << 0),
        COUNT_4 (1 << 2),
        COUNT_8 (1 << 3),
        COUNT_16 (1 << 4);

        public final int value;

        Count(int value) {

            //Power of two assertion
            assert (value & (value - 1)) == 0;
            this.value = value;
        }

        //TODO: Change to use enum valueOf
        public static Count fromInt(int value) {
            switch (value) {
                case 1:
                    return COUNT_1;
                case 2:
                    return COUNT_4;
                case 8:
                    return COUNT_8;
                case 16:
                    return COUNT_16;
                default:
                    throw new IllegalArgumentException("Invalid rotation value");
            }
        }
    }

    private int count;
    private int value;

    public Rotation(Count count) {
        this(count, 0);
    }

    public Rotation(Count count, float radians) {
        this(count, 0);
        setRadians(radians);
    }

    public Rotation(Count count, int value) {
        this.count = count.value;
        this.value = value;
    }

    public Rotation(Rotation template) {
        set(template);
    }

    public int getCount() { return count; }

    public int getValue() {
        return value;
    }

    public Rotation setValue(int value) {
        this.value = value;
        update();
        return this;
    }

    public Rotation set(Rotation other) {
        count = other.count;
        value = other.value;
        return this;
    }

    public Rotation setValue(Rotation other) {
        value *= other.count;
        value /= count;
        return this;
    }

    public float getRadians() {
        return (float)value / count * 2 * (float)Math.PI;
    }

    public Rotation setRadians(float radians) {
        value = (int)(radians / (2 * Math.PI) * count);
        update();
        return this;
    }

    public float getDegrees() {
        return (float)value / count * 360;
    }

    public Rotation setDegrees(float degrees) {
        value = (int)(degrees / 360 * count);
        update();
        return this;
    }

    private void update() {

        //Yay bit masks
        value &= (count - 1);
    }
}
