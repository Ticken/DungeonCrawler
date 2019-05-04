package ca.nanorex.dungeoncrawler.engine.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    //Map of event types to lists of listeners
    private Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners;

    public EventManager() {

        listeners = new HashMap<Class<? extends Event>, List<EventListener<? extends Event>>>();
    }

    public void publish(Event event) {

        //Todo: Add null check for event

        //Get listener list
        List<EventListener<? extends Event>> listenerList = listeners.get(event.getClass());

        //If not null, add event to each listener
        if (listenerList != null) {
            for (EventListener<? extends Event> listener: listenerList) {
                listener.addEvent(event);
            }
        }
    }

    public <T extends Event> void registerListener(EventListener<T> listener, Class<T> eventClass) {

        //Get listener list
        List<EventListener<? extends Event>> listenerList = listeners.get(eventClass);

        //Create listener list if does not exist
        if (listenerList == null) {
            listenerList = new ArrayList<EventListener<? extends Event>>();
            listeners.put(eventClass, listenerList);
        }

        //Add listener
        listenerList.add(listener);

        listener.eventClass = eventClass;
    }

    <T extends Event> void unregisterListener(EventListener<T> listener) {

        //Get listener list
        List<EventListener<? extends Event>> listenerList = listeners.get(listener.eventClass);

        //Remove listener if list exists
        if (listenerList != null)
            listenerList.remove(listener);
    }
}
