package ca.nanorex.dungeoncrawler.engine.world.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stat implements Serializable {
    private float baseValue;
    private float value;
    private List<StatModifier> modifiers;

    public Stat() {
        this(0);
    }

    public Stat(float baseValue) {
        this.baseValue = baseValue;
        modifiers = new ArrayList<StatModifier>();
    }

    public float getValue() {
        return value;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
        updateValue();
    }

    public void addModifier(StatModifier modifier) {
        modifiers.add(modifier);
        Collections.sort(modifiers);
        updateValue();
    }

    public void removeModifier(StatModifier modifier) {
        modifiers.remove(modifier);
        updateValue();
    }

    private void updateValue() {
        value = baseValue;
        for (StatModifier modifier: modifiers)
            value = modifier.applyTo(value);
    }
}
