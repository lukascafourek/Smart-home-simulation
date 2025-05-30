package cz.cvut.fel.omo.sem.actors.people;

import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.house.Room;
import cz.cvut.fel.omo.sem.reports.ActivityAndUsageReport;

/**
 * Teenager class represents a teenager person in the simulation.
 */
public class Teenager extends Person {

    public Teenager(String name, int age, Room room, PersonType type) {
        super(name, age, room, type);
        ActivityAndUsageReport.getInstance().addPerson(this);
        EventObservableImpl.getInstance().addObserver(EventType.CRYING_BABY, this);
        EventObservableImpl.getInstance().addObserver(EventType.EMPTY_FRIDGE, this);
        EventObservableImpl.getInstance().addObserver(EventType.REFILL_INK, this);
    }
}
