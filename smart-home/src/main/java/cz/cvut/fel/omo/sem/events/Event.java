package cz.cvut.fel.omo.sem.events;

/**
 * Event is a class that represents an event in the simulation.
 * It contains the event type, the event source, and the event ID.
 * @param <T> the type of the event source
 */
public class Event<T> {

    private final EventType eventType;
    private final T eventSrc;
    private final int eventID;

    public Event(EventType eventType, T eventSrc, int eventID) {
        this.eventType = eventType;
        this.eventSrc = eventSrc;
        this.eventID = eventID;
    }

    public T getEventSrc() {
        return eventSrc;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getEventID() {
        return eventID;
    }
}

