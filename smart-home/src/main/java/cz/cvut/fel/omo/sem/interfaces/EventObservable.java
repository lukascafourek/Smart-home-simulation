package cz.cvut.fel.omo.sem.interfaces;

import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventType;

/**
 * EventObservable interface for Observer pattern
 * It is used to notify observers which update events in EventObserver
 * It is implemented in EventObservableImpl class
 */
public interface EventObservable {

    /**
     * Notify event observers about event
     * @param event event to notify about
     */
    void notifyEvent(Event<?> event);

    /**
     * Add event observer for event type to list of observers
     * @param eventType event type to add event observer for
     * @param eventObserver event observer to add
     */
    void addObserver(EventType eventType, EventObserver eventObserver);
}
