package ca.nanorex.dungeoncrawler.engine.world.objects;

public abstract class BaseComponent {

    public enum Category {
        TRANSFORM,
        MOVEMENT,
        RENDER
    }

    public abstract Category getCategory();
}
