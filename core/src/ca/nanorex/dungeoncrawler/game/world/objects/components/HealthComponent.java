package ca.nanorex.dungeoncrawler.game.world.objects.components;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.Stat;

public abstract class HealthComponent extends ObjectComponent {

    private int health;
    private Stat maxHealth; //what's the difference between this and the stats array, same with timers

    public HealthComponent(GameObject object, int maxHealth) {
        super(object);

        stats.put("max_health", new Stat(maxHealth));
    }

    @Override
    public void update() {
    }

    /*
    public boolean isAlive() { return health > 0; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public float getHealthPercent() { return (float)health / maxHealth; }

    public void takeDamage(int damage) { health -= damage; }
    public void heal(int damage) { health += damage; if () health = getStat("max_health").getValue(); }

    public void kill() { takeDamage(health); }
    public void healFull() { heal(stats.get("max_health").getValue() - health); }
    */
}
