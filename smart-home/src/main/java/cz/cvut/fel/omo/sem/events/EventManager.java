package cz.cvut.fel.omo.sem.events;

import java.util.LinkedList;
import java.util.Queue;

/**
 * EventManager is a singleton class that manages the event queue.
 * It is responsible for adding events to the queue and providing the next event in the queue.
 */
public class EventManager {

    private final Queue<Event<?>> eventQueue = new LinkedList<>();
    private static EventManager instance;

    private EventManager() {
    }

    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    void addEvent(Event<?> event) {
        eventQueue.add(event);
    }

    /**
     * Gets the next event in the queue.
     * @return the next event in the queue
     */
    public Event<?> getNextEvent() {
        return eventQueue.poll();
    }

    /**
     * Checks if the event queue is empty.
     * @return true if the event queue is empty, false otherwise
     */
    public boolean isQueueEmpty() {
        return eventQueue.isEmpty();
    }

    /**
     * Checks if the event queue does not contain the event.
     * @param eventType the type of the event
     * @param eventSrc the source of the event
     * @param <T> the type of the event source
     * @return true if the event is not in the queue, false otherwise
     */
    public <T> boolean eventNotInQueue(EventType eventType, T eventSrc) {
        return eventQueue.stream()
                .noneMatch(event ->
                        event.getEventType() == eventType && event.getEventSrc() == eventSrc);
    }
}
