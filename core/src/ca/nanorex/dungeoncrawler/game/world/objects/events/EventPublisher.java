package ca.nanorex.dungeoncrawler.game.world.objects.events;

import java.util.List;

public class EventPublisher<T extends Event> {
    private List<EventListener<T>> listeners;

    public EventPublisher(List<EventListener<T>> listeners) {
        this.listeners = listeners;
    }

    public void publish(T event) {
        for (EventListener<T> listener: listeners) {
            listener.getEvents().add(event);
        }
    }

    /*public void addListener(EventListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(EventListener<T> listener) {
        listener = null;
        listeners.remove(listener);
    }*/
}
