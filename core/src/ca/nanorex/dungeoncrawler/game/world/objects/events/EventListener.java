package ca.nanorex.dungeoncrawler.game.world.objects.events;

import java.util.LinkedList;
import java.util.Queue;

public class EventListener<T extends Event> {
    private Queue<T> events;

    public EventListener() {
        events = new LinkedList<T>();
    }

    public Queue<T> getEvents() {
        return events;
    }
}
