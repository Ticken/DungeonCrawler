package ca.nanorex.dungeoncrawler.game.world.objects.events;

import com.badlogic.gdx.math.Rectangle;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;

public class AttackEvent extends Event {
    public GameObject attacker;
    public int damage;
    public Rectangle hurtbox;
}
