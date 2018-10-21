package ca.nanorex.dungeoncrawler.game.world.objects;

import java.io.Serializable;

public class StatModifier implements Serializable, Comparable<StatModifier> {

    enum Type {
        ADD,
        MULTIPLY
    }

    private Type type;
    private float value;

    public StatModifier(Type type, float value) {
        this.type = type;
        this.value = value;
    }

    public float applyTo(float value) {
        if (type == Type.ADD)
            return value + this.value;
        return value * this.value;
    }

    public int compareTo(StatModifier other) {
        if (type == other.type)
            return  0;
        if (type == Type.ADD && other.type == Type.MULTIPLY)
            return -1;
        return 1;
    }
}
