package ca.nanorex.dungeoncrawler.game.world.objects.components;

import ca.nanorex.dungeoncrawler.game.world.objects.Stat;

public abstract class HealthComponent extends ObjectComponent {

    private int health;
    private Stat maxHealth; //what's the difference between this and the stats array, same with timers

    public HealthComponent(int maxHealth) {
    }
}
