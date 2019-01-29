package ca.nanorex.dungeoncrawler.engine.event;

import com.badlogic.gdx.utils.Disposable;

import java.util.LinkedList;
import java.util.Queue;

public class EventListener<T extends Event> implements Disposable {

    private EventManager manager;
    private Queue<T> events;
    Class<T> eventClass;

    public EventListener() {
        events = new LinkedList<T>();
    }

    public T getEvent() {
        return events.poll();
    }

    void addEvent(Event event) {
        events.add((T)event);
    }

    @Override
    public void dispose() {
        manager.unregisterListener(this);
    }
}
