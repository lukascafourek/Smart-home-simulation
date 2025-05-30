package cz.cvut.fel.omo.sem.interfaces;

import cz.cvut.fel.omo.sem.events.Event;

/**
 * EventObserver interface for Observer pattern
 * It is used to update events in observers which are added in EventObservable
 * Observers are Window, Radiator and Person classes
 */
public interface EventObserver {

    /**
     * Update event in observer
     * Each observer updates different events
     * @param event event to update
     */
    void updateEvent(Event<?> event);
}
