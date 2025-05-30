package cz.cvut.fel.omo.sem.events;

import cz.cvut.fel.omo.sem.actors.people.Person;
import cz.cvut.fel.omo.sem.actors.people.PersonState;
import cz.cvut.fel.omo.sem.devices.radiator.Radiator;
import cz.cvut.fel.omo.sem.house.Window;
import cz.cvut.fel.omo.sem.interfaces.EventObservable;
import cz.cvut.fel.omo.sem.interfaces.EventObserver;

import java.util.*;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * EventObservableImpl is a singleton class that manages the observers and notifies them about events.
 * It is responsible for registering observers for specific event types and notifying them when an event occurs.
 * It also keeps track of the event observers and the event report.
 */
public class EventObservableImpl implements EventObservable {

    public static int eventIndex = 1;
    private static EventObservableImpl instance;
    private final Map<EventType, List<EventObserver>> eventObservers = new HashMap<>();
    private final StringBuilder sb = new StringBuilder();

    private EventObservableImpl() {}

    public static synchronized EventObservableImpl getInstance() {
        if (instance == null) {
            instance = new EventObservableImpl();
        }
        return instance;
    }

    @Override
    public void addObserver(EventType eventType, EventObserver eventObserver) {
        eventObservers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(eventObserver);
    }

    @Override
    public void notifyEvent(Event<?> event) {
        logger.entering(EventObservableImpl.class.getSimpleName(), "notifyEvent", event.getEventType());
        List<EventObserver> eventObservers = this.eventObservers.getOrDefault(event.getEventType(), new ArrayList<>());
        if ((event.getEventType().equals(EventType.WIND)) && !eventObservers.isEmpty()) {
            eventObservers.stream()
                    .filter(Window.class::isInstance)
                    .map(Window.class::cast)
                    .forEach(window -> window.updateEvent(event));
            buildEventReport(event, "Windows");
        } else if ((event.getEventType().equals(EventType.TEMP_CHANGE)) && !eventObservers.isEmpty()) {
            eventObservers.stream()
                    .filter(Radiator.class::isInstance)
                    .map(Radiator.class::cast)
                    .forEach(radiator -> radiator.updateEvent(event));
            buildEventReport(event, "Radiators");
        } else if (!eventObservers.isEmpty()) {
            eventObservers.stream()
                    .filter(Person.class::isInstance)
                    .map(Person.class::cast)
                    .filter(person -> person.getPersonState().equals(PersonState.INSIDE))
                    .findAny()
                    .ifPresentOrElse(person-> {
                        person.updateEvent(event);
                        buildEventReport(event, person.getPersonType().toString() + " " + person.getName());
                    }, () -> EventManager.getInstance().addEvent(event));
        }
        logger.exiting(EventObservableImpl.class.getSimpleName(), "notifyEvent");
    }

    private void buildEventReport(Event<?> event, String observer) {
        sb.append(event.getEventID()).append(": ").append(event.getEventType().toString())
                .append(" caused by ").append(event.getEventSrc().getClass().getSimpleName())
                .append(" solved by ").append(observer).append("\n");
    }

    /**
     * Method to build event report for person handling event
     * This method is used by person instead of buildEventReport to avoid it being public
     * @param event handled event to be reported
     * @param person person who handled the event
     */
    public void personHandlesEventQueue(Event<?> event, Person person) {
        buildEventReport(event, person.getPersonType().toString() + " " + person.getName());
    }

    public StringBuilder getStringBuilder() {
        return sb;
    }
}
